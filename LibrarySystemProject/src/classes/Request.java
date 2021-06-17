package classes;

import java.text.SimpleDateFormat;

public class Request {

    private int requestId;
    private int borrowingId;
    private java.sql.Timestamp requestDate;
    private int statusId;

    public Request(int requestId, int borrowingId, java.sql.Timestamp requestDate, int statusId) {

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

    public java.sql.Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(java.sql.Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getRequestData() {

        return getRequestId() + " -- " + getBorrowingId()
                + " -- " + getRequestDate() + " -- " + DataDisplayHelper.formatStatusToString(getStatusId());

    }
}
