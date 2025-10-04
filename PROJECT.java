import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Book class
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + (isIssued ? "Yes" : "No");
    }
}

// User class
class User {
    int userId;
    String name;

    User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String toString() {
        return "UserID: " + userId + ", Name: " + name;
    }
}

// Library class
class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    HashMap<Integer, Integer> issuedBooks = new HashMap<>(); // BookID → UserID

    // User Registration
    void registerUser(int userId, String name) {
        users.add(new User(userId, name));
        System.out.println("User registered successfully!");
    }

    // Add book
    void addBook(int id, String title, String author) {
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    // Delete book
    void deleteBook(int id) {
        for (Book b : books) {
            if (b.id == id) {
                books.remove(b);
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Issue book
    void issueBook(int bookId, int userId) {
        for (Book b : books) {
            if (b.id == bookId) {
                if (!b.isIssued) {
                    b.isIssued = true;
                    issuedBooks.put(bookId, userId);
                    System.out.println("Book issued to user ID: " + userId);
                } else {
                    System.out.println("Book already issued!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Return book with user ID verification
    void returnBook(int bookId, int userId) {
        for (Book b : books) {
            if (b.id == bookId) {
                if (b.isIssued) {
                    Integer issuedToUserId = issuedBooks.get(bookId);
                    if (issuedToUserId != null && issuedToUserId == userId) {
                        b.isIssued = false;
                        issuedBooks.remove(bookId);
                        System.out.println("Book returned successfully by user ID: " + userId);
                    } else {
                        System.out.println("This book is not issued to user ID: " + userId);
                    }
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // View all books
    void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // Search book by title
    void searchBook(String title) {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                System.out.println("Book Found: " + b);
                return;
            }
        }
        System.out.println("Book not found!");
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. User Registration");
            System.out.println("2. Add Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. View All Books");
            System.out.println("7. Search Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String uname = sc.nextLine();
                    lib.registerUser(uid, uname);
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    lib.addBook(id, title, author);
                    break;

                case 3:
                    System.out.print("Enter Book ID to delete: ");
                    int delId = sc.nextInt();
                    lib.deleteBook(delId);
                    break;

                case 4:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUserId = sc.nextInt();
                    lib.issueBook(issueId, issueUserId);
                    break;

                case 5:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int returnUserId = sc.nextInt();
                    lib.returnBook(returnId, returnUserId);
                    break;

                case 6:
                    lib.viewBooks();
                    break;

                case 7:
                    System.out.print("Enter Book Title to search: ");
                    String searchTitle = sc.nextLine();
                    lib.searchBook(searchTitle);
                    break;

                case 8:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 8);

        sc.close();
    }
}
