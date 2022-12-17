import java.util.Scanner;

public class MenuStudent {
    Student student;
    Scanner input = new Scanner(System.in);
    public MenuStudent(Student student) {
        this.student = student;
        menu();
    }
    public void menu(){
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
    public void listQuizzes(){
        Course course = DataBase.getCourse(student.getCode());
        if (course.getQuizzes().size()==0){
            System.out.println("The list of quizzes is empty.");
            return;
        }
        int i =0;
        for (Integer integer:course.getQuizzes()) {
            Quiz quiz = DataBase.getQuiz(integer);
            if (student.getScoreQuiz(quiz.getCode())!=-1)
                System.out.println((i++)+"."+quiz.getCode());
        }
        Quiz quiz;
        do {
            System.out.print("Please enter code  one of the quizzes : ");
            quiz = DataBase.getQuiz(input.nextInt());
        } while (quiz==null);
        startQuiz(quiz);
    }
    public void startQuiz(Quiz quiz){
        System.out.println("* * * Start Quiz * * *");
        if (quiz.getQuestions().size()==0){
            System.out.println("The list of questions in this quiz is empty");
            return;
        }
        String answer="";
        float score = 0;
        for (int i = 0; i < quiz.getQuestions().size() ; i++) {
            Question question = DataBase.getQuestion(quiz.getQuestions().get(i));
            System.out.println((i+1)+" of "+quiz.getQuestions().size()+" . "+question.getText());
            System.out.print("Answer : ");
            answer = input.nextLine();
            if (question.getText().compareToIgnoreCase(answer)==0)
                score+=question.getScore();
        }
        student.getReportQuizzes().add(new ReportQuiz(quiz.getCode(),score));
        System.out.println("Quiz score : "+score);
    }
    public void historyQuizzes(){
        Course course = DataBase.getCourse(student.getCode());
        if (course.getQuizzes().size()==0){
            System.out.println("The list of quizzes is empty.");
            return;
        }
        int i =1;
        for (Integer integer:course.getQuizzes()) {
            Quiz quiz = DataBase.getQuiz(integer);
            if (quiz!=null){
                quiz.calculateAverage();
                float score = student.getScoreQuiz(quiz.getCode());
                System.out.println((i++)+"."+quiz.getCode()+"\tScore: "
                        +(score==-1.0f?"Absent":score+"") + "\tAverage: "+ quiz.getAverage());
            }
        }
    }
}
