import java.util.Date;

public class Student extends Person {
    private int matricNumber;
    private String majorCourse;
    private int academicYear;

    public Student() {};

    public Student(int academicYear, String majorCourse, int matricNumber, String Username, boolean status, String userEmail, int userId, String userRole) {
        super(Username, status, userEmail, userId, userRole);
        this.academicYear = academicYear;
        this.majorCourse = majorCourse;
        this.matricNumber = matricNumber;
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
        System.out.println("Matric number: " + this.getMatricNumber());
        System.out.println("Faculty: " + this.getMajorCourse());
        System.out.println("Year: " + this.getAcademicYear());
    }

    public int getMatricNumber() {
        return matricNumber;
    }

    public String getMajorCourse() {
        return majorCourse;
    }

    public int getAcademicYear() {
        return academicYear;
    }
    
    
    
}
