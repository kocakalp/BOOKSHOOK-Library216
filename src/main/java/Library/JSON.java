package Library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class JSON {
    private static final ArrayList<Book> books = new ArrayList<Book>();
    private static final GsonBuilder builder = new GsonBuilder();
    private static final Gson gson = builder.create();
    private static final String filePath = "mainJson";

    public static void addBook(String filePath) {
        Type BookType= new TypeToken<List<Book>>() {
        }.getType();
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            books.add(gson.fromJson(reader,BookType.getClass()));
            updateJsonFile();
        } catch (Exception e) {
            System.out.println("e");
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
        try{
            Formatter f = new Formatter(filePath);
            f.format(gson.toJson(books));
        } catch (Exception e) {

        }
    }
}
