package com.myjfx.simplefx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class adminController {
    private Admin adminUser;
    private ArrayList<Person> accountList;
    private BorderPane adminLayout;
    private Button manageAccountsBtn;
    private Button makeAnnouncementsBtn;
    private Chat announcementChat;
    
    public adminController(Admin adminUser, ArrayList<Person> accountList, BorderPane adminLayout, Button manageAccountsBtn) {
        this.adminUser = adminUser;
        this.accountList = accountList;
        this.adminLayout = adminLayout;
        this.manageAccountsBtn = manageAccountsBtn;
    }

    public adminController(Chat announcementChat, BorderPane adminLayout, Button makeAnnouncementsBtn) {
        this.announcementChat = announcementChat;
        this.adminLayout = adminLayout;
        this.makeAnnouncementsBtn = makeAnnouncementsBtn;
    }

    public void addAccount(ActionEvent event) {
        VBox manageLayout = new VBox(10);
        manageLayout.setPadding(new Insets(15));

        String[] kulliyah = {"IRKHS", "KAHS", "AED", "BRIDG", "CFL", "CCC", "DENT", "EDUC", "ENGIN", "ECONS", "KICT", "IHART", "IIBF", "ISTAC",
                "KSTCL", "LAWS", "MEDIC", "NURS", "PHARM", "PLNET", "KOS", "SC4SH"};

        String[] roles = {"Student", "Lecturer"};

        // Role dropdown at the top
        ComboBox<String> user_dropdown = new ComboBox<>(FXCollections.observableArrayList(roles));
        user_dropdown.setPromptText("Select Role");

        // Common fields container
        VBox commonFields = new VBox(10);

        // Student specific fields container
        VBox studentFields = new VBox(10);

        // Lecturer specific fields container
        VBox lecturerFields = new VBox(10);

        // Feedback label
        Label feedback = new Label();

        // Common fields (appear for both roles)
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        // Student specific fields
        TextField matricField = new TextField();
        matricField.setPromptText("Matric Number");

        TextField academicYearField = new TextField();
        academicYearField.setPromptText("Academic Year");

        ComboBox<String> kulliyah_dropdown = new ComboBox<>(FXCollections.observableArrayList(kulliyah));
        kulliyah_dropdown.setPromptText("Select Kulliyah");

        // Lecturer specific fields
        TextField lecturerIdField = new TextField();
        lecturerIdField.setPromptText("Lecturer ID");

        TextField departmentField = new TextField();
        departmentField.setPromptText("Department Name");

        TextField coursesTaughtField = new TextField();
        coursesTaughtField.setPromptText("Courses Taught (comma separated)");

        TextField officeLocationField = new TextField();
        officeLocationField.setPromptText("Office Location");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        // Add fields to their respective containers
        commonFields.getChildren().addAll(
                new Label("Common Information"),
                nameField,
                emailField
        );

        studentFields.getChildren().addAll(
                new Label("Student Information"),
                matricField,
                academicYearField,
                new HBox(10, new Label("Kulliyah: "), kulliyah_dropdown)
        );

        lecturerFields.getChildren().addAll(
                new Label("Lecturer Information"),
                lecturerIdField,
                departmentField,
                coursesTaughtField,
                officeLocationField,
                usernameField
        );

        // Initially hide both role-specific fields
        studentFields.setVisible(false);
        lecturerFields.setVisible(false);

        // Role selection handler
        user_dropdown.valueProperty().addListener((obs, oldVal, newVal) -> {
            if ("Student".equals(newVal)) {
                studentFields.setVisible(true);
                lecturerFields.setVisible(false);
            } else if ("Lecturer".equals(newVal)) {
                studentFields.setVisible(false);
                lecturerFields.setVisible(true);
            } else {
                studentFields.setVisible(false);
                lecturerFields.setVisible(false);
            }
        });

        Button addBtn = new Button("Add");

        addBtn.setOnAction(e -> {
            String selectedRole = user_dropdown.getValue();
            String name = nameField.getText();
            String email = emailField.getText();

            if (selectedRole == null) {
                feedback.setText("Please select a role");
                return;
            }

            if (name.isEmpty() || email.isEmpty()) {
                feedback.setText("Name and Email are required");
                return;
            }

            if ("Student".equals(selectedRole)) {
                String matricNo = matricField.getText();
                String academicYear = academicYearField.getText();
                String currentKulliyah = kulliyah_dropdown.getValue();

                if (matricNo.isEmpty() || academicYear.isEmpty() || currentKulliyah == null) {
                    feedback.setText("Please fill all student fields");
                    return;
                }

                // Call student add method
                adminUser.addAccount(name, email, Integer.parseInt(matricNo),
                        Integer.parseInt(academicYear), currentKulliyah, selectedRole, accountList.size()+1);
                feedback.setText("Student added successfully!");

            } else if ("Lecturer".equals(selectedRole)) {
                String lecturerId = lecturerIdField.getText();
                String departmentName = departmentField.getText();
                String coursesTaught = coursesTaughtField.getText();
                String officeLocation = officeLocationField.getText();
                String username = usernameField.getText();

                if (lecturerId.isEmpty() || departmentName.isEmpty() || coursesTaught.isEmpty() ||
                        officeLocation.isEmpty() || username.isEmpty()) {
                    feedback.setText("Please fill all lecturer fields");
                    return;
                }

                // Call lecturer add method
                adminUser.addAccount(
                        coursesTaught,
                        departmentName,
                        Integer.parseInt(lecturerId),
                        officeLocation,
                        username,
                        true, // status
                        email,
                        accountList.size()+1, // userId
                        selectedRole
                );
                feedback.setText("Lecturer added successfully!");
            }

            // Clear fields after adding
            nameField.clear();
            emailField.clear();
            matricField.clear();
            academicYearField.clear();
            lecturerIdField.clear();
            departmentField.clear();
            coursesTaughtField.clear();
            officeLocationField.clear();
            usernameField.clear();
            kulliyah_dropdown.getSelectionModel().clearSelection();
        });

        manageLayout.getChildren().addAll(
                new Label("Add Account"),
                new HBox(10, new Label("Role: "), user_dropdown),
                commonFields,
                studentFields,
                lecturerFields,
                addBtn,
                feedback
        );

        manageAccountsBtn.setOnAction(ev -> {
            adminLayout.setCenter(manageLayout);
        });
    }

    public void removeAccount(ActionEvent event) {
        VBox manageLayout = new VBox();
        TextField deleteField = new TextField();
        deleteField.setPromptText("Username to delete");

        Button deleteBtn = new Button("Delete");

        Label feedback = new Label();

        // Delete function
        deleteBtn.setOnAction(e -> {
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
                new Label("Delete Account"),
                deleteField, deleteBtn,
                feedback
        );
        manageAccountsBtn.setOnAction(ev -> {
            adminLayout.setCenter(manageLayout);
        });
    }

    public void addAnnouncement(ActionEvent event) {
        Label announcementLabel = new Label("Make an announcement (Global)");
        TextArea announcementArea = new TextArea();
        Button submitAnnouncement = new Button("Submit");
        Label output = new Label();

        VBox manageLayout = new VBox(10, announcementLabel, announcementArea, submitAnnouncement, output);
        manageLayout.setPadding(new Insets(10));

        submitAnnouncement.setOnAction(e -> {
            if (announcementArea.getText().isEmpty()) {
                output.setText("Error. Must enter an announcement in text area");
            } else {
                announcementChat.addMessage(new Message(announcementArea.getText()));
            }
        });

        makeAnnouncementsBtn.setOnAction(e -> {
            adminLayout.setCenter(manageLayout);
        });



    }
}
