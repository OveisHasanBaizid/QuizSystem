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
            case 2 -> System.out.println();
        }
        menu();
    }
    public void listQuizzes(){
        Course course = DataBase.getCourse(student.getCode());
        int i =0;
        for (Integer integer:course.getQuizzes()) {
            Quiz quiz = DataBase.getQuiz(integer);
            if (student.getScoreQuiz(quiz.getCode())!=-1)
                System.out.println((i++)+"."+quiz.getCode());
        }

    }
}
