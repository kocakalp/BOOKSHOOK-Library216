package Library;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<Book> books = JSON.getBooks();

    // editers.
    public void editTitle(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setTitle(input);
            JSON.updateJsonFile();
        }
    }
    public void editAuthor(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setAuthor(input);
            JSON.updateJsonFile();
        }
    }
    public void editPublisher(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setPublisher(input);
            JSON.updateJsonFile();
        }
    }
    public void editPublicationYear(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setPublicationYear(input);
            JSON.updateJsonFile();
        }
    }
    public void editIsbn(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setIsbn(input);
            JSON.updateJsonFile();
        }
    }
    public void editEdition(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setEdition(input);
            JSON.updateJsonFile();
        }
    }

    // can be improved
    public void addTags(String input, Book edited) {
        if (books.contains(edited)) {
            if (!edited.getTags().contains(input)) {
                edited.addTag(input);
                JSON.updateJsonFile();
            }
        }
    }
    public void remoweTags(String input, Book edited) {
        if (books.contains(edited)) {
            if (edited.getTags().contains(input)) {
                edited.removeTag(input);
                JSON.updateJsonFile();
            }
        }
    }
    public void addTranslator(String input, Book edited) {
        if (books.contains(edited)) {
            if (!edited.getTranslators().contains(input)) {
                edited.addTranslator(input);
                JSON.updateJsonFile();
            }
        }
    }
    public void removeTranslator(String input, Book edited) {
        if (books.contains(edited)) {
            if (!edited.getTranslators().contains(input)) {
                edited.removeTranslator(input);
                JSON.updateJsonFile();
            }
        }
    }

    //JSON classındaki addbook ve remowe book metodları buraya direk taşınabilir.
    public void addBooks(String jsonFilePath) {
        JSON.addBook(jsonFilePath);
    }
    public void remoweBook(Book remowed) {
        JSON.remoweBook(remowed);
    }

    }


