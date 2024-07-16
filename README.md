# Despliegue de una Aplicación Spring Boot con Docker en Render

Esta guía proporciona un proceso paso a paso para desplegar una aplicación simple de Spring Boot utilizando Docker en Render.

## Prerrequisitos

Antes de comenzar, asegúrate de tener lo siguiente instalado:

- Docker: [Instalar Docker](https://docs.docker.com/get-docker/)
- Una cuenta en Render: [Regístrate en Render](https://render.com/)

## Estructura del Proyecto

La estructura del proyecto es la siguiente:
```
.
├── .mvn/wrapper
├── src
├── target
├── .gitignore
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
```

## Dockerfile

Aquí tienes un `Dockerfile` básico para una aplicación Spring Boot:

```Dockerfile
# Utilizar la imagen oficial de Maven para crear un artefacto de compilación.
# https://hub.docker.com/_/maven
FROM maven:3.8.1-jdk-11 AS build
COPY pom.xml /app/
COPY src /app/src
WORKDIR /app
RUN mvn clean package -DskipTests

# Utilizar la imagen oficial de OpenJDK para una fase de producción más ligera.
# https://hub.docker.com/_/openjdk
FROM openjdk:11-jre-slim
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Ejecutar el archivo jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

# Pasos para el Despliegue

1. **Construir la Imagen Docker**

   Construye tu imagen Docker localmente utilizando el siguiente comando:
   ```bash
   docker build -t mi-aplicacion-spring-boot .
   ```
2. **Probar la Imagen Docker**
   
   Ejecuta la imagen Docker localmente para asegurarte de que funcione:
   ```
   docker run -p 8080:8080 mi-aplicacion-spring-boot
   ```
   Abre tu navegador y navega a http://localhost:8080 para verificar si la aplicación está funcionando correctamente.

3. **Subir la Imagen Docker a Docker Hub**

   Etiqueta tu imagen Docker y súbela a Docker Hub (reemplaza tu-usuario-dockerhub con tu nombre de usuario de Docker Hub):
   ```
   docker tag mi-aplicacion-spring-boot tu-usuario-dockerhub/mi-aplicacion-spring-boot
   docker push tu-usuario-dockerhub/mi-aplicacion-spring-boot
   ```

4. **Desplegar en Render**
   - Crear un Nuevo Servicio Web: Inicia sesión en tu cuenta de Render, haz clic en el botón New y selecciona Web Service.
   - Configurar el Servicio Web: En la sección Connect, elige Docker, para Docker Repository, ingresa la ruta a tu imagen Docker: docker.io/tu-usuario-dockerhub/mi-aplicacion-spring-boot. Establece el Port en 8080. Haz clic en Create Web Service.
   - Desplegar: Render descargará la imagen Docker e iniciará el proceso de despliegue. Una vez que el despliegue esté completo, verás una URL donde tu aplicación estará funcionando.


# Conclusión

Tu aplicación Spring Boot debería estar ahora en funcionamiento en Render utilizando Docker. Puedes realizar cambios en tu aplicación, reconstruir la imagen Docker y subir las actualizaciones a Docker Hub. Render desplegará automáticamente la última imagen.
