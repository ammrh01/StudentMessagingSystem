package com.myjfx.simplefx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class simpleInterface extends Application {
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Person> accountList = new ArrayList<>();
        Person admin = new Admin(999, "admin", "admin123", "admin@system.com");
        Admin adminUser = (Admin) admin;

        ObservableList<Group> groupList = FXCollections.observableArrayList();


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
        // In the login page section of simpleInterface.java

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
        Button registerGroup = new Button("Register Group");

        //gabung contact name dgn view profile punya button
        HBox profileView = new HBox(10, contact, profile, registerGroup);
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


        BorderPane bPane = new BorderPane();
        bPane.setTop(profileLayout);
        bPane.setCenter(chatLayout);
        bPane.setLeft(contactsListWindow);

        Scene scene2 = new Scene(bPane, 640, 480);

        profile.setOnAction(e -> showProfileScene(primaryStage, accountList, contact, scene2));

        Person currentUser;

        login.setOnAction(e -> {
            for (Person users : accountList) {
                if (users instanceof Student || users instanceof Lecturer) {
                        if (username.getText().equals(users.getUsername())) {
                            loginUser(accountList, username, currentProfile, primaryStage, scene2, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList, searchBox, searchList);
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

        registerGroup.setOnAction(e -> showGroupRegistrationScene(primaryStage, accountList, scene2, currentProfile, registerGroup, loginpage, groupList));
        
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

    private void searchContact(TextField searchContact, Person currentUser, ArrayList<Person> users, Pane searchList) {
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
                });
                searchList.getChildren().add(rectangle);
            }
        }
    }

    private void loginUser(ArrayList<Person> accountList, TextField username, Label currentProfile, Stage primaryStage, Scene scene2, VBox chatbox, TextField messageField, ScrollPane chatboxWindow, Label contact, Button sendButton, VBox contactsList, TextField searchBox, Pane searchList) {
        // First check admin user
        for (Person users : accountList) {
            Person currentUser;
            if (username.getText().equals(users.getUsername())) {
                currentProfile.setText(username.getText());
                primaryStage.setScene(scene2);
                username.clear();

                currentUser = users;
                updateContacts(currentUser, chatbox, messageField, chatboxWindow, contact, sendButton, contactsList);
                searchBox.addEventFilter(KeyEvent.ANY, keyEvent -> {
                    searchContact(searchBox, currentUser, accountList, searchList);
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

    private void showGroupRegistrationScene(Stage primaryStage, ArrayList<Person> accountList, Scene scene2, Label currentProfile, Button registerGroup, VBox loginpage, ObservableList<Group> groupList) {
        // Create table for groups
        TableView<Group> groupTable = new TableView<>();

        // Create columns
        TableColumn<Group, String> groupNameCol = new TableColumn<>("Group Name");
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));

        TableColumn<Group, String> membersCol = new TableColumn<>("Members");
        membersCol.setCellValueFactory(new PropertyValueFactory<>("membersList"));

        TableColumn<Group, Void> joinCol = new TableColumn<>("Join Group");

        Person currentUser = null;

        for (Person users : accountList) {

            if (currentProfile.getText().equals(users.getUsername())) {
                currentUser = users;

                System.out.println("Username found! " + currentUser);
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
                                refreshTable(groupTable, groupList);
                            }
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

        // Add controls for creating new groups
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

        Button backButton = new Button("Back to Login");
        backButton.setOnAction(e -> primaryStage.setScene(scene2));

        VBox layout = new VBox(10,
                new Label("Group Registration"),
                newGroupName,
                createGroupBtn,
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

    public static void main(String[] args) {
        launch(args);
    }
}
