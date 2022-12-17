import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CRUD_Professor {
    Scanner input = new Scanner(System.in);
    public CRUD_Professor() {
        try {
            menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void menu() throws IOException {
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

    public void create() throws IOException {
        System.out.println("* * * Create Professor * * *");
        input.nextLine();
        System.out.print("Name : ");
        String name = input.nextLine();
        System.out.print("username : ");
        String username = input.nextLine();
        System.out.print("password : ");
        String password = input.nextLine();
        if (!DataBase.addUser(new Professor(name,username,password)))
            System.out.println("The username entered is duplicate.");
        else{
            DataBase.saveUser();
            System.out.println("Professor added successfully.");
        }
    }
    public void edit() throws IOException {
        System.out.println("* * * Edit Professor * * *");
        if (DataBase.getProfessors().size()==0){
            System.out.println("The list of professors is empty.");
            return ;
        }
        Professor professor = selectProfessor();
        input.nextLine();
        System.out.print("Name : ");
        professor.setName(input.nextLine());
        System.out.print("Password : ");
        professor.setPassword(input.nextLine());
        DataBase.saveUser();
        System.out.println("Professor edited successfully.");
    }
    public void remove() throws IOException {
        System.out.println("* * * Remove Professor * * *");
        if (DataBase.getProfessors().size()==0){
            System.out.println("The list of professors is empty.");
            return ;
        }
        Professor professor = selectProfessor();
        DataBase.removeUser(professor);
        DataBase.saveUser();
        System.out.println("Professor removed successfully.");
    }
    public Professor selectProfessor(){
        ArrayList<Professor> professors = DataBase.getProfessors();
        int i=1;
        for (Professor p:professors) {
            System.out.println((i++)+"."+p.getName());
        }
        int item=0;
        do {
            System.out.print("Please select one of the professors : ");
            item = input.nextInt();
        }while (item>professors.size() || item<1);
        return professors.get(item-1);
    }
}
