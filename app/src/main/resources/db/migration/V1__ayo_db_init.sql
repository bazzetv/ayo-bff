
DROP TABLE IF EXISTS auth_password;
DROP TABLE IF EXISTS auth_google;
DROP TABLE IF EXISTS auth_apple;
DROP TABLE IF EXISTS account;

CREATE TABLE account (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       name VARCHAR(255),
                       created_at TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE auth_google (
                             account_id UUID PRIMARY KEY REFERENCES account(id) ON DELETE CASCADE,
                             google_id VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE auth_apple (
                            account_id UUID PRIMARY KEY REFERENCES account(id) ON DELETE CASCADE,
                            apple_id VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE auth_password (
                               account_id UUID PRIMARY KEY REFERENCES account(id) ON DELETE CASCADE,
                               password_hash VARCHAR(255) NOT NULL
);

CREATE INDEX idx_account_email ON account(email);
CREATE INDEX idx_auth_google_id ON auth_google(google_id);
CREATE INDEX idx_auth_apple_id ON auth_apple(apple_id);