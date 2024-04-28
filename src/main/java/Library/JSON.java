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

public class JSON {
    private static final ArrayList<Book> books = new ArrayList<>();
    private static ObservableList<Book> data = UserInterface.getData();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Book.class,new BookAdapter()).create();
    private static final String filePath = "mainJson.json";

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

    public static void updateJsonFile() {
        try (FileWriter f = new FileWriter(filePath)) {
            f.write(gson.toJson(books));
        } catch (Exception e) {
            System.out.println("An error occurred while updating JSON file!!");
        }
    }
    public static ArrayList<Book> getBooks() {
        return books;
    }



    //THIS MAIN IS FOR TESTING PURPOSES.
    /*
    public static void main(String[] args) {

        System.out.println("START");
        Book book1 = new Book("PBook","AutHor","pUblIshEr","1994", "1000000000", "2",new ArrayList<String>(),new ArrayList<String>());
        Book book2 = new Book("sevALB00k07","pUblIshErJava","bAsı_mcI","1994", "1000000000", "3",new ArrayList<String>(),new ArrayList<String>());
        Book book3 = new Book("eNreDİZBook","AutHo772r","pUblIs_--78","1994", "1000000000", "4",new ArrayList<String>(),new ArrayList<String>());
        Book book4 = new Book("Bo0Ok/}*\\","AutHorJFK","pUblIshEr77","1994", "1000000000", "5",new ArrayList<String>(),new ArrayList<String>());
        book3.addTranslator("translateMan");
        book4.addTranslator("3424");
        book4.addTranslator("Çe_vir_Man");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(new Book());
        updateJsonFile();
        //addBooks(filePath);
        for (Book b : books) {
            System.out.println(b);
        }
    }
     */
}