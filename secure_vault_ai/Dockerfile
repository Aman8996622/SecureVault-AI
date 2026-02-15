# =========================
# Build Stage
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy only pom.xml first for dependency caching
COPY secure_vault_ai/pom.xml .

# Download dependencies offline
RUN mvn dependency:go-offline

# Copy source code
COPY secure_vault_ai/src ./src

# Build the application, skip tests
RUN mvn clean package -DskipTests

# =========================
# Run Stage
# =========================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 4000
EXPOSE 4000

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
# =========================
# Build Stage
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy only pom.xml first for dependency caching
COPY secure_vault_ai/pom.xml .

# Download dependencies offline
RUN mvn dependency:go-offline

# Copy source code
COPY secure_vault_ai/src ./src

# Build the application, skip tests
RUN mvn clean package -DskipTests

# =========================
# Run Stage
# =========================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 4000
EXPOSE 4000

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
