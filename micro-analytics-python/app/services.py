import io
import pandas as pd
import matplotlib
# Configuración crítica para entornos Docker (sin interfaz gráfica)
matplotlib.use('Agg')
import matplotlib.pyplot as plt

class AnalyticsService:

    @staticmethod
    def process_summary(data: list) -> dict:
        """
        Toma la lista de diccionarios que devuelve el CRUD de Spring Boot
        y la transforma en un DataFrame de Pandas para calcular estadísticas.
        """
        if not data:
            return {"message": "No hay datos suficientes para generar analíticas", "total_records": 0}

        # 1. Cargamos los datos JSON en un DataFrame de Pandas
        df = pd.DataFrame(data)

        # 2. Calculamos métricas descriptivas estándar
        summary = {
            "total_records": int(df.shape[0]),
            "total_columns": int(df.shape[1]),
            "columns_names": list(df.columns),
            "preview_records": data[:3]  # Envía los 3 primeros registros como vista previa
        }

        # Si tus registros tienen un campo numérico, calculamos promedios de forma automática
        numeric_cols = df.select_dtypes(include=['number']).columns.tolist()
        if numeric_cols:
            first_numeric = numeric_cols[0]
            summary["numeric_insight"] = {
                "target_field": first_numeric,
                "average_value": float(df[first_numeric].mean()),
                "max_value": float(df[first_numeric].max()),
                "min_value": float(df[first_numeric].min())
            }

        return summary

    @staticmethod
    def generate_chart_bytes(data: list) -> io.BytesIO:
        """
        Analiza los datos del CRUD de Java, genera un gráfico de barras
        usando Matplotlib y lo devuelve volcado en un buffer binario de memoria.
        """
        if not data:
            raise ValueError("No data available to plot")

        df = pd.DataFrame(data)

        # Buscamos la primera columna de texto para agrupar y contar repeticiones
        text_cols = df.select_dtypes(include=['object', 'category']).columns.tolist()
        column_to_plot = text_cols[0] if text_cols else df.columns[0]

        # Contamos la frecuencia de cada valor único
        data_counts = df[column_to_plot].value_counts()

        # Creamos el lienzo del gráfico (Tamaño 6x4 pulgadas)
        plt.figure(figsize=(6, 4))

        # Dibujamos las barras con un estilo limpio de laboratorio
        data_counts.plot(kind='bar', color='#3498db', edgecolor='black', zorder=2)

        # Añadimos etiquetas, cuadrícula y títulos
        plt.title("Analítica de Registros del CRUD de Java", fontsize=12, fontweight='bold', pad=15)
        plt.xlabel(column_to_plot.capitalize(), fontsize=10, labelpad=10)
        plt.ylabel("Cantidad de Repeticiones", fontsize=10, labelpad=10)
        plt.grid(axis='y', linestyle='--', alpha=0.7, zorder=1)
        plt.tight_layout()

        # Volcamos la imagen directamente en la memoria RAM del contenedor (Buffer bytes)
        buf = io.BytesIO()
        plt.savefig(buf, format="png", dpi=100)
        buf.seek(0)
        plt.close() # Libera la memoria del lienzo de Matplotlib de forma inmediata

        return buf
