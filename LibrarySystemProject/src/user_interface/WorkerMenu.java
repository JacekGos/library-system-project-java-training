package user_interface;

import classes.*;
import data_access.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkerMenu implements MenuHelper {

    private static Scanner myInput = new Scanner(System.in);
    private static byte choosedOption = 0;

    private WorkerMenu() {
    }

    public static void showWorkerMenu(LibraryWorker libraryWorker) {
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
        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(8);

        chooseWorkerMenuOption(choosedOption, libraryWorker);

    }

    //Main menu options

    private static void chooseWorkerMenuOption(byte choosedOption, LibraryWorker libraryWorker) {

        switch (choosedOption) {
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
                borrowingsView(libraryWorker);
                break;
            case 5:
                addLibraryElementView(libraryWorker);
                break;
            case 6:
                deleteLibraryElementView(libraryWorker);
                break;
            case 7:
                findLibraryElementView(libraryWorker);
                break;
            case 8:
                MenuHelper.logOnPanel();
                break;
            default:
                System.out.println("Błąd!\n ");
                showWorkerMenu(libraryWorker);
        }
    }

    private static void createAccountView(LibraryWorker libraryWorker) {
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

        WorkerMenu.createAccountView(libraryWorker);

    }

    private static void deleteAccountView(LibraryWorker libraryWorker) {

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

        WorkerMenu.deleteAccountView(libraryWorker);
    }

    private static void findUserView(LibraryWorker libraryWorker) {

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

    private static void borrowingsView(LibraryWorker libraryWorker) {

        System.out.println("_____________________");
        System.out.println("Aktualne zapytania o wypożyczenia: ");
        System.out.println("Id -- Id wypożyczenia -- Data zapytania -- Id Użytkownika -- Status zapytania");

        List<Request> requestList = new ArrayList<Request>();

        requestList = RequestDataAccess.getAllRequests(libraryWorker);

        for (Request requestObj : requestList)
        {
            System.out.println(requestObj.getRequestData(libraryWorker));
        }

        System.out.println("1. Kontynuuj\n" +
                "2. Powrót");
        System.out.print("Wybierz opcje: ");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                requestAcceptanceView(libraryWorker, requestList);
                break;
            case 2:
                showWorkerMenu(libraryWorker);
                break;
        }

    }

    private static void addLibraryElementView(LibraryWorker libraryWorker) {

        System.out.println("_____________________");
        System.out.println("Dodawanie pozycji bibliotecznej:\n" +
                "1. Rozpocznij dodawanie\n" +
                "2. Powrót");
        System.out.print("Wybierz opcje: ");
        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                libraryElementCreator(libraryWorker);
                break;
            case 2:
                showWorkerMenu(libraryWorker);
                break;
        }

        WorkerMenu.addLibraryElementView(libraryWorker);
    }

    private static void deleteLibraryElementView(LibraryWorker libraryWorker) {
        System.out.println("_____________________");
        System.out.println("Usuwanie pozycji bibliotecznej:\n" +
                "1. Rozpocznij usuwanie pozycji\n" +
                "2. Powrót");
        System.out.print("Wybierz opcje: ");
        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                libraryElementRemover(libraryWorker);
                break;
            case 2:
                showWorkerMenu(libraryWorker);
                break;
        }

        WorkerMenu.deleteAccountView(libraryWorker);
    }


    private static void findLibraryElementView(LibraryWorker libraryWorker) {

        System.out.println("Wyszukiwarka pozycji:\n" +
                "1. Wyszukaj\n" +
                "2. Powrót");
        System.out.print("Wybierz opcje: ");

        choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(2);

        switch (choosedOption) {

            case 1:
                libraryElementSearcher(libraryWorker);
                break;
            case 2:
                showWorkerMenu(libraryWorker);
                break;
        }

    }


    //Main menu nested options

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

            String login = MenuHelper.loginCreator(name, surName, accountType);
            LibraryUser libraryUserToCreate = new LibraryUser(0, name, surName, login, "password", 2, 0.0);

            status = LibraryUserDataAccess.insertLibraryUser(libraryUserToCreate);

            if (status > 0) {
                System.out.println("Konto zostało utworzone");
            } else {
                System.out.println("Coś poszło nie tak");
            }

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
                System.out.printf("Użytkownik o id %d został usunięty%n", userId);
            } else {
                System.out.println("Coś poszło nie tak");
            }

        } else if (accountType == 2) {

            status = LibraryUserDataAccess.deleteLibraryUser(userId);

            if (status > 0) {
                System.out.printf("Użytkownik o id %d został usunięty%n", userId);
            } else {
                System.out.println("Coś poszło nie tak");
            }


        } else {
            System.out.println("Nastąpił błąd!");
            WorkerMenu.deleteAccountView(libraryWorker);
        }
    }

    public static void userSearcher(LibraryWorker libraryWorker) {

        List<LibraryWorker> libraryWorkerList = new ArrayList<LibraryWorker>();
        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();

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
        libraryUserList = LibraryUserDataAccess.getAllLibraryUsersByNameSurNameId(name, surName, userId);

        System.out.printf("Znaleziono %d wyników%n", (libraryWorkerList.size() + libraryUserList.size()));
        System.out.println("Id -- Imie -- Nazwisko -- Nazwa użytkownika -- Typ użytkownika");

        for (LibraryWorker libraryWorkerObj : libraryWorkerList) {
            System.out.println(libraryWorkerObj.getUserData());
        }

        for (LibraryUser libraryUserObj : libraryUserList) {
            System.out.println(libraryUserObj.getUserData());
        }

        System.out.print("<--- Wciśnij przycisk aby powrócić");
        myInput.nextLine();
        findUserView(libraryWorker);
    }

    public static void libraryElementCreator(LibraryWorker libraryWorker) {

        int libraryElementType = 0;
        int libraryElementSort = 0;
        int bookPagesNumber = 0;
        int movieDurationTime = 0;
        int movieDuration = 0;
        String title = null;

        System.out.println("_____________________");
        System.out.println("Dodawanie pozycji: ");
        System.out.print("1. Książka\n" +
                "2. Film\n" +
                "3. Czasopismo\n");
        System.out.print("Wybierz opcje: ");
        libraryElementType = MenuHelper.checkChoosedOptionValidation(3);

        System.out.print("Podaj tytuł: ");
        title = myInput.nextLine();

        System.out.println("Rodzaj: ");
        System.out.print("1. Historyczna\n" +
                "2. Fantastyka\n" +
                "3. Kryminał\n" +
                "4. Edukacja\n" +
                "5. Technologie\n");
        System.out.print("Wybierz gatunek: ");
        libraryElementSort = MenuHelper.checkChoosedOptionValidation(5);

        if (libraryElementType == 1) {

            System.out.print("Podaj liczbe stron: ");
            bookPagesNumber = MenuHelper.checkChoosedOptionValidation(-1);

            Book bookToCreate = new Book(0, (byte) 1, title, libraryElementSort, 1, bookPagesNumber);

            BookDataAccess.insertBook(bookToCreate);

        } else if (libraryElementType == 2) {

            System.out.print("Podaj czas trwania: ");
            movieDurationTime = MenuHelper.checkChoosedOptionValidation(-1);

            Movie movieToCreate = new Movie(0, (byte) 2, title, libraryElementSort, 1, movieDurationTime);

            MovieDataAccess.insertMovie(movieToCreate);

        } else if (libraryElementType == 3) {
            System.out.println("Opcja chwilowo niedostępna");
            WorkerMenu.libraryElementCreator(libraryWorker);
        } else {
            System.out.println("Nastąpił błąd!");
            WorkerMenu.addLibraryElementView(libraryWorker);
        }

    }

    public static void libraryElementRemover(LibraryWorker libraryWorker) {

        int libraryElementId = 0;
        int status = 0;

        System.out.println("_____________________");
        System.out.println("Usuwanie pozycji: ");

        System.out.print("Podaj id pozycji: ");
        libraryElementId = MenuHelper.checkChoosedOptionValidation(-1);

        status = BookDataAccess.deleteLibraryElement(libraryElementId);

        if (status > 0) {
            System.out.printf("Pozycja o id %d została usunięta%n", libraryElementId);
        } else {
            System.out.println("Coś poszło nie tak");
        }

        WorkerMenu.deleteLibraryElementView(libraryWorker);
    }

    public static void libraryElementSearcher(LibraryWorker libraryWorker) {

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
        findLibraryElementView(libraryWorker);
    }

    public static void requestAcceptanceView(LibraryWorker libraryWorker, List<Request> requestList) {

        List<Request> requestListByUser = new ArrayList<Request>();

        int userId = 0;
        int requestId = 0;
        int borrowingId = 0;

        System.out.println("_____________________");
        System.out.print("Podaj id użytkownika: ");
        userId = MenuHelper.checkChoosedOptionValidation(-1);

        requestListByUser = createRequestListByUser(libraryWorker, requestList, userId);

        System.out.println("Id -- Id wypożyczenia -- Data zapytania -- Id Użytkownika -- Status zapytania");
        for (Request requestObj : requestListByUser)
        {
            System.out.println(requestObj.getRequestData(libraryWorker));
        }

        System.out.print("Podaj id zapytania: ");
        requestId = MenuHelper.checkChoosedOptionValidation(-1);

        if (checkIfRequestExists(requestId, requestListByUser) == true) {
            System.out.println("_____________________");
            System.out.println("1. Zaakceptuj:\n" +
                    "2. Odrzuć\n" +
                    "3. Powrót");
            System.out.print("Wybierz opcje: ");
            choosedOption = (byte) MenuHelper.checkChoosedOptionValidation(3);

            borrowingId = getBorrowingIdFromRequest(requestId, requestList);

            switch (choosedOption) {

                case 1:
                    BorrowingDataAccess.updateBorrowingStatus(borrowingId, (byte)1);
                    RequestDataAccess.deleteRequest(requestId);

                    borrowingsView(libraryWorker);
                    break;
                case 2:
                    BorrowingDataAccess.updateBorrowingStatus(borrowingId, (byte)2);
                    RequestDataAccess.deleteRequest(requestId);

                    borrowingsView(libraryWorker);
                    break;
                case 3:
                    borrowingsView(libraryWorker);
                    break;
            }
        } else {
            System.out.println("Niepoprawny Id zapytania");
            borrowingsView(libraryWorker);
        }

    }

    public static List<Request> createRequestListByUser(LibraryWorker libraryWorker, List<Request> requestList, int userId) {

        List<Request> requestListByUser = new ArrayList<Request>();

        for (Request requestObj : requestList) {
            if (RequestDataAccess.getUserIdByBorrowingId(libraryWorker, requestObj.getBorrowingId()) == userId) {
                requestListByUser.add(requestObj);
            }
        }

        return requestListByUser;
    }

    public static boolean checkIfRequestExists(int requestId, List<Request> requestList) {
        for (Request requestObj : requestList) {
            if (requestObj.getRequestId() == requestId) {

                return true;
            }
        }
        return false;
    }

    public static int getBorrowingIdFromRequest(int requestId, List<Request> requestList) {
        for (Request requestObj : requestList) {
            if (requestObj.getRequestId() == requestId) {
                return requestObj.getBorrowingId();
            }
        }
        return -1;
    }

}
