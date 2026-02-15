# =========================
# Build Stage
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY secure_vault_ai/pom.xml .
RUN mvn dependency:go-offline

COPY secure_vault_ai/src ./src
RUN mvn clean package -DskipTests

# =========================
# Run Stage
# =========================
FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 4000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
