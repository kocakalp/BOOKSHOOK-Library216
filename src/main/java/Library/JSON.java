package Library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class JSON {
    private static ArrayList<Book> books = new ArrayList<Book>();
    private static final GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
    private static final Gson gson = builder.create();
    private static final String filePath = "mainJson";

    public static void addBook(String filePath) {

        Type collectionType = new TypeToken<Collection<Book>>(){}.getType();

        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            books.addAll(gson.fromJson(reader, collectionType));
            updateJsonFile();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void remoweBook(Book b) {
        try {
            books.remove(b);
            updateJsonFile();
        } catch (Exception e) {
            System.out.println("e");
        }
    }
    private static void updateJsonFile() {

        try (FileWriter f = new FileWriter(filePath)) {
                f.write(gson.toJson(books));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //test
    public static void main(String[] args) {
        System.out.println("START");
        /*Book book1 = new Book("PKitap","Yazar","basımcı","1994", "1000000000", "2",new ArrayList<String>(),new ArrayList<String>());
        Book book2 = new Book("sevAL kİTAP","Yazar","basımcı","1994", "1000000000", "3",new ArrayList<String>(),new ArrayList<String>());
        Book book3 = new Book("eNreDİZKitap","Yazar","basımcı","1994", "1000000000", "4",new ArrayList<String>(),new ArrayList<String>());
        Book book4 = new Book("pKitap","Yazar","basımcı","1994", "1000000000", "5",new ArrayList<String>(),new ArrayList<String>());
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        updateJsonFile();*/
        addBook(filePath);
        for (Book b : books) {
            System.out.println(b);
        }

    }
}
