package Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Test
        /*
        ArrayList <String> arrayList=  new ArrayList<>();
        arrayList.add("aoafoa");
        arrayList.add("Alp");
        Book book1 = new Book("PKitap","Yazar","basımcı","1994", "1000", "new", arrayList );


        Book book2 = new Book("sevAL kİTAP","Yazar","basımcı","1994", "1000", "new", arrayList );
        Book book3 = new Book("eNreDİZKitap","Yazar","basımcı","1994", "1000", "new", arrayList );
        Book book4 = new Book("pKitap","Yazar","basımcı","1994", "1000", "new", arrayList );

        ArrayList <Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        Collections.sort(books);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
         */

        Book book1 = new Book("PKitap","Yazar","basımcı");
        System.out.println("ENTER :" );
        String title = sc.nextLine();
       // book1.editTitle(title);



    }


}
