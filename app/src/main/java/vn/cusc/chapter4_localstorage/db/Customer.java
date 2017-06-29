package vn.cusc.chapter4_localstorage.db;

/**
 * Created by ntdan on 6/27/2017.
 */
public class Customer {
    int id;
    String fullname;
    String email;
    String image;

    public Customer() {
    }

    public Customer(int id, String fullname, String email, String image) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
