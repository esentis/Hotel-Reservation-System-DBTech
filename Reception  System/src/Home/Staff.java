package Home;

public class Staff {
    private long Id;
    private String FirstName;
    private String LastName;
    private String UserName;
    private String email;
    private String password;
    private long PhoneNumber;
    private long RoleId;


    public Staff(){}

    

    public Staff(long id, String firstName, String lastName, String userName, String email, String password, long phoneNumber, long roleId) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        this.email = email;
        this.password = password;
        PhoneNumber = phoneNumber;
        RoleId = roleId;
    }

    public long getId() {
        return Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public long getRoleId() {
        return RoleId;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setRoleId(long roleId) {
        RoleId = roleId;
    }
}
