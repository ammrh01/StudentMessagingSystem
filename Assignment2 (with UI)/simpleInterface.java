package com.myjfx.simplefx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import java.util.ArrayList;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class simpleInterface extends Application {
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Person> accountList = new ArrayList<>();
        ArrayList<Chat> chatList = new ArrayList<>();
        Person admin = new Admin(999, "admin", "admin123", "admin@system.com");
        Admin adminUser = (Admin) admin;
        accountList.add(adminUser);

        ObservableList<Group> groupList = FXCollections.observableArrayList();

        adminUser.addAccount("Bob", "bob@gmail.com", 240000, 1, "KICT", "Student", 001);
        adminUser.addAccount("Alice", "alice@gmail.com", 240010, 2, "KICT", "Student", 002);
        adminUser.addAccount("Michael", "michael@gmail.com", 240020, 1, "KICT", "Student", 003);

        adminUser.addAccount("OOP", "IT department", 240069, "KICT", "CikguSir", true, "Sir@gmail.com", 241, "Lecturer");
        checkAccounts(accountList, adminUser, chatList);

        Chat announcements = new Chat();
        announcements.setChatName("Announcements");
        announcements.addMessage(new Message("Scheduled Maintenance at 2 AM on 2//6/2025"));

        // LOGIN SCENE
        TextField username = new TextField();
        username.setPromptText("Username");
        username.setMaxWidth(200);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(200);

        Label helper = new Label();
        Button login = new Button("Sign in");
        Label topTitle = new Label("i-Ta'leem's Student Messaging Service");
        Label[] bottomInfos = new Label[2];

        VBox topLayout = new VBox(10, topTitle);
        topLayout.setAlignment(Pos.CENTER);
        topLayout.setPadding(new Insets(10));

        bottomInfos[0] = new Label("Powered by Grup OOPS");
        bottomInfos[1] = new Label("Copyright (C) IIUM");

        HBox bottomInfo = new HBox(10);
        bottomInfo.setAlignment(Pos.CENTER);
        bottomInfo.setPadding(new Insets(10));
        bottomInfo.getChildren().addAll(bottomInfos);

        VBox loginFields = new VBox(10, new Label("Login"), username, password, login, helper);
        loginFields.setPadding(new Insets(10));
        loginFields.setAlignment(Pos.CENTER);
        
        BorderPane loginPage = new BorderPane();
        loginPage.setTop(topLayout);
        loginPage.setCenter(loginFields);
        loginPage.setBottom(bottomInfo);

        Scene loginScene = new Scene(loginPage, 640, 480);

        // MAIN CHAT INTERFACE SCENE

        // CHAT WINDOW (CENTER)
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

        Button profile = new Button("View Profile");
        Button registerGroup = new Button("Register Group");
        Button createGroup = new Button("Create Group");

        HBox profileView = new HBox(10, contact, profile);
        profileView.setPadding(new Insets(10));
        profileView.setAlignment(Pos.CENTER);

        VBox chatLayout = new VBox(10, profileView, chatboxWindow, messageBox);
        chatLayout.setAlignment(Pos.TOP_CENTER);
        chatLayout.setPadding(new Insets(10));
        chatLayout.setMaxHeight(400);

        // TOP INFOS, LOGGED USER AND LOG OUT (TOP)
        Label currentProfile = new Label();
        Button logOut = new Button("Log out");

        HBox profileLayout = new HBox(10, currentProfile, logOut);
        profileLayout.setPadding(new Insets(10));
        profileLayout.setAlignment(Pos.TOP_RIGHT);
        profileLayout.setStyle("-fx-border-width: 0 0 1px 0; -fx-border-color: lightgrey;");

        // CONTACTS LIST (LEFT)
        TextField searchBox = new TextField();
        searchBox.setPromptText("Search");

        StackPane searchList = new StackPane();

        VBox searchContact = new VBox(10, searchBox, searchList);

        VBox contactsList = new VBox(10);
        contactsList.setPadding(new Insets(10));
        contactsList.setAlignment(Pos.TOP_CENTER);

        VBox contactsListWindow = new VBox(10, searchContact, contactsList);
        contactsListWindow.setPrefHeight(480);

        contactsList.setStyle("-fx-border-width: 0 1px 0 0; -fx-border-color: lightgrey;");

        // SET PANE, ORGANIZE EVERYTHING
        BorderPane bPane = new BorderPane();
        bPane.setTop(profileLayout);
        bPane.setCenter(chatLayout);
        bPane.setLeft(contactsListWindow);

        Scene chatScene = new Scene(bPane, 640, 480);

        // ADMIN SCENE

        // TOP PANE
        Label adminProfile = new Label();
        Button adminLogout = new Button("Log out");

        HBox profileLayout1 = new HBox(10, adminProfile, adminLogout);
        profileLayout1.setPadding(new Insets(10));
        profileLayout1.setAlignment(Pos.TOP_RIGHT);
        profileLayout1.setStyle("-fx-border-width: 0 0 1px 0; -fx-border-color: lightgrey;");

        // RIGHT PANE
        String[] commands = {"Add Account", "Remove Account", "Make Announcement"};
        Button[] commandList = new Button[commands.length];

        for (int i = 0; i < commandList.length; i++) {
            commandList[i] = new Button(commands[i]);
        }

        VBox adminCommands = new VBox(10);
        adminCommands.setPadding(new Insets(10));
        adminCommands.setAlignment(Pos.CENTER);
        adminCommands.getChildren().addAll(commandList);

        adminCommands.setStyle("-fx-border-width: 0 0 0 1px; -fx-border-color: lightgrey;");

        // LEFT PANE (REPORTS)

        Label reportLabel = new Label("Reports");

        VBox reportList = new VBox(10, reportLabel);
        reportList.setPadding(new Insets(10));
        reportList.setAlignment(Pos.TOP_CENTER);

        reportList.setStyle("-fx-border-width: 0 1px 0 0; -fx-border-color: lightgrey;");

        BorderPane adminLayout = new BorderPane();
        adminLayout.setTop(profileLayout1);
        adminLayout.setRight(adminCommands);
        adminLayout.setLeft(reportList);

        adminController accountHandling = new adminController(adminUser, accountList, adminLayout, commandList[0]);
        commandList[0].setOnAction(accountHandling::addAccount);

        adminController accountHandling1 = new adminController(adminUser, accountList, adminLayout, commandList[1]);
        commandList[1].setOnAction(accountHandling1::removeAccount);

        adminController announcementHandle = new adminController(announcements, adminLayout, commandList[2]);
        commandList[2].setOnAction(announcementHandle::addAnnouncement);

        Scene adminScene = new Scene(adminLayout, 640, 480);

        profile.setOnAction(e -> showProfileScene(primaryStage, accountList, contact, chatScene));

        login.setOnAction(e -> {
            for (Person users : accountList) {
                if (username.getText().equals(users.getUsername())) {
                    if (users instanceof Student) {
                        Controller handlePassword = new Controller(password.getText(), users);
                        Boolean checkPass = handlePassword.checkPassword();
                        if (checkPass) {
                            loginUser(accountList, username, currentProfile, primaryStage, chatScene, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList, searchBox, searchList, groupList, announcements);
                            profileView.getChildren().remove(createGroup);
                            profileView.getChildren().add(registerGroup);
                        } else {
                            helper.setText("Error: Invalid password. Please try again");
                        }
                    } else if (users instanceof Admin) {
                        adminLogin(username.getText(), adminProfile, primaryStage, adminScene, username, contactsList, chatbox);
                        listReports(adminUser, reportList);
                    }
                    else if (users instanceof Lecturer ) {
                        Controller handlePassword = new Controller(password.getText(), users);
                        Boolean checkPass = handlePassword.checkPassword();
                        if (checkPass) {
                            loginUser(accountList, username, currentProfile, primaryStage, chatScene, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList, searchBox, searchList, groupList, announcements);
                            profileView.getChildren().add(createGroup);
                            profileView.getChildren().remove(registerGroup);
                        }
                    }
                }
            }
        });

        createGroup.setOnAction(e ->createGroupscene(primaryStage,groupList, chatScene) );
        registerGroup.setOnAction(e -> showGroupRegistrationScene(primaryStage, accountList, chatScene, currentProfile, groupList));
        
        logOut.setOnAction(e -> {
            currentProfile.setText("");
            password.clear();
            primaryStage.setScene(loginScene);
            chatbox.getChildren().clear();
            contactsList.getChildren().clear();
            sendButton.setOnAction(null);
            bPane.setCenter(chatLayout);
            searchBox.clear();
            searchList.getChildren().clear();

            checkAccounts(accountList, adminUser, chatList);
        });

        adminLogout.setOnAction(e -> {
            currentProfile.setText("");
            password.clear();
            primaryStage.setScene(loginScene);
            reportList.getChildren().clear();
            sendButton.setOnAction(null);
            bPane.setCenter(chatLayout);

            checkAccounts(accountList, adminUser, chatList);

        });

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Student Messaging System for i-Ta'leem");
        primaryStage.show();
    }

    private void updateContacts(Person currentUser, VBox chatbox, TextField messageField, ScrollPane chatboxWindow, Label contact, Button sendButton, VBox contactsList, ObservableList<Group> groupList, Chat announcementChat) {
        contactsList.getChildren().clear();

        Button announcement = new Button("Announcements");
        Controller handleAnnouncement = new Controller(chatbox, chatboxWindow, announcementChat, contact);
        announcement.setOnAction(handleAnnouncement::listAnnouncements);
        contactsList.getChildren().add(announcement);

        Group currentGroup = null;
        for (Group groups : groupList) {
            if (groups.getMembers().contains(currentUser)) {
                currentGroup = groups;
                Button buttonGroup = new Button(groups.getGroupName());
                Controller handleMessage = new Controller(chatbox, messageField, chatboxWindow, currentGroup, currentUser, contact, sendButton);
                buttonGroup.setOnAction(e -> {
                    handleMessage.openGroupChat(e);
                    sendButton.setOnAction(handleMessage::sendGroupMessage);
                });
                contactsList.getChildren().add(buttonGroup);
            }
        }


        for (Chat chat : currentUser.getChats()) {
            for (Person receivers : chat.getParticipants()) {
                if (!(receivers.getUsername().equals(currentUser.getUsername()))) {
                    System.out.println(receivers.getUsername());
                    Button buttonContact = new Button(receivers.getUsername());
                    Controller handleMessage = new Controller(chatbox, messageField, chatboxWindow, chat, currentUser, receivers, contact, sendButton);
                    buttonContact.setOnAction(e -> {
                        handleMessage.openChat(e);
                        sendButton.setOnAction(handleMessage::handleSend);
                    });
                    contactsList.getChildren().add(buttonContact);
                }
            }
        }
    }

    private void searchContact(TextField searchContact, Person currentUser, ArrayList<Person> users, Pane searchList, VBox chatbox, TextField messageField, ScrollPane chatboxWindow, Label contact, Button sendButton, VBox contactsList, ObservableList<Group> groupList, Chat announcementChat) {
        searchList.getChildren().clear();
        for (Person person : users) {
            if (searchContact.getText().contains(person.getUsername()) && !(person.getUsername().equals(currentUser.getUsername()))) {
                StackPane rectangle = new StackPane();
                rectangle.setPadding(new Insets(10));
                rectangle.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 5;");
                Button buttonContact = new Button(person.getUsername());
                rectangle.getChildren().add(buttonContact);
                buttonContact.setOnAction(e -> {
                    currentUser.openChat(person);
                    updateContacts(currentUser, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList, groupList, announcementChat);
                });
                searchList.getChildren().add(rectangle);
            }
        }
    }

    private void loginUser(ArrayList<Person> accountList, TextField username, Label currentProfile, Stage primaryStage, Scene chatScene, VBox chatbox, TextField messageField, ScrollPane chatboxWindow, Label contact, Button sendButton, VBox contactsList, TextField searchBox, Pane searchList, ObservableList<Group> groupList, Chat announcementChat) {
        // First check admin user
        for (Person users : accountList) {
            Person currentUser;
            if (username.getText().equals(users.getUsername())) {
                currentProfile.setText(username.getText());
                primaryStage.setScene(chatScene);
                username.clear();

                currentUser = users;
                updateContacts(currentUser, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList, groupList, announcementChat);
                searchBox.addEventFilter(KeyEvent.ANY, keyEvent -> {
                    searchContact(searchBox, currentUser, accountList, searchList, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList, groupList, announcementChat);
                });
            } else {
                currentUser = null;
            }
        }
    }

    private void adminLogin(String enteredUsername,Label currentProfile, Stage primaryStage, Scene adminScene, TextField username, VBox contactsList, VBox chatbox) {
            currentProfile.setText(enteredUsername);
            primaryStage.setScene(adminScene);
            username.clear();

            contactsList.getChildren().clear();
            chatbox.getChildren().clear();
    }

    private void showProfileScene(Stage primaryStage, ArrayList<Person> accountList, Label contact, Scene chatScene) {
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
                backButton.setOnAction(f -> primaryStage.setScene(chatScene)); // go back to main chat

                profileBox.getChildren().add(backButton);
                profileBox.setPadding(new Insets(20));
                profileBox.setAlignment(Pos.CENTER);

                Scene profileScene = new Scene(profileBox, 400, 300);
                primaryStage.setScene(profileScene);
            }
        }
    }

    private void showGroupRegistrationScene(Stage primaryStage, ArrayList<Person> accountList, Scene chatScene, Label currentProfile, ObservableList<Group> groupList) {
        // Create table
        TableView<Group> groupTable = new TableView<>();

        // Create columns for table
        TableColumn<Group, String> groupNameCol = new TableColumn<>("Group Name");
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));

        TableColumn<Group, String> membersCol = new TableColumn<>("Members");
        membersCol.setCellValueFactory(new PropertyValueFactory<>("membersList"));

        TableColumn<Group, Void> joinCol = new TableColumn<>("Join Group");
        refreshTable(groupTable, groupList);


        Person currentUser = null;
        for (Person users : accountList) {
            if (currentProfile.getText().equals(users.getUsername())) {
                currentUser = users;
            }
        }

        // Add join button to each row
        Person finalCurrentUser = currentUser;
        joinCol.setCellFactory(new Callback<TableColumn<Group, Void>, TableCell<Group, Void>>() {
            @Override
            public TableCell<Group, Void> call(final TableColumn<Group, Void> param) {
                return new TableCell<Group, Void>() {
                    private final Button joinButton = new Button("Join");
                    {
                        joinButton.setOnAction((ActionEvent event) -> {
                            Group group = getTableView().getItems().get(getIndex());

                            if (!group.getMembers().contains(finalCurrentUser)) {
                                group.addMember(finalCurrentUser);
                                System.out.println("Member added! Name: " + finalCurrentUser.getUsername());
                                System.out.println("Group: " + group.getGroupName());
                                for (Person members : group.getMembers()) {
                                    System.out.println(members.getUsername());
                                }
                            }
                            refreshTable(groupTable, groupList);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(joinButton);
                        }
                    }
                };
            }
        });

        groupTable.getColumns().addAll(groupNameCol, membersCol, joinCol);

        Button backButton = new Button("Back to Login");
        backButton.setOnAction(e -> {
                    primaryStage.setScene(chatScene);
                });


        VBox layout = new VBox(10,
                new Label("Group Registration"),
                groupTable,
                backButton
        );
        layout.setPadding(new Insets(10));

        Scene groupScene = new Scene(layout, 600, 400);
        primaryStage.setScene(groupScene);
    }

    private void refreshTable(TableView<Group> groupTable, ObservableList<Group> groupList) {
        groupTable.setItems(groupList);
        groupTable.refresh();
    }

    private void createGroupscene(Stage primaryStage,ObservableList<Group>groupList,Scene scene2){
        primaryStage.setTitle("Create Group");
        Label label1= new Label("Create Group");
        Label label2= new Label("Group Count");

        Button createbtn= new Button("Create");

        Button backButton = new Button("Back to Login");
        Label output = new Label();
        TextField newGroupName = new TextField();
        newGroupName.setPromptText("Enter group name");

        TextField newGroupCount = new TextField();
        newGroupCount.setPromptText("Enter group count");
        VBox vb =new VBox(10);
        vb.getChildren().add(label1);
        vb.getChildren().add(newGroupName);
        vb.getChildren().add(label2);
        vb.getChildren().add(newGroupCount);
        vb.getChildren().add(createbtn);
        vb.getChildren().add(output);
        vb.getChildren().add(backButton);
        Scene scene2a= new Scene(vb,500,500);
        primaryStage.setScene(scene2a);
        primaryStage.show();



        createbtn.setOnAction(e -> {
            if (!newGroupName.getText().isEmpty()) {
                Group newGroup = new Group(newGroupName.getText());
                //  groupTable.getItems().add(newGroup);
                // newGroupName.clear();

                for (int i = 0; i < Integer.parseInt(newGroupCount.getText()); i++) {
                    groupList.add(new Group( newGroupName.getText() + i));
                }

                String groupsAdded = "Group added:" + "\n";
                for (Group listGroups : groupList) {
                    groupsAdded += listGroups.getGroupName() + "\n";
                }
                output.setText(groupsAdded);
            }
        } );

        backButton.setOnAction(e -> {
            primaryStage.setScene(scene2);
        });
    }

    private void checkAccounts(ArrayList<Person> accountList, Admin adminUser, ArrayList<Chat> chatList) {
        for (Person accounts : adminUser.getAccounts()) {
            if (!(accountList.contains(accounts))) {
                accountList.add(accounts);
            }
        }
        for (Person users : accountList) {
            users.setPassword("abc123");
            chatList.addAll(users.getChats());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void listReports(Admin adminUser, VBox reportList) {
        ArrayList<String> listOptions = new ArrayList<>();

        for (Person allUsers : adminUser.getAccounts()) {
            System.out.println(allUsers.getUsername());
            for (Message reports : allUsers.getMessageReports()) {
                System.out.println("Reports added");
                listOptions.add(reports.getSender().getUsername() + ": " + reports.getContent());
            }
        }

        ListView<String> reportArea = new ListView<>(FXCollections.observableArrayList(listOptions));
        reportArea.setMaxHeight(250);
        reportArea.setMaxWidth(150);

        reportArea.setOnMouseClicked(e -> {

        });

        reportList.getChildren().addAll(reportArea);
    }
}
