package com.myjfx.simplefx;

public class Admin {
    private int adminId;
    private String username;
    private String passwordHash;
    private String email;
    
    public Admin(){
        
    }

    public Admin(int adminId, String username, String passwordHash, String email) {
        this.adminId = adminId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }
    
    public void manageAccounts(){
        System.out.println("\n--- Manage Accounts ---");
        System.out.println("\n1.Delete account\n2.Add account\n......");
                
    }
    
    public void manageMessages(){
        System.out.println("\n--- Manage Messages ---");
        System.out.println("\n1.Delete message\n2.Search message\n......");
                
    }
    
    public void sendAnnouncement(){
        System.out.println("\n---Send Announcement ---");
        System.out.println("\n1.Broadcast message\n2.Send to all students\n......");
                
    }
    
    public void viewReports(){
        System.out.println("\n---View Reports ---");
        System.out.println("\n1.View submitted reports\n2.Search reports by category(abuse,...)\n......");
                
    }
    
}
