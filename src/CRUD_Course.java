import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Course {
    Scanner input = new Scanner(System.in);
    public CRUD_Course() {
        try {
            menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void menu() throws IOException {
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

    public void create() throws IOException {
        System.out.println("* * * Create Course * * *");
        input.nextLine();
        System.out.print("Name : ");
        String name = input.nextLine();
        System.out.print("Code Professor : ");
        int codeProfessor = input.nextInt();
        Course course = new Course(name , codeProfessor);
        if (!DataBase.addCourse(course))
            System.out.println("There is no professor with this code");
        else{
            DataBase.saveCourse();
            System.out.println("Course added successfully.");
        }
    }
    public void edit() throws IOException {
        System.out.println("* * * Edit Course * * *");
        if (DataBase.courses.size()==0){
            System.out.println("The list of courses is empty.");
            return;
        }
        Course course = DataBase.selectCourse();
        input.nextLine();
        System.out.print("Name : ");
        course.setName(input.nextLine());
        DataBase.saveCourse();
        System.out.println("Course edited successfully.");
    }
    public void remove() throws IOException {
        System.out.println("* * * Remove Course * * *");
        if (DataBase.courses.size()==0){
            System.out.println("The list of courses is empty.");
            return;
        }
        Course course = DataBase.selectCourse();
        DataBase.removeCourse(course);
        DataBase.saveCourse();
        System.out.println("Course removed successfully.");
    }
}
