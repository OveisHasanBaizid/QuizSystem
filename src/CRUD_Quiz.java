import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Quiz {
    Scanner input = new Scanner(System.in);
    public CRUD_Quiz() {
        menu();
    }
    public void menu(){
        System.out.println("* * * Menu CRUD Quiz * * *");
        int item =0;
        do {
            System.out.println("1.Create");
            System.out.println("2.Edit");
            System.out.println("3.Remove");
            System.out.println("4.Back");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        }while (item>4 || item<1);
        if (item==4)
            return;
        switch (item){
            case 1 -> create();
            case 2 -> edit();
            case 3 -> remove();
        }
        menu();
    }

    public void create(){
        System.out.println("* * * Create Quiz * * *");
        ArrayList<Course> courses = DataBase.courses;
        if (courses.size()==0){
            System.out.println("The list of courses is empty.");
            return;
        }
        System.out.println("List Courses : ");
        int i=1;
        for (Course c:courses) {
            System.out.println((i)+"."+c.getCode()+"\t"+ c.getName());
        }
        int item=0;
        do {
            System.out.print("Please select one of the courses : ");
            item = input.nextInt();
        }while (item>courses.size() || item<1);
        Course course = courses.get(item-1);
        Quiz quiz = new Quiz();
        DataBase.quizzes.add(quiz);
        course.getQuizzes().add(quiz.getCode());
        System.out.println("Professor added successfully.");
    }
    public void edit(){
        System.out.println("* * * Edit Quiz * * *");
    }
    public void remove(){
        System.out.println("* * * Remove Quiz * * *");
        ArrayList<Course> courses = DataBase.courses;

        if (courses.size()==0){
            System.out.println("The list of courses is empty.");
            return;
        }

        System.out.println("List Courses : ");
        int i=1;
        for (Course c:courses) {
            System.out.println((i)+"."+c.getCode()+"\t"+ c.getName());
        }
        int itemCourse=0;
        do {
            System.out.print("Please select one of the courses : ");
            itemCourse = input.nextInt();
        }while (itemCourse>courses.size() || itemCourse<1);

        Course course = courses.get(itemCourse-1);

        if (course.getQuizzes().size()==0){
            System.out.println("The list of quizzes is empty.");
            return;
        }

        System.out.println("List quizzes : ");
        for (int j = 0; j < course.getQuizzes().size() ; j++) {
            System.out.println(j+"."+course.getQuizzes().get(j));
        }
        int itemQuiz=0;
        do {
            System.out.print("Please select one of the quizzes : ");
            itemQuiz = input.nextInt();
        }while (itemQuiz>course.getQuizzes().size() || itemQuiz<1);

        DataBase.removeQuiz(course.getQuizzes().get(itemQuiz-1));
        course.getQuizzes().remove(itemQuiz-1);

        System.out.println("Quiz removed successfully.");
    }
}
