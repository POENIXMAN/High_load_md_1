
CREATE TABLE student_entity (
    identifier UUID PRIMARY KEY,
    fio VARCHAR(255) NOT NULL,
    class_name VARCHAR(255),
    date_of_birth DATE NOT NULL
);

CREATE TABLE subject_entity (
    identifier UUID PRIMARY KEY,
    class_name VARCHAR(255) NOT NULL,
    teacher_name VARCHAR(255) NOT NULL,
    room_number INTEGER NOT NULL
);

CREATE TABLE grade_entity (
    grade_id UUID PRIMARY KEY,
    student_id UUID NOT NULL,
    subject_id UUID NOT NULL,
    grade_value INTEGER NOT NULL,
    grading_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student_entity(identifier),
    FOREIGN KEY (subject_id) REFERENCES subject_entity(identifier)
);

INSERT INTO student_entity (identifier, fio, class_name, date_of_birth)
VALUES ('d05c166e-b067-499b-8b5c-282e5210e254', 'Ivan Ivanov', '10A', '2006-05-15');

INSERT INTO student_entity (identifier, fio, class_name, date_of_birth)
VALUES ('c00b091f-50fc-4212-baed-c044a596e026', 'Anna Smirnova', '10B', '2006-08-20');

INSERT INTO subject_entity (identifier, class_name, teacher_name, room_number)
VALUES ('f0cfb060-fc50-4c7b-ab50-826dc39170ff', 'Mathematics', 'Maria Petrova', 101);

INSERT INTO subject_entity (identifier, class_name, teacher_name, room_number)
VALUES ('af754035-523b-4126-90c6-1c58c42a978a', 'Physics', 'Alexei Sidorov', 102);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    '4b05e63b-dfdb-4898-9252-a2c7a7757e5f',
    (SELECT identifier FROM student_entity WHERE fio = 'Ivan Ivanov'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Physics'),
    2,
    '2024-05-11'
);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    '3fe56078-62ce-479a-87fc-cfdb80f988f1',
    (SELECT identifier FROM student_entity WHERE fio = 'Anna Smirnova'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Mathematics'),
    2,
    '2024-05-08'
);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    'db040c90-6158-4e49-b8e5-f9a5356d8e0b',
    (SELECT identifier FROM student_entity WHERE fio = 'Anna Smirnova'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Mathematics'),
    4,
    '2024-05-20'
);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    'ba581f2e-bb50-40ce-a696-a9a9dcf8912c',
    (SELECT identifier FROM student_entity WHERE fio = 'Ivan Ivanov'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Mathematics'),
    4,
    '2025-01-01'
);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    'a9814743-2da6-4eab-9eff-e4da9e61de79',
    (SELECT identifier FROM student_entity WHERE fio = 'Anna Smirnova'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Mathematics'),
    5,
    '2025-01-31'
);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    'e47a9af1-3bd9-4600-9101-0172043cc03b',
    (SELECT identifier FROM student_entity WHERE fio = 'Anna Smirnova'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Physics'),
    4,
    '2024-10-21'
);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    'f293de77-5146-48ce-8803-cbf91c7d5cc1',
    (SELECT identifier FROM student_entity WHERE fio = 'Anna Smirnova'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Mathematics'),
    5,
    '2024-12-24'
);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    '065af328-e609-48c1-89b9-3e1ece0a0a4c',
    (SELECT identifier FROM student_entity WHERE fio = 'Anna Smirnova'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Mathematics'),
    4,
    '2025-01-26'
);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    'c45b0bd8-06ba-419b-87f9-405c82a646ec',
    (SELECT identifier FROM student_entity WHERE fio = 'Anna Smirnova'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Mathematics'),
    3,
    '2024-08-23'
);

INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
VALUES (
    'baf39296-cbf9-4b01-a923-6c353f3f9eae',
    (SELECT identifier FROM student_entity WHERE fio = 'Ivan Ivanov'),
    (SELECT identifier FROM subject_entity WHERE class_name = 'Mathematics'),
    4,
    '2024-06-13'
);
