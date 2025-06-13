package project;

import java.util.ArrayList;
import java.util.List;


public class Chat {
    private int chatId;
    private String chatName;
    private List<Person> participants = new ArrayList<>();
    private List<Message> messageList = new ArrayList<>();


    public Chat(){
        java.util.Random random = new java.util.Random();
        this.chatId = random.nextInt(900) + 100; // (0–899) + 100 = 100–999
    }

    public void addMessage(Message content){
        messageList.add(content);
    }

    public List<Message> getMessages(){
       return messageList;
    }

    public boolean hasParticipants(Person s1, Person s2){
        return (participants.contains(s1) && participants.contains(s2)) ||
                (participants.contains(s2) && participants.contains(s1));
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public void addParticipant(Person p){
        if (!participants.contains(p)) {
            participants.add(p);
        }
    }

    public int getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
}