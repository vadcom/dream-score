# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Dream Score is a leaderboard/scoring REST API built with Spring Boot 2.1 and Java 8. It stores scores in MongoDB and was initially generated from an OpenAPI spec via swagger-codegen.

## Build & Run

```bash
# Build (Maven wrapper not included — requires system Maven)
mvn clean package

# Run locally (requires MongoDB on localhost:27017)
mvn spring-boot:run

# Run JAR directly
java -jar target/dream-score-1.1.1.jar

# Run with prod profile (reads MONGODB_URL env var)
java -jar target/dream-score-1.1.1.jar --spring.profiles.active=prod

# Run tests
mvn test
```

## Key Configuration

- Base path: `/score/v3`
- Default port: `8080`
- MongoDB database: `dreamscore`, collection: `score`
- Local profile connects to `mongodb://localhost:27017`; prod profile uses `MONGODB_URL` env var
- API docs at `/score/v3/api-docs`, Swagger UI at `/score/v3/swagger-ui.html`

## Architecture

Single-module Maven project. All source is under `io.poleray`:

- **`Swagger2SpringBoot`** — Application entry point (`main` class)
- **`api/AppApi`** — Interface defining REST endpoints (swagger-codegen generated, but manually modified)
- **`api/AppApiController`** — Controller implementation; delegates all logic to `ScoreDAO`
- **`dao/ScoreDAO`** — Data access layer using the MongoDB sync driver directly (no Spring Data). Manages its own `MongoClient` via `@PostConstruct`. Constructs raw `Document` queries with string-based JSON parsing.
- **`model/Score`, `model/Section`** — Domain models

## API Endpoints

All endpoints are under `/{app}/{section}` where `app` and `section` are path variables identifying the game/application and leaderboard section:

- `GET /{app}/{section}` — Get top scores (paginated with `positionToSkip` and `count`); optional `deviceId` for local-only scores
- `POST /{app}/{section}` — Submit a new score; returns surrounding scores
- `GET /{app}/{section}/id/{id}` — Get scores around a specific record ID
- `GET /{app}/{section}/user` — Get scores for a specific user (by `user` param, cursor-paginated by `id`)
- `GET /{app}/sections` — List all sections for an app

## Important Notes

- The `deviceId` query parameter filters scores to a single device (local leaderboard) vs. global when omitted
- MongoDB queries in `ScoreDAO` use raw string interpolation (`Document.parse`) — be aware of injection risk if modifying query construction
- The `Accept: application/json` header is required for all endpoints; requests without it return 501
