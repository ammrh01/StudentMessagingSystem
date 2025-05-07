import java.util.ArrayList;
import java.util.List;

public abstract class Person {

    private int userId;
    private String Username;
    private String userEmail;
    protected String userRole;
    private boolean active;
    protected List<Chat> chats = new ArrayList<>();

    private static int objectnum = 0;


    Person() {
        objectnum++;
    }

    Person(String Username, boolean active, String userEmail, int userId, String userRole) {
        this.Username = Username;
        this.active = active;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userRole = userRole;

        objectnum++;
    }

    abstract void sendMessage(Person recipient, String content);
    abstract void viewMessage();
    abstract void viewChats();
    abstract void openChat(String chatName);
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

    public boolean isActive() {
        return active;
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

    public void setStatus(boolean active) {
        this.active = active;
    }

    public static int getNumberOfObjects() {
        return objectnum;
    }
}

