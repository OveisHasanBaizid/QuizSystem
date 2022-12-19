import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Quiz {
    Scanner input = new Scanner(System.in);
    Course course;

    public CRUD_Quiz() {
        if (DataBase.courses.size() == 0) {
            System.out.println("The list of course is empty.");
            return;
        }
        course = DataBase.selectCourse();

        try {
            menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void menu() throws IOException {
        System.out.println("* * * Menu CRUD Quiz * * *");
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
        System.out.println("* * * Create Quiz * * *");
        Quiz quiz = new Quiz();
        DataBase.quizzes.add(quiz);
        course.getQuizzes().add(quiz.getCode());
        DataBase.saveQuiz();
        DataBase.saveCourse();
        System.out.println("Quiz added successfully.");
    }

    public void edit() {
        System.out.println("* * * Edit Quiz * * *");
        System.out.println("The quiz cannot be edited.");
    }

    public void remove() throws IOException {
        System.out.println("* * * Remove Quiz * * *");
        if (course.getQuizzes().size()==0){
            System.out.println("The list of quizzes is empty.");
            return;
        }
        Quiz quiz = DataBase.selectQuiz(course);
        DataBase.removeQuiz(quiz.getCode());
        course.getQuizzes().remove(Integer.valueOf(quiz.getCode()));
        DataBase.saveQuiz();
        DataBase.saveCourse();
        System.out.println("Quiz removed successfully.");
    }
}
