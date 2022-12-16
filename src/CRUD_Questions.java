import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Questions {
    Scanner input = new Scanner(System.in);
    Course course;
    Quiz quiz;

    public CRUD_Questions() {
        course = DataBase.selectCourse();
        quiz = selectQuiz();
        menu();
    }

    public Quiz selectQuiz() {
        System.out.println("List Quiz : ");
        for (int i = 0; i < course.getQuizzes().size(); i++) {
            Quiz quiz = new Quiz();
            System.out.println((i) + "." + quiz.getCode());
        }
        int itemQuiz = 0;
        do {
            System.out.print("Please select one of the courses : ");
            itemQuiz = input.nextInt();
        } while (itemQuiz > course.getQuizzes().size() || itemQuiz < 1);
        return DataBase.getQuiz(course.getQuizzes().get(itemQuiz - 1));
    }

    public void menu() {
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

    public void create() {
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
        System.out.println("Question added successfully.");
    }

    public void edit() {
        System.out.println("* * * Edit Question * * *");
        if (quiz.getQuestions().size()==0){
            System.out.println("The list of questions is empty.");
            return;
        }
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question question = DataBase.getQuestion(quiz.getQuestions().get(i));
            System.out.println((i) + "." + question.getText());
        }
        int itemQuestion = 0;
        do {
            System.out.print("Please select one of the questions : ");
            itemQuestion = input.nextInt();
        } while (itemQuestion > quiz.getQuestions().size() || itemQuestion < 1);

        Question question = DataBase.getQuestion(quiz.getQuestions().get(itemQuestion-1));

        input.nextLine();

        System.out.print("Text : ");
        question.setText(input.nextLine());
        System.out.print("Answer : ");
        question.setAnswer(input.nextLine());
        System.out.print("Score : ");
        question.setScore(input.nextFloat());

        System.out.println("Question edited successfully.");

    }

    public void remove() {
        System.out.println("* * * Remove Question * * *");
        if (quiz.getQuestions().size()==0){
            System.out.println("The list of questions is empty.");
            return;
        }
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question question = DataBase.getQuestion(quiz.getQuestions().get(i));
            System.out.println((i) + "." + question.getText());
        }
        int itemQuestion = 0;
        do {
            System.out.print("Please select one of the questions : ");
            itemQuestion = input.nextInt();
        } while (itemQuestion > quiz.getQuestions().size() || itemQuestion < 1);
        DataBase.removeQuestion(quiz.getQuestions().get(itemQuestion));
        quiz.getQuestions().remove(itemQuestion-1);
        System.out.println("Question removed successfully.");
    }
}
