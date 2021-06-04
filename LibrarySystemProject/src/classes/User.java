package classes;

public abstract class User {

    private int userId;
    private String userName;
    private String userSurName;
    private String login;
    private String password;
    private int accountType;

    public User(int userId, String userName, String userSurName, String login, String password, int accountType) {
        this.userId = userId;
        this.userName = userName;
        this.userSurName = userSurName;
        this.login = login;
        this.password = password;
        this.accountType = accountType;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void changeUserName(String newUserName) {
        this.userName = newUserName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void changeUserSurName(String newUserSurName) {
        this.userSurName = newUserSurName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountType(){
        return accountType;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
