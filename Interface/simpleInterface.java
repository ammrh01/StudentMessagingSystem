package com.myjfx.simplefx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class simpleInterface extends Application {
    public void start(Stage primaryStage) throws Exception {
        Person[] studentlist = new Person[3];

        studentlist[0] = new Student(1, "KICT", 240000, "Bob", true, "bob@gmail.com", 001, "Student");
        studentlist[1] = new Student(1, "KICT", 240000, "Alice", true, "alice@gmail.com", 002, "Student");
        studentlist[2] = new Student(1, "KICT", 240000, "Michael", true, "michael@gmail.com", 003, "Student");

        Chat[] chatList = new Chat[2];
        //Between bob and alice
        chatList[0] = new Chat();
        chatList[0].addParticipant(studentlist[0]);
        chatList[0].addParticipant(studentlist[1]);
        studentlist[1].getChats().add(chatList[0]);
        studentlist[0].getChats().add(chatList[0]);

        //Between Alice and Michael
        chatList[1] = new Chat();
        chatList[1].addParticipant(studentlist[1]);
        chatList[1].addParticipant(studentlist[2]);
        studentlist[1].getChats().add(chatList[1]);
        studentlist[2].getChats().add(chatList[1]);



        // Login page
        TextField username = new TextField();
        username.setPromptText("Username");

        Button login = new Button("Login");

        VBox loginpage = new VBox(10, username, login);
        loginpage.setPadding(new Insets(10));
        loginpage.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(loginpage, 640, 480);

        // Message page
        VBox chatbox = new VBox(10);
        chatbox.setPadding(new Insets(10));
        chatbox.setAlignment(Pos.TOP_LEFT);

        Label contact = new Label();

        ScrollPane scrollPane1 = new ScrollPane(chatbox);
        scrollPane1.setFitToWidth(true);
        scrollPane1.setPrefHeight(480);

        TextField textField = new TextField();
        textField.setPromptText("Type a message...");

        Button button = new Button("Send Message");
        Text currentProfile = new Text();
        Button shiftMessage = new Button("Sender");
        Button logOut = new Button("Log out");

        shiftMessage.setOnAction(e -> {
            if (shiftMessage.getText().equals("Sender")) {
                shiftMessage.setText("Receiver");
            } else if (shiftMessage.getText().equals("Receiver")) {
                shiftMessage.setText("Sender");
            }
        });

        VBox layout1 = new VBox(10, contact, scrollPane1, textField, button, shiftMessage);
        layout1.setAlignment(Pos.CENTER);
        layout1.setPadding(new Insets(10));

        // Profile name, and log out button
        VBox layout2 = new VBox(10, layout1, currentProfile, logOut);
        layout2.setAlignment(Pos.TOP_LEFT);
        layout2.setPadding(new Insets(10));
        //StackPane root = new StackPane(profileLayout);

        //Contacts list
        VBox contactslayout = new VBox(10);
        contactslayout.setPadding(new Insets(10));
        contactslayout.setAlignment(Pos.TOP_CENTER);

        BorderPane bPane = new BorderPane();
        bPane.setCenter(layout2);
        bPane.setLeft(contactslayout);

        Scene scene2 = new Scene(bPane, 640, 480);

        login.setOnAction(e -> {
            for (int i = 0; i < studentlist.length; i++) {
                if (username.getText().equals(studentlist[i].getUsername())) {
                    currentProfile.setText(username.getText());
                    primaryStage.setScene(scene2);
                    username.clear();


                    // List down all available contacts
                    for (int j = 0; j < studentlist.length; j++ ) {
                        for (Chat chat : studentlist[j].chats) {
                            if (chat.hasParticipants(studentlist[i], studentlist[j])) {
                                if (studentlist[j].getUsername() != studentlist[i].getUsername()) {
                                System.out.println(studentlist[j].getUsername());
                                Button buttonContact = new Button(studentlist[j].getUsername());
                                contactslayout.getChildren().add(buttonContact);
                                    Controller handleMessage = new Controller(chatbox, textField, scrollPane1, chatList, studentlist[i], studentlist[j], contact, buttonContact, currentProfile.getText());
                                    buttonContact.setOnAction(handleMessage::openChat);
                                    button.setOnAction(handleMessage::handleSend);
                                }
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
            contactslayout.getChildren().clear();
            button.setOnAction(null);
        });

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Simple FX Interface");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
