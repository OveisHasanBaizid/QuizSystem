import java.io.IOException;
import java.util.Scanner;

public class MenuStudent {
    Student student;
    Scanner input = new Scanner(System.in);
    public MenuStudent(Student student) {
        this.student = student;
        try {
            menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void menu() throws IOException {
        System.out.println("* * * Menu Student * * *");
        int item = 0;
        do {
            System.out.println("1.List quizzes");
            System.out.println("2.History quizzes");
            System.out.println("3.Back");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        } while (item > 3 || item < 1);
        if (item==3)
            return;
        switch (item) {
            case 1 -> listQuizzes();
            case 2 -> historyQuizzes();
        }
        menu();
    }
    public void listQuizzes() throws IOException {
        Course course = DataBase.getCourse(student.getCourse());
        if (course== null || course.getQuizzes().size()==0 || student.getReportQuizzes().size() == course.getQuizzes().size()){
            System.out.println("The list of quizzes is empty.");
            return;
        }
        Quiz quiz = DataBase.selectQuiz(course);
        if (student.completedQuizzes(quiz.getCode())){
            System.out.println("This quiz has already been done.");
            return;
        }
        startQuiz(quiz);
    }
    public void startQuiz(Quiz quiz) throws IOException {
        System.out.println("* * * Start Quiz * * *");
        if (quiz.getQuestions().size()==0){
            System.out.println("The list of questions in this quiz is empty");
            return;
        }
        String answer="";
        input.nextLine();
        float score = 0;
        for (int i = 0; i < quiz.getQuestions().size() ; i++) {
            Question question = DataBase.getQuestion(quiz.getQuestions().get(i));
            if (question!=null){
                System.out.println((i+1)+" of "+quiz.getQuestions().size()+" . "+question.getText());
                System.out.print("Answer : ");
                answer = input.nextLine();
                if (question.getAnswer().compareToIgnoreCase(answer)==0)
                    score+=question.getScore();
            }
        }
        student.getReportQuizzes().add(new ReportQuiz(quiz.getCode(),score));
        quiz.getStudents().add(student.getCode());
        DataBase.saveQuiz();
        DataBase.saveUser();
        System.out.println("Quiz score : "+score);
    }
    public void historyQuizzes(){
        Course course = DataBase.getCourse(student.getCourse());
        if (course==null || course.getQuizzes().size()==0){
            System.out.println("The list of history quizzes is empty.");
            return;
        }
        int i =1;
        for (Integer integer:course.getQuizzes()) {
            Quiz quiz = DataBase.getQuiz(integer);
            if (quiz!=null ){
                quiz.calculateAverage();
                float score = student.getScoreQuiz(quiz.getCode());
                System.out.println((i++)+".\tCode: "+quiz.getCode()+"\tScore: "
                        +(score==-1.0f?"Absent":score+"") + "\tAverage: "+ quiz.getAverage());
            }
        }
    }
}
