package classes;

public class Movie extends LibraryElement{

    private byte typeId = 2;
    private int durationTime;

    public Movie(int libraryElementId, String title, int sortId, int statusId, int durationTime) {
        super(libraryElementId, title, sortId, statusId);
        this.durationTime = durationTime;
    }

    public int getDurationTime() {
        return durationTime;
    }
}
