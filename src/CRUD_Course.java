import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Course {
    Scanner input = new Scanner(System.in);
    public CRUD_Course() {
        menu();
    }
    public void menu(){
        System.out.println("* * * Menu CRUD Course * * *");
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
        System.out.println("* * * Create Course * * *");
        System.out.print("Name : ");
        String name = input.nextLine();
        System.out.print("Code Professor : ");
        int codeProfessor = input.nextInt();
        Course course = new Course(name , codeProfessor);
        if (!DataBase.addCourse(course))
            System.out.println("There is no professor with this code");
        else
            System.out.println("Course added successfully.");
    }
    public void edit(){
        System.out.println("* * * Edit Course * * *");
        ArrayList<Course> courses = DataBase.courses;
        if (courses.size()==0){
            System.out.println("The list of courses is empty.");
            return;
        }
        int i=1;
        for (Course c:courses) {
            System.out.println((i)+"."+c.getCode()+"\t"+ c.getName());
        }
        int item=0;
        do {
            System.out.print("Please select one of the courses : ");
            item = input.nextInt();
        }while (item>courses.size() || item<1);
        input.nextLine();
        System.out.print("Name : ");
        courses.get(item-1).setName(input.nextLine());
        System.out.println("Course edited successfully.");
    }
    public void remove(){
        System.out.println("* * * Remove Course * * *");
        ArrayList<Course> courses = DataBase.courses;
        if (courses.size()==0){
            System.out.println("The list of courses is empty.");
            return;
        }
        int i=1;
        for (Course c:courses) {
            System.out.println((i)+"."+c.getCode()+"\t"+ c.getName());
        }
        int item=0;
        do {
            System.out.print("Please select one of the courses : ");
            item = input.nextInt();
        }while (item>courses.size() || item<1);
        DataBase.removeCourse(courses.get(item-1));
    }
}
