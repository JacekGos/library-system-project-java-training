package user_interface;

import classes.LibraryWorker;
import data_access.LibraryWorkerDataAccess;

import java.util.ArrayList;
import java.util.List;
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
        System.out.print("Wybierz opcje: ");
        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(7);

        chooseWorkerMenuOption(choosedOption, libraryWorker);

    }

    private static void chooseWorkerMenuOption(byte choosedOption, LibraryWorker libraryWorker){

        switch (choosedOption){
            case 1:
                createAccountView(libraryWorker);
                break;
            case 2:
                deleteAccountView(libraryWorker);
                break;
            case 3:
                findUserView(libraryWorker);
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
        System.out.print("Wybierz opcje: ");
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

    private static void deleteAccountView(LibraryWorker libraryWorker){
        System.out.println("_____________________");
        System.out.println("Usuwanie konta użytkownika:\n" +
                "1. Rozpocznij usuwanie konta\n" +
                "2. Powrót");
        System.out.print("Wybierz opcje: ");
        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                userAccountRemover(libraryWorker);
                break;
            case 2:
                showWorkerMenu(libraryWorker);
                break;
        }
    }

    private static void findUserView(LibraryWorker libraryWorker){

        System.out.println("Wyszukiwarka użytkowników:\n" +
                "1. Wyszukaj użytkownika\n" +
                "2. Powrót");
        System.out.print("Wybierz opcje: ");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                userSearcher(libraryWorker);
                break;
            case 2:
                showWorkerMenu(libraryWorker);
                break;
        }




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
        int status = 0;
        String name = null;
        String surName = null;

        System.out.println("_____________________");
        System.out.println("Kreator konta: ");
        System.out.print("1. Pracownik\n" +
                        "2. Użytkownik\n");
        System.out.print("Wybierz opcje: ");
        accountType = MenuHelper.checkChoosedOptionValidation(2);

        System.out.print("Imie: ");
        name = MenuHelper.formatName();

        System.out.print("Nazwisko: ");
        surName = MenuHelper.formatName();

        if (accountType == 1) {

            String login = MenuHelper.loginCreator(name, surName, accountType);
            LibraryWorker libraryWorkerToCreate = new LibraryWorker(0, name, surName, login, "password", 1);

            status = LibraryWorkerDataAccess.insertLibraryWorker(libraryWorkerToCreate);

            if (status > 0) {
                System.out.println("Konto zostało utworzone");
            } else {
                System.out.println("Coś poszło nie tak");
            }

        } else if (accountType == 2) {

        } else {
            System.out.println("Nastąpił błąd!");
            WorkerMenu.createAccountView(libraryWorker);
        }

    }

    public static void userAccountRemover(LibraryWorker libraryWorker) {

        int accountType = 0;
        int userId = 0;
        int status = 0;

        System.out.println("_____________________");
        System.out.println("Usuwanie konta: ");
        System.out.print("1. Pracownik\n" +
                        "2. Użytkownik\n");
        System.out.print("Wybierz opcje: ");
        accountType = MenuHelper.checkChoosedOptionValidation(2);

        System.out.print("Podaj id użytkownika: ");
        userId = MenuHelper.checkChoosedOptionValidation(-1);

        if (accountType == 1) {

            status = LibraryWorkerDataAccess.deleteLibraryWorker(userId);

            if (status > 0) {
                System.out.printf("Użytkownik o id %d został usunięty", userId);
            } else {
                System.out.println("Coś poszło nie tak");
            }

        } else if (accountType == 2) {

        } else {
            System.out.println("Nastąpił błąd!");
            WorkerMenu.deleteAccountView(libraryWorker);
        }
    }

    public static void userSearcher(LibraryWorker libraryWorker) {

        List<LibraryWorker> libraryWorkerList = new ArrayList<LibraryWorker>();

        String name;
        String surName;
        int userId;

        System.out.println("_____________________");
        System.out.println("Podaj dane: ");

        System.out.print("Imie: ");
        name = MenuHelper.formatName();

        System.out.print("Nazwisko: ");
        surName = MenuHelper.formatName();

        System.out.print("Id: ");
        userId = MenuHelper.checkChoosedOptionValidation(-1);



        libraryWorkerList = LibraryWorkerDataAccess.getAllLibraryWorkerByNameSurNameId(name, surName, userId);

        System.out.printf("Znaleziono %d wyników%n", libraryWorkerList.size());
        System.out.println("Id -- Imie -- Nazwisko -- Nazwa użytkownika -- Typ użytkownika");

        for (LibraryWorker libraryWorkerObj : libraryWorkerList) {
            System.out.println(libraryWorkerObj.getUserData());
        }

        System.out.print("<--- Wciśnij przycisk aby powrócić");
        myInput.nextLine();
        findUserView(libraryWorker);
    }

}
