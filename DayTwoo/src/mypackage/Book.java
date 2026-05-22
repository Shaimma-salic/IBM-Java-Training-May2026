package mypackage;

public class Book {
    
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true; 
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return available;
    }

    public void borrowBook() {
        if (available) {
            available = false;
            System.out.println("You have borrowed: " + title);
        } else {
            System.out.println("Sorry, " + title + " is currently unavailable.");
        }
    }

    public void returnBook() {
        if (!available) {
            available = true;
            System.out.println("You have returned: " + title);
        } else {
            System.out.println("This book was not borrowed: " + title);
        }
    }

    public String getInfo() {
        return "Title: " + title + ", Author: " + author + ", Available: " + available;
    }

}

