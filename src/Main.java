import java.io.IOException;
import java.util.Scanner;

public class Main implements java.io.Serializable{



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Menu menu = new Menu();
        Menu.DisplayInitialMainMenu();
        menu.UserChoice();

    }



}
