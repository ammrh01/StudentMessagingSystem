package com.myjfx.simplefx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.List;

public class Controller {

    private VBox chatBox;
    private TextField inputField;
    private ScrollPane scrollPane;
    private Chat[] chatlist = null;
    private Label currentContact;
    private Button buttonContact;
    private Person sender;
    private Person receiver;
    private Student[] studentlist = null;

    private String currentProfile;

    public Controller() {

    }

    public Controller(VBox chatBox, TextField inputField, ScrollPane scrollPane, Chat[] chatList, Person sender, Person receiver, Label contact, Button buttoncontact, String currentProfile) {
        this.chatBox = chatBox;
        this.inputField = inputField;
        this.scrollPane = scrollPane;
        this.chatlist = chatList;
        this.sender = sender;
        this.receiver = receiver;
        this.currentContact = contact;
        this.buttonContact = buttoncontact;
        this.currentProfile = currentProfile;
    }



    public void handleSend(ActionEvent event) {
        String message = currentProfile + "\n" + inputField.getText().trim();
        if (!(currentContact.getText().isEmpty())) {
            if (!(inputField.getText().isEmpty())) {
                Label chatMessage = new Label(message);
                chatMessage.setAlignment(Pos.TOP_RIGHT);
                chatMessage.setWrapText(true);

                VBox chatWindow = new VBox(chatMessage);
                chatWindow.setAlignment(Pos.TOP_LEFT);
                chatWindow.setPadding(new Insets(5));

                // Scroll to the bottom
                scrollPane.layout();
                scrollPane.setVvalue(1.0);

                    for (Chat chat : chatlist) {
                       if (chat.hasParticipants(sender, receiver)) {
                           chat.addMessage(new Message(sender, inputField.getText().trim()));
                           System.out.println("Message added!");
                       }
                    }

                chatBox.getChildren().add(chatWindow);
                inputField.clear();

            }
        }
    }

    public void openChat(ActionEvent event) {
        chatBox.getChildren().clear();
        currentContact.setText(buttonContact.getText());

        for (Chat chat : chatlist) {
            if (chat.hasParticipants(sender, receiver)) {
                for (Message message : chat.getMessages()) {
                    Label chatMessage = new Label(message.getSender().getUsername() + "\n" + message.getContent());
                    chatMessage.setAlignment(Pos.TOP_RIGHT);
                    chatMessage.setWrapText(true);

                    VBox chatWindow = new VBox(chatMessage);
                    chatWindow.setAlignment(Pos.TOP_LEFT);
                    chatWindow.setPadding(new Insets(5));

                    chatBox.getChildren().add(chatWindow);
                    inputField.clear();

                    // Scroll to the bottom
                    scrollPane.layout();
                    scrollPane.setVvalue(1.0);
                }
            }
        }
    }
}