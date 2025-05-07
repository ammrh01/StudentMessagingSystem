import java.util.Scanner;
public class MainClass {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String currentsession;
    int choice = -1;
    Person userLogged = null;

    Person[] userlist = new Person[2];

    userlist[0] = new Student(1, "KICT", 2415, "Bobby Bob", true, "bob@gmail.com", 103, "Student");
    
    userlist[1] = new Student(1, "KICT", 2416, "Alicey Alice", true, "alice@gmail.com", 104, "Student");

    System.out.println("Login (enter name): ");
    for (int i = 0; i < userlist.length; i++) {
      System.out.printf("%d: %s\n", i+1, userlist[i].getUsername());
    }

    System.out.print("> ");
    currentsession = in.nextLine();

    for (int i = 0; i < userlist.length; i++) {
      if (currentsession.contains(userlist[i].getUsername())) {
        userLogged = (Student) userlist[i];
        System.out.println("Hello, " + userLogged.getUsername() + ". What do you want to do?");
      }
    }

    while (choice != 0) {
    choice = Options(in, userlist, userLogged);

    switch(choice) {
      case 1: getChats(userLogged); break;
      case 2: sendAMessage(in, userLogged, userlist); break;
      case 3: openAChat(in, userLogged); break;
    }
    }

  }

  public static int Options(Scanner in, Person[] userlist, Person currentUser) {
    int choice;
    System.out.println("1. View chats\n2. Send message\n3. Open chat\n");
    System.out.print("> ");
    choice = in.nextInt();

    return choice;
  }


  public static void sendAMessage(Scanner in, Person currentUser, Person[] userlist) {
    String sendTo;
    String message;
    in.nextLine();
    System.out.print("Send to: ");
    sendTo = in.nextLine();
    System.out.print("Enter message: ");
    message = in.nextLine();

    for (int i = 0; i < userlist.length; i++) {
      if (userlist[i].getUsername().equals(sendTo)) {
        currentUser.sendMessage(userlist[i], message);
      }
    }
  }

  public static void getChats(Person currentuser) {
    currentuser.viewChats();
  }

  public static void openAChat(Scanner in, Person currentUser) {
    String name;

    System.out.print(">");
    name = in.nextLine();

    currentUser.openChat(name);
  }
}
