import java.util.Date;

public class Message {
    private int messageId;
    private int senderId;
    private String content;
    private Date timestamp;
    private boolean isRead;

    // Constructor
    public Message(Person sender, String content) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = new Date(); // Auto-set to current time
        this.isRead = false; // Default to unread
    }

    // Getters
    public String getContent() {
        return content;
    }

    public int getSender() {
        return senderId;
    }

    public boolean isRead() {
        return isRead;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getMessageId() {
        return messageId;
    }

    // Methods
    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String toString() {
        return "Message [ID=" + messageId + 
               ", Sender=" + senderId + 
               ", Content='" + content + '\'' + 
               ", Time=" + timestamp + 
               ", " + (isRead ? "Read" : "Unread") + "]";
    }
}
