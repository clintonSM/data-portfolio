# Usar una imagen base de Java
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo .jar generado en el contenedor
COPY notificaciones-1.0.0.jar /app/app.jar

# Comando para ejecutar la aplicación cuando el contenedor arranque
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Exponer el puerto en el que la aplicación escucha
EXPOSE 8080