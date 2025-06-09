package project;

import java.util.List;

public class Lecturer extends Person {
    private String departmentName;
    private String coursesTaught;
    private String officeLocation;

    public Lecturer() {
    }

    public Lecturer(String Username, String coursesTaught, String departmentName, String officeLocation, String userEmail, int userId, String userRole, boolean status) {
        super(Username, status, userEmail, userId, userRole);
        this.coursesTaught = coursesTaught;
        this.departmentName = departmentName;
        this.officeLocation = officeLocation;
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

    @Override
    public List<Chat> getChats() {
        return chats;
    }

    public Group createGroup(String groupName) {
        Group newGroup = new Group(groupName);
        return newGroup;
    }



}
