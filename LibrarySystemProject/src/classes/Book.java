package classes;

public class Book extends LibraryElement{

    private byte typeId = 1;
    private int pagesNumber;

    public Book(int libraryElementId, String title, int sortId, int statusId, int pagesNumber) {
        super(libraryElementId, title, sortId, statusId);
        this.pagesNumber = pagesNumber;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public int getTypeId() {
        return typeId;
    }
}
