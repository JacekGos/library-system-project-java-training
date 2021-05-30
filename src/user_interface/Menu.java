package user_interface;

import java.util.Scanner;

public final class Menu {

    static Scanner myInput = new Scanner( System.in );

    private Menu(){}

    public static void showMenu(){
        System.out.println("Wybierz opcje: ");
        System.out.println("1. Wyszukaj\n" +
                           "2. Wypożycz\n" +
                           "3. Zwróć\n" +
                           "4. Twoje wypożyczenia\n" +
                           "5. Wyloguj\n");

        byte choosedOption = myInput.nextByte();

        chooseMenuOption(choosedOption);

    }
    private static void chooseMenuOption(byte choosedOption){
        System.out.printf("Option: %d%n", choosedOption);
    }

}
