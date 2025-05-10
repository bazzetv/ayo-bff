# 🏋️ AYO-BFF — Backend For Frontend (Kotlin)

Bienvenue dans le backend de l'application AYO, écrit en **Kotlin**. Ce BFF (Backend For Frontend) agit comme une passerelle entre le frontend mobile (Expo / React Native) et les services d’IA ou de gestion d'entraînement.

---

## 🚀 Stack Technique

- **Langage** : Kotlin
- **Serveur** : Ktor
- **Base de données** : PostgreSQL (via Docker)
- **Gestion de dépendances** : Gradle
- **Conteneurisation** : Docker / Docker Compose

---

## ⚙️ Prérequis

Avant de lancer le projet, assure-toi d’avoir installé les outils suivants :

### 📦 Kotlin & Gradle
- [SDKMAN (recommandé)](https://sdkman.io/)
  ```bash
  curl -s "https://get.sdkman.io" | bash
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  sdk install kotlin
  sdk install gradle
  ```

### 🐳 Docker & Docker Compose
- [Installer Docker Desktop](https://www.docker.com/products/docker-desktop/)
    - Une fois installé, vérifie :
  ```bash
  docker -v
  docker compose version
  ```

---

## 📥 Installation & Lancement

1. **Clone du dépôt**
   ```bash
   git clone https://github.com/bazzetv/ayo-bff.git
   cd ayo-bff
   ```

2. **Lancer la base de données et services**
   ```bash
   docker compose up -d
   ```

3. **Lancer l'application Kotlin**
   ```bash
   ./gradlew run
   ```

---

## 📚 Structure du projet

- `src/main/kotlin/` → Code principal du serveur
- `resources/application.conf` → Configuration Ktor (ports, CORS, JWT, etc.)
- `docker-compose.yml` → Base PostgreSQL + volumes
- `migrations/` (si présent) → Fichiers SQL / Flyway pour initialisation BDD

---

## 🧪 Tests

```bash
./gradlew test
```

---

## 🔒 License

```
Copyright (c) 2025 Issam BAZZE

This project is licensed under the AYO License - No Commercial Use Allowed

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to use
and modify the Software for **personal and non-commercial purposes only**.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
```
