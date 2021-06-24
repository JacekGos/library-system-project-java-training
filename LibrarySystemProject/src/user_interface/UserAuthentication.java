package user_interface;

import classes.LibraryUser;
import classes.LibraryWorker;
import classes.User;
import data_access.LibraryUserDataAccess;
import data_access.LibraryWorkerDataAccess;

import java.util.Scanner;

public final class UserAuthentication {

    private UserAuthentication() {}
    private static Scanner myInput = new Scanner( System.in );

    public static void logOnPanel() {

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

                libraryUser.getAllBorrowings();
                UserMenu.showUserMenu(libraryUser);

            } else {

                System.out.println("Nieprawidłowe dane");
                UserAuthentication.logOnPanel();

            }

        } else {

            System.out.println("Nieprawidłowe dane");
            UserAuthentication.logOnPanel();

        }

    }

    static boolean checkAccountValidation(User user, String login, String password, int accountType) {

        if (user.getLogin().equals(login) && user.getPassword().equals(password) && user.getAccountType() == accountType) { return true; }

        return false;
    }


}
