public class User {
    private int UserID;
    private String userName;
    private String password;
    private byte[] salt;
    private String email;

    private double balance;
    private int roleID;


    public User(String userName, String password,byte[] salt,String email,int roleID) {
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.balance = 0;
        this.roleID = roleID;
    }

    public String getUserName() {
        return userName;
    }


    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserID() {
        return UserID;
    }


    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}

