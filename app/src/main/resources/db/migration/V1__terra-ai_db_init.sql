-- Suppression des tables dans le bon ordre
DROP TABLE IF EXISTS generated_images;
DROP TABLE IF EXISTS generation_requests;
DROP TABLE IF EXISTS auth_password;
DROP TABLE IF EXISTS auth_google;
DROP TABLE IF EXISTS auth_apple;
DROP TABLE IF EXISTS users;

-- Création de la table des utilisateurs
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       name VARCHAR(255),
                       created_at TIMESTAMP DEFAULT now() NOT NULL
);

-- Table pour l'authentification avec Google
CREATE TABLE auth_google (
                             user_id UUID PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
                             google_id VARCHAR(255) UNIQUE NOT NULL
);

-- Table pour l'authentification avec Apple
CREATE TABLE auth_apple (
                            user_id UUID PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
                            apple_id VARCHAR(255) UNIQUE NOT NULL
);

-- Table pour l'authentification classique (email + mot de passe)
CREATE TABLE auth_password (
                               user_id UUID PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
                               password_hash VARCHAR(255) NOT NULL
);

-- Table pour stocker les requêtes de génération
CREATE TABLE generation_requests (
                                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                     request_id VARCHAR(255) NOT NULL,
                                     user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                                     model VARCHAR(255) NOT NULL,
                                     prompt TEXT NOT NULL,
                                     num_images INTEGER NOT NULL CHECK (num_images > 0),
                                     status VARCHAR(50) CHECK (status IN ('pending', 'done', 'failed')) NOT NULL DEFAULT 'pending',
                                     created_at TIMESTAMP DEFAULT now() NOT NULL
);

-- Table pour stocker les images générées
CREATE TABLE generated_images (
                                  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                  generation_request_id UUID NOT NULL REFERENCES generation_requests(id) ON DELETE CASCADE,
                                  status VARCHAR(50) CHECK (status IN ('pending', 'done', 'failed')) NOT NULL DEFAULT 'pending',
                                  url TEXT,
                                  created_at TIMESTAMP DEFAULT now() NOT NULL
);

-- Index pour accélérer les recherches
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_auth_google_id ON auth_google(google_id);
CREATE INDEX idx_auth_apple_id ON auth_apple(apple_id);
CREATE INDEX idx_generation_status ON generation_requests(status);
CREATE INDEX idx_generated_images_status ON generated_images(status);