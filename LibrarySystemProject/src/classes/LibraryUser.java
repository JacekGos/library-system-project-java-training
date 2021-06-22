package classes;

import data_access.BorrowingDataAccess;

import java.util.ArrayList;
import java.util.List;

public class LibraryUser extends User{

    private double penalty;
    private List<Borrowing> userBorrowingsList = new ArrayList<Borrowing>();

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

    @Override
    public String getUserData() {
        return super.getUserData();
    }

    public void addBorrowing(int borrowingId, int libraryElementId, java.sql.Timestamp borrowingDate, int borrowingStatusId, int libraryUserId) {

        Borrowing borrowing = new Borrowing(borrowingId, libraryElementId, borrowingDate, borrowingStatusId, libraryUserId);
        userBorrowingsList.add(borrowing);

    }

    public void getAllBorrowings() {

        for (Borrowing borrowingObj : userBorrowingsList) {

            System.out.println(borrowingObj.getBorrowingData());

        }

    }

    public void getBorrowingsData() {

        userBorrowingsList.clear();
        BorrowingDataAccess.getAllBorrowingsByUserId(this.getUserId(), this);

        for (Borrowing borrowingObj : userBorrowingsList) {

            System.out.println(borrowingObj.getBorrowingData());
        }

    }

    public void getBorrowingsToReturn() {

      /*  userBorrowingsList.clear();
        BorrowingDataAccess.getAllBorrowingsToReturnByUserId(this.getUserId(), this);*/

        for (Borrowing borrowingObj : userBorrowingsList) {
            if (borrowingObj.getBorrowingStatusId() == 4 ) {
                System.out.println("status" + borrowingObj.getBorrowingStatusId());
                System.out.println(borrowingObj.getBorrowingData());
                System.out.println("");
            }
        }

    }

    public boolean checkBorrowingAvailableToReturn(int borrowingId) {
        for (Borrowing borrowingObj : userBorrowingsList) {
            if (borrowingObj.borrowingId == borrowingId && borrowingObj.getBorrowingStatusId() == 4) {
                return true;
            }
        }
        return false;
    }


    private class Borrowing {

        private int borrowingId;
        private int libraryElementId;
        private java.sql.Timestamp borrowingDate;
        private int borrowingStatusId;
        private int libraryUserId;

        private Borrowing(int borrowingId, int libraryElementId, java.sql.Timestamp borrowingDate, int borrowingStatusId, int libraryUserId) {

            this.borrowingId = borrowingId;
            this.libraryElementId = libraryElementId;
            this.borrowingDate = borrowingDate;
            this.borrowingStatusId = borrowingStatusId;
            this.libraryUserId = libraryUserId;

        }

        private Borrowing() {}

        public int getBorrowingId() {
            return borrowingId;
        }

        public void setBorrowingId(int borrowingId) {
            this.borrowingId = borrowingId;
        }

        public int getLibraryElementId() {
            return libraryElementId;
        }

        public void setLibraryElementId(int libraryElementId) {
            this.libraryElementId = libraryElementId;
        }

        public java.sql.Timestamp getBorrowingDate() {
            return borrowingDate;
        }

        public void setBorrowingDate(java.sql.Timestamp borrowingDate) {
            this.borrowingDate = borrowingDate;
        }

        public int getBorrowingStatusId() {
            return borrowingStatusId;
        }

        public void setBorrowingStatusId(int borrowingStatusId) {
            this.borrowingStatusId = borrowingStatusId;
        }

        public int getLibraryUserId() {
            return libraryUserId;
        }

        public void setLibraryUserId(int libraryUserId) {
            this.libraryUserId = libraryUserId;
        }

        public String getBorrowingData() {

            return getBorrowingId() + " -- " + getLibraryElementId()
                    + " -- " + getBorrowingDate() + " -- " + DataDisplayHelper.formatStatusToString(getBorrowingStatusId());

        }

    }
}
