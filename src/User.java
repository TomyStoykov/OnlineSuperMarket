public class User {
    private int UserID;
    private String userName;
    private String password;
    private String email;

    private double balance;
    private int roleID;
    private int shoppingCardID;

    public User(String userName, String password, String email,int roleID,int shoppingCardID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.roleID = roleID;
        this.shoppingCardID = 0;
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

    public int getShoppingCardID() {
        return shoppingCardID;
    }

    public void setShoppingCardID(int shoppingCardID) {
        this.shoppingCardID = shoppingCardID;
    }
}

