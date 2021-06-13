package classes;

public abstract class LibraryElement implements DataDisplayHelper{

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

    public void setLibraryElementId(int libraryElementId) {
        this.libraryElementId = libraryElementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public abstract String getLibraryElementData();
}