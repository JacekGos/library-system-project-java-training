package classes;

public class Book extends LibraryElement{

    private byte typeId = 1;
    private int pagesNumber;

    public Book(int libraryElementId, String title, int sortId, int statusId, int pagesNumber) {
        super(libraryElementId, title, sortId, statusId);
        this.pagesNumber = pagesNumber;
    }

    public Book() {

    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public int getTypeId() {
        return typeId;
    }

    @Override
    public String getLibraryElementData() {

        return  getLibraryElementId() + " -- " + getTitle() + " -- " + DataDisplayHelper.formatTypeToString(getTypeId())
                + " -- " + DataDisplayHelper.formatSortToString(getSortId()) + " -- " + getPagesNumber()
                + " -- " + DataDisplayHelper.formatStatusToString(getStatusId());
    }
}
