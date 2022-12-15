import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Student {
    Scanner input = new Scanner(System.in);
    public CRUD_Student() {
        menu();
    }
    public void menu(){
        System.out.println("* * * Menu CRUD Student * * *");
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
        System.out.println("* * * Create Student * * *");
        input.nextLine();
        System.out.print("Name : ");
        String name = input.nextLine();
        System.out.print("username : ");
        String username = input.nextLine();
        System.out.print("password : ");
        String password = input.nextLine();

        Course course = DataBase.selectCourse();

        Student student = new Student(username,password,name,course.getCode());

        if (DataBase.addUser(student))
            System.out.println("The username entered is duplicate.");
        else {
            course.getStudents().add(student.getCode());
            System.out.println("Professor added successfully.");
        }
    }
    public void edit(){
        System.out.println("* * * Edit Student * * *");
        Course course = DataBase.selectCourse();

        ArrayList<Student> studentsCourse = DataBase.getStudentsCourse(course.getStudents());

        int i=1;
        for (Student s:studentsCourse) {
            System.out.println((i)+"."+s.getName());
        }
        int item=0;
        do {
            System.out.print("Please select one of the professors : ");
            item = input.nextInt();
        }while (item>studentsCourse.size() || item<1);
        input.nextLine();

        System.out.print("Name : ");
        studentsCourse.get(item-1).setName(input.nextLine());
        System.out.print("Password : ");
        studentsCourse.get(item-1).setPassword(input.nextLine());

        System.out.println("Student edited successfully.");

    }
    public void remove(){
        System.out.println("* * * Remove Student * * *");
        Course course = DataBase.selectCourse();

        ArrayList<Student> studentsCourse = DataBase.getStudentsCourse(course.getStudents());

        int i=1;
        for (Student s:studentsCourse) {
            System.out.println((i)+"."+s.getName());
        }
        int item=0;
        do {
            System.out.print("Please select one of the professors : ");
            item = input.nextInt();
        }while (item>studentsCourse.size() || item<1);
        input.nextLine();

        Student student = studentsCourse.get(item-1);

        course.getStudents().remove(student.getCode());
        DataBase.removeUser(student);
        System.out.println("Professor removed successfully.");
    }
}
