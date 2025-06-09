package project;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import java.io.*;
import java.util.Scanner;

public class mainInterface extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Person> accountList = new ArrayList<>();
        ArrayList<Chat> chatList = new ArrayList<>();
        ObservableList<Group> groupList = FXCollections.observableArrayList();

        Person admin = new Admin(999, "admin", "admin123", "admin@system.com");
        Admin adminUser = (Admin) admin;
        accountList.add(adminUser);

        try {
            File accounts = new File("C:\\Users\\ammar\\IdeaProjects\\simplefx\\src\\main\\java\\project\\accounts.txt");

            Scanner input = new Scanner(accounts);

        while (input.hasNextLine()) {
            String[] splitInfos = null;
            splitInfos = input.nextLine().split(",");
            
            if (splitInfos[5].equals("Student")) {
                adminUser.addAccount(splitInfos[0], splitInfos[1], Integer.parseInt(splitInfos[2]), Integer.parseInt(splitInfos[3]), splitInfos[4], splitInfos[5], Integer.parseInt(splitInfos[6]));
            }
            if (splitInfos[6].equals("Lecturer")) {
                adminUser.addAccount(splitInfos[0], splitInfos[1], splitInfos[2], splitInfos[3], splitInfos[4], Integer.parseInt(splitInfos[5]), splitInfos[6]);
            }
        }
        
        if (!accounts.exists()) {
            throw new IOException();
        } else {System.out.println("Accounts loaded");}
        } catch (IOException e) {
            System.out.println("Error: File does not exist");

        }
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

        Image logoImage = new Image("C:\\Users\\ammar\\IdeaProjects\\simplefx\\src\\main\\java\\project\\IIUM.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);

        bottomInfos[0] = new Label("Powered by Grup OOPS");
        bottomInfos[1] = new Label("Copyright © IIUM");

        HBox bottomInfo = new HBox(10);
        bottomInfo.setAlignment(Pos.CENTER);
        bottomInfo.setPadding(new Insets(10));
        bottomInfo.getChildren().addAll(bottomInfos);

        VBox loginFields = new VBox(10, logoView, new Label("Login"), username, password, login, helper);
        loginFields.setPadding(new Insets(10));
        loginFields.setAlignment(Pos.CENTER);

        BorderPane loginPage = new BorderPane();
        loginPage.setTop(topTitle);
        loginPage.setCenter(loginFields);
        loginPage.setBottom(bottomInfo);

        Scene loginScene = new Scene(loginPage, 640, 480);
        loginScene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());

        // MAIN CHAT INTERFACE SCENE
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

        Label currentProfile = new Label();
        Button logOut = new Button("Log out");

        HBox profileRight = new HBox(10, currentProfile, logOut);
        profileRight.setPadding(new Insets(10));
        profileRight.setAlignment(Pos.TOP_RIGHT);

        BorderPane profileLayout = new BorderPane();
        profileLayout.setPadding(new Insets(10));
        profileLayout.setRight(profileRight);
        profileLayout.setStyle("-fx-border-width: 0 0 1px 0; -fx-border-color: lightgrey");

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search");

        StackPane searchList = new StackPane();

        VBox searchContact = new VBox(10, searchBox, searchList);

        VBox contactsList = new VBox(10);
        contactsList.setPadding(new Insets(10));
        contactsList.setAlignment(Pos.TOP_CENTER);

        VBox contactsListWindow = new VBox(10, searchContact, contactsList);
        contactsListWindow.setPrefHeight(480);

        contactsList.setStyle("-fx-border-width: 0 1px 0 0; -fx-border-color: lightgrey");

        BorderPane bPane = new BorderPane();
        bPane.setTop(profileLayout);
        bPane.setCenter(chatLayout);
        bPane.setLeft(contactsListWindow);

        Scene chatScene = new Scene(bPane, 640, 480);
        chatScene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());

        // ADMIN SCENE
        Label adminProfile = new Label();
        Button adminLogout = new Button("Log out");

        HBox profileLayout1 = new HBox(10, adminProfile, adminLogout);
        profileLayout1.setPadding(new Insets(10));
        profileLayout1.setAlignment(Pos.TOP_RIGHT);
        profileLayout1.setStyle("-fx-border-width: 0 0 1px 0; -fx-border-color: lightgrey");

        String[] commands = {"Add Account", "Remove Account", "Make Announcement"};
        Button[] commandList = new Button[commands.length];

        for (int i = 0; i < commandList.length; i++) {
            commandList[i] = new Button(commands[i]);
        }

        VBox adminCommands = new VBox(10);
        adminCommands.setPadding(new Insets(10));
        adminCommands.setAlignment(Pos.CENTER);
        adminCommands.getChildren().addAll(commandList);

        adminCommands.setStyle("-fx-border-width: 0 0 0 1px; -fx-border-color: lightgrey");

        Label reportLabel = new Label("Reports");

        VBox reportList = new VBox(10, reportLabel);
        reportList.setPadding(new Insets(10));
        reportList.setAlignment(Pos.TOP_CENTER);

        reportList.setStyle("-fx-border-width: 0 1px 0 0; -fx-border-color: lightgrey");

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
        adminScene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
        profile.setOnAction(e -> showProfileScene(primaryStage, accountList, contact, chatScene));

        login.setOnAction(e -> {
        try {
            Controller handlePassword;
            Boolean checkPass;
            for (Person users : accountList) {
                if (username.getText().equals(users.getUsername())) {
                    if (users instanceof Student) {
                        handlePassword = new Controller(password.getText(), users);
                        checkPass = handlePassword.checkPassword();
                        if (checkPass) {
                            loginUser(accountList, username, currentProfile, primaryStage, chatScene, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList, searchBox, searchList, groupList, announcements);
                            profileLayout.setLeft(registerGroup);
                        } else {
                            throw new Exception("Error: Invalid password. Please try again");
                        }
                    } else if (users instanceof Admin) {
                        adminLogin(username.getText(), adminProfile, primaryStage, adminScene, username, contactsList, chatbox);
                        listReports(adminUser, reportList);
                    } else if (users instanceof Lecturer) {
                        handlePassword = new Controller(password.getText(), users);
                        checkPass = handlePassword.checkPassword();
                        if (checkPass) {
                            loginUser(accountList, username, currentProfile, primaryStage, chatScene, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList, searchBox, searchList, groupList, announcements);
                            profileLayout.setLeft(createGroup);
                        } else {
                            throw new Exception("Error: Invalid password. Please try again");
                        }
                    }
                }
            }
        } catch (Exception loginExc) {
            helper.setText(loginExc.getMessage());
        }
        });

        createGroup.setOnAction(e -> createGroupscene(primaryStage, groupList, chatScene, currentProfile));
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
            helper.setText("");
            checkAccounts(accountList, adminUser, chatList);
        });

        adminLogout.setOnAction(e -> {
            currentProfile.setText("");
            password.clear();
            primaryStage.setScene(loginScene);
            reportList.getChildren().clear();
            sendButton.setOnAction(null);
            bPane.setCenter(chatLayout);
            helper.setText("");
            checkAccounts(accountList, adminUser, chatList);
        });

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Student Messaging System for i-Ta'leem");
        primaryStage.show();
    }

    private void updateContacts(Person currentUser, VBox chatbox, TextField messageField, ScrollPane chatboxWindow, Label contact, Button sendButton, VBox contactsList, ObservableList<Group> groupList, Chat announcementChat) {
        contactsList.getChildren().clear();
        Button announcement = new Button("Announcements");
        Controller handleAnnouncement = new Controller(chatbox, chatboxWindow, announcementChat, contact, currentUser);
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
            }
        }
    }

    private void adminLogin(String enteredUsername, Label currentProfile, Stage primaryStage, Scene adminScene, TextField username, VBox contactsList, VBox chatbox) {
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
                VBox profileBox = new VBox(10, nameLabel, emailLabel, roleLabel);
                if (person instanceof Student) {
                    Student student = (Student) person;
                    Label matricLabel = new Label("Matric No: " + student.getMatricNumber());
                    Label majorLabel = new Label("Major: " + student.getMajorCourse());
                    Label yearLabel = new Label("Academic Year: " + student.getAcademicYear());
                    profileBox.getChildren().addAll(matricLabel, majorLabel, yearLabel);
                }
                Button backButton = new Button("Back");
                backButton.setOnAction(f -> primaryStage.setScene(chatScene));
                profileBox.getChildren().add(backButton);
                profileBox.setPadding(new Insets(20));
                profileBox.setAlignment(Pos.CENTER);
                Scene profileScene = new Scene(profileBox, 400, 300);
                profileScene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
                primaryStage.setScene(profileScene);
            }
        }
    }

    private void showGroupRegistrationScene(Stage primaryStage, ArrayList<Person> accountList, Scene chatScene, Label currentProfile, ObservableList<Group> groupList) {
        TableView<Group> groupTable = new TableView<>();
        TableColumn<Group, String> groupNameCol = new TableColumn<>("Group Name");
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        TableColumn<Group, String> membersCol = new TableColumn<>("Members");
        membersCol.setCellValueFactory(new PropertyValueFactory<>("membersList"));
        TableColumn<Group, Void> joinCol = new TableColumn<>("Join Group");
        refreshTable(groupTable, groupList);
        TextField newGroupName = new TextField();
        newGroupName.setPromptText("Enter group name");
        Button createGroupBtn = new Button("Create New Group");
        createGroupBtn.setOnAction(e -> {
            if (!newGroupName.getText().isEmpty()) {
                Group newGroup = new Group(newGroupName.getText());
                groupTable.getItems().add(newGroup);
                newGroupName.clear();
            }
        });
        Student currentUser = null;
        for (Person users : accountList) {
            if (currentProfile.getText().equals(users.getUsername())) {
                currentUser = (Student) users;
            }
        }
        Student finalCurrentUser = currentUser;
        joinCol.setCellFactory(new Callback<TableColumn<Group, Void>, TableCell<Group, Void>>() {
            @Override
            public TableCell<Group, Void> call(final TableColumn<Group, Void> param) {
                return new TableCell<Group, Void>() {
                    private final Button joinButton = new Button("Join");
                    {
                        joinButton.setOnAction((ActionEvent event) -> {
                            Group group = getTableView().getItems().get(getIndex());
                            if (!group.getMembers().contains(finalCurrentUser)) {
                                finalCurrentUser.joinGroup(group);
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
        backButton.setOnAction(e -> primaryStage.setScene(chatScene));
        VBox layout = new VBox(10, new Label("Group Registration"), newGroupName, createGroupBtn, groupTable, backButton);
        layout.setPadding(new Insets(10));
        Scene groupScene = new Scene(layout, 600, 400);
        groupScene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
        primaryStage.setScene(groupScene);
    }

    private void refreshTable(TableView<Group> groupTable, ObservableList<Group> groupList) {
        groupTable.setItems(groupList);
        groupTable.refresh();
    }

    private void createGroupscene(Stage primaryStage, ObservableList<Group> groupList, Scene scene2, Label currentProfile) {
        primaryStage.setTitle("Create Group");
        Label label1 = new Label("Create Group");
        Label label2 = new Label("Group Count");
        Button createbtn = new Button("Create");
        Button backButton = new Button("Back to Login");
        Label output = new Label();
        TextField newGroupName = new TextField();
        newGroupName.setPromptText("Enter group name");
        TextField newGroupCount = new TextField();
        newGroupCount.setPromptText("Enter group count");
        VBox vb = new VBox(10);
        vb.getChildren().addAll(label1, newGroupName, label2, newGroupCount, createbtn, output, backButton);
        Scene scene2a = new Scene(vb, 500, 500);
        scene2a.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
        primaryStage.setScene(scene2a);
        primaryStage.show();
        createbtn.setOnAction(e -> {
            if (!newGroupName.getText().isEmpty()) {
                for (int i = 0; i < Integer.parseInt(newGroupCount.getText()); i++) {
                    groupList.add(new Group(newGroupName.getText() + i));
                }
                String groupsAdded = "Group added:\n";
                for (Group listGroups : groupList) {
                    groupsAdded += listGroups.getGroupName() + "\n";
                }
                output.setText(groupsAdded);
            }
        });
        backButton.setOnAction(e -> primaryStage.setScene(scene2));
    }

    private void checkAccounts(ArrayList<Person> accountList, Admin adminUser, ArrayList<Chat> chatList) {
        for (Person accounts : adminUser.getAccounts()) {
            if (!(accountList.contains(accounts))) {
                accountList.add(accounts);
            }
        }
        for (Person users : accountList) {
            users.setPassword("password");
            chatList.addAll(users.getChats());
        }
    }

    private void listReports(Admin adminUser, VBox reportList) {
        ArrayList<String> listOptions = new ArrayList<>();
        for (Person allUsers : adminUser.getAccounts()) {
            for (Message reports : allUsers.getMessageReports()) {
                listOptions.add(reports.getSender().getUsername() + ": " + reports.getContent());
            }
        }
        ListView<String> reportArea = new ListView<>(FXCollections.observableArrayList(listOptions));
        reportArea.setMaxHeight(250);
        reportArea.setMaxWidth(150);
        reportList.getChildren().addAll(reportArea);
    }

    public static void main(String[] args) {
        launch(args);
    }
}