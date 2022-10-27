public class Response {
    private user user;
    private int response_id;
    private invitation invite;
    private boolean accepted;
    private boolean declined;

    public user getUser() {
        return user;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }
}
