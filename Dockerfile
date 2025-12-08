# Multi-stage Dockerfile pour Course Service
# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /build

# Copier les fichiers Maven
COPY pom.xml .
COPY .mvn .mvn

# Copier le code source
COPY src src

# Compiler l'application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copier le JAR depuis le stage de build
COPY --from=builder /build/target/course-service-*.jar app.jar

# Créer un utilisateur non-root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Exposer le port
EXPOSE 8084

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8084/actuator/health || exit 1

# Exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]

