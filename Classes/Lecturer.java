import java.util.Date;

public class Lecturer extends Person{
    private int lecturerId;
    private String departmentName;
    private String coursesTaught;
    private String officeLocation;

    public Lecturer() {
    }

    public Lecturer(String coursesTaught, String departmentName, int lecturerId, String officeLocation, String Username, boolean status, String userEmail, int userId, String userRole) {
        super(Username, status, userEmail, userId, userRole);
        this.coursesTaught = coursesTaught;
        this.departmentName = departmentName;
        this.lecturerId = lecturerId;
        this.officeLocation = officeLocation;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getCoursesTaught() {
        return coursesTaught;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    @Override
    void openChat(int chatId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void reportUser(int userId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void sendMessage(int userId, String content, Date timestamp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void viewChats() {
        // TODO Auto-generated method stub
        
    }

    @Override
    void viewMessage(int chatId, int userId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void viewProfile() {
        System.out.println("Name: " + this.getUsername());
        System.out.println("Role: " + this.getUserRole());
        System.out.println("Department: " + this.getDepartmentName());
        System.out.println("Courses taught: " + this.getCoursesTaught());
        System.out.println("Office location: " + this.getOfficeLocation());
    }

    



}
