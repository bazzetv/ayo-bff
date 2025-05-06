-- Drop existing if needed
DROP TABLE IF EXISTS progress;
DROP TABLE IF EXISTS program;
DROP TABLE IF EXISTS exercise;
DROP TYPE IF EXISTS sex;
DROP TYPE IF EXISTS target_muscle;
DROP TYPE IF EXISTS equipment;

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

-- Table program
CREATE TABLE program (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  title VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  duration_weeks INT NOT NULL,
  sex TEXT[] NOT NULL CHECK (sex <@ ARRAY['MALE', 'FEMALE']),  structure JSONB NOT NULL,
  image_url VARCHAR(512) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT now()
);

-- Table progress
CREATE TABLE progress (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL,
  program_id UUID NOT NULL REFERENCES program(id) ON DELETE CASCADE,
  current_week INT NOT NULL,
  current_day INT NOT NULL,
  started_at TIMESTAMP NOT NULL DEFAULT now(),
  finished_at TIMESTAMP
);

CREATE TABLE exercise (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(255) NOT NULL,
  target_muscle target_muscle NOT NULL,
  equipment equipment,
  description TEXT,
  video_url VARCHAR(512),
  image_url VARCHAR(512),
  created_at TIMESTAMP NOT NULL DEFAULT now()
);

-- Indexes
CREATE INDEX idx_program_title ON program(title);
CREATE INDEX idx_progress_user_id ON progress(user_id);
CREATE INDEX idx_exercise_target_muscle ON exercise(target_muscle);