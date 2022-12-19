import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBase {
    static Scanner input = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Quiz> quizzes = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Question> questions = new ArrayList<>();
    public DataBase() {

    }
    public static boolean addUser(User user){
        if (existUser(user))
            return false;
        users.add(user);
        return true;
    }
    public static boolean removeUser(User user){
        if (!existUser(user))
            return false;
        users.remove(user);
        return true;
    }
    public static boolean existUser(User user){
        for (User user1:users) {
            if (user.getUsername().equals(user1.getUsername()))
                return true;
        }
        return false;
    }
    public static boolean addCourse(Course course){
        if (!existProfessor(course.getProfessor()))
            return false;
        courses.add(course);
        return true;
    }
    public static boolean removeCourse(Course course){
        if (!existCourse(course.getCode()))
            return false;
        users.remove(course);
        return true;
    }

    public static boolean existCourse(int code){
        for (Course c: courses) {
            if (c.getCode()==code)
                return true;
        }
        return false;
    }
    public static boolean existProfessor(int code){
        for (User u:users) {
            if (u.getCode()==code && u instanceof Professor)
                return true;
        }
        return false;
    }
    public static Student getStudent(int code){
        for (User u:users) {
            if (u.getCode()==code && u instanceof Student)
                return (Student) u;
        }
        return null;
    }
    public static Student getStudent(String username , String password){
        for (User u:users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password) && u instanceof Student)
                return (Student) u;
        }
        return null;
    }
    public static Quiz getQuiz(int code){
        for (Quiz q:quizzes) {
            if (q.getCode()==code)
                return q;
        }
        return null;
    }
    public static boolean removeQuiz(int code){
        Quiz quiz = getQuiz(code);
        if (quiz==null)
            return false;
        quizzes.remove(quiz);
        return true;
    }
    public static boolean removeQuestion(int code){
        Question question = getQuestion(code);
        if (question==null)
            return false;
        questions.remove(question);
        return true;
    }
    public static ArrayList<Professor> getProfessors(){
        ArrayList<Professor> pro = new ArrayList<>();
        for (User user1:users) {
            if (user1 instanceof Professor)
                pro.add((Professor) user1);
        }
        return pro;
    }
    public static ArrayList<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        for (User user1:users) {
            if (user1 instanceof Student)
                students.add((Student) user1);
        }
        return students;
    }
    public static ArrayList<Student> getStudentsCourse(ArrayList<Integer> studentsCode){
        ArrayList<Student> students = new ArrayList<>();
        for (Student s:getStudents()) {
            if (studentsCode.contains(s.getCode()))
                students.add(s);
        }
        return students;
    }
    public static Course getCourse(int code){
        for (Course c:courses) {
            if (c.getCode()==code)
                return c;
        }
        return null;
    }
    public static Course selectCourse(){
        System.out.println("List Courses : ");
        int i=1;
        for (Course c:courses) {
            System.out.println((i++)+"."+c.getCode()+"\t"+ c.getName());
        }
        int itemCourse=0;
        do {
            System.out.print("Please select one of the courses : ");
            itemCourse = input.nextInt();
        }while (itemCourse>courses.size() || itemCourse<1);
        return courses.get(itemCourse-1);
    }
    public static Quiz selectQuiz(Course course) {
        System.out.println("List Quiz : ");
        for (int i = 0; i < course.getQuizzes().size(); i++) {
            Quiz quiz = DataBase.getQuiz(course.getQuizzes().get(i));
            if (quiz!=null)
                System.out.println((i+1) + "." + quiz.getCode());
        }
        int item = 0;
        do {
            System.out.print("Please select one of the quizzes : ");
            item = input.nextInt();
        } while (item > course.getQuizzes().size() || item < 1);
        return getQuiz(course.getQuizzes().get(item - 1));
    }
    public static Question getQuestion(int code){
        for (Question q:questions) {
            if (q.getCode()==code)
                return q;
        }
        return null;
    }
    //////////// File Course //////////////
    public static void saveCourse() throws IOException {
        FileOutputStream f1 = new FileOutputStream("Courses");
        ObjectOutputStream out = new ObjectOutputStream(f1);
        for (Course course:courses) {
            out.writeObject(course);
        }
        f1.close();
    }

    public static void readCourse() throws Exception {
        FileInputStream f = new FileInputStream("Courses");
        ObjectInputStream in = new ObjectInputStream(f);
        try {
            for (;;) {
                courses.add((Course) in.readObject());
                Course.increaseBaseID();
            }
        } catch (EOFException e) {
            // End of stream
        }
        f.close();
    }
    //////////// File Quiz //////////////
    public static void saveQuiz() throws IOException {
        FileOutputStream f1 = new FileOutputStream("Quizzes");
        ObjectOutputStream out = new ObjectOutputStream(f1);
        for (Quiz quiz:quizzes) {
            out.writeObject(quiz);
        }
        f1.close();
    }

    public static void readQuiz() throws Exception {
        FileInputStream f = new FileInputStream("Quizzes");
        ObjectInputStream in = new ObjectInputStream(f);
        try {
            for (;;) {
                quizzes.add((Quiz) in.readObject());
                Quiz.increaseBaseID();
            }
        } catch (EOFException e) {
            // End of stream
        }
        f.close();
    }
    //////////// File Question //////////////
    public static void saveQuestion() throws IOException {
        FileOutputStream f1 = new FileOutputStream("Questions");
        ObjectOutputStream out = new ObjectOutputStream(f1);
        for (Question question:questions) {
            out.writeObject(question);
        }
        f1.close();
    }

    public static void readQuestion() throws Exception {
        FileInputStream f = new FileInputStream("Questions");
        ObjectInputStream in = new ObjectInputStream(f);
        try {
            for (;;) {
                questions.add((Question) in.readObject());
                Question.increaseBaseID();
            }
        } catch (EOFException e) {
            // End of stream
        }
        f.close();
    }
    //////////// File User //////////////
    public static void saveUser() throws IOException {
        FileOutputStream f1 = new FileOutputStream("Users");
        ObjectOutputStream out = new ObjectOutputStream(f1);
        for (User user:users) {
            out.writeObject(user);
        }
        f1.close();
    }

    public static void readUser() throws Exception {
        FileInputStream f = new FileInputStream("Users");
        ObjectInputStream in = new ObjectInputStream(f);
        try {
            for (;;) {
                users.add((User) in.readObject());
                Question.increaseBaseID();
            }
        } catch (EOFException e) {
            // End of stream
        }
        f.close();
    }
}
