Este repositorio proporciona una plantilla y un esqueleto completo para una arquitectura de microservicios. Demuestra la integración de múltiples frameworks de Java (**Spring Boot**, **Quarkus** y **Struts**) trabajando juntos dentro de un ecosistema unificado impulsado por **Spring Cloud** y contenedorizado con **Docker**.

## 🚀 Vista General de la Arquitectura

El sistema está estructurado como una colección de servicios especializados diseñados para mostrar el descubrimiento de servicios, el enrutamiento de API, la autenticación y el desarrollo de backends heterogéneos.

## 📁 Estructura del Repositorio

*   **`apiGateway/`**: El punto de entrada para todas las solicitudes de los clientes. Se encarga del enrutamiento inteligente y el equilibrio de carga mediante Spring Cloud Gateway.
*   **`eurekaService/`**: El registro de servicios (Netflix Eureka) donde todos los microservicios se registran dinámicamente para permitir el descubrimiento automatizado.
*   **`micro-oauth/`**: Servidor de autenticación y autorización centralizado para asegurar los endpoints en toda la red de servicios.
*   **`micro-core-spring-boot/`**: Servicio de lógica de negocio principal implementado con **Spring Boot**, enfocado en capacidades estándar de backend empresarial.
*   **`micro-api-quarkus/`**: Un microservicio nativo de la nube de alto rendimiento creado con **Quarkus** para demostrar capacidades Java supersónicas y subatómicas (bajo consumo de memoria y arranque rápido).
*   **`micro-report-struts/`**: Un módulo de informes o integración heredada implementado con Apache **Struts** para mostrar la coexistencia de múltiples frameworks.

## 🛠️ Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:
*   [Java Development Kit (JDK)](https://oracle.com) (Versión recomendada que coincida con tu configuración local)
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
