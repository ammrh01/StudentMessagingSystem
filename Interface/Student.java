package com.myjfx.simplefx;

import java.util.List;

public class Student extends Person {
    private int matricNumber;
    private String majorCourse;
    private int academicYear;

    public Student() {};

    public Student(int academicYear, String majorCourse, int matricNumber, String Username, boolean active, String userEmail, int userId, String userRole) {
        super(Username, active, userEmail, userId, userRole);
        this.academicYear = academicYear;
        this.majorCourse = majorCourse;
        this.matricNumber = matricNumber;
    }

    @Override
    void openChat(String chatName) {

    }

    @Override
    void reportUser(int userId) {
        
    }

    @Override
    void sendMessage(Person recipient, String content) {
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
              Message message = new Message(recipient, content);
              chat.addMessage(message);

              System.out.println("Message sent to " + recipient.getUsername() + ": " + content);
    }

    @Override
    public void viewChats() {
        System.out.println("---Chats---");
        for (Chat c : chats) {
           // if (c.getParticipant(this)) {
                System.out.println(c.getChatName());
          //  }
        }
        System.out.println("-----------");
        
    }

    @Override
    void viewProfile() {
        System.out.println("Name: " + this.getUsername());
        System.out.println("Role: " + this.getUserRole());
        System.out.println("Matric number: " + this.getMatricNumber());
        System.out.println("Faculty: " + this.getMajorCourse());
        System.out.println("Year: " + this.getAcademicYear());
    }

    public int getMatricNumber() {
        return matricNumber;
    }

    public String getMajorCourse() {
        return majorCourse;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void joinChatroom(int chatroomId) {

    }

    public void leaveChatroom(int chatroomId) {
        
    }
    
    public List<Chat> getChats() {
        return chats;
    }
    
}
