import java.util.ArrayList;

public class DataBase {
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Quiz> quizzes = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    public DataBase() {

    }
    public static boolean addProfessor(Professor professor){
        if (existUser(professor))
            return false;
        users.add(professor);
        return true;
    }
    public static boolean removeProfessor(Professor professor){
        if (!existUser(professor))
            return false;
        users.remove(professor);
        return true;
    }
    public static boolean existUser(User user){
        for (User user1:users) {
            if (user.getUsername().equals(user1.getUsername()))
                return true;
        }
        return false;
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
