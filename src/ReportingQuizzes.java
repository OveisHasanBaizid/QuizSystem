import java.util.Scanner;

public class ReportingQuizzes {
    Course course;
    Quiz quiz;
    Scanner input = new Scanner(System.in);

    public ReportingQuizzes() {
        course = DataBase.selectCourse();
        quiz = DataBase.getQuiz(course.getCode());
        menu();
    }

    public void menu() {
        System.out.println("* * * Menu CRUD Quiz * * *");
        int item = 0;
        do {
            System.out.println("1.Average All Class");
            System.out.println("3.Show Score Students");
            System.out.println("3.Back");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        } while (item > 3 || item < 1);
        if (item == 3)
            return;
        switch (item) {
            case 1 -> {
                quiz.calculateAverage();
                System.out.println("Average All Class : " + quiz.getAverage());
            }
            case 2 -> showScoreStudents();
        }
        menu();
    }

    public void showScoreStudents() {
        int i = 1;
        for (Integer s : quiz.getStudents()) {
            Student student = DataBase.getStudent(s);
            if (student != null) {
                float score = student.getScoreQuiz(quiz.getCode());
                if (score != -1)
                    System.out.println((i++)+"."+student.getCode()+"\t"+"Absent");
                else
                    System.out.println((i++)+"."+student.getCode()+"\t"+score);
            }
        }
    }
}
