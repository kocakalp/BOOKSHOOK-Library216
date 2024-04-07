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
import java.util.Scanner;
import java.nio.file.Paths;

public class Library {
    private ArrayList<Book> books = new ArrayList<Book>();
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();
    private static final Type BookType= new TypeToken<List<Book>>() {
    }.getType();

    public void addBook(String filePath) {
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            books.add(gson.fromJson(reader,BookType.getClass()));
            books.get(books.size() - 1).setPath(filePath);
        } catch (Exception e) {

        }
    }

    public void remoweBook(Book b) {
        try {
            books.remove(b);
            Formatter f = new Formatter(b.getPath());
            f.format(gson.toJson(books));
        } catch (Exception e) {

        }
    }
    /*public static void main(String[] args) {
        Gson gson = new Gson();
    }
    void searchBook(){
        ArrayList <String> arrayList=  new ArrayList<>();

        Book book1 = new Book("PKitap","Yazar","basımcı","1994", "1000", "new", arrayList );


        Book book2 = new Book("sevAL kİTAP","Yazar","basımcı","1994", "1000", "new", arrayList );
        Book book3 = new Book("eNreDİZKitap","Yazar","basımcı","1994", "1000", "new", arrayList );
        Book book4 = new Book("pKitap","Yazar","basımcı","1994", "1000", "new", arrayList );

        ArrayList <Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter a book name that you wanna search from your library");
        String searchWord= sc.nextLine().toLowerCase();

        boolean isFound=false;
        for(Book book:books){
            if(book.getTitle().toLowerCase().contains(searchWord)){
                System.out.println("Book Title:"+ book.getTitle());
                isFound=true;
            }
        }
        if(!isFound){
            System.out.println("Book can not found!");//hjgh
        }
    }*/
}
