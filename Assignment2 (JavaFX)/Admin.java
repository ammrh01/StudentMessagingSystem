package project;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Person {
    private String password;
    private ArrayList<Person> accounts = new ArrayList<>();

    public Admin() {
    }

    public Admin(int adminId, String username, String password, String email) {
        super(username, true, email, adminId, "Admin");
        this.password=password;
    }

    public void addAccount(String username, String email, int matricNo, int academicYear, String kulliyah, String userRole, int userId) {
        Student student = new Student(academicYear, kulliyah, matricNo, username, true, email, userId, userRole);
        accounts.add(student);
    }

    public void addAccount(String username, String coursesTaught, String departmentName, String officeLocation, String userEmail, int userId, String userRole) {
        Lecturer lecturer = new Lecturer(username, coursesTaught, departmentName, officeLocation, userEmail, userId, userRole, true);
        accounts.add(lecturer);
    }

    public void makeAnnouncement(Chat announcementChat, String text) {
        announcementChat.addMessage(new Message(text));
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
        System.out.println("Admin does not send group messages.");
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
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}