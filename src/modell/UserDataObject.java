package modell;

public class UserDataObject {
    private final String USERNAME;
    private final String PASSWORD;

    public UserDataObject(String USERNAME, String PASSWORD) {
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
