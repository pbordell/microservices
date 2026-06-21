from fastapi import FastAPI, Header, HTTPException
import httpx
from fastapi.responses import StreamingResponse
from app.services import AnalyticsService

app = FastAPI(title="Micro Analytics Python", docs_url="/analytics/docs")

GATEWAY_URL = "http://apigateway:8082/core-api/CrudBasic/cruds/"

@app.get("/analytics/summary")
async def get_summary(authorization: str = Header(None)):
    """
    Mantiene la ruta limpia alineada con el Gateway.
    """
    if not authorization:
        raise HTTPException(status_code=401, detail="Missing Authorization Header")

    # 1. Consumimos el microservicio de Spring Boot propagando el token JWT recibido
    headers = {"Authorization": authorization}
    async with httpx.AsyncClient() as client:
        try:
            response = await client.get(GATEWAY_URL, headers=headers)
            if response.status_code != 200:
                raise HTTPException(status_code=response.status_code, detail="Error fetching data from Core")
            data = response.json()
        except Exception as e:
            raise HTTPException(status_code=500, detail=f"Connection to Core failed: {str(e)}")

    # 2. Delegamos el procesamiento matemático en el servicio de Pandas
    return AnalyticsService.process_summary(data)


@app.get("/analytics/chart")
async def get_chart(authorization: str = Header(None)):
    """
    Mantiene la ruta limpia alineada con el Gateway.
    """
    if not authorization:
        raise HTTPException(status_code=401, detail="Missing Authorization Header")

    # 1. Consumimos los datos del CRUD de Spring Boot propagando el token
    headers = {"Authorization": authorization}
    async with httpx.AsyncClient() as client:
        try:
            response = await client.get(GATEWAY_URL, headers=headers)
            if response.status_code != 200:
                raise HTTPException(status_code=response.status_code, detail="Error fetching data from Core")
            data = response.json()
        except Exception as e:
            raise HTTPException(status_code=500, detail=f"Connection to Core failed: {str(e)}")

    # 2. Intentamos generar el gráfico llamando al servicio de Matplotlib
    try:
        chart_buffer = AnalyticsService.generate_chart_bytes(data)
    except ValueError as ve:
        raise HTTPException(status_code=400, detail=str(ve))

    # 3. Devolvemos el buffer binario como una imagen PNG real directamente al cliente HTTP
    return StreamingResponse(chart_buffer, media_type="image/png")
