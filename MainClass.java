public class MainClass {
  public static void main(String[] args) {
    Student Bob = new Student(1, "KICT", 2415, "Bobby Bob", true, "bob@gmail.com", 103, "Student");
    
    Student Alice = new Student(1, "KICT", 2416, "Alicey Alice", true, "alice@gmail.com", 104, "Student");

    Bob.sendMessage(Alice, "Hello alice");
    Bob.viewChats();
  }
}
