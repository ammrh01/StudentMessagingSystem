package com.myjfx.simplefx;

import java.util.List;
import java.util.ArrayList;

public class Admin extends Person {
    private int adminId;
    private String username;
    private String passwordHash;
    private String email;
    private ArrayList<Person> accounts = new ArrayList<>();

    public Admin(){

    }
    public Admin(int adminId, String username, String passwordHash, String email) {
        super(username, true, email, adminId, "Admin");
        this.passwordHash = passwordHash;
    }

    public void addAccount(String username, String email, int matricNo, int academicYear, String kulliyah, String userRole, int userId) {
            accounts.add(new Student(academicYear, kulliyah, matricNo, username, true, email, userId, userRole));
    }

    public void addAccount(String coursesTaught, String departmentName, int lecturerId, String officeLocation, String Username, boolean status, String userEmail, int userId, String userRole) {
        accounts.add(new Lecturer(coursesTaught, departmentName, lecturerId, officeLocation, Username, true, userEmail, userId, userRole));
    }

    public ArrayList<Person> getAccounts() {
        return accounts;
    }


    @Override
    public Message sendMessage(Person recipient, String content) {
        System.out.println("Admin does not send messages.");
        return null;
    }

    @Override
    public Message sendMessage(Group groupChat, Person sender, String content) {
        System.out.println("Admin does not send messages.");
        return null;
    }

    @Override
    Chat openChat(Person target) {
        return null;
    }

    @Override
    public void reportMessage(Message message) {
        System.out.println("Admin cannot report users.");
    }

    @Override
    public List<Chat> getChats() {
        return chats;
    }

}