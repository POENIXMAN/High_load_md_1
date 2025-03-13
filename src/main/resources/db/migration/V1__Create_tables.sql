CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE student_entity (
                         identifier UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         fio VARCHAR(255) NOT NULL,
                         class_name VARCHAR(255),
                         date_of_birth DATE NOT NULL
);

CREATE TABLE subject_entity (
                         identifier UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         class_name VARCHAR(255) NOT NULL,
                         teacher_name VARCHAR(255) NOT NULL,
                         room_number INT
);

CREATE TABLE grade_entity (
                       grade_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       student_id UUID REFERENCES student(identifier) ON DELETE CASCADE,
                       subject_id UUID REFERENCES subject(identifier) ON DELETE CASCADE,
                       grade_value INT NOT NULL,
                       grading_date DATE NOT NULL
);