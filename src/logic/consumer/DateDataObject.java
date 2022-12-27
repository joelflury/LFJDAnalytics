package logic.consumer;

public class DateDataObject {
    private String firstDate;
    private String lastDate;

    public DateDataObject(String firstDate, String lastDate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public String getLastDate() {
        return lastDate;
    }
}
