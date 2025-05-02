import java.util.ArrayList;
import java.util.List;


public class Chat {
    private int chatId;
    private String chatName;
    private List<Person> participants = new ArrayList<>();
    private List<Message> messageList = new ArrayList<>();
     
        
    public Chat(){
    }

    public Chat(String chatName){
        java.util.Random random = new java.util.Random();
        int randomId = random.nextInt(900) + 100; // (0–899) + 100 = 100–999
        
        this.chatId=randomId;
        this.chatName=chatName;
    }

    public void addMessage(Message content){
        messageList.add(content);
    }

    public void getMessages(){
    

    }

    public boolean getParticipant(Person p){
        return participants.contains(p);

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
}
