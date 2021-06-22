package classes;

public class LibraryWorker extends User{

    public LibraryWorker(int userId, String userName, String userSurName, String login, String password, int accountType) {
        super(userId, userName, userSurName, login, password, accountType);
    }

    public LibraryWorker() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}