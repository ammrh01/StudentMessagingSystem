package com.myjfx.simplefx;

import java.util.List;

public class Lecturer extends Person {
    private int lecturerId;
    private String departmentName;
    private String coursesTaught;
    private String officeLocation;

    public Lecturer() {
    }

    public Lecturer(String coursesTaught, String departmentName, int lecturerId, String officeLocation, String Username, boolean status, String userEmail, int userId, String userRole) {
        super(Username, status, userEmail, userId, userRole);
        this.coursesTaught = coursesTaught;
        this.departmentName = departmentName;
        this.lecturerId = lecturerId;
        this.officeLocation = officeLocation;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getCoursesTaught() {
        return coursesTaught;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    @Override
    Chat openChat(Person target) {
        Chat chat = null;
        for (Chat chats : target.getChats()) {
            if (chats.hasParticipants(this, target)) {
                chat = chats;
                return chat;
            }
        }
        return chat;
    }

    @Override
    void sendMessage(Group groupChat, Person sender, String content) {
    }

    @Override
    void reportUser(int userId) {

    }

    @Override
    Chat sendMessage(Person recipient, String content) {
        Chat chat  = null;

        for (Chat c : chats) {
            if (c.hasParticipants(this, recipient)) {
                chat = c;
                break;
            }
        }

        if (chat == null) {
            System.out.println("Chat not found. Creating new chat....");
            // Create a new chat
            chat = new Chat();
            chat.addParticipant(this);
            chat.addParticipant(recipient);
            chats.add(chat);
            recipient.chats.add(chat);
        }
        // Add the message
        Message message = new Message(this, content);
        chat.addMessage(message);

        System.out.println("Message sent to " + recipient.getUsername() + ": " + content);

        return chat;
    }

    @Override
    public List<Chat> getChats() {
        return chats;
    }

    @Override
    void viewProfile() {
        System.out.println("Name: " + this.getUsername());
        System.out.println("Role: " + this.getUserRole());
        System.out.println("Department: " + this.getDepartmentName());
        System.out.println("Courses taught: " + this.getCoursesTaught());
        System.out.println("Office location: " + this.getOfficeLocation());
    }

    public void addChatroom(int chatroomId) {

    }

    public void removeChatroom(int chatroomId) {

    }

    public void addStudent(int studentId, int chatroomId) {

    }

    public void removeStudent(int studentId, int chatroomId) {
        
    }



}
