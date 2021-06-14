package user_interface;

import classes.*;
import data_access.BookDataAccess;
import data_access.MovieDataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class UserMenu implements MenuHelper {

    private static Scanner myInput = new Scanner( System.in );
    private static byte choosedOption = 0;

    private UserMenu(){}

    public static void showUserMenu(LibraryUser libraryUser){
        System.out.println("_____________________");
        System.out.println("Menu główne: ");
        System.out.println("1. Wyszukaj\n" +
                            "2. Wypożycz\n" +
                            "3. Zwróć\n" +
                            "4. Twoje wypożyczenia\n" +
                            "5. Wyloguj");
        System.out.print("Wybierz opcje: ");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(5);

        chooseUserMenuOption(choosedOption, libraryUser);

    }
    private static void chooseUserMenuOption(byte choosedOption, LibraryUser libraryUser){

        switch (choosedOption){
            case 1:
                findLibraryElementView(libraryUser);
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
            case 5:
                MenuHelper.logOnPanel();
                break;
            default:
                System.out.println("Błąd!\n ");
        }
    }

    private static void findLibraryElementView(LibraryUser libraryUser){

        System.out.println("Wyszukiwarka pozycji:\n" +
                        "1. Wyszukaj\n" +
                        "2. Powrót");
        System.out.print("Wybierz opcje: ");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                libraryElementSearcher(libraryUser);
                break;
            case 2:
                showUserMenu(libraryUser);
                break;
        }

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

    //Main menu nested options

    public static void libraryElementSearcher(LibraryUser libraryUser) {

        List<LibraryElement> libraryElementList = new ArrayList<LibraryElement>();
        List<Book> bookList = new ArrayList<Book>();
        List<Movie> movieList = new ArrayList<Movie>();

        String title;
        String sortName;
        String durationTime;
        String numberOfPages;
        int libraryElementId;
        int status;

        String statusStringFormat = null;

        System.out.println("_____________________");
        System.out.println("Podaj dane: ");

        System.out.print("Tytuł: ");
        title = myInput.nextLine();

        System.out.print("Gatunek: ");
        sortName = myInput.nextLine();

        bookList = BookDataAccess.getAllBooksByTitleAndSort(title, sortName);
        movieList = MovieDataAccess.getAllMoviesByTitleAndSort(title, sortName);

        libraryElementList.addAll(bookList);
        libraryElementList.addAll(movieList);

        System.out.printf("Znaleziono %d wyników%n", (libraryElementList.size()));
        System.out.println("Id -- Tytuł -- Typ -- Gatunek -- Liczba stron / czas trwania -- Status ");

        for (LibraryElement libraryElementObj : libraryElementList) {

            System.out.println(libraryElementObj.getLibraryElementData());

        }

        System.out.print("<--- Wciśnij przycisk aby powrócić");
        myInput.nextLine();
        findLibraryElementView(libraryUser);
    }

}


