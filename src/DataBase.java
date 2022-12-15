import java.util.ArrayList;

public class DataBase {
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Quiz> quizzes = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
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
    public static ArrayList<Professor> getProfessors(){
        ArrayList<Professor> pro = new ArrayList<>();
        for (User user1:users) {
            if (user1 instanceof Professor)
                pro.add((Professor) user1);
        }
        return pro;
    }
}
