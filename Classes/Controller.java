package com.myjfx.simplefx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.List;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Controller {

    private VBox chatBox;
    private TextField inputField;
    private ScrollPane scrollPane;
    private Chat currentChat = null;
    private Label contactLabel;
    private Button sendButton;
    private Person sender;
    private Person receiver;
    private Stage primaryStage;
    private StackPane viewProfile;
    private Scene scene2;

    public Controller() {

    }

    public Controller(VBox chatBox, TextField inputField, ScrollPane chatboxWindow, Chat currentChat, Person sender, Person receiver, Label contactLabel, Button sendButton) {
        this.chatBox = chatBox;
        this.inputField = inputField;
        this.scrollPane = chatboxWindow;
        this.currentChat = currentChat;
        this.sender = sender;
        this.receiver = receiver;
        this.contactLabel = contactLabel;
        this.sendButton = sendButton;
    }

    public Controller(Stage primaryStage, Person receiver,  Scene scene2) {
        this.primaryStage = primaryStage;
        this.receiver = receiver;
        this.viewProfile = viewProfile;
        this.scene2 = scene2;

    }

    public void createChat(String user, String content) {
        String message = user + "\n" + content;
                Label chatMessage = new Label(message);
                chatMessage.setWrapText(true);

                VBox chatWindow = new VBox(chatMessage);
                chatWindow.setPadding(new Insets(5));

                if (user.equals(this.sender.getUsername())) {
                    chatWindow.setAlignment(Pos.TOP_RIGHT);
                    chatMessage.setTextAlignment(TextAlignment.RIGHT);
                } else {
                    chatWindow.setAlignment(Pos.TOP_LEFT);
                    chatMessage.setTextAlignment(TextAlignment.LEFT);
                }

                // Scroll to the bottom
                scrollPane.layout();
                scrollPane.setVvalue(1.0);

                chatBox.getChildren().add(chatWindow);
                inputField.clear();
    }

    public void handleSend(ActionEvent event) {
        Person receiver = sender.getViewingContact();

        if (!(contactLabel.getText().isEmpty())) {
            if (!(inputField.getText().isEmpty())) {
                currentChat = sender.sendMessage(receiver, inputField.getText());
                createChat(sender.getUsername(), inputField.getText());
            }
        }
    }

    public void openChat(ActionEvent event) {
        System.out.println("Chat opened!");
        chatBox.getChildren().clear();
        contactLabel.setText(receiver.getUsername());
        Chat currentChat = sender.openChat(receiver);
        sender.setViewingContact(receiver);

        for (Message message : currentChat.getMessages()) {
            createChat(message.getSender().getUsername(), message.getContent());
        }


    }
}