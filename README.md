Este repositorio proporciona una plantilla y un esqueleto completo para una arquitectura de microservicios. Demuestra la integración de múltiples frameworks de Java (**Spring Boot**, **Quarkus** y **Struts**), Python y node, trabajando juntos dentro de un ecosistema unificado impulsado por **Spring Cloud** y contenedorizado con **Docker**.

## 🚀 Vista General de la Arquitectura

El sistema está estructurado como una colección de servicios especializados diseñados para mostrar el descubrimiento de servicios, el enrutamiento de API, la autenticación y el desarrollo de backends heterogéneos.

## 📁 Estructura del Repositorio

*   **`apiGateway/`**: El punto de entrada para todas las solicitudes de los clientes. Se encarga del enrutamiento inteligente y el equilibrio de carga mediante Spring Cloud Gateway.
*   **`eurekaService/`**: El registro de servicios (Netflix Eureka) donde todos los microservicios se registran dinámicamente para permitir el descubrimiento automatizado.
*   **`micro-oauth/`**: Servidor de autenticación y autorización centralizado para asegurar los endpoints en toda la red de servicios.
*   **`micro-core-spring-boot/`**: Servicio de lógica de negocio principal implementado con **Spring Boot**, enfocado en capacidades estándar de backend empresarial.
*   **`micro-api-quarkus/`**: Un microservicio nativo de la nube de alto rendimiento creado con **Quarkus** para demostrar capacidades Java supersónicas y subatómicas (bajo consumo de memoria y arranque rápido).
*   **`micro-report-struts/`**: Un módulo de informes o integración heredada implementado con Apache **Struts** para mostrar la coexistencia de múltiples frameworks.
*   **`micro-notification-node/`**: Un módulo desarrolado en node para enviar notificaciones por correo (Es necesario configurar las credenciales).
*   **`micro-analytics-python/`**: Un módulo para generar estadisticas y graficos sobre los micros.

## 🛠️ Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:
*   [Java Development Kit (JDK)](https://oracle.com) (Versión recomendada que coincida con tu configuración local)
*   [Node] (Versión recomendada que coincida con tu configuración local)
*   [Docker](https://docker.com) y **Docker Compose**
*   [Maven](https://apache.org) (Si compilas los servicios individuales manualmente)

## ⚡ Primeros Pasos

### 1. Clonar el repositorio
```bash
git clone https://github.com
cd microservices
```

### 2. Compilar las aplicaciones
Compila y empaqueta todos los servicios Java en sus respectivos directorios:
```bash
# Ejemplo para compilación de módulos individuales o multi-módulo de Maven
mvn clean package -DskipTests
```

### 3. Ejecutar con Docker Compose
Toda la pila tecnológica se puede iniciar fácilmente utilizando la configuración de `docker-compose.yml` de la raíz:

```bash
docker-compose up --build
```

Este comando activará el servidor de descubrimiento Eureka, el API Gateway, la seguridad y todos los servicios de backend específicos de cada framework.

## 🔗 Mapa de Servicios y Endpoints (Configuración por Defecto)

Una vez que la pila de contenedores esté activa, los siguientes puertos por defecto suelen estar mapeados:

| Componente del Servicio | Framework | Propósito |
| :--- | :--- | :--- |
| **Servidor Eureka** | Spring Cloud | Interfaz de usuario del registro de servicios (normalmente en `http://localhost:8761`) |
| **API Gateway** | Spring Cloud | Punto de acceso público para rutas de la API |
| **Autenticación** | Spring Security | Gestión de tokens / OAuth2 |
| **API Principal** | Spring Boot | Microservicio de negocio estándar |
| **API Ligera** | Quarkus | Endpoints optimizados de alta velocidad |
| **Motor de Informes** | Apache Struts | Presentación MVC heredada / Informes |

## 🧪 Juego de Pruebas con Postman (Secuencia de Verificación)

Para validar el flujo completo de la arquitectura, realiza las siguientes peticiones en orden (puedes importarlas en Postman o ejecutarlas mediante `curl`):

### 1. Obtención del Token de Acceso (OAuth2)
Antes de consumir cualquier recurso protegido, debes solicitar un token válido al servidor de identidad:

```bash
curl -X POST http://localhost:9191/oauth/token \
  -H "Authorization: Basic bW9iaWxlOnBlcmFzb3ZpYzg3" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password&username=user&password=password"
```
*Guarda el campo `access_token` de la respuesta JSON para las siguientes peticiones.*

### 2. Consumo de Datos a través del Gateway (Micro-Core)
Prueba que el Gateway valida el token y te permite consultar los datos del CRUD básico:

```bash
curl -X GET http://localhost:8082/core-api/CrudBasic/cruds/ \
  -H "Authorization: Bearer <TU_TOKEN_JWT>"
```

### 3. Generación de Reporte y Disparo de Alerta (Flujo Completo)
Lanza la petición al módulo de Struts para exportar los datos. El microservicio de Struts descargará los registros, generará el Excel y, de manera interna, **enviará una petición POST securizada a través del Gateway (`apigateway:8082`)** para que el servicio de Node envíe el correo de alerta:

```bash
curl -X GET http://localhost:8082/reports/generateReport \
  -H "Authorization: Bearer <TU_TOKEN_JWT>"
```
* **Resultado esperado:** Descarga del binario `.xls` y recepción de un correo electrónico en tu bandeja de entrada enviado en tiempo real por el servicio de Node.js.




