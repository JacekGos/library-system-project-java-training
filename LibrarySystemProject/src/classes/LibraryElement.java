package classes;

public abstract class LibraryElement {

    private int libraryElementId;
    private String title;
    private int sortId;
    private int statusId;

    public LibraryElement(int libraryElementId, String title, int sortId, int statusId) {
        this.libraryElementId = libraryElementId;
        this.title = title;
        this.sortId = sortId;
        this.statusId = statusId;
    }

    public LibraryElement() {

    }

    public int getLibraryElementId() {
        return libraryElementId;
    }

    public String getTitle() {
        return title;
    }

    public int getSortId() {
        return sortId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}