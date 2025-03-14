    import uuid
    from datetime import datetime, timedelta
    from random import randint, choice

    from pathlib import Path

    db_init_dir = Path("db-init")

    db_init_dir.mkdir(exist_ok=True)

    file_path = db_init_dir / "init.sql"

    students = [
        {"fio": "Ivan Ivanov", "class_name": "10A", "date_of_birth": "2006-05-15"},
        {"fio": "Anna Smirnova", "class_name": "10B", "date_of_birth": "2006-08-20"}
    ]

    subjects = [
        {"class_name": "Mathematics", "teacher_name": "Maria Petrova", "room_number": 101},
        {"class_name": "Physics", "teacher_name": "Alexei Sidorov", "room_number": 102}
    ]

    grades = []
    for _ in range(10):
        student = choice(students)
        subject = choice(subjects)
        grade_value = randint(2, 5)
        grading_date = (datetime.now() - timedelta(days=randint(1, 365))).strftime('%Y-%m-%d')
        grades.append({
            "student_fio": student["fio"],
            "subject_class_name": subject["class_name"],
            "grade_value": grade_value,
            "grading_date": grading_date
        })

    with open(file_path, "w") as f:
        f.write("""
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
    """)

        for student in students:
            f.write(f"""
    INSERT INTO student_entity (identifier, fio, class_name, date_of_birth)
    VALUES ('{uuid.uuid4()}', '{student["fio"]}', '{student["class_name"]}', '{student["date_of_birth"]}');
    """)

        for subject in subjects:
            f.write(f"""
    INSERT INTO subject_entity (identifier, class_name, teacher_name, room_number)
    VALUES ('{uuid.uuid4()}', '{subject["class_name"]}', '{subject["teacher_name"]}', {subject["room_number"]});
    """)

        for grade in grades:
            f.write(f"""
    INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date)
    VALUES (
        '{uuid.uuid4()}',
        (SELECT identifier FROM student_entity WHERE fio = '{grade["student_fio"]}'),
        (SELECT identifier FROM subject_entity WHERE class_name = '{grade["subject_class_name"]}'),
        {grade["grade_value"]},
        '{grade["grading_date"]}'
    );
    """)