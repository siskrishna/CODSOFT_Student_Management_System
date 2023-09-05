import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<Student> enrolledStudents = new ArrayList<>();

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    @Override
    public String toString() {
        return "Course Code: " + code +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nCapacity: " + capacity +
                "\nEnrolled Students: " + enrolledStudents.size();
    }
}

class Student {
    private int studentId;
    private String name;
    private List<Course> registeredCourses = new ArrayList<>();

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.enrollStudent(this);
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.removeStudent(this);
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId +
                "\nName: " + name +
                "\nRegistered Courses: " + registeredCourses.size();
    }
}

public class studentmanagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a course");
            System.out.println("2. Register a student");
            System.out.println("3. Display available courses");
            System.out.println("4. Display student information");
            System.out.println("5. Register a student for a course");
            System.out.println("6. Drop a course for a student");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter course code:");
                    String courseCode = scanner.nextLine();
                    System.out.println("Enter course title:");
                    String courseTitle = scanner.nextLine();
                    System.out.println("Enter course description:");
                    String courseDescription = scanner.nextLine();
                    System.out.println("Enter course capacity:");
                    int courseCapacity = scanner.nextInt();
                    courses.add(new Course(courseCode, courseTitle, courseDescription, courseCapacity));
                    break;
                case 2:
                    System.out.println("Enter student ID:");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter student name:");
                    String studentName = scanner.nextLine();
                    students.add(new Student(studentId, studentName));
                    break;
                case 3:
                    System.out.println("Available Courses:");
                    for (Course course : courses) {
                        System.out.println(course.toString());
                    }
                    break;
                case 4:
                    System.out.println("Enter student ID:");
                    int searchStudentId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    Student foundStudent = null;
                    for (Student student : students) {
                        if (student.getStudentId() == searchStudentId) {
                            foundStudent = student;
                            break;
                        }
                    }
                    if (foundStudent != null) {
                        System.out.println(foundStudent.toString());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("Enter student ID:");
                    int studentIdToRegister = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter course code:");
                    String courseCodeToRegister = scanner.nextLine();
                    Student studentToRegister = null;
                    Course courseToRegister = null;
                    for (Student student : students) {
                        if (student.getStudentId() == studentIdToRegister) {
                            studentToRegister = student;
                            break;
                        }
                    }
                    for (Course course : courses) {
                        if (course.getCode().equals(courseCodeToRegister)) {
                            courseToRegister = course;
                            break;
                        }
                    }
                    if (studentToRegister != null && courseToRegister != null) {
                        studentToRegister.registerCourse(courseToRegister);
                        System.out.println("Student successfully registered for the course.");
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter student ID:");
                    int studentIdToDrop = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter course code:");
                    String courseCodeToDrop = scanner.nextLine();
                    Student studentToDrop = null;
                    Course courseToDrop = null;
                    for (Student student : students) {
                        if (student.getStudentId() == studentIdToDrop) {
                            studentToDrop = student;
                            break;
                        }
                    }
                    for (Course course : courses) {
                        if (course.getCode().equals(courseCodeToDrop)) {
                            courseToDrop = course;
                            break;
                        }
                    }
                    if (studentToDrop != null && courseToDrop != null) {
                        studentToDrop.dropCourse(courseToDrop);
                        System.out.println("Student successfully dropped the course.");
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
