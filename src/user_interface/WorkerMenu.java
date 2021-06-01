package user_interface;

import java.util.Scanner;

public class WorkerMenu implements MenuHelper{

    private static Scanner myInput = new Scanner( System.in );
    private static byte choosedOption = 0;

    public static void showWorkerMenu(){
        System.out.println("_____________________");
        System.out.println("Menu główne: ");
        System.out.println("1. Stwórz nowe konto\n" +
                            "2. Usuń konto\n" +
                            "3. Wyszukaj użytkownika\n" +
                            "4. Wypożyczenia\n" +
                            "5. Dodaj pozycje\n" +
                            "6. Usuń pozycje\n" +
                            "7. Wyszukaj pozycje\n" +
                            "8. Wyloguj");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(7);

        chooseWorkerMenuOption(choosedOption);

    }

    private static void chooseWorkerMenuOption(byte choosedOption){

        switch (choosedOption){
            case 1:
                createAccountView();
                break;
            case 2:
                deleteAccountView();
                break;
            case 3:
                findUserView();
                break;
            case 4:
                borrowingsView();
                break;
            case 5:
                addLibraryElementView();
                break;
            case 6:
                removeLibraryElementView();
                break;
            case 7:
                findLibraryElementView();
                break;
            default:
                System.out.println("Błąd!\n ");
                showWorkerMenu();
        }
    }

    private static void createAccountView(){
        System.out.println("_____________________");

    }

    private static void deleteAccountView(){
        System.out.println("_____________________");

    }

    private static void findUserView(){
        System.out.println("_____________________");

    }

    private static void borrowingsView(){
        System.out.println("_____________________");

    }

    private static void addLibraryElementView(){
        System.out.println("_____________________");

    }

    private static void removeLibraryElementView(){
        System.out.println("_____________________");

    }

    private static void findLibraryElementView(){
        System.out.println("_____________________");

    }



}
