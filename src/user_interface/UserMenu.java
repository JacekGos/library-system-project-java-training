package user_interface;

import java.util.Scanner;

public final class UserMenu implements MenuHelper {

    private static Scanner myInput = new Scanner( System.in );
    private static byte choosedOption = 0;

    private UserMenu(){}

    public static void logOnPanel(){

        String login = null;
        String password = null;

        System.out.println("Witaj!");

        System.out.print("Login: ");
        login = myInput.next();

        System.out.print("Password: ");
        password = myInput.next();
    }

    public static void showUserMenu(){
        System.out.println("Wybierz opcje: ");
        System.out.println("1. Wyszukaj\n" +
                           "2. Wypożycz\n" +
                           "3. Zwróć\n" +
                           "4. Twoje wypożyczenia\n" +
                           "5. Wyloguj");
        try{
            choosedOption = myInput.nextByte();
        }catch (Exception e){
            System.out.println("Nastąpił błąd...");
        }

        //choosedOption = myInput.nextByte();
        System.out.println(MenuHelper.checkOptionValidation(choosedOption));

        chooseUserMenuOption(choosedOption);

    }
    private static void chooseUserMenuOption(byte choosedOption){

        switch (choosedOption){
            case 1:
                searcherView();
                break;
            case 2:
                borrowingView();
                break;
            case 3:
                returningView();
                break;
            case 4:
                userBorrowingsView();
                break;
            default:
                System.out.printf("Błąd!\n ");
        }
    }

    private static void searcherView(){
        System.out.printf("Wyszukiwarka:\n ");
    }

    private static void borrowingView(){
        System.out.printf("Wypożyczalnia:\n ");
    }

    private static void returningView(){
        System.out.printf("Zwróć pozycję:\n ");
    }

    private static void userBorrowingsView(){
        System.out.printf("Twoje wypożyczenia:\n ");
    }
}
