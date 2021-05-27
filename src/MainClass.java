import java.util.*;
import java.util.Map;

public class MainClass {
    static Scanner inInt = new Scanner(System.in);
    static Scanner inString = new Scanner(System.in);
    static Student students[] = new Student[101];

    public static void main(String args[]) {
        int librarianCode = 123;

        addBookInitial("C", 10);
        addBookInitial("C++", 10);
        addBookInitial("JAVA", 10);
        addBookInitial("Data Structures and Algorithms", 100);
        addBookInitial("Competitive Programming Book - 3", 100);
        addBookInitial("Competitive Programming Handbook", 100);

        System.out.println("" +
                "______________________________________________________________________\n" +
                "________________Welcome to the central library of PUST________________\n" +
                "______________________________________________________________________\n");

        while(true) {
            System.out.println(
                    "-> press 1 if you are a student\n" +
                    "-> press 2 if you are the librarian\n" +
                    "-> press 0 to exit\n"
            );

            int pressedKey = inInt.nextInt();

            if(pressedKey == 0) break;
            else if (pressedKey == 1) {
                System.out.print("Enter student ID: [please enter a num between 1 to 100] ");
                int ID = inInt.nextInt();
                students[ID] = new Student(ID);

                studentFunctions(ID);
            }
            else if (pressedKey == 2) {
                while (true) {
                    System.out.print("Make sure that you are the librarian. Enter the librarian code: ");

                    int librarianCodeInput = inInt.nextInt();

                    if (librarianCode == librarianCodeInput) break;
                    else System.out.println("The librarian code didn't matched. Try again.\n");
                }

                librarianFunctions();
            }
        }
    }

    static void studentFunctions(int ID){
        while(true){
            System.out.println(
                    "-> press 1 to search a book\n" +
                    "-> press 2 to take a book\n" +
                    "-> press 0 to exit\n"
            );

            int key = inInt.nextInt();

            if(key==0) break;
            else if(key == 1){
                System.out.print("Type the book name: ");
                String book = inString.nextLine();
                searcheTheBook(book);
            }
            else if(key == 2){
                if(students[ID].getTakenBooks() < 3){
                    System.out.print("Type the book name: ");
                    String book = inString.nextLine();
                    takeTheBook(book, ID);
                }
                else{
                    System.out.println("You have already taken 3 books. You can't take any book now.");
                }
            }
        }
    }

    // functions for librarian
    static void librarianFunctions(){
        while(true){
            System.out.println("-> press 1 to add a book\n" +
                    "-> press 2 to delete a book\n" +
                    "-> press 0 to exit\n");

            int key = inInt.nextInt();

            if(key == 0) break;
            else if(key == 1){
                System.out.print("Enter the book name: ");
                String book = inString.nextLine();
                System.out.print("How many books do you want to add?: ");
                int num = inInt.nextInt();
                addBook(book, num);
            }
            else if(key==2){
                System.out.print("Enter the book name: ");
                String book = inString.nextLine();
                deleteBook(book);
            }
        }
    }

    // TreeMap for storing Books
    static Map<String, Integer> map=new HashMap<String, Integer>();

    // add book function
    static void addBook(String book, int num) {
        if(!map.containsKey(book)){
            map.put(book, num);
        }
        else{
            int prev = map.get(book);
            map.put(book, prev+num);
        }
        System.out.println("The Book '" +book+"' has been added successfully.\n" +
                "Now available copies of this book: " + map.get(book) + "\n");
    }

    // add book function for initial state
    static void addBookInitial(String book, int num) {
        map.put(book, num);
    }

    // delete book function
    static void deleteBook(String book) {
        if(!map.containsKey(book) || map.get(book)==0){
            System.out.println("There are no book called '" +book+"'.");
        }
        else{
            int prev = map.get(book);
            map.put(book, prev-1);
            System.out.println("The Book '" +book+"' has been deleted successfully.\n" +
                    "Now available copies of this book: " + map.get(book) + "\n");
        }
    }

    // search book function
    static void searcheTheBook(String book){
        if(!map.containsKey(book) || map.get(book)==0){
            System.out.println("There are no book called '" +book+"'.\n");
        }
        else{
            int prev = map.get(book);
            System.out.println("Book found!");
            System.out.println("Available copy: " + map.get(book));
        }
    }

    // take book function
    static void takeTheBook(String book, int ID){
        if(!map.containsKey(book) || map.get(book)==0){
            System.out.println("Sorry. There are no book named '" +book+"'.\n");
        }
        else{
            students[ID].setTakenBooks();
            int prev = map.get(book);
            map.put(book, prev-1);
            System.out.println("You have taken this book. You will get this book in hand soon. Thank you.\n");
        }
    }
}