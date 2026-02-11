# Blindfolded Solution Generator

Blindfolded Solution Generator is currently a Java application with a built-in HTTP server (`com.sun.net.httpserver.HttpServer`).
It can run locally and can also be deployed as a standalone backend for a static frontend (for example GitHub Pages).

## Current Project Setup

- Frontend and backend are currently stored in the same repository.
- The original server-rendered UI is still available at:
  - `/`
  - `/execute`
  - `/styles.css`
- JSON API endpoints are available for split hosting.

## Run Locally

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

Default port is `8080`.
If the `PORT` environment variable is set, that port is used.

## API Endpoints

### Health Check

```http
GET /api/status
```

Example response:

```json
{"success":true,"status":"ok"}
```

### Execute Action

```http
GET /api/execute?action=...&scramble=...&edgeAlgorithm=...&cornerAlgorithm=...&memoryHelper=true|false
```

Example:

```bash
curl "http://localhost:8080/api/execute?action=generateScramble&memoryHelper=false"
```

## CORS Configuration

To allow requests from a frontend domain (for example GitHub Pages), set:

```bash
CORS_ALLOWED_ORIGINS="https://<github-user>.github.io"
```

Multiple origins are supported (comma-separated):

```bash
CORS_ALLOWED_ORIGINS="https://<github-user>.github.io,https://example.com"
```

---

## Render Deployment: Exact Steps

### Which Render service type should you use?

Use a **Web Service** with **Docker**.

Do **not** use Static Site for the backend, because this service must process HTTP API requests.

### Step-by-step in Render

1. Open Render.
2. Click **New +** → **Web Service**.
3. Connect GitHub and choose this repository.
4. Choose **Docker** as environment/runtime (Render will detect the `Dockerfile`).
5. Set a service name (for example `blindfolded-backend`).
6. Choose a region and plan (Free plan is fine to start).
7. Set environment variables:
   - `PORT` (optional, Render usually injects one automatically)
   - `CORS_ALLOWED_ORIGINS` = your frontend URL (for example `https://<github-user>.github.io`)
8. Deploy.
9. Test:
   - `https://<your-service>.onrender.com/api/status`

If this returns `{"success":true,"status":"ok"}`, your backend is live.

### What do you upload to Render?

You do not upload files manually.
Render pulls from GitHub on deploy.

In your current monorepo setup this is fully supported.
Render will build and run the backend process defined by your Dockerfile.

---

## Do You Need a Separate Repository?

No, not required.

Two valid options:

1. **Single repository (recommended for now)**
   - Frontend + backend together.
   - Frontend hosted on GitHub Pages.
   - Backend hosted on Render.

2. **Separate repositories (optional later)**
   - Useful if you want independent release/versioning pipelines.

If you are just shipping now, keep one repository.

---

## GitHub Pages + Render Together

### Frontend (GitHub Pages)

- Publish static files from `/docs` or `gh-pages` branch.
- Configure frontend API base URL to your Render backend URL:
  - `https://<your-service>.onrender.com`

### Backend (Render)

- Stays reachable as a Web Service.
- Frontend calls:
  - `/api/status`
  - `/api/execute`

---

## Docker Local Test

```bash
docker build -t blindfolded-backend .
docker run --rm -p 8080:8080 -e PORT=8080 -e CORS_ALLOWED_ORIGINS="http://localhost:3000" blindfolded-backend
```

Then test:

```bash
curl http://localhost:8080/api/status
```

---

## CI/CD (Optional)

A workflow is included at `.github/workflows/render-deploy.yml`.

It does:

1. Java compile check on push (`main` / `master`).
2. Render deploy hook trigger.

Required GitHub secret:

- `RENDER_DEPLOY_HOOK_URL`

---

## Important Directories

- `src/app` - web server, command registry, application layer.
- `src/model` - cube model and solving logic.
- `src/view` - result/rendering layer.
- `src/web` - HTML/CSS for server-rendered UI.
