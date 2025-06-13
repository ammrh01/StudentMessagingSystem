package project;

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
    Chat openChat(Person target) {
        Chat chat = null;
        if (target.getChats().isEmpty()) {
            chat = new Chat();
            chat.addParticipant(this);
            chat.addParticipant(target);
            this.chats.add(chat);
            target.chats.add(chat);
            System.out.println("Chat not found, but chat added!");
        }
        for (Chat chats : target.getChats()) {
            if (chats.hasParticipants(this, target)) {
                chat = chats;
                return chat;
            }
            if (!chats.hasParticipants(this, target)) {
                chat = new Chat();
                chat.addParticipant(this);
                chat.addParticipant(target);
                System.out.println("Chat not found, but chat added!");
            }
        }
        return chat;

    }

    public void joinGroup(Group group) {
        group.addMember(this);
    }

    public void leaveGroup(Group group) {
        group.removeMember(this);
    }

    @Override
    void reportMessage(Message message) {
        System.out.println("Message reported. Details: ");
        System.out.println(message.toString());
        this.getMessageReports().add(message);
    }

    @Override
    Message sendMessage(Group groupChat, Person sender, String content) {
        Message message = new Message(this, content);
        groupChat.addMessage(message);
        return message;
    }

    @Override
    Message sendMessage(Person recipient, String content) {
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

              return message;
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
    
    public List<Chat> getChats() {
        return chats;
    }
    
}
