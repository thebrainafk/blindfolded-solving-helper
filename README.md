# Blindfolded Solving Helper
# https://blindfolded-backend.onrender.com

Blindfolded Solving Helper is a small Java web app for blindfolded Rubik’s Cube practice.
It generates scrambles and computes solution-oriented output for blindfold methods (including M2 and Old Pochmann), so you can focus on memo and execution training.

The project includes:
- a lightweight backend server
- a built-in web interface
- simple HTTP API endpoints used by the UI

## What this project is for

This repository is the backend + web UI for the live tool.
Its purpose is to take cube-related actions (for example generating a scramble or calculating method-specific output) and return the result in a usable training format.

## Run locally

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

Open:
- `http://localhost:8080/`

If needed, set a custom port:

```bash
PORT=9090 java -cp out Main
```

## API (minimal)

Health check:

```http
GET /api/status
```

Action endpoint:

```http
GET /api/execute?action=...&scramble=...&edgeAlgorithm=...&cornerAlgorithm=...&memoryHelper=true|false
```

Example:

```bash
curl "http://localhost:8080/api/execute?action=generateScramble&memoryHelper=false"
```

## Project structure

- `src/app` - HTTP server and command handling
- `src/model` - cube model and solving logic
- `src/view` - response/result rendering and commands
- `src/web` - built-in HTML/CSS frontend
- `src/resources` - lookup tables and setup-move data
