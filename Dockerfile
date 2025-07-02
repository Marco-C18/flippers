# Etapa 1: Compilación
FROM maven:3.9.0-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos del proyecto
COPY . .

# Permisos de ejecución al wrapper y compilar
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia del .jar 
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
