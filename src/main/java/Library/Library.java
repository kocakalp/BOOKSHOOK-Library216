package Library;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {
    private static final ArrayList<Book> books = JSON.getBooks();
    private static ArrayList<Book> exportedBooks = new ArrayList();
    //Edit methods

    public static boolean editCoverPath(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidCoverPath(input)) {
            edited.setCoverPath(input);
            JSON.updateJsonFile();
            return true;
        }else{
            return false;

        }
    }
    public static void editCover(Image cover, Book edited) {
        if (books.contains(edited)) {
            edited.setCover(cover);
            JSON.updateJsonFile();
        }
    }

    public static boolean editTitle(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidTitle(input)) {
            edited.setTitle(input);
            JSON.updateJsonFile();
            return true;
        }else{
            return false;
        }
    }

    public static boolean editAuthor(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidAuthor(input)) {
            edited.setAuthor(input);
            JSON.updateJsonFile();
            return true;
        }else{
            return false;
        }
    }
    public static boolean editPublisher(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidPublisher(input)) {
            edited.setPublisher(input);
            JSON.updateJsonFile();
            return true;
        }else{
            return false;
        }
    }
    public static boolean editDate(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidDate(input)) {
            edited.setDate(input);
            JSON.updateJsonFile();
            return true;
        }else {
            return false;
        }
    }
    public static boolean editLanguage(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidLanguage(input)) {
            edited.setLanguage(input);
            JSON.updateJsonFile();
            return true;
        }else {
            return false;
        }
    }
    public static boolean editIsbn(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidISBN(input)) {
            edited.setIsbn(input);
            JSON.updateJsonFile();
            return true;
        }else{
            return false;
        }
    }
    public static boolean editEdition(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidEdition(input)) {
            edited.setEdition(input);
            JSON.updateJsonFile();
            return true;
        }else{
            return false;
        }
    }
    public static boolean editRating(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidRating(input)) {
            edited.setRating(input);
            JSON.updateJsonFile();
            return true;
        }else{
            return false;
        }
    }

    public static boolean editSubtitle(String input, Book edited) {
        if (books.contains(edited)&& edited.isValidSubtitle(input)) {
            edited.setSubtitle(input);
            JSON.updateJsonFile();
            return true;
        }else{
            return false;
        }
    }

    public static  boolean editTags(String input,Book edited) {
        List<String> tagList = Arrays.asList(input.split("\\s*,\\s*"));
        if (books.contains(edited)&& edited.isValidTags(input)) {
            edited.setTags(String.valueOf(tagList));
            JSON.updateJsonFile();
            return true;
        }else{
            return false;
        }
    }
    public static  boolean editTranslators(String input,Book edited) {
        List<String> translatorList = Arrays.asList(input.split("\\s*,\\s*"));
        if (books.contains(edited)&& edited.isValidTranslators(input)) {
            edited.setTranslators(String.valueOf(translatorList));
            JSON.updateJsonFile();
            return true;
        }else{
            return false;
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
    public static void addBooks(String jsonFilePath) {
        JSON.addBooks(jsonFilePath);
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
        if (filePath.isBlank() || filePath.equals("mainJson.json")) System.out.println("invalid fileName");
        JSON.exportJsonFile(filePath,exportedBooks);
        exportedBooks.clear();
    }
}


