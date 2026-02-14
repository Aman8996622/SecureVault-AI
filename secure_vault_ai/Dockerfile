# =========================
# Build Stage
# =========================
FROM --platform=linux/amd64 maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy only pom.xml first for caching
COPY pom.xml .docker compose up -d

# Download dependencies
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application, skip tests
RUN mvn clean package -DskipTests

# =========================
# Run Stage
# =========================
FROM --platform=linux/amd64 eclipse-temurin:17-jre

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 4000
EXPOSE 4000

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
