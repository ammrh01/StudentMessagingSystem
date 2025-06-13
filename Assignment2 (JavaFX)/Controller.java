package project;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

public class Controller {

    private VBox chatBox;
    private TextField inputField;
    private ScrollPane scrollPane;
    private Message message;
    private Label contactLabel;
    private Button sendButton;
    private Person sender;
    private Person receiver;
    private Stage primaryStage;
    private StackPane viewProfile;
    private Scene scene2;
    private Group group;
    private Chat announcement;
    private String password;

    public Controller() {

    }

    public Controller(VBox chatBox, TextField inputField, ScrollPane chatboxWindow, Chat currentChat, Person sender, Person receiver, Label contactLabel, Button sendButton) {
        this.chatBox = chatBox;
        this.inputField = inputField;
        this.scrollPane = chatboxWindow;
        this.sender = sender;
        this.receiver = receiver;
        this.contactLabel = contactLabel;
        this.sendButton = sendButton;
    }

    public Controller(VBox chatBox, TextField inputField, ScrollPane chatboxWindow, Group groupList, Person sender, Label contactLabel, Button sendButton) {
        this.chatBox = chatBox;
        this.inputField = inputField;
        this.scrollPane = chatboxWindow;
        this.group = groupList;
        this.sender = sender;
        this.contactLabel = contactLabel;
        this.sendButton = sendButton;
    }

    public Controller(Stage primaryStage, Person receiver, Scene scene2) {
        this.primaryStage = primaryStage;
        this.receiver = receiver;
        this.viewProfile = viewProfile;
        this.scene2 = scene2;
    }

    public Controller(String pass, Person currentUser) {
        this.password = pass;
        this.sender = currentUser;
    }

    public Controller(VBox chatBox, ScrollPane chatboxWindow, Chat announcement, Label contactLabel, Person sender) {
        this.chatBox = chatBox;
        this.scrollPane = chatboxWindow;
        this.announcement = announcement;
        this.contactLabel = contactLabel;
        this.sender = sender;
    }

    public void createChat(Message messageInfo) {
        Person user = messageInfo.getSender();
        String content = messageInfo.getContent();
        String message = user.getUsername() + "\n" + content;
                Label chatMessage = new Label(message);
                chatMessage.setWrapText(true);

                Label report = new Label("Report");
                report.setVisible(false);
                report.setTextFill(Color.RED);

                report.setStyle("-fx-font-size: 9px;");

                report.setOnMouseClicked(event -> {
                    sender.reportMessage(messageInfo);
                });

                VBox chatWindow = new VBox(chatMessage, report);
                chatWindow.setPadding(new Insets(5));

                if (user.equals(this.sender)) {
                    chatWindow.setAlignment(Pos.TOP_RIGHT);
                    chatMessage.setTextAlignment(TextAlignment.RIGHT);
                } else {
                    chatWindow.setAlignment(Pos.TOP_LEFT);
                    chatMessage.setTextAlignment(TextAlignment.LEFT);
                }

                chatWindow.setOnMouseEntered(event -> {
                    report.setVisible(true);
                });

                chatWindow.setOnMouseExited(event -> {
                    report.setVisible(false);
                });

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
                message = sender.sendMessage(receiver, inputField.getText());
                createChat(message);
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
            createChat(message);
        }
    }
    public void openGroupChat(ActionEvent event) {
        System.out.println("Group chat opened!");
        chatBox.getChildren().clear();
        contactLabel.setText(group.getGroupName());
        if (!group.getMessages().isEmpty()) {
            for (Message message : group.getMessages()) {
                createChat(message);
            }
        }

    }

    public void sendGroupMessage(ActionEvent event) {
            if (!(contactLabel.getText().isEmpty())) {
                if (!(inputField.getText().isEmpty())) {
                    message = sender.sendMessage(group, sender, inputField.getText());
                    createChat(message);
                }
            }
    }

    public boolean checkPassword() {
        boolean isMatch = BCrypt.checkpw(password, sender.getPassword());
        return isMatch;
    }

    public void listAnnouncements(ActionEvent event) {
        chatBox.getChildren().clear();
        contactLabel.setText("Announcements");

        for (Message message : announcement.getMessages()) {
            Label chatMessage = new Label(message.getContent());
            chatMessage.setWrapText(true);

            VBox chatWindow = new VBox(chatMessage);
            chatWindow.setPadding(new Insets(5));
            chatWindow.setAlignment(Pos.TOP_LEFT);
            chatMessage.setTextAlignment(TextAlignment.LEFT);

            // Scroll to the bottom
            scrollPane.layout();
            scrollPane.setVvalue(1.0);

            chatBox.getChildren().add(chatWindow);
        }
    }
}