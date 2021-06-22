package user_interface;

import classes.LibraryUser;
import classes.LibraryWorker;
import classes.User;
import data_access.LibraryUserDataAccess;
import data_access.LibraryWorkerDataAccess;

import java.util.Objects;
import java.util.Scanner;

public interface MenuHelper {

    Scanner myInput = new Scanner( System.in );

    // @TODO move logOn logic to another class such as UserAuthentication
    static void logOnPanel() {

        boolean userDataNotNull = false;

        String login = null;
        String password = null;

        System.out.println("_____________________");
        System.out.println("Witaj!");

        System.out.print("Login: ");
        login = myInput.next();

        System.out.print("Password: ");
        password = myInput.next();


        LibraryWorker libraryWorker = LibraryWorkerDataAccess.getLibraryWorkerByLogin(login);

        try {
            userDataNotNull = checkAccountValidation(libraryWorker, login, password, 1);
        } catch (NullPointerException e) { }

        if ( userDataNotNull == true) {

            WorkerMenu.showWorkerMenu(libraryWorker);

        } else if (userDataNotNull == false) {

            LibraryUser libraryUser = LibraryUserDataAccess.getLibraryUserByLogin(login);

            try {
                userDataNotNull = checkAccountValidation(libraryUser, login, password, 2);
            } catch (NullPointerException e) { }

            if (userDataNotNull == true) {

                UserMenu.showUserMenu(libraryUser);

            } else {

                System.out.println("Nieprawidłowe dane");
                MenuHelper.logOnPanel();

            }

        } else {

            System.out.println("Nieprawidłowe dane");
            MenuHelper.logOnPanel();

        }

    }

    static boolean checkAccountValidation(User user, String login, String password, int accountType) {

        if (user.getLogin().equals(login) && user.getPassword().equals(password) && user.getAccountType() == accountType) { return true; }

        return false;
    }



    static int checkChoosedOptionValidation(int amountOfOptions){

        boolean isCorrect = false;
        int value = 0;

        while (isCorrect != true){

            try{
                value = myInput.nextInt();
            }catch (Exception e){
                System.out.print("Nieprawidłowa wartość, spróbuj ponownie: ");
                myInput.nextLine(); //Added because scanner had all the time first introduced value so there was always an exception
                continue;
            }

            if (value > 0 && value <= amountOfOptions || (amountOfOptions == -1 && value >= 0)){
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

        repeatedLogin = LibraryWorkerDataAccess.getNumberOfLibraryWorkersByNameAndSurname(name, surName);

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
