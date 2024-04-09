package Library;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {


    public static void main(String[] args) {
        Gson gson = new Gson();
    }
    private ArrayList<Book> books = new ArrayList<Book>();

    void searchBookByTitle(String title){
        ArrayList <String> arrayList=  new ArrayList<>();

       /* Book book1 = new Book("PKitap","Yazar","basımcı","1994", "1000", "new", arrayList );


        Book book2 = new Book("sevAL kİTAP","Yazar","basımcı","1994", "1000", "new", arrayList );
        Book book3 = new Book("eNreDİZKitap","Yazar","basımcı","1994", "1000", "new", arrayList );
        Book book4 = new Book("pKitap","Yazar","basımcı","1994", "1000", "new", arrayList );

        ArrayList <Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);*/

        boolean isFound=false;
        for(Book book:books){
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
                System.out.println("Book Title:"+ book.getTitle());
                isFound=true;
            }
        }
        if(isFound=false){
            System.out.println("Book can not found for the title:"+title);
        }
    }
    void searchBookByAuthor(String authorName){
        boolean isFound=false;

        for(Book book:books){
            if(book.getAuthor().equalsIgnoreCase(authorName)){
                System.out.println("Book Title:"+book.getTitle());
                isFound=true;
            }
        }
        if(isFound=false){
            System.out.println("Book can not found for the author:"+authorName);
        }
    }
    void seachBookByTags(String tag){
        boolean isFound=false;
        for(Book book:books){
            for(String bookTag : book.getTags()){
                if(bookTag.toLowerCase().contains(tag.toLowerCase())){
                    System.out.println("Book Title:"+book.getTitle());
                    isFound=true;
                }
            }
        }
        if(isFound=false){
            System.out.println("Book can not found for the tag:"+tag);
        }
    }






}
