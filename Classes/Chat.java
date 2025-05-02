import java.util.ArrayList;
public class Chat {
    private int chatId;
    private String chatName;
    private ArrayList<String> messageList;
    private int[] participants;
    private String email;
     
        
    public Chat(){
    }
    public Chat(int chatId,String chatName,ArrayList<String> messageList,int[] participants,String email){
        this.chatId=chatId;
        this.chatName=chatName;
        this.messageList=messageList;
        this.participants=participants;
        this.email=email;
    }
public void addMessage(String message){

}
public void getMessages(){

}
public void getParticipants(){

}
    
    
public int getchatId(){
return chatId;
}

public String getchatName(){
return chatName;
}
public ArrayList<String> getmessageList(){
return messageList;
}
public int[] getparticipants(){
return participants;
}
public String getemail(){
return email;
}
public int setchatId(int chatId){
return this.chatId=chatId;
}
public String setchatName(String chatName){
return this.chatName=chatName;
}
public ArrayList<String> setmessageList(ArrayList<String> messageList){
return this.messageList=messageList;
}
public int[] setparticipants(int[] participants){
return this.participants=participants;
}
public String setemail(String email){
return this.email=email;
}
}
