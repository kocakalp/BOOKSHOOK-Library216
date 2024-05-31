package Library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class JSON {
    private static final LinkedList<Book> books = new LinkedList();
    private static final ObservableList<Book> data = UserInterface.getData();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Book.class,new BookAdapter()).create();
    private static final String filePath = "mainJson";

    public static LinkedList<Book> getBooks() {return books;}

    //These two methods can be moved directly to the library class.
    public static void addBooks(String filePath) {
        Type t = new TypeToken<Collection<Book>>(){}.getType();
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            ArrayList<Book> books1 = new ArrayList<>(gson.fromJson(reader, t));
            for(Book b : books1) {
                if(!books.contains(b)){
                    books.add(b);
                    data.add(b);
                }
            }
        } catch (JsonSyntaxException | ClassCastException e) {
            addBook(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            System.out.println("An exception occurred");
        } finally {
            updateJsonFile();
        }
    }
    public static void addBook(String filePath) {
        Type t = new TypeToken<Book>(){}.getType();
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            Book b = gson.fromJson(reader, t);
            if(!books.contains(b)){
                books.add(b);
                data.add(b);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding a book!!");
        } finally {
            updateJsonFile();
        }
    }

    public static void removeBook(Book b) {
        try {
            books.remove(b);
        } catch (Exception e) {
            System.out.println("An error occurred while removing a book!!");
        }
        finally {
            updateJsonFile();
        }
    }

    public static void updateJsonFile() {exportJsonFile(filePath,books);}
    public static void exportJsonFile(String fileName,LinkedList<Book> books) {
        try (FileWriter f = new FileWriter(fileName+".json")) {
            f.write(gson.toJson(books));
        } catch (Exception e) {
            System.out.println("An error occurred while updating JSON file!!");
        }
    }
}