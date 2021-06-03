package user_interface;

import java.util.Scanner;

public final class UserMenu implements MenuHelper {

    private static Scanner myInput = new Scanner( System.in );
    private static byte choosedOption = 0;

    private UserMenu(){}

    public static void showUserMenu(){
        System.out.println("_____________________");
        System.out.println("Menu główne: ");
        System.out.println("1. Wyszukaj\n" +
                            "2. Wypożycz\n" +
                            "3. Zwróć\n" +
                            "4. Twoje wypożyczenia\n" +
                            "5. Wyloguj");
        System.out.print("Wybierz opcje: ");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(4);

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
                System.out.println("Błąd!\n ");
        }
    }

    private static void searcherView(){
        System.out.println("Wyszukiwarka:\n ");
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
