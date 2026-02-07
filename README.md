# Blindfolded Solution Generator

This repository contains the first iteration of the Blindfolded Solution Generator project. The current codebase focuses on the core command execution logic so it can be integrated into a future web API.

## Getting started

The project is a plain Java application. From the repository root, compile and run the entry point:

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

## Using the command service

The application layer exposes a command registry and service that you can call from a web controller or another UI:

```java
CubeManager cubeManager = new CubeManager();
CommandRegistry registry = new CommandRegistry(cubeManager);
CommandService service = new CommandService(registry);

Result result = service.execute(new CommandRequest("generateScramble", null));
```

## Simple web UI

The current entry point starts a tiny HTTP server so you can execute commands in a browser:

1. Run the app: `java -cp out Main`
2. Open `http://localhost:8080`
3. Pick a command and execute it to see a message.

## Available commands

- `generateScramble` – runs the current cube manager test routine and returns a status message.
- `status` – returns a simple status message to verify command execution.

## Project structure

- `src/app` – command registry/service for UI or web adapters.
- `src/model` – cube model, cube state, and move parsing.
- `src/view` – shared result/response types returned by commands.

## Next steps

- Add commands for applying moves, loading a cube state, and exporting blindfolded letter sequences.
- Improve cube state formatting for readable output.
- Add a thin HTTP API (e.g., SparkJava or Spring Boot) so a future web UI can call the command service.
