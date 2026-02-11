# Blindfolded Solution Generator

This repository contains the first iteration of the Blindfolded Solution Generator project. The current codebase focuses on the core command execution logic so it can be integrated into a future web API.

## Getting started

The project is a plain Java application. From the repository root, compile and run the entry point:

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

The server reads the `PORT` environment variable and falls back to `8080`.

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
3. Pick a command, add optional arguments, and execute it to see the output and cube state.

## API endpoints

For split frontend/backend hosting, you can call these endpoints from a separate frontend:

- `GET /api/status` – health check.
- `GET /api/execute?action=...&scramble=...&edgeAlgorithm=...&cornerAlgorithm=...&memoryHelper=true|false` – executes the requested action and returns JSON.

Configure CORS for your frontend domain using `CORS_ALLOWED_ORIGINS`, for example:

```bash
CORS_ALLOWED_ORIGINS="https://<github-user>.github.io"
```

Multiple origins can be comma-separated.

## Deploying backend on Render (free tier)

### Do I need a separate repository?

No. You can keep everything in this repository.

- **Frontend**: host static files on GitHub Pages (or another static host).
- **Backend**: deploy this Java service to Render using the same repository.

You only need a separate repository if you want independent release/versioning between frontend and backend.

### What do I upload to Render?

When Render is connected to GitHub, you do not upload files manually.
Render pulls from the repository automatically on each deploy.

If you use this monorepo setup, Render still only runs the backend process based on the Dockerfile/commands.

### Docker deployment

A Dockerfile is included. You can run locally with:

```bash
docker build -t blindfolded-backend .
docker run --rm -p 8080:8080 -e PORT=8080 blindfolded-backend
```

## GitHub Actions workflow for automated deploy

This repository includes `.github/workflows/render-deploy.yml`:

1. Compiles the Java project on every push to `main`/`master`.
2. Triggers Render via deploy hook.

Add a repository secret named `RENDER_DEPLOY_HOOK_URL` with your Render deploy hook URL.

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
