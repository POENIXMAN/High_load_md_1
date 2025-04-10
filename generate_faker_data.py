import argparse
import requests
from faker import Faker
import random
from datetime import datetime, timedelta

fake = Faker()

BASE_URL = "http://localhost:8080"

def clear_students():
    print("Clearing all students...")
    response = requests.delete(f"{BASE_URL}/students/clear")
    response.raise_for_status()

def clear_subjects():
    print("Clearing all subjects...")
    response = requests.delete(f"{BASE_URL}/subjects/clear")
    response.raise_for_status()

def clear_grades():
    print("Clearing all grades...")
    response = requests.delete(f"{BASE_URL}/grades/clear")
    response.raise_for_status()

def generate_students(count, clear=False):
    if clear:
        clear_students()

    for i in range(count):
        student = {
            "fio": fake.name(),
            "className": f"{random.randint(9, 11)}{random.choice(['A', 'B', 'C'])}",
            "dateOfBirth": fake.date_of_birth(minimum_age=15, maximum_age=18).isoformat()
        }

        print(f"Creating student {i+1}/{count}: {student['fio']}")
        response = requests.post(f"{BASE_URL}/students/student", json=student)
        response.raise_for_status()

def generate_subjects(count, clear=False):
    if clear:
        clear_subjects()

    subjects = ["Mathematics", "Physics", "Chemistry", "Biology", "Literature", "History"]

    for i in range(min(count, len(subjects))):
        subject = {
            "className": subjects[i],
            "teacherName": fake.name(),
            "roomNumber": random.randint(100, 200)
        }

        print(f"Creating subject {i+1}: {subject['className']}")
        response = requests.post(f"{BASE_URL}/subjects/subject", json=subject)
        response.raise_for_status()

def generate_grades(count, clear=False):
    if clear:
        clear_grades()

    students = requests.get(f"{BASE_URL}/students").json()
    subjects = requests.get(f"{BASE_URL}/subjects").json()

    for i in range(count):
        student = random.choice(students)
        subject = random.choice(subjects)

        grade = {
            "studentId": student["studentId"],
            "subjectId": subject["subjectId"],
            "gradeValue": random.randint(2, 5),
            "gradingDate": (datetime.now() - timedelta(days=random.randint(1, 365))).isoformat()
        }

        print(f"Creating grade {i+1}/{count} for student {student['fio']}")
        response = requests.post(f"{BASE_URL}/grades/grade", json=grade)
        response.raise_for_status()

def main():
    parser = argparse.ArgumentParser(description='Generate or clear test data for school journal')
    parser.add_argument('--count', type=int, default=500,
                       help='Number of objects to create (ignored with --clear)')
    parser.add_argument('--endpoint', required=True,
                       choices=['students', 'subjects', 'grades', 'all'],
                       help='Endpoint to target')
    parser.add_argument('--clear', action='store_true',
                       help='Clear data instead of generating')

    args = parser.parse_args()

    if args.clear:
        # Clear data
        if args.endpoint == 'all':
            print("Clearing ALL data...")
            clear_grades()
            clear_subjects()
            clear_students()
        elif args.endpoint == 'students':
            clear_students()
        elif args.endpoint == 'subjects':
            clear_subjects()
        elif args.endpoint == 'grades':
            clear_grades()
    else:
        # Generate data
        if args.endpoint == 'all':
            generate_students(args.count)
            generate_subjects(args.count)
            generate_grades(args.count)
        elif args.endpoint == 'students':
            generate_students(args.count)
        elif args.endpoint == 'subjects':
            generate_subjects(args.count)
        elif args.endpoint == 'grades':
            generate_grades(args.count)

if __name__ == "__main__":
    main()