import java.util.ArrayList;

public class Quiz {
    private int baseID = 1;
    private int code;
    private ArrayList<Integer> questions;
    private ArrayList<Integer> students;
    private float average;

    public Quiz() {
        this.code = baseID++;
        this.questions = new ArrayList<>();
        this.students = new ArrayList<>();
        average = 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<Integer> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Integer> questions) {
        this.questions = questions;
    }

    public ArrayList<Integer> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Integer> students) {
        this.students = students;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
