import java.util.Scanner;

public class Main {
    Scanner input = new Scanner(System.in);

    public Main() {
        startMenu();
    }

    public void startMenu() {
        System.out.println("* * * Start Menu * * *");
        int item = 0;
        do {
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Exit");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        } while (item > 3 || item < 1);
        switch (item) {
            case 1 -> menuTypeLogin();
            case 2 -> register();
            case 3 -> System.exit(0);
        }
        startMenu();
    }

    public void menuTypeLogin() {
        System.out.println("* * * Menu Type Login * * *");
        int item = 0;
        do {
            System.out.println("1.Admin");
            System.out.println("2.Student");
            System.out.println("3.Back");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        } while (item > 3 || item < 1);
        switch (item) {
            case 1 -> loginAdmin();
            case 2 -> loginStudent();
            case 3 -> startMenu();
        }
        menuTypeLogin();
    }

    public void register() {
        System.out.println("* * * Register * * *");

        menuTypeLogin();
    }

    public void loginAdmin() {
        System.out.println("* * * Login Admin * * *");
        String username, password;
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

    public void menuAdmin() {
        System.out.println("* * * Menu Admin * * *");
        int item = 0;
        do {
            System.out.println("1.CRUD Professor");
            System.out.println("2.CRUD Course");
            System.out.println("3.CRUD Student");
            System.out.println("4.CRUD Quiz");
            System.out.println("5.CRUD Questions");
            System.out.println("6.Reporting Quizzes");
            System.out.println("7.Reporting Courses");
            System.out.println("8.Back");
            System.out.print("Please select one of the options : ");
            item = input.nextInt();
        } while (item > 8 || item < 1);
        switch (item) {
            case 1 -> new CRUD_Professor();
            case 2 -> new CRUD_Course();
            case 3 -> new CRUD_Student();
            case 4 -> new CRUD_Quiz();
            case 5 -> new CRUD_Question();
            case 6 -> new ReportingQuizzes();
            case 7 -> ReportingAllQuizzes();
            case 8 -> menuTypeLogin();
        }
        menuAdmin();
    }

    public void ReportingAllQuizzes() {
        Course course = DataBase.selectCourse();
        if (course.getQuizzes().size()==0){
            System.out.println("The list of quizzes is empty.");
            return;
        }
        int i = 0;
        for (Integer integer : course.getQuizzes()) {
            Quiz quiz = DataBase.getQuiz(integer);
            if (quiz != null)
                System.out.println((i++) + "." + quiz.getCode() + "\tN: " + quiz.getStudents().size()
                        + "\t" +"NP: "+ quiz.studentsParticipating()+"\tAverage: "+ quiz.getAverage());
        }
    }

    public void loginStudent() {
        System.out.println("* * * Login Student * * *");
        String username, password;
        input.nextLine();
        System.out.print("Username: ");
        username = input.nextLine();

        System.out.print("Password: ");
        password = input.nextLine();

        Student student = DataBase.getStudent(username,password);
        if (student==null){
            System.out.println("The information entered is incorrect.");
            menuTypeLogin();
        }else
            new MenuStudent(student);
    }

    public static void main(String[] args) {
        new Main();
    }
}
