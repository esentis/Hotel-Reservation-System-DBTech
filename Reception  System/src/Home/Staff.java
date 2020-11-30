package Home;

public class Staff {
    private String firstname;
    private String lasstname;
    private String email;
    private String username;
    private String password;
    private Long phonenumber;

    public Staff() {
    }

    public Staff(String firstname, String lasstname, String email, String username, String password, Long phonenumber) {
        this.firstname = firstname;
        this.lasstname = lasstname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLasstname() {
        return lasstname;
    }

    public void setLasstname(String lasstname) {
        this.lasstname = lasstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
