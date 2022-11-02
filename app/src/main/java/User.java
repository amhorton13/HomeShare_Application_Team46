import java.util.ArrayList;

public class User {

    private int user_id;
    private String username;
    private String password;
    private String age;
    private String biography;
    private ArrayList<String> user_interests;

    private ArrayList<Invitation> user_invitations;
    private ArrayList<Invitation> invitations_responded_to;

    public User(int user_id, String username, String password, String age, String biography){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.biography = biography;
        user_interests = new ArrayList<>();
        user_invitations = new ArrayList<>();
        invitations_responded_to = new ArrayList<>();
    }

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

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
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

    public void addInvitation(Invitation invite){
        user_invitations.add(invite);
    }

    public void addRespondedTo(Invitation invite){
        invitations_responded_to.add(invite);
    }
}

