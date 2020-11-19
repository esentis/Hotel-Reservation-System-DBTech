package Home;

public class Pelatis {
    private int pelatisId;
    private String name;
    private String lastname;
    private String email;
    private int phoneNumber;


    public Pelatis(int pelatisId, String name, String lastname, String email, int phoneNumber) {
        this.pelatisId = pelatisId;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Pelatis() {
    }

    public int getPelatisId() {
        return pelatisId;
    }

    public void setPelatisId(int pelatisId) {
        this.pelatisId = pelatisId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Pelatis{" +
                "pelatisId=" + pelatisId +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
