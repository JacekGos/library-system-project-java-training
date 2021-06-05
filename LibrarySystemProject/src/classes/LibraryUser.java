package classes;

public class LibraryUser extends User{

    private double penalty;

    public LibraryUser(int userId, String userName, String userSurName, String login, String password, int accountType, double penalty) {
        super(userId, userName, userSurName, login, password, accountType);
        this.penalty = penalty;
    }

    public LibraryUser(){

    }
    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }
}
