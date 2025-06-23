import java.util.List;
import java.sql.Date;
import java.util.Scanner;

import com.libraryapp.DAO.AuthorDAO;
import com.libraryapp.DAO.BookDAO;
import com.libraryapp.DAO.CategoryDAO;
import com.libraryapp.DAO.CustomerDAO;
import com.libraryapp.daoimpl.AuthorDAOimpl;
import com.libraryapp.daoimpl.BookDAOimpl;
import com.libraryapp.daoimpl.CategoryDAOimpl;
import com.libraryapp.daoimpl.CustomerDAOimpl;
import com.libraryapp.model.Author;
import com.libraryapp.model.Book;
import com.libraryapp.model.Category;
import com.libraryapp.model.Member;

public class LibrarySystemApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthorDAO authorDAO = new AuthorDAOimpl();
        BookDAO bookDAO = new BookDAOimpl();
        CategoryDAO categoryDAO = new CategoryDAOimpl();
        CustomerDAO customerDAO = new CustomerDAOimpl();

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Manage Authors");
            System.out.println("2. Manage Books");
            System.out.println("3. Manage Categories");
            System.out.println("4. Manage Members");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1: // Manage Authors
                    manageAuthors(sc, authorDAO);
                    break;
                case 2: // Manage Books
                    manageBooks(sc, bookDAO);
                    break;
                case 3: // Manage Categories
                    manageCategories(sc, categoryDAO);
                    break;
                case 4: // Manage Members
                    manageMembers(sc, customerDAO);
                    break;
                case 5: // Exit
                    System.out.println("Exiting Library Management System");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void manageAuthors(Scanner sc, AuthorDAO authorDAO) {
        while (true) {
            System.out.println("\n--- Author Management ---");
            System.out.println("1. Add Author");
            System.out.println("2. Update Author");
            System.out.println("3. Get Author Details");
            System.out.println("4. Delete Author");
            System.out.println("5. Display All Authors");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1: // Add Author
                    System.out.print("Enter author ID: ");
                    int authorId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter author name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter author bio: ");
                    String bio = sc.nextLine();
                    Author newAuthor = new Author(authorId, name, bio);
                    authorDAO.addAuthor(newAuthor);
                    System.out.println("Author added successfully!");
                    break;

                case 2: // Update Author
                    System.out.print("Enter author ID to update: ");
                    int updateAuthorId = sc.nextInt();
                    sc.nextLine();
                    Author authorToUpdate = authorDAO.getAuthor(updateAuthorId);
                    if (authorToUpdate != null) {
                        System.out.println("- - - Give New Data or Press Enter to skip - - -");
                        System.out.print("Enter new name (current: " + authorToUpdate.getName() + "): ");
                        String newName = sc.nextLine();
                        if (!newName.isEmpty()) authorToUpdate.setName(newName);
                        System.out.print("Enter new bio (current: " + authorToUpdate.getBio() + "): ");
                        String newBio = sc.nextLine();
                        if (!newBio.isEmpty()) authorToUpdate.setBio(newBio);
                        authorDAO.updateAuthor(authorToUpdate);
                        System.out.println("Author updated successfully!");
                    } else {
                        System.out.println("Author not found with ID: " + updateAuthorId);
                    }
                    break;

                case 3: // Get Author Details
                    System.out.print("Enter author ID to retrieve: ");
                    int getAuthorId = sc.nextInt();
                    Author author = authorDAO.getAuthor(getAuthorId);
                    if (author != null) {
                        System.out.println("Author Details: ");
                        System.out.println("\nAuthor ID: " + author.getAuthorId());
                        System.out.println("Name: " + author.getName());
                        System.out.println("Bio: " + author.getBio());
            
                    } else {
                        System.out.println("Author not found with ID: " + getAuthorId);
                    }
                    break;

                case 4: // Delete Author
                    System.out.print("Enter author ID to delete: ");
                    int deleteAuthorId = sc.nextInt();
                    authorDAO.deleteAuthor(deleteAuthorId);
                    System.out.println("Author deleted successfully!");
                    break;

                case 5: // Display All Authors
                    List<Author> authors = authorDAO.getAllAuthors();
                    if (authors.isEmpty()) {
                        System.out.println("No authors found.");
                    } else {
                        for (Author a : authors) {
                        	 System.out.println("\nAuthor ID: " + a.getAuthorId());
                             System.out.println("Name: " + a.getName());
                             System.out.println("Bio: " + a.getBio());
                             System.out.println("----------------------------------------------------------------");
                        }
                    }
                    break;

                case 6: // Back to Main Menu
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void manageBooks(Scanner sc, BookDAO bookDAO) {
        while (true) {
            System.out.println("\n--- Book Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Get Book Details");
            System.out.println("4. Delete Book");
            System.out.println("5. Display All Books");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1: // Add Book
                    System.out.print("Enter book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author ID: ");
                    int authorId = sc.nextInt();
                    System.out.print("Enter published year: ");
                    int publishedYear = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Is available (true/false): ");
                    boolean available = sc.nextBoolean();
                    System.out.print("Enter category ID: ");
                    int categoryId = sc.nextInt();
                    Book newBook = new Book(bookId, title, authorId, publishedYear, genre, available, categoryId);
                    bookDAO.addBook(newBook);
                    System.out.println("Book added successfully!");
                    break;

                case 2: // Update Book
                    System.out.print("Enter book ID to update: ");
                    int updateBookId = sc.nextInt();
                    sc.nextLine();
                    Book bookToUpdate = bookDAO.getBook(updateBookId);
                    if (bookToUpdate != null) {
                        System.out.println("- - - Give New Data or Press Enter to skip - - -");
                        System.out.print("Enter new title (current: " + bookToUpdate.getTitle() + "): ");
                        String newTitle = sc.nextLine();
                        if (!newTitle.isEmpty()) bookToUpdate.setTitle(newTitle);
                        System.out.print("Enter new author ID (current: " + bookToUpdate.getAuthorId() + "): ");
                        String newAuthorId = sc.nextLine();
                        if (!newAuthorId.isEmpty()) bookToUpdate.setAuthorId(Integer.parseInt(newAuthorId));
                        System.out.print("Enter new published year (current: " + bookToUpdate.getPublishedYear() + "): ");
                        String newPublishedYear = sc.nextLine();
                        if (!newPublishedYear.isEmpty()) bookToUpdate.setPublishedYear(Integer.parseInt(newPublishedYear));
                        System.out.print("Enter new genre (current: " + bookToUpdate.getGenre() + "): ");
                        String newGenre = sc.nextLine();
                        if (!newGenre.isEmpty()) bookToUpdate.setGenre(newGenre);
                        System.out.print("Is available (current: " + bookToUpdate.isAvailable() + "): ");
                        String newAvailable = sc.nextLine();
                        if (!newAvailable.isEmpty()) bookToUpdate.setAvailable(Boolean.parseBoolean(newAvailable));
                        System.out.println("Book updated successfully!");
                    } else {
                        System.out.println("Book not found with ID: " + updateBookId);
                    }
                    break;

                case 3: // Get Book Details
                    System.out.print("Enter book ID to retrieve: ");
                    int getBookId = sc.nextInt();
                    Book book = bookDAO.getBook(getBookId);
                    if (book != null) {
                        System.out.println("Book Details: ");
                        System.out.println("\nBook ID: " + book.getBookId());
                        System.out.println("Title: " + book.getTitle());
                        System.out.println("Author ID: " + book.getAuthorId());
                        System.out.println("Published Year: " + book.getPublishedYear());
                        System.out.println("Genre: " + book.getGenre());
                        System.out.println("Available: " + book.isAvailable());
                        System.out.println("Category ID: " + book.getCategoryId());
                    } else {
                        System.out.println("Book not found with ID: " + getBookId);
                    }
                    break;

                case 4: // Delete Book
                    System.out.print("Enter book ID to delete: ");
                    int deleteBookId = sc.nextInt();
                    bookDAO.deleteBook(deleteBookId);
                    System.out.println("Book deleted successfully!");
                    break;

                case 5: // Display All Books
                    List<Book> books = bookDAO.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        for (Book b : books) {
                        	System.out.println("\nBook ID: " + b.getBookId());
                            System.out.println("Title: " + b.getTitle());
                            System.out.println("Author ID: " + b.getAuthorId());
                            System.out.println("Published Year: " + b.getPublishedYear());
                            System.out.println("Genre: " + b.getGenre());
                            System.out.println("Available: " + b.isAvailable());
                            System.out.println("Category ID: " + b.getCategoryId());
                            System.out.println("----------------------------------------------------------------");
                        }
                    }
                    break;

                case 6: // Back to Main Menu
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void manageCategories(Scanner sc, CategoryDAO categoryDAO) {
        while (true) {
            System.out.println("\n--- Category Management ---");
            System.out.println("1. Add Category");
            System.out.println("2. Update Category");
            System.out.println("3. Get Category Details");
            System.out.println("4. Delete Category");
            System.out.println("5. Display All Categories");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1: // Add Category
                    System.out.print("Enter category ID: ");
                    int categoryId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter category name: ");
                    String categoryName = sc.nextLine();
                    Category newCategory = new Category(categoryId, categoryName);
                    categoryDAO.addCategory(newCategory);
                    System.out.println("Category added successfully!");
                    break;

                case 2: // Update Category
                    System.out.print("Enter category ID to update: ");
                    int updateCategoryId = sc.nextInt();
                    sc.nextLine();
                    Category categoryToUpdate = categoryDAO.getCategory(updateCategoryId);
                    if (categoryToUpdate != null) {
                        System.out.println("- - - Give New Data or Press Enter to skip - - -");
                        System.out.print("Enter new name (current: " + categoryToUpdate.getCategoryName() + "): ");
                        String newName = sc.nextLine();
                        if (!newName.isEmpty()) categoryToUpdate.setCategoryName(newName);
                        categoryDAO.updateCategory(categoryToUpdate);
                        System.out.println("Category updated successfully!");
                    } else {
                        System.out.println("Category not found with ID: " + updateCategoryId);
                    }
                    break;

                case 3: // Get Category Details
                    System.out.print("Enter category ID to retrieve: ");
                    int getCategoryId = sc.nextInt();
                    Category category = categoryDAO.getCategory(getCategoryId);
                    if (category != null) {
                        System.out.println("Category Details: ");
                        System.out.println("\nCategory ID: " + category.getCategoryId());
                        System.out.println("Name: " + category.getCategoryName());
                    } else {
                        System.out.println("Category not found with ID: " + getCategoryId);
                    }
                    break;

                case 4: // Delete Category
                    System.out.print("Enter category ID to delete: ");
                    int deleteCategoryId = sc.nextInt();
                    categoryDAO.deleteCategory(deleteCategoryId);
                    System.out.println("Category deleted successfully!");
                    break;

                case 5: // Display All Categories
                    List<Category> categories = categoryDAO.getAllCategories();
                    if (categories.isEmpty()) {
                        System.out.println("No categories found.");
                    } else {
                        for (Category c : categories) {
                        	System.out.println("\nCategory ID: " + c.getCategoryId());
                            System.out.println("Name: " + c.getCategoryName());
                            System.out.println("----------------------------------------------------------------");
                        }
                    }
                    break;

                case 6: // Back to Main Menu
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void manageMembers(Scanner sc, CustomerDAO customerDAO) {
        while (true) {
            System.out.println("\n--- Member Management ---");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. Get Member Details");
            System.out.println("4. Delete Member");
            System.out.println("5. Display All Members");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1: // Add Member
                    System.out.print("Enter member ID: ");
                    int memberId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter join date (YYYY-MM-DD): ");
                    String joinDateStr = sc.nextLine();
                    Date joinDate = java.sql.Date.valueOf(joinDateStr);
                    System.out.print("Enter membership type: ");
                    String membershipType = sc.nextLine();
                    Member newMember = new Member(memberId, name, email, phone, joinDate, membershipType);
                    customerDAO.addCustomer(newMember);
                    System.out.println("Member added successfully!");
                    break;

                case 2: // Update Member
                    System.out.print("Enter member ID to update: ");
                    int updateMemberId = sc.nextInt();
                    sc.nextLine();
                    Member memberToUpdate = customerDAO.getCustomer(updateMemberId);
                    if (memberToUpdate != null) {
                        System.out.println("- - - Give New Data or Press Enter to skip - - -");
                        System.out.print("Enter new name (current: " + memberToUpdate.getName() + "): ");
                        String newName = sc.nextLine();
                        if (!newName.isEmpty()) memberToUpdate.setName(newName);
                        System.out.print("Enter new email (current: " + memberToUpdate.getEmail() + "): ");
                        String newEmail = sc.nextLine();
                        if (!newEmail.isEmpty()) memberToUpdate.setEmail(newEmail);
                        System.out.print("Enter new phone (current: " + memberToUpdate.getPhone() + "): ");
                        String newPhone = sc.nextLine();
                        if (!newPhone.isEmpty()) memberToUpdate.setPhone(newPhone);
                        System.out.print("Enter new join date (YYYY-MM-DD, current: " + memberToUpdate.getJoinDate() + "): ");
                        String newJoinDateStr = sc.nextLine();
                        if (!newJoinDateStr.isEmpty()) memberToUpdate.setJoinDate(java.sql.Date.valueOf(newJoinDateStr));
                        System.out.print("Enter new membership type (current: " + memberToUpdate.getMembershipType() + "): ");
                        String newMembershipType = sc.nextLine();
                        if (!newMembershipType.isEmpty()) memberToUpdate.setMembershipType(newMembershipType);
                        customerDAO.updateCustomer(memberToUpdate);
                        System.out.println("Member updated successfully!");
                    } else {
                        System.out.println("Member not found with ID: " + updateMemberId);
                    }
                    break;

                case 3: // Get Member Details
                    System.out.print("Enter member ID to retrieve: ");
                    int getMemberId = sc.nextInt();
                    Member member = customerDAO.getCustomer(getMemberId);
                    if (member != null) {
                        System.out.println("Member Details: ");
                        System.out.println("\nMember ID: " + member.getMemberId());
                        System.out.println("Name: " + member.getName());
                        System.out.println("Email: " + member.getEmail());
                        System.out.println("Phone: " + member.getPhone());
                        System.out.println("Join Date: " + member.getJoinDate());
                        System.out.println("Membership Type: " + member.getMembershipType());
                        
                    } else {
                        System.out.println("Member not found with ID: " + getMemberId);
                    }
                    break;

                case 4: // Delete Member
                    System.out.print("Enter member ID to delete: ");
                    int deleteMemberId = sc.nextInt();
                    customerDAO.deleteCustomer(deleteMemberId);
                    System.out.println("Member deleted successfully!");
                    break;

                case 5: // Display All Members
                    List<Member> members = customerDAO.getAllCustomers();
                    if (members.isEmpty()) {
                        System.out.println("No members found.");
                    } else {
                        for (Member m : members) {
                        	 System.out.println("\nMember ID: " + m.getMemberId());
                             System.out.println("Name: " + m.getName());
                             System.out.println("Email: " + m.getEmail());
                             System.out.println("Phone: " + m.getPhone());
                             System.out.println("Join Date: " + m.getJoinDate());
                             System.out.println("Membership Type: " + m.getMembershipType());
                             System.out.println("----------------------------------------------------------------");
                         
                        }
                    }
                    break;

                case 6: // Back to Main Menu
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}