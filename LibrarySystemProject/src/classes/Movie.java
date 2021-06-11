package classes;

public class Movie extends LibraryElement{

    private byte typeId = 2;
    private int durationTime;

    public Movie(int libraryElementId, String title, int sortId, int statusId, int durationTime) {
        super(libraryElementId, title, sortId, statusId);
        this.durationTime = durationTime;
    }

    public Movie() {

    }

    public int getDurationTime() {
        return durationTime;
    }

    public byte getTypeId() {
        return typeId;
    }

    public void setTypeId(byte typeId) {
        this.typeId = typeId;
    }

    @Override
    public String getLibraryElementData() {
        return null;
    }
}
