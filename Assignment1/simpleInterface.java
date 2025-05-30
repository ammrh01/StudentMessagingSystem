package com.myjfx.simplefx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class simpleInterface extends Application {
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Person> accountList = new ArrayList<>();
        Person admin = new Admin(999, "admin", "admin123", "admin@system.com");
        Admin adminUser = (Admin) admin;


        accountList.add(adminUser);

        adminUser.addAccount("Bob", "bob@gmail.com", 240000, 1, "KICT", "Student", 001);
        adminUser.addAccount("Alice", "alice@gmail.com", 240010, 2, "KICT", "Student", 002);
        adminUser.addAccount("Michael", "michael@gmail.com", 240020, 1, "KICT", "Student", 003);

        accountList.addAll(adminUser.getAccounts());

        ArrayList<Chat> chatList = new ArrayList<>();

        for (Person users : accountList) {
            chatList.addAll(users.getChats());
        }


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

        //profile punya button
        Button profile = new Button("View Profile");

        //gabung contact name dgn view profile punya button
        HBox profileView = new HBox(10, contact, profile);
        profileView.setPadding(new Insets(10));
        profileView.setAlignment(Pos.CENTER);

        VBox chatLayout = new VBox(10, profileView, chatboxWindow, messageBox);
        chatLayout.setAlignment(Pos.TOP_CENTER);
        chatLayout.setPadding(new Insets(10));
        chatLayout.setMaxHeight(400);

        Label currentProfile = new Label();
        Button logOut = new Button("Log out");

        HBox profileLayout = new HBox(10, currentProfile, logOut);
        profileLayout.setPadding(new Insets(10));
        profileLayout.setAlignment(Pos.TOP_RIGHT);

        profileLayout.setStyle("-fx-border-width: 0 0 1px 0; -fx-border-color: lightgrey;");

        //Contacts list

        TextField searchContact = new TextField();
        searchContact.setPromptText("Search");


        VBox contactsList = new VBox(10);
        contactsList.setPadding(new Insets(10));
        contactsList.setAlignment(Pos.TOP_CENTER);

        VBox contactsListWindow = new VBox(10, searchContact, contactsList);
        contactsListWindow.setPrefHeight(480);



        contactsList.setStyle("-fx-border-width: 0 1px 0 0; -fx-border-color: lightgrey;");


        BorderPane bPane = new BorderPane();
        bPane.setTop(profileLayout);
        bPane.setCenter(chatLayout);
        bPane.setLeft(contactsListWindow);

        Scene scene2 = new Scene(bPane, 640, 480);

        profile.setOnAction(e -> showProfileScene(primaryStage, accountList, contact, scene2));

        login.setOnAction(e -> {
            for (Person users : accountList) {
                if (users instanceof Student || users instanceof Lecturer) {
                        if (username.getText().equals(users.getUsername())) {
                            loginUser(accountList, username, currentProfile, primaryStage, scene2, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList, searchContact);
                        }
                } else if (users instanceof Admin) {
                    Admin admin1 = (Admin) users;
                    if (username.getText().equals(users.getUsername())) {
                        String enteredUsername = username.getText();
                        adminLogin(enteredUsername, admin1, accountList, currentProfile, primaryStage, scene2, username, contactsList, chatbox, bPane, profileView);
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
            bPane.setCenter(chatLayout);
        });

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Student Messaging System for i-Ta'leem");
        primaryStage.show();
    }

    private void updateContacts(Person currentUser, VBox chatbox, TextField messageField, ScrollPane chatboxWindow, Label contact, Button sendButton, VBox contactsList) {
        contactsList.getChildren().clear();
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

    private void searchContact(TextField searchContact, Person currentUser, ArrayList<Person> users, VBox contactsList) {
        contactsList.getChildren().clear();
        for (Person person : users) {
            if (searchContact.getText().contains(person.getUsername()) && !(person.getUsername().equals(currentUser.getUsername()))) {
                Button buttonContact = new Button(person.getUsername());
                buttonContact.setOnAction(e -> {
                    currentUser.openChat(person);
                });
                contactsList.getChildren().add(buttonContact);
            }
        }
    }

    private void loginUser(ArrayList<Person> accountList, TextField username, Label currentProfile, Stage primaryStage, Scene scene2, VBox chatbox, TextField messageField, ScrollPane chatboxWindow, Label contact, Button sendButton, VBox contactsList, TextField searchContact) {
        // First check admin user
        for (Person users : accountList) {
            Person currentUser;
            if (username.getText().equals(users.getUsername())) {
                currentProfile.setText(username.getText());
                primaryStage.setScene(scene2);
                username.clear();

                currentUser = users;
                updateContacts(currentUser, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList);
                searchContact.addEventFilter(KeyEvent.ANY, keyEvent -> {
                    searchContact(searchContact, currentUser, accountList, contactsList);
                });
            } else {
                currentUser = null;
            }
        }
    }

    private void adminLogin(String enteredUsername, Admin adminUser, ArrayList<Person> accountList, Label currentProfile, Stage primaryStage, Scene scene2, TextField username, VBox contactsList, VBox chatbox, BorderPane bPane, HBox profileView) {
        if (enteredUsername.equals(adminUser.getUsername())) {
            currentProfile.setText(enteredUsername);
            primaryStage.setScene(scene2);
            username.clear();

            // For admin, you can add buttons or views related to admin features here
            contactsList.getChildren().clear();
            chatbox.getChildren().clear();

            Button manageAccountsBtn = new Button("Manage Accounts");
            profileView.getChildren().add(manageAccountsBtn);

            VBox manageLayout = new VBox();
            String[] kulliyah = {"IRKHS", "KAHS", "AED", "BRIDG", "CFL", "CCC", "DENT", "EDUC", "ENGIN", "ECONS", "KICT", "IHART", "IIBF", "ISTAC",
                    "KSTCL", "LAWS", "MEDIC", "NURS", "PHARM", "PLNET", "KOS", "SC4SH"};

            String[] roles = {"Student", "Lecturer"};

            ComboBox kulliyah_dropdown = new ComboBox(FXCollections.observableArrayList(kulliyah));
            ComboBox user_dropdown = new ComboBox(FXCollections.observableArrayList(roles));

            HBox kulliyah_list = new HBox(10, new Label("Kulliyah: "), kulliyah_dropdown);
            HBox user_roles = new HBox(10, new Label("Role: "), user_dropdown);

            TextField[] accountInfo = new TextField[4];

            accountInfo[0] = new TextField();
            accountInfo[0].setPromptText("Name");

            accountInfo[1] = new TextField();
            accountInfo[1].setPromptText("Email");

            accountInfo[2] = new TextField();
            accountInfo[2].setPromptText("Matric");

            accountInfo[3] = new TextField();
            accountInfo[3].setPromptText("Academic year");


            Button addBtn = new Button("Add");

            TextField deleteField = new TextField();
            deleteField.setPromptText("Username to delete");

            Button deleteBtn = new Button("Delete");

            Label feedback = new Label();

            // Add funciton
            addBtn.setOnAction(event -> {
                String name = accountInfo[0].getText();
                String email = accountInfo[1].getText();
                String matricno = accountInfo[2].getText();
                String academicYear = accountInfo[3].getText();
                String currentKulliyah = kulliyah_dropdown.getValue().toString();
                String currentRole = user_dropdown.getValue().toString();

                if (name.isEmpty() || matricno.isEmpty() || email.isEmpty() || academicYear.isEmpty()) {
                    feedback.setText("Fill all fields");
                    return;
                }

                adminUser.addAccount(name, email, Integer.parseInt(matricno), Integer.parseInt(academicYear), currentKulliyah, currentRole, accountList.size()+1);
                feedback.setText("Added!");
            });

            // Delete function
            deleteBtn.setOnAction(event -> {
                String uname = deleteField.getText();

                boolean deleted = false;
                for (Person users : accountList) {
                    if (users != null && users.getUsername().equals(uname)) {
                        users = null;
                        feedback.setText("Deleted: " + uname);
                        deleted = true;
                        break;
                    }
                }

                if (!deleted) {
                    feedback.setText("Not found");
                }
            });

            manageLayout.getChildren().addAll(
                    new Label("Add Account"),
                    accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3],
                    kulliyah_list, user_roles, addBtn,
                    new Label("Delete Account"),
                    deleteField, deleteBtn,
                    feedback
            );

            manageAccountsBtn.setOnAction(ev -> {
                bPane.setCenter(manageLayout);
            });


            return;  // Exit so you don't continue to students login
        }
    }

    private void showProfileScene(Stage primaryStage, ArrayList<Person> accountList, Label contact, Scene scene2) {
        String contactName = contact.getText();
        for (Person person : accountList) {
            if (person.getUsername().equals(contactName) && person instanceof Student) {
                Label nameLabel = new Label("Username: " + person.getUsername());
                Label emailLabel = new Label("Email: " + person.getUserEmail());
                Label roleLabel = new Label("Role: " + person.getUserRole());

                // If it's a student, cast and show additional fields
                VBox profileBox = new VBox(10, nameLabel, emailLabel, roleLabel);
                if (person instanceof Student) {
                    Student student = (Student) person;
                    Label matricLabel = new Label("Matric No: " + student.getMatricNumber());
                    Label majorLabel = new Label("Major: " + student.getMajorCourse());
                    Label yearLabel = new Label("Academic Year: " + student.getAcademicYear());
                    profileBox.getChildren().addAll(matricLabel, majorLabel, yearLabel);
                }

                Button backButton = new Button("Back");
                backButton.setOnAction(f -> primaryStage.setScene(scene2)); // go back to main chat

                profileBox.getChildren().add(backButton);
                profileBox.setPadding(new Insets(20));
                profileBox.setAlignment(Pos.CENTER);

                Scene profileScene = new Scene(profileBox, 400, 300);
                primaryStage.setScene(profileScene);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
