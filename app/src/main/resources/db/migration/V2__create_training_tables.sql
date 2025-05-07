-- Drop existing if needed
DROP TABLE IF EXISTS progress;
DROP TABLE IF EXISTS program;
DROP TABLE IF EXISTS exercise;
DROP TYPE IF EXISTS sex;
DROP TYPE IF EXISTS target_muscle;
DROP TYPE IF EXISTS equipment;
DROP TYPE IF EXISTS category;
DROP TYPE IF EXISTS level;

-- Enums
CREATE TYPE sex AS ENUM ('MALE', 'FEMALE');
CREATE TYPE target_muscle AS ENUM (
  'CHEST', 'BACK', 'LEGS', 'SHOULDERS', 'BICEPS', 'TRICEPS',
  'CORE', 'GLUTES', 'CALVES', 'FULL_BODY'
);
CREATE TYPE equipment AS ENUM (
  'BODYWEIGHT', 'DUMBBELL', 'BARBELL', 'KETTLEBELL',
  'MACHINE', 'CABLE', 'BAND', 'OTHER'
);

CREATE TYPE category AS ENUM ('BODYBUILDING', 'POWERBUILDING', 'BODYSHAPE', 'FUNCTIONAL', 'ENDURANCE');
CREATE TYPE level AS ENUM ('BEGINNER', 'INTERMEDIATE', 'ADVANCED');

CREATE TABLE program (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  title VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  duration_weeks INT NOT NULL,
  days_per_week INT NOT NULL,
  level level NOT NULL,
  category category NOT NULL,
  goal TEXT,
  coach_name VARCHAR(255),
  structure JSONB NOT NULL,
  image_url VARCHAR(512) NOT NULL,
  tags TEXT[],
  is_published BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_program_category ON program(category);
CREATE INDEX idx_program_level ON program(level);
CREATE INDEX idx_program_tags ON program USING GIN (tags);

CREATE TABLE exercise (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(255) NOT NULL,
  target_muscle target_muscle NOT NULL,
  equipment equipment,
  video_url VARCHAR(512),
  image_url VARCHAR(512),
  created_at TIMESTAMP NOT NULL DEFAULT now()
);

-- Indexes
CREATE INDEX idx_program_title ON program(title);
CREATE INDEX idx_exercise_target_muscle ON exercise(target_muscle);

CREATE TABLE progress (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_id UUID NOT NULL REFERENCES account(id),
    programs JSONB NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);
CREATE INDEX idx_progress_user_id ON progress(account_id);