package Library;

import java.util.ArrayList;

public class Library {
    private static final ArrayList<Book> books = JSON.getBooks();

    // edit methods
    public static void editTitle(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setTitle(input);
            JSON.updateJsonFile();
        }
    }
    public static void editAuthor(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setAuthor(input);
            JSON.updateJsonFile();
        }
    }
    public static void editPublisher(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setPublisher(input);
            JSON.updateJsonFile();
        }
    }
    public static void editPublicationYear(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setPublicationYear(input);
            JSON.updateJsonFile();
        }
    }
    public static void editIsbn(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setIsbn(input);
            JSON.updateJsonFile();
        }
    }
    public static void editEdition(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setEdition(input);
            JSON.updateJsonFile();
        }
    }
    public static  void editTags(String input,Book edited) {
        if (books.contains(edited)) {
            edited.setTags(input);
            JSON.updateJsonFile();
        }
    }

    public static  void editTranslators(String input,Book edited) {
        if (books.contains(edited)) {
            edited.setTranslators(input);
            JSON.updateJsonFile();
        }
    }

    // can be improved
    public static void addTags(String input, Book edited) {
        if (books.contains(edited)) {
            edited.addTag(input);
            JSON.updateJsonFile();
        }
    }
    public static void removeTags(String input, Book edited) {
        if (books.contains(edited)) {
            edited.removeTag(input);
            JSON.updateJsonFile();
        }
    }
    public static void addTranslator(String input, Book edited) {
        if (books.contains(edited)) {
            edited.addTranslator(input);
            JSON.updateJsonFile();
        }
    }
    public static void removeTranslator(String input, Book edited) {
        if (books.contains(edited)) {
            edited.removeTranslator(input);
            JSON.updateJsonFile();
        }
    }

    //JSON classındaki addbook ve remowe book metodları buraya direk taşınabilir.
    public static void addBooks(String jsonFilePath) {
        JSON.addBooks(jsonFilePath);
    }
    public static void removeBook(Book removed) {
        JSON.removeBook(removed);
    }
}


