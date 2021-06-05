package user_interface;

import classes.LibraryWorker;
import data_access.LibraryWorkerDataAccess;

import java.util.Objects;
import java.util.Scanner;

public interface MenuHelper {

    Scanner myInput = new Scanner( System.in );

    /*
    Finish it when LibraryUser connection to db will be created
     */
    static void logOnPanel() {

        String login = null;
        String password = null;

        System.out.println("Witaj!");

        System.out.print("Login: ");
        login = myInput.next();

        System.out.print("Password: ");
        password = myInput.next();

        LibraryWorker libraryWorker = LibraryWorkerDataAccess.getLibraryWorkerByLogin(login);

        System.out.println(libraryWorker.getPassword() == null);

        if (libraryWorker.getPassword() != null) {
            if (libraryWorker.getPassword().equals(password) && libraryWorker.getAccountType() == 1) {
                WorkerMenu.showWorkerMenu(libraryWorker);
            }
        } else if (libraryWorker.getUserId() != 0) {
            System.out.println("Szukanie konta typu LibraryUser");
        }


    }

    static int checkChoosedOptionValidation(int amountOfOptions){

        boolean isCorrect = false;
        int value = 0;

        System.out.print("Wybierz opcje: ");

        while (isCorrect != true){

            try{
                value = myInput.nextByte();
            }catch (Exception e){
                System.out.print("Nieprawidłowa wartość, spróbuj ponownie: ");
                myInput.nextLine(); //Added because scanner had all the time first introduced value so there was always an exception
                continue;
            }

            if (value > 0 && value <= amountOfOptions){
                isCorrect = true;
            }else{
                System.out.print("Nieprawidłowa wartość, spróbuj ponownie: ");
                continue;
            }
        }

        return value;
    }

    static String loginCreator(String name, String surName, int accountType) {

        String login = null;
        char[] nameArray = name.toCharArray();
        char[] surNameArray = surName.toCharArray();
        int repeatedLogin = 0;

        nameArray[0] = Character.toLowerCase(nameArray[0]);
        surNameArray[0] = Character.toLowerCase(surNameArray[0]);

        name = new String(nameArray);
        surName = new String(surNameArray);
        login = name + "." + surName;

        repeatedLogin = LibraryWorkerDataAccess.getNumberLibraryWorkersByNameAndSurname(name, surName);

        if (repeatedLogin == 0) {
            login = name + "." + surName;
        } else {
            login = name + "." + (repeatedLogin + 1) + "." + surName;
        }

        return login;
    }

    static String formatName() {

        String name;
        name = myInput.next();
        char[] nameArray = name.toCharArray();

        nameArray[0] = Character.toUpperCase(nameArray[0]);

        for (int i = 1; i < nameArray.length; i++) {
            nameArray[i] = Character.toLowerCase(nameArray[i]);
        }

        name = new String(nameArray);

        return name;
    }

}
