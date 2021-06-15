package classes;

import java.text.SimpleDateFormat;

public class Request {

    private static String datePattern = "dd-MM-yyyy hh:mm";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

    private int requestId;
    private int borrowingId;
    private String requestDate;
    private int statusId;

    public Request(int requestId, int borrowingId, String requestDate, int statusId) {

        this.requestId = requestId;
        this.borrowingId = borrowingId;
        this.requestDate = requestDate;
        this.statusId = statusId;

    }

    public Request() {}

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(int borrowingId) {
        this.borrowingId = borrowingId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
