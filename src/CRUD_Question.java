import java.io.IOException;
import java.util.Scanner;

public class CRUD_Question {
    Scanner input = new Scanner(System.in);
    Course course;
    Quiz quiz;

    public CRUD_Question() {
        course = DataBase.selectCourse();
        if (course.getQuizzes().size()==0){
            System.out.println("The list of questions is empty.");
            return;
        }
        quiz = DataBase.selectQuiz(course);
        try {
            menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void menu() throws IOException {
        System.out.println("* * * Menu CRUD Questions * * *");
        int item = 0;
        do {
            System.out.println("1.Create");
            System.out.println("2.Edit");
            System.out.println("3.Remove");
            System.out.println("4.Back");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        } while (item > 4 || item < 1);
        if (item == 4)
            return;
        switch (item) {
            case 1 -> create();
            case 2 -> edit();
            case 3 -> remove();
        }
        menu();
    }

    public void create() throws IOException {
        System.out.println("* * * Create Question * * *");
        input.nextLine();
        System.out.print("Text : ");
        String text = input.nextLine();
        System.out.print("Answer : ");
        String answer = input.nextLine();
        System.out.print("Score : ");
        float score = input.nextFloat();
        Question question = new Question(text, answer, score);
        quiz.getQuestions().add(question.getCode());
        DataBase.saveQuestion();
        DataBase.saveQuiz();
        System.out.println("Question added successfully.");
    }

    public void edit() throws IOException {
        System.out.println("* * * Edit Question * * *");
        Question question = selectQuestion();

        input.nextLine();

        System.out.print("Text : ");
        question.setText(input.nextLine());
        System.out.print("Answer : ");
        question.setAnswer(input.nextLine());
        System.out.print("Score : ");
        question.setScore(input.nextFloat());
        DataBase.saveQuestion();
        System.out.println("Question edited successfully.");
    }

    public void remove() throws IOException {
        System.out.println("* * * Remove Question * * *");
        if (quiz.getQuestions().size()==0){
            System.out.println("The list of questions is empty.");
            return;
        }
        Question question = selectQuestion();
        quiz.getQuestions().remove(question.getCode());
        DataBase.questions.remove(question);
        DataBase.saveQuestion();
        DataBase.saveQuiz();
        System.out.println("Question removed successfully.");
    }
    public Question selectQuestion(){
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question question = DataBase.getQuestion(quiz.getQuestions().get(i));
            System.out.println((i) + "." + question.getText());
        }
        int itemQuestion = 0;
        do {
            System.out.print("Please select one of the questions : ");
            itemQuestion = input.nextInt();
        } while (itemQuestion > quiz.getQuestions().size() || itemQuestion < 1);
        return DataBase.getQuestion(quiz.getQuestions().get(itemQuestion-1));
    }
}
