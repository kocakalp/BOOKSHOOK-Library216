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

    // can be improved
    public static void addTags(String input, Book edited) {
        if (books.contains(edited)) {
            if (!edited.getTags().contains(input)) {
                edited.addTag(input);
                JSON.updateJsonFile();
            }
        }
    }
    public static void removeTags(String input, Book edited) {
        if (books.contains(edited)) {
            if (edited.getTags().contains(input)) {
                edited.removeTag(input);
                JSON.updateJsonFile();
            }
        }
    }
    public static void addTranslator(String input, Book edited) {
        if (books.contains(edited)) {
            if (!edited.getTranslators().contains(input)) {
                edited.addTranslator(input);
                JSON.updateJsonFile();
            }
        }
    }
    public static void removeTranslator(String input, Book edited) {
        if (books.contains(edited)) {
            if (!edited.getTranslators().contains(input)) {
                edited.removeTranslator(input);
                JSON.updateJsonFile();
            }
        }
    }

    //JSON classındaki addbook ve remowe book metodları buraya direk taşınabilir.
    public static void addBooks(String jsonFilePath) {
        JSON.addBook(jsonFilePath);
    }
    public static void removeBook(Book removed) {
        JSON.removeBook(removed);
    }

    }


