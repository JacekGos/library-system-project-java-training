package classes;

public class LibraryUser extends User{

    private double penalty;

    protected LibraryUser(int userId, String userName, String userSurName, String login, String password) {
        super(userId, userName, userSurName, login, password);
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }
}
