import argparse
import requests
from faker import Faker
import uuid
import random
from datetime import datetime, timedelta

fake = Faker()

def generate_students(count, endpoint):
    base_url = "http://app:8080/students"

    # Clear existing dat

    # Generate new students
    for i in range(count):
        student = {
            "fio": fake.name(),
            "className": f"{random.randint(9, 11)}{random.choice(['A', 'B', 'C'])}",
            "dateOfBirth": fake.date_of_birth(minimum_age=15, maximum_age=18).isoformat()
        }

        print(f"Creating student {i+1}/{count}: {student['fio']}")
        response = requests.post(f"{base_url}/student", json=student)
        response.raise_for_status()

def generate_subjects(count, endpoint):
    base_url = "http://app:8080/subjects"  # Assuming you have a similar endpoint for subjects


    subjects = ["Mathematics", "Physics", "Chemistry", "Biology", "Literature", "History"]

    for i in range(min(count, len(subjects))):  #
        subject = {
            "className": subjects[i],
            "teacherName": fake.name(),
            "roomNumber": random.randint(100, 200)
        }

        print(f"Creating subject {i+1}/{count}: {subject['className']}")
        response = requests.post(f"{base_url}/subject", json=subject)
        response.raise_for_status()

def generate_grades(count, endpoint):
    # First get existing students and subjects
    students = requests.get("http://app:8080/students").json()
    subjects = requests.get("http://app:8080/subjects").json()  # Adjust if your endpoint is different

    base_url = "http://app:8080/grades"  # Assuming you have a grades endpoint


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
        response = requests.post(f"{base_url}/grade", json=grade)
        response.raise_for_status()

def main():
    parser = argparse.ArgumentParser(description='Generate test data for school journal')
    parser.add_argument('--count', type=int, default=500, help='Number of objects to create')
    parser.add_argument('--endpoint', required=True, choices=['students', 'subjects', 'grades'],
                       help='Endpoint to generate data for')

    args = parser.parse_args()

    if args.endpoint == 'students':
        generate_students(args.count, args.endpoint)
    elif args.endpoint == 'subjects':
        generate_subjects(args.count, args.endpoint)
    elif args.endpoint == 'grades':
        generate_grades(args.count, args.endpoint)

if __name__ == "__main__":
    main()