package Library;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.LinkedList;

public class Library {
    private static final LinkedList<Book> books = JSON.getBooks();
    private static final LinkedList<Book> exportedBooks = new LinkedList<>();
    //Edit methods


    public static LinkedList<Book> getExportedBooks() {
        return exportedBooks;
    }

    //Edit methods
    public static void editTitle(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidTitle(input)) {
            edited.setTitle(input);
            JSON.updateJsonFile();
        }else{
            UserInterface.showAlert();
        }
    }
    public static void editAuthor(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidAuthor(input)) {
            edited.setAuthor(input);
            JSON.updateJsonFile();
        }else{
            UserInterface.showAlert();
        }
    }
    public static void editSubtitle(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidSubtitle(input)) {
            edited.setSubtitle(input);
            JSON.updateJsonFile();
        }else{
            UserInterface.showAlert();
        }
    }
    public static void editPublisher(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidPublisher(input)) {
            edited.setPublisher(input);
            JSON.updateJsonFile();
        }else{
            UserInterface.showAlert();
        }
    }
    public static void editDate(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidDate(input)) {
            edited.setDate(input);
            JSON.updateJsonFile();
        }else {
            UserInterface.showAlert();
        }
    }
    public static void editIsbn(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidISBN(input)) {
            edited.setIsbn(input);
            JSON.updateJsonFile();
        }else{
            UserInterface.showAlert();
        }
    }
    public static void editEdition(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidEdition(input)) {
            edited.setEdition(input);
            JSON.updateJsonFile();
        }else{
            UserInterface.showAlert();
        }
    }
    public static void editLanguage(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidLanguage(input)) {
            edited.setLanguage(input);
            JSON.updateJsonFile();
        }else {
            UserInterface.showAlert();
        }
    }
    public static void editRating(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidRating(input)) {
            edited.setRating(input);
            JSON.updateJsonFile();
        }else{
            UserInterface.showAlert();
        }
    }
    public static  void editTags(String input,Book edited) {
        if (books.contains(edited) && edited.isValidTags(input)) {
            edited.setTags(input);
            JSON.updateJsonFile();
        } else {
            UserInterface.showAlert();
        }
    }
    public static  void editTranslators(String input,Book edited) {
        if (books.contains(edited)) {
            edited.setTranslators(input);
            JSON.updateJsonFile();
        }
    }
    public static void editCoverPath(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidCoverPath(input)) {
            edited.setCoverPath(input);
            JSON.updateJsonFile();
        }else{
            UserInterface.showAlert();
        }
    }
    public static void editCover(Image cover, Book edited) {
        if (books.contains(edited)) {
            edited.setCover(cover);
            JSON.updateJsonFile();
        }
    }

    //can be improved
    //might be useless
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

    //The addBook and remove book methods in the JSON class can be moved here directly.
    public static boolean addBooks(String jsonFilePath) {
        int i = books.size();
        JSON.addBooks(jsonFilePath);
        return i != books.size();
    }
    public static void removeBook(Book removed) {
        JSON.removeBook(removed);
        exportedBooks.remove(removed);
    }

    public static void addExportedBook(Book book) {
       if (!exportedBooks.contains(book)){
           exportedBooks.add(book);
       } else {
           exportedBooks.remove(book);
       }
    }
    public static void exportBook(String filePath) {
        if (filePath.isBlank() || filePath.equals("mainJson") || filePath.equals("mainJson.json")) System.out.println("invalid fileName");
        JSON.exportJsonFile(filePath,exportedBooks);
        exportedBooks.clear();
    }
}


