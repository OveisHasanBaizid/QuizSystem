import java.util.ArrayList;

public class Student extends User{
    private String name;
    private int course;
    private ArrayList<ReportQuiz> reportQuizzes;
    public Student(String username, String password, String name, int course) {
        super(username, password);
        this.name = name;
        this.course = course;
        reportQuizzes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public ArrayList<ReportQuiz> getReportQuizzes() {
        return reportQuizzes;
    }

    public void setReportQuizzes(ArrayList<ReportQuiz> reportQuizzes) {
        this.reportQuizzes = reportQuizzes;
    }
    public float getScoreQuiz(int code){
        for (ReportQuiz r:reportQuizzes) {
            if (r.getCodeQuiz()==code)
                return r.getScore();
        }
        return -1;
    }
}
