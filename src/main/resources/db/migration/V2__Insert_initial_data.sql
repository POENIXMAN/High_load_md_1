INSERT INTO student_entity (identifier, fio, class_name, date_of_birth) VALUES
                                                                     (uuid_generate_v4(), 'John Doe', '10A', '2005-01-01'),
                                                                     (uuid_generate_v4(), 'Jane Smith', '10A', '2005-02-02'),
                                                                     (uuid_generate_v4(), 'Alice Johnson', '10B', '2005-03-03');

INSERT INTO subject_entity (identifier, class_name, teacher_name, room_number) VALUES
                                                                            (uuid_generate_v4(), 'Mathematics', 'Mr. Smith', 1),
                                                                            (uuid_generate_v4(), 'Physics', 'Ms. Johnson', 2);


INSERT INTO grade_entity (grade_id, student_id, subject_id, grade_value, grading_date) VALUES
                                                                                    (uuid_generate_v4(), (SELECT identifier FROM student WHERE fio = 'John Doe'), (SELECT identifier FROM subject WHERE class_name = 'Mathematics'), 85, '2023-01-15'),
                                                                                    (uuid_generate_v4(), (SELECT identifier FROM student WHERE fio = 'Jane Smith'), (SELECT identifier FROM subject WHERE class_name = 'Mathematics'), 90, '2023-01-16'),
                                                                                    (uuid_generate_v4(), (SELECT identifier FROM student WHERE fio = 'Alice Johnson'), (SELECT identifier FROM subject WHERE class_name = 'Mathematics'), 78, '2023-01-17'),
                                                                                    (uuid_generate_v4(), (SELECT identifier FROM student WHERE fio = 'John Doe'), (SELECT identifier FROM subject WHERE class_name = 'Physics'), 88, '2023-01-18'),
                                                                                    (uuid_generate_v4(), (SELECT identifier FROM student WHERE fio = 'Jane Smith'), (SELECT identifier FROM subject WHERE class_name = 'Physics'), 92, '2023-01-19'),
                                                                                    (uuid_generate_v4(), (SELECT identifier FROM student WHERE fio = 'Alice Johnson'), (SELECT identifier FROM subject WHERE class_name = 'Physics'), 81, '2023-01-20');