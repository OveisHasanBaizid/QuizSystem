import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private static int baseID = 1;
    private int code;
    private ArrayList<Integer> questions;
    private ArrayList<Integer> students;
    private float average;

    public Quiz() {
        this.code = baseID++;
        this.questions = new ArrayList<>();
        this.students = new ArrayList<>();
        this.average = 0;
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
    public void calculateAverage(){
        float sum = 0;
        int counter=0;
        for (Integer s: students){
            Student student = DataBase.getStudent(s);
            if (student!=null){
                float score = student.getScoreQuiz(getCode());
                if (score!=-1){
                    sum+=score;
                    counter++;
                }
            }
        }
        sum/=counter;
        this.average = sum;
    }
    public int studentsParticipating(){
        int counter=0;
        for (Integer s: students){
            Student student = DataBase.getStudent(s);
            if (student!=null){
                float score = student.getScoreQuiz(getCode());
                if (score!=-1){
                    counter++;
                }
            }
        }
        return counter;
    }
    static void increaseBaseID(){
        baseID++;
    }
}
