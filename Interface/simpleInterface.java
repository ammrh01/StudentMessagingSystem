package com.myjfx.simplefx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import java.util.List;
import javafx.scene.shape.Rectangle;

public class simpleInterface extends Application {
    public void start(Stage primaryStage) throws Exception {
        Person[] accountList = new Person[3];

        accountList[0] = new Student(1, "KICT", 240000, "Bob", true, "bob@gmail.com", 001, "Student");
        accountList[1] = new Student(1, "KICT", 240000, "Alice", true, "alice@gmail.com", 002, "Student");
        accountList[2] = new Student(1, "KICT", 240000, "Michael", true, "michael@gmail.com", 003, "Student");

        Chat[] chatList = new Chat[2];
        //Between bob and alice
        chatList[0] = new Chat();
        chatList[0].addParticipant(accountList[0]);
        chatList[0].addParticipant(accountList[1]);
        accountList[1].getChats().add(chatList[0]);
        accountList[0].getChats().add(chatList[0]);

        //Between Alice and Michael
        chatList[1] = new Chat();
        chatList[1].addParticipant(accountList[1]);
        chatList[1].addParticipant(accountList[2]);
        accountList[1].getChats().add(chatList[1]);
        accountList[2].getChats().add(chatList[1]);



        // Login page
        TextField username = new TextField();
        username.setPromptText("Username");
        username.setMaxWidth(200);

        Button login = new Button("Login");

        VBox loginpage = new VBox(10, username, login);
        loginpage.setPadding(new Insets(10));
        loginpage.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(loginpage, 640, 480);

        // Message page
        VBox chatbox = new VBox(10);
        chatbox.setPadding(new Insets(10));

        Label contact = new Label();

        ScrollPane chatboxWindow = new ScrollPane(chatbox);
        chatboxWindow.setFitToWidth(true);
        chatboxWindow.setPrefHeight(480);

        TextField messageField = new TextField();
        messageField.setPromptText("Type a message...");

        Button sendButton = new Button("Send Message");

        HBox messageBox = new HBox(10, messageField, sendButton);
        messageBox.setPadding(new Insets(10));
        messageBox.setAlignment(Pos.CENTER);

        VBox chatLayout = new VBox(10, contact, chatboxWindow, messageBox);
        chatLayout.setAlignment(Pos.TOP_CENTER);
        chatLayout.setPadding(new Insets(10));
        chatLayout.setMaxHeight(400);

        Text currentProfile = new Text();
        Button logOut = new Button("Log out");

        HBox profileLayout = new HBox(10, currentProfile, logOut);
        profileLayout.setPadding(new Insets(10));
        profileLayout.setAlignment(Pos.TOP_RIGHT);

        profileLayout.setStyle("-fx-border-width: 0 0 1px 0; -fx-border-color: lightgrey;");

        //Contacts list
        VBox contactsList = new VBox(10);
        contactsList.setPadding(new Insets(10));
        contactsList.setAlignment(Pos.TOP_CENTER);

        contactsList.setStyle("-fx-border-width: 0 1px 0 0; -fx-border-color: lightgrey;");


        BorderPane bPane = new BorderPane();
        bPane.setTop(profileLayout);
        bPane.setCenter(chatLayout);
        bPane.setLeft(contactsList);

        Scene scene2 = new Scene(bPane, 640, 480);



        login.setOnAction(e -> {
            for (int i = 0; i < accountList.length; i++) {
                if (username.getText().equals(accountList[i].getUsername())) {
                    currentProfile.setText(username.getText());
                    primaryStage.setScene(scene2);
                    username.clear();

                    Person currentUser = accountList[i];

                    for (Chat chat : currentUser.getChats()) {
                        for (Person receivers : chat.getParticipants()) {
                            if (!(receivers.getUsername().equals(currentUser.getUsername()))) {
                                System.out.println(receivers.getUsername());
                                Button buttonContact = new Button(receivers.getUsername());
                                Controller handleMessage = new Controller(chatbox, messageField, chatboxWindow, chat, currentUser, receivers, contact, sendButton);
                                buttonContact.setOnAction(handleMessage::openChat);
                                sendButton.setOnAction(handleMessage::handleSend);
                                contactsList.getChildren().add(buttonContact);
                            }
                        }
                    }
                }
            }
        });

        logOut.setOnAction(e -> {
            currentProfile.setText("");
            primaryStage.setScene(scene1);
            chatbox.getChildren().clear();
            contactsList.getChildren().clear();
            sendButton.setOnAction(null);
        });

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Student Messaging System for i-Ta'leem");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
