package Library;



import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<Book> books = new ArrayList<Book>();

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
    }


}