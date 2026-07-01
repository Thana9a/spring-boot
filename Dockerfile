# take image from docker hub

FROM eclipse-temurin:21-jdk-jammy AS deps  
#the eclipse is name repo and 21 jdk jammy is tag (as is change name to deps) os that can call dependency

# this is work dir where are you work on the container (so now you work on build folder)
WORKDIR /build

COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/

# Fix Windows CRLF line endings so mvnw runs correctly inside Linux container
RUN sed -i 's/\r$//' mvnw

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw dependency:go-offline -DskipTests

################################################################################
# Stage 2: Build the application JAR
FROM deps AS package

WORKDIR /build

COPY ./src src/

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    mv target/$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout).jar target/app.jar

################################################################################
# Stage 3: Extract Spring Boot layered JAR for optimized caching
FROM package AS extract

WORKDIR /build

RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted

################################################################################
# Stage 4: Final minimal runtime image (this is the "demo-app" image)
FROM eclipse-temurin:21-jre-jammy AS final

WORKDIR /app

# Create a non-privileged user for security best practices
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

USER appuser

# Copy extracted Spring Boot layers
COPY --from=extract build/target/extracted/dependencies/ ./
COPY --from=extract build/target/extracted/spring-boot-loader/ ./
COPY --from=extract build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract build/target/extracted/application/ ./

EXPOSE 9000

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
