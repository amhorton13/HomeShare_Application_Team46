import java.util.ArrayList;

public class user {

    private int user_id;
    private String username;
    private String password;
    private String school_year;
    private String biography;
    private ArrayList<String> user_interests;

    private ArrayList<invitation> user_invitations;
    private ArrayList<invitation> invitations_responded_to;

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBiography() {
        return biography;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setSchool_year(String school_year) {
        this.school_year = school_year;
    }

    public String getSchool_year() {
        return school_year;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_interests(ArrayList<String> user_interests) {
        this.user_interests = user_interests;
    }

    public ArrayList<String> getUser_interests() {
        return user_interests;
    }
}

