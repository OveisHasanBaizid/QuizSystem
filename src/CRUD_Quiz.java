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
    }
    public void edit(){
        System.out.println("* * * Edit Quiz * * *");
    }
    public void remove(){
        System.out.println("* * * Remove Quiz * * *");
    }
}
