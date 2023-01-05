package modell;

public class UserDataObject {
    private String userName;
    private String password;

    public UserDataObject(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
