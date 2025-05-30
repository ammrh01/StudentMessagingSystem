package com.myjfx.simplefx;

import java.util.Date;

public class Message {
    private Person sender;
    private String content;
    private Date timestamp;
    private boolean isRead;

    // Constructor
    public Message(Person sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = new Date(); // Auto-set to current time
        this.isRead = false; // Default to unread
    }

    public Message(String content) {
        this.content = content;
        this.timestamp = new Date(); // Auto-set to current time
        this.isRead = false; // Default to unread
    }

    // Getters
    public String getContent() {
        return content;
    }

    public Person getSender() {
        return sender;
    }

    public boolean isRead() {
        return isRead;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    // Methods
    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String toString() {
    return     ", Sender=" + sender.getUsername() +
               ", Content='" + content + '\'' + 
               ", Time=" + timestamp;
    }
}
