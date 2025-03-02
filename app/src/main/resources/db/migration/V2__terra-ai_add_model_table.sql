DROP TABLE IF EXISTS models;

CREATE TABLE models
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(255)                   NOT NULL UNIQUE,
    api_path    TEXT                           NOT NULL,
    version     TEXT,
    parameters  JSONB                          NOT NULL,
    description TEXT                           NOT NULL,
    image_url   TEXT                           NOT NULL,
    created_at  TIMESTAMP        DEFAULT now() NOT NULL
);

INSERT INTO models (name, api_path, parameters, description, image_url, version)
VALUES
    ('flux-schnell',
     'v1/models/black-forest-labs/flux-schnell/predictions',
     '{
       "go_fast": true,
       "guidance": 3.5,
       "megapixels": "1",
       "num_outputs": 3,
       "aspect_ratio": "1:1",
       "output_format": "jpg",
       "output_quality": 80,
       "prompt_strength": 0.8,
       "num_inference_steps": 3
     }'::jsonb,
     'Un modèle rapide pour la génération d''images réalistes.',
     '/terra-ai-bucket/models/flux-schnell.webp',
     null
    ),
    ('flux-dev',
     'v1/models/black-forest-labs/flux-dev/predictions',
     '{
       "go_fast": true,
       "guidance": 3.5,
       "megapixels": "1",
       "num_outputs": 1,
       "aspect_ratio": "1:1",
       "output_format": "webp",
       "output_quality": 80,
       "prompt_strength": 0.8,
       "num_inference_steps": 28
     }'::jsonb,
     'Un modèle avancé pour la génération d''images ultra-réalistes.',
     '/terra-ai-bucket/models/flux-dev.webp',
     null
    ),
    ('ghibsky-style',
     'v1/predictions',
     '{
       "model": "dev",
       "guidance_scale": 3.5,
       "aspect_ratio": "9:16",
       "output_format": "jpg",
       "output_quality": 100,
       "num_outputs": 1
     }'::jsonb,
     'Un modèle inspiré du style Ghibli, idéal pour des images artistiques et poétiques.',
     '/terra-ai-bucket/models/ghibsky.jpg',
     'a9f94946fa0377091ac0bcfe61b0d62ad9a85224e4b421b677d4747914b908c0'
    );