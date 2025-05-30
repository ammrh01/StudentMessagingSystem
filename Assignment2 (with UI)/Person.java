package com.myjfx.simplefx;

import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;


public abstract class Person {

    private int userId;
    private String Username;
    private String password;
    private String userEmail;
    protected String userRole;
    private boolean active;
    protected List<Chat> chats = new ArrayList<>();
    protected List<Message> messageReports = new ArrayList<>();
    private Person viewingContact;


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

    abstract Message sendMessage(Person recipient, String content);
    abstract Message sendMessage(Group groupChat, Person sender, String content);
    abstract Chat openChat(Person target);
    abstract void reportMessage(Message message);
    abstract List<Chat> getChats();

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

    public Person getViewingContact() { return viewingContact; }

    public void setViewingContact(Person viewingContact) { this.viewingContact = viewingContact; }

    // Hash the password
    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

    // Check the password
    boolean isMatch = BCrypt.checkpw(password, hashed);

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = hashed;
    }

    public List<Message> getMessageReports() {
        return messageReports;
    }

    public void setMessageReports(List<Message> messageReports) {
        this.messageReports = messageReports;
    }
}

