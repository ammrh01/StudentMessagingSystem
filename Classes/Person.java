public abstract class Person {
    private int userId;
    private String Username;
    private String userEmail;
    protected String userRole;
    private boolean status;

    public Person() {
    }

    public Person(String Username, boolean status, String userEmail, int userId, String userRole) {
        this.Username = Username;
        this.status = status;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userRole = userRole;
    }

    abstract void sendMessage(int userId, String content, java.util.Date timestamp);
    abstract void viewMessage(int chatId, int userId);
    abstract void viewChats();
    abstract void openChat(int chatId);
    abstract void viewProfile();
    abstract void reportUser(int userId);

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return Username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public boolean isStatus() {
        return status;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

