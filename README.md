# 📌 Terra AI - Backend & BFF Architecture

## 🌍 Vue d’ensemble du projet
Terra AI est une application permettant aux utilisateurs de générer des images personnalisées via des modèles IA après entraînement sur leurs propres photos. L’application est composée de plusieurs modules :

- 📱 **Application mobile (React Native) et Web**
- 🚀 **Backend Python (FastAPI)** pour la gestion de l’IA
- 🔗 **Backend for Frontend (BFF) Kotlin (Ktor)** pour faciliter la communication entre le front et l’API Python

## 🛠️ Fonctionnalités principales

### 🔑 Authentification
- **Connexion via Google** ou **création de compte classique**
- Gestion des sessions utilisateur

### 🎭 Optimisation de prompt
- Un utilisateur soumet un **prompt textuel**
- L’IA analyse et optimise ce prompt
- L’utilisateur reçoit le prompt amélioré et peut l’accepter ou le modifier

### 📸 Entraînement du modèle personnalisé
- L’utilisateur **upload 10 à 20 images**
- Le modèle FLUX.1-DEV est entraîné avec ces images
- Une notification est envoyée lorsque l’entraînement est terminé

### 🎨 Génération d’images IA
- L’utilisateur fournit un **prompt texte**
- L’image est générée avec **FLUX.1-Schnell** ou **FLUX.1-DEV** si un modèle personnalisé est disponible
- Une notification est envoyée lorsque l’image est prête à être téléchargée

---

## 📡 API & WebSockets

### 🚀 **Backend Python (FastAPI)**
Le backend gère :
1. **L’optimisation de prompt**
2. **L’entraînement du modèle personnalisé**
3. **La génération d’image IA**
4. **Le stockage temporaire des fichiers générés**

**Endpoints principaux :**
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `POST` | `/auth/login` | Authentification utilisateur via Google ou compte classique |
| `POST` | `/prompt/optimize` | Optimisation d’un prompt utilisateur |
| `POST` | `/training/upload` | Upload des images d’entraînement |
| `POST` | `/training/start` | Démarrage de l’entraînement du modèle |
| `GET` | `/generate/image` | Génération d’une image via un prompt |
| `GET` | `/generate/download/{user_id}` | Téléchargement des images générées |
| `GET` | `/training/status` | Statut de l’entraînement |

---

### 🔗 **Backend for Frontend (BFF) Kotlin**
Le BFF agit comme une passerelle entre le **front** et le **backend Python**.

**Endpoints principaux :**
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `POST` | `/api/auth/login` | Redirection de l'authentification vers le backend |
| `POST` | `/api/prompt/optimize` | Envoi du prompt à l’IA et réception de la version optimisée |
| `POST` | `/api/training/upload` | Transfert des images vers le backend Python |
| `POST` | `/api/training/start` | Lancement de l’entraînement |
| `POST` | `/api/generate/image` | Génération d’images avec gestion de la file d’attente |

**WebSockets pour notifications :**
| WS Événement | Description |
|-------------|-------------|
| `/ws/prompt` | Notifie le front lorsque le **prompt optimisé** est prêt |
| `/ws/training` | Notifie le front lorsque **l’entraînement** est terminé |
| `/ws/generate` | Notifie le front lorsque **l’image générée** est prête |

---

## 📌 Ce qu’il reste à faire
### 🔥 **Priorité 1 - Implémentation des fonctionnalités clés**
- [ ] Finaliser l’**authentification Google**
- [ ] Intégrer l’**optimisation de prompt**
- [ ] Tester l’entraînement **LoRA sur FLUX.1-DEV**
- [ ] Gérer la **génération d’images avec FLUX.1-Schnell**

### 🎯 **Priorité 2 - Optimisation**
- [ ] Ajouter **l’offloading CPU** et **float8** pour optimiser la génération sur 3070 Ti
- [ ] Ajouter un **mécanisme de gestion de file d’attente**
- [ ] Passer à un stockage distant (S3 ou autre) pour les fichiers d’entraînement

---

## 🚀 Conclusion
Terra AI est un projet ambitieux visant à démocratiser la génération d’images personnalisées à partir d’**un modèle IA entraîné sur les propres photos des utilisateurs**. Ce document sert de guide de développement, avec un découpage clair des tâches à accomplir pour mener le projet à terme.

---

📌 **GitHub Repository**: [bazzetv/terra-api-model](https://github.com/bazzetv/terra-api-model)  
💡 **Contact**: [@bazzetv](https://github.com/bazzetv)
