import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner input = new Scanner(System.in);
    ArrayList<Professor> professors;
    ArrayList<Student> students;
    ArrayList<Quiz> quizzes;
    ArrayList<Course> courses;
    public Main() {
        professors = new ArrayList<>();
        students = new ArrayList<>();
        quizzes = new ArrayList<>();
        courses = new ArrayList<>();
        startMenu();
    }
    public void startMenu(){
        System.out.println("* * * Start Menu * * *");
        int item =0;
        do {
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Exit");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        }while (item>2 || item<1);
        switch (item){
            case 1 -> menuTypeLogin();
            case 2 -> register();
            case 3 -> System.exit(0);
        }
        startMenu();
    }
    public void menuTypeLogin(){
        System.out.println("* * * Menu Type Login * * *");
        int item =0;
        do {
            System.out.println("1.Admin");
            System.out.println("2.Student");
            System.out.println("3.Back");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        }while (item>2 || item<1);
        switch (item){
            case 1 -> loginAdmin();
            case 2 -> loginStudent();
            case 3 -> startMenu();
        }
        menuTypeLogin();
    }
    public void register(){
        System.out.println("* * * Register * * *");

        menuTypeLogin();
    }
    public void loginAdmin(){
        System.out.println("* * * Login Admin * * *");
        String username , password ;
        input.nextLine();
        System.out.print("Username: ");
        username = input.nextLine();

        System.out.print("Password: ");
        password = input.nextLine();

        if (username.equals("Admin") && password.equals("Admin"))
            menuAdmin();
        else
            menuTypeLogin();
    }
    public void menuAdmin(){
        System.out.println("* * * Menu Admin * * *");
        int item =0;
        do {
            System.out.println("1.CRUD Professor");
            System.out.println("2.CRUD Course");
            System.out.println("3.CRUD Student");
            System.out.println("4.CRUD Quiz");
            System.out.println("5.Back");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        }while (item>5 || item<1);
        switch (item){
            case 1 -> new CRUD_Professor(professors);
            case 2 -> new CRUD_Course(courses);
            case 3 -> new CRUD_Student(students);
            case 4 -> new CRUD_Quiz(quizzes);
            case 5 -> menuTypeLogin();
        }
        menuAdmin();
    }
    public void loginStudent(){
        System.out.println("* * * Login Student * * *");

        menuTypeLogin();
    }
    public static void main(String[] args) {
        new Main();
    }
}
