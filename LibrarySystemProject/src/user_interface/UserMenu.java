package user_interface;

import classes.*;
import data_access.BookDataAccess;
import data_access.BorrowingDataAccess;
import data_access.MovieDataAccess;
import data_access.RequestDataAccess;

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
                borrowingView(libraryUser);
                break;
            case 3:
                returningView(libraryUser);
                break;
            case 4:
                userBorrowingsView(libraryUser);
                break;
            case 5:
                UserAuthentication.logOnPanel();
                break;
            default:
                System.out.println("Błąd!\n ");
        }
    }

    private static void findLibraryElementView(LibraryUser libraryUser){

        System.out.println("_____________________");
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

    private static void borrowingView(LibraryUser libraryUser){
        System.out.println("_____________________");
        System.out.println("Wypożyczalnia:\n" +
                        "1. Rozpocznij\n" +
                        "2. Powrót");
        System.out.print("Wybierz opcje: ");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                libraryElementBorrowing(libraryUser);
                break;
            case 2:
                showUserMenu(libraryUser);
                break;
        }

    }

    private static void returningView(LibraryUser libraryUser){
        System.out.println("_____________________");
        System.out.println("Zwroty:");
        showUserBorowingsToReturn(libraryUser);
        System.out.println("1. Zwróć pozycję\n" +
                "2. Powrót");
        System.out.print("Wybierz opcje: ");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                libraryElementReturns(libraryUser);
                break;
            case 2:
                showUserMenu(libraryUser);
                break;
        }
    }

    private static void userBorrowingsView(LibraryUser libraryUser){

        System.out.println("_____________________");
        System.out.println("Twoje wypożyczenia:\n" +
                "1. Wyświetl\n" +
                "2. Powrót");
        System.out.print("Wybierz opcje: ");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                showUserBorowings(libraryUser);

                System.out.print("<--- Wciśnij przycisk aby powrócić");
                myInput.nextLine();
                userBorrowingsView(libraryUser);

                break;
            case 2:
                showUserMenu(libraryUser);
                break;
        }

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

            System.out.println(libraryElementObj.toString());

        }

        System.out.print("<--- Wciśnij przycisk aby powrócić");
        myInput.nextLine();
        findLibraryElementView(libraryUser);
    }

    public static void libraryElementBorrowing(LibraryUser libraryUser) {

        Book book = new Book();
        Movie movie = new Movie();
        int libraryElementId = -1;
        int updateStatus = 0;
        int borrowingInsertStatus = 0;
        int lastBorrowingId = 0;
        java.sql.Timestamp borrowingDate = null;
        long currentTime = 0;

        System.out.println("_____________________");
        System.out.print("Podaj id pozycji: ");
        libraryElementId = MenuHelper.checkChoosedOptionValidation(-1);

        book = BookDataAccess.getBookById(libraryElementId);

        if (book.getTypeId() == 1 && book.getStatusId() == 1) {

            updateStatus = BookDataAccess.updateLibraryElementStatusById(libraryElementId, 2);

            if (updateStatus > 0) {

                currentTime = System.currentTimeMillis();
                borrowingDate = new java.sql.Timestamp(currentTime);

                libraryUser.addBorrowing(0, libraryElementId, borrowingDate, 2, libraryUser.getUserId());
                borrowingInsertStatus = BorrowingDataAccess.insertBorrowing(libraryElementId, borrowingDate, 2, libraryUser.getUserId());
                lastBorrowingId = BorrowingDataAccess.getLastBorrowingID(libraryUser);

                if (borrowingInsertStatus > 0) {

                    lastBorrowingId = BorrowingDataAccess.getLastBorrowingID(libraryUser);
                    Request request = new Request(0, lastBorrowingId, borrowingDate, 2);
                    RequestDataAccess.insertRequest(request);

                    System.out.println(book.getTitle() + " status: oczukująca na zatwierdzenie\nUdaj się do punktu wypożyczeń");
                }
            }

            borrowingView(libraryUser);

        } else {

            movie = MovieDataAccess.getMovieById(libraryElementId);

            if (movie.getTypeId() == 2 && movie.getStatusId() == 1) {

                updateStatus = BookDataAccess.updateLibraryElementStatusById(libraryElementId, 2);

                if (updateStatus > 0) {

                    currentTime = System.currentTimeMillis();
                    borrowingDate = new java.sql.Timestamp(currentTime);

                    libraryUser.addBorrowing(0, libraryElementId, borrowingDate, 2, libraryUser.getUserId());
                    borrowingInsertStatus = BorrowingDataAccess.insertBorrowing(libraryElementId, borrowingDate, 2, libraryUser.getUserId());
                    lastBorrowingId = BorrowingDataAccess.getLastBorrowingID(libraryUser);

                    if (borrowingInsertStatus > 0) {

                        lastBorrowingId = BorrowingDataAccess.getLastBorrowingID(libraryUser);
                        Request request = new Request(0, lastBorrowingId, borrowingDate, 2);
                        RequestDataAccess.insertRequest(request);

                        System.out.println(movie.getTitle() + " status: oczukująca na zatwierdzenie\nUdaj się do punktu wypożyczeń");

                    }

                }

                borrowingView(libraryUser);
            }
        }

        System.out.println("Poyzcja niedostępna");
        borrowingView(libraryUser);

    }

    public static void showUserBorowings(LibraryUser libraryUser) {

        System.out.println("Id -- Id pozycji -- Data wypożyczenia -- Status");

        libraryUser.getBorrowingsData();

    }

    public static void showUserBorowingsToReturn(LibraryUser libraryUser) {

        System.out.println("Id -- Id pozycji -- Data wypożyczenia -- Status");

        libraryUser.getBorrowingsToReturn();

    }

    public static void libraryElementReturns(LibraryUser libraryUser) {
        int borrowingId = 0;
        int libraryElementId = 0;

        System.out.println("_____________________");
        System.out.print("Podaj Id wypożyczenia: ");
        borrowingId = MenuHelper.checkChoosedOptionValidation(-1);

        if (libraryUser.checkBorrowingAvailableToReturn(borrowingId) == true) {
            BorrowingDataAccess.updateBorrowingStatus(borrowingId, (byte)6);
            libraryElementId = BorrowingDataAccess.getLibraryElementByBorrowingId(borrowingId);
            BookDataAccess.updateLibraryElementStatusById(libraryElementId, 1);
        } else {
            System.out.println("Niepoprawny Id wypożyczenia");
            returningView(libraryUser);
        }

        System.out.println("Pozycja została zwrócona");
        UserMenu.returningView(libraryUser);
    }

}


