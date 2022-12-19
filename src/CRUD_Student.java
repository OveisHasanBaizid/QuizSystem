import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Student {
    Scanner input = new Scanner(System.in);
    public CRUD_Student() {
        try {
            menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void menu() throws IOException {
        System.out.println("* * * Menu CRUD Student * * *");
        int item;
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

        if (!DataBase.addUser(student))
            System.out.println("The username entered is duplicate.");
        else {
            course.getStudents().add(student.getCode());
            DataBase.saveUser();
            DataBase.saveCourse();
            System.out.println("Student added successfully.");
        }
    }
    public void edit() throws IOException {
        System.out.println("* * * Edit Student * * *");
        Course course = DataBase.selectCourse();
        if (course.getStudents().size()==0){
            System.out.println("The list of students is empty.");
            return ;
        }
        Student student = selectStudent(course);
        input.nextLine();

        System.out.print("Name : ");
        student.setName(input.nextLine());
        System.out.print("Password : ");
        student.setPassword(input.nextLine());
        DataBase.saveUser();
        System.out.println("Student edited successfully.");
    }
    public void remove() throws IOException {
        System.out.println("* * * Remove Student * * *");
        Course course = DataBase.selectCourse();
        if (course.getStudents().size()==0){
            System.out.println("The list of students is empty.");
            return ;
        }
        Student student = selectStudent(course);

        course.getStudents().remove(student.getCode());
        DataBase.removeUser(student);
        DataBase.saveUser();
        DataBase.saveCourse();
        System.out.println("Student removed successfully.");
    }
    public Student selectStudent(Course course){
        ArrayList<Student> studentsCourse = DataBase.getStudentsCourse(course.getStudents());
        int i=1;
        for (Student s:studentsCourse) {
            System.out.println((i)+"."+s.getName());
        }
        int item=0;
        do {
            System.out.print("Please select one of the students : ");
            item = input.nextInt();
        }while (item>studentsCourse.size() || item<1);
        return studentsCourse.get(item-1);
    }
}
