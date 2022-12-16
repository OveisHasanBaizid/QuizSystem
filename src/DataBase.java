import java.util.ArrayList;
import java.util.Scanner;

public class DataBase {
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
        if (existProfessor(course.getProfessor()))
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
    public static Course selectCourse(){
        Scanner input = new Scanner(System.in);
        System.out.println("List Courses : ");
        int i=1;
        for (Course c:courses) {
            System.out.println((i)+"."+c.getCode()+"\t"+ c.getName());
        }
        int itemCourse=0;
        do {
            System.out.print("Please select one of the courses : ");
            itemCourse = input.nextInt();
        }while (itemCourse>courses.size() || itemCourse<1);
        return courses.get(itemCourse-1);
    }
    public static Question getQuestion(int code){
        for (Question q:questions) {
            if (q.getCode()==code)
                return q;
        }
        return null;
    }
}
