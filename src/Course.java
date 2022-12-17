import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private static int baseID = 1;
    private int code;
    private String name;
    private int professor;
    private ArrayList<Integer> students;
    private ArrayList<Integer> quizzes;

    public Course(String name, int professor) {
        this.code = baseID++;
        this.name = name;
        this.professor = professor;
        students = new ArrayList<>();
        quizzes = new ArrayList<>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }

    public ArrayList<Integer> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Integer> students) {
        this.students = students;
    }

    public ArrayList<Integer> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Integer> quizzes) {
        this.quizzes = quizzes;
    }

    static void increaseBaseID(){
        baseID++;
    }
}
