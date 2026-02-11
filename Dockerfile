FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY src ./src
RUN javac -d out $(find src -name "*.java")

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/out ./out
COPY src/resources ./src/resources
COPY src/web ./src/web
ENV PORT=8080
EXPOSE 8080
CMD ["java", "-cp", "out", "Main"]
