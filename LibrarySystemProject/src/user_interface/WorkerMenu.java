package user_interface;

import classes.LibraryWorker;
import data_access.LibraryWorkerDataAccess;

import java.util.Scanner;

public class WorkerMenu implements MenuHelper{



    private static Scanner myInput = new Scanner( System.in );
    private static byte choosedOption = 0;

    public static void showWorkerMenu(LibraryWorker libraryWorker){
        System.out.println("_____________________");
        System.out.println("Witaj " + libraryWorker.getUserName());
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

        chooseWorkerMenuOption(choosedOption, libraryWorker);

    }

    private static void chooseWorkerMenuOption(byte choosedOption, LibraryWorker libraryWorker){

        switch (choosedOption){
            case 1:
                createAccountView(libraryWorker);
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
                showWorkerMenu(libraryWorker);
        }
    }

    private static void createAccountView(LibraryWorker libraryWorker){
        System.out.println("_____________________");
        System.out.println("Tworzenie konta użytkownika:\n" +
                "1. Rozpocznij kreator konta\n" +
                "2. Powrót");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                userAccountCreator(libraryWorker);
                break;
            case 2:
                showWorkerMenu(libraryWorker);
                break;
        }

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

    public static void userAccountCreator(LibraryWorker libraryWorker) {

        int accountType = 2;
        String name = null;
        String surName = null;

        System.out.println("_____________________");
        System.out.println("Kreator konta: ");
        System.out.print("1. Pracownik biblioteki\n" +
                        "2. Użytkownik biblioteki\n" +
                        "Podaj typ konta: ");
        accountType = MenuHelper.checkChoosedOptionValidation(2);

        System.out.print("Imie: ");
        name = MenuHelper.formatName();

        System.out.print("Nazwisko: ");
        surName = MenuHelper.formatName();

        if (accountType == 1) {

            String login = MenuHelper.loginCreator(name, surName, accountType);
            LibraryWorker libraryWorkerToCreate = new LibraryWorker(0, name, surName, login, "password", 1);

            LibraryWorkerDataAccess.insertLibraryWorker(libraryWorkerToCreate);

        } else if (accountType == 2) {

        } else {
            System.out.println("Nastąpił błąd!");
            WorkerMenu.createAccountView(libraryWorker);
        }

    }

    public int createNewLibraryWorker(LibraryWorker libraryWorker) {
        return 0;
    }

}
