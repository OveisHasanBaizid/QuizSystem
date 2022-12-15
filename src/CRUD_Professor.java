import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Professor {
    Scanner input = new Scanner(System.in);
    public CRUD_Professor() {
        menu();
    }
    public void menu(){
        System.out.println("* * * Menu CRUD Professor * * *");
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
        System.out.println("* * * Create Professor * * *");
        input.nextLine();
        System.out.print("Name : ");
        String name = input.nextLine();
        System.out.print("username : ");
        String username = input.nextLine();
        System.out.print("password : ");
        String password = input.nextLine();
        if (DataBase.addUser(new Professor(name,username,password)))
            System.out.println("The username entered is duplicate.");
        else
            System.out.println("Professor added successfully.");
    }
    public void edit(){
        System.out.println("* * * Edit Professor * * *");
        ArrayList<Professor> professors = DataBase.getProfessors();
        if (professors.size()==0){
            System.out.println("The list of professors is empty.");
            return;
        }
        int i=1;
        for (Professor p:professors) {
            System.out.println((i)+"."+p.getName());
        }
        int item=0;
        do {
            System.out.print("Please select one of the professors : ");
            item = input.nextInt();
        }while (item>professors.size() || item<1);
        input.nextLine();
        System.out.print("Name : ");
        professors.get(item-1).setName(input.nextLine());
        System.out.print("Password : ");
        professors.get(item-1).setPassword(input.nextLine());

        System.out.println("Professor edited successfully.");
    }
    public void remove(){
        System.out.println("* * * Remove Professor * * *");
        ArrayList<Professor> professors = DataBase.getProfessors();
        if (professors.size()==0){
            System.out.println("The list of professors is empty.");
            return;
        }
        int i=1;
        for (Professor p:professors) {
            System.out.println((i)+"."+p.getName());
        }
        int item=0;
        do {
            System.out.print("Please select one of the professors : ");
            item = input.nextInt();
        }while (item>professors.size() || item<1);
        DataBase.removeUser(professors.get(item-1));
        System.out.println("Professor removed successfully.");
    }
}
