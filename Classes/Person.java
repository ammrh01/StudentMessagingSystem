package com.myjfx.simplefx;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {

    private int userId;
    private String Username;
    private String userEmail;
    protected String userRole;
    private boolean active;
    protected List<Chat> chats = new ArrayList<>();
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

    abstract Chat sendMessage(Person recipient, String content);
    abstract void sendMessage(Group groupChat, Person sender, String content);
    abstract Chat openChat(Person target);
    abstract void viewProfile();
    abstract void reportUser(int userId);
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
}

