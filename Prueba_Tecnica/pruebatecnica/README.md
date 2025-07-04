# 📌 Proyecto de Gestión de Tareas con Spring WebFlux y MongoDB

Este proyecto implementa una API reactiva para la gestión de tareas, utilizando Spring WebFlux y MongoDB como base de datos. Se incluye autenticación JWT para proteger los endpoints.

---

## 🐳 Levantar MongoDB con Docker

Asegúrate de tener Docker instalado y ejecutando en tu máquina.

### Comando

```bash
docker run --name mongodb-local -p 27017:27017 -d mongo:latest
```
---

## 🐳 Para ver los registros colocar estos comandos


### Comando Listar contenedores

```bash
docker ps
```

### Ejecutar shell de mongo, Nota: esto solo se podra realizar cuando se tenga un primer registro.

```bash
docker exec -it <contenedor_id> mongosh
```

### En shell de mongo ejecutar

```bash
use usuarios
```

```bash
show collections
```

```bash
db.userEntity.find().pretty()
```

## 🧩  Levantar el proyecto

Asegúrate de tener java 17.

### Comando

```bash
git clone https://github.com/UnmsmCc/Pruebas-Tecnicas.git
```
### Ubicarse en la raiz del proyecto pruebatecnica, a la altura del archivo pom.xml y ejecutar

```bash
mvn clean install
```
### Esto generará un archivo .jar en la carpeta target/, por ejemplo:

```bash
target/mi-proyecto-0.0.1-SNAPSHOT.jar
```

### Ejecutar el .jar

```bash
java -jar target/mi-proyecto-0.0.1-SNAPSHOT.jar
```

# 📬 Orden de Ejecución de Colección Postman 

Este documento describe el flujo sugerido para ejecutar los endpoints definidos en Postman

---

## ✅ Requisitos

- Proyecto backend ejecutándose (por ejemplo, con `java -jar target/mi-proyecto.jar`)
- MongoDB disponible (puedes usar Docker)
- Tener instalada la app Postman
- Tener la colección importada en Postman (`mi-api.postman_collection.json`)

---

## ▶️ Flujo de Ejecución Manual

### 1. 🔐 Registro de Usuario

**Método**: `POST`  
**Endpoint**: `http://localhost:8080/auth/register`

**Body (JSON)**:
```json
{
  "email": "usuario@gmail.com",
  "password": "123456"
}
```
### 1. 🔐 Obtener accesToken y copiar el valor

**Método**: `POST`  
**Endpoint**: `http://localhost:8080/auth/login`

**Body (JSON)**:
```json
{
  "email": "usuario@gmail.com",
  "password": "123456"
}
```
### 1. Crear tarea

**Método**: `POST`  
**Endpoint**: `http://localhost:8080/tasks`

**Header**:
```
Authorization: Bearer {accesToken}
```
**Body (JSON)**:
```json
{
    "id":"123",
    "title":"Prueba tecnica",
    "detail":"nuevo"
}
```
### 2. Listar tareas

**Método**: `GET`  
**Endpoint**: `http://localhost:8080/tasks`

**Header**:
```
Authorization: Bearer {accesToken}
```
### 3. Actualizar tarea

**Método**: `POST`  
**Endpoint**: `http://localhost:8080/tasks/123`
**Header**:
```
Authorization: Bearer {accesToken}
```
**Body (JSON)**:
```json
{
    "id":"123",
    "title":"Prueba tecnica",
    "detail":"En progreso"
}
```
### 4. Elimnar tarea

**Método**: `DELETE`  
**Endpoint**: `http://localhost:8080/tasks/123`

**Header**:
```
Authorization: Bearer {accesToken}
```