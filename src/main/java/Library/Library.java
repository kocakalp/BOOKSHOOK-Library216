package Library;

import java.util.ArrayList;

public class Library {
    private static final ArrayList<Book> books = JSON.getBooks();

    // edit methods
    public static void editTitle(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setTitle(input);
            JSON.updateJsonFile();
        }
    }
    public static void editAuthor(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setAuthor(input);
            JSON.updateJsonFile();
        }
    }
    public static void editPublisher(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setPublisher(input);
            JSON.updateJsonFile();
        }
    }
    public static void editPublicationYear(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setPublicationYear(input);
            JSON.updateJsonFile();
        }
    }
    public static void editIsbn(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setIsbn(input);
            JSON.updateJsonFile();
        }
    }
    public static void editEdition(String input, Book edited) {
        if (books.contains(edited)) {
            edited.setEdition(input);
            JSON.updateJsonFile();
        }
    }

    // can be improved
    public static void addTags(String input, Book edited) {
        if (books.contains(edited)) {
            if (!edited.getTags().contains(input)) {
                edited.addTag(input);
                JSON.updateJsonFile();
            }
        }
    }
    public static void removeTags(String input, Book edited) {
        if (books.contains(edited)) {
            if (edited.getTags().contains(input)) {
                edited.removeTag(input);
                JSON.updateJsonFile();
            }
        }
    }
    public static void addTranslator(String input, Book edited) {
        if (books.contains(edited)) {
            if (!edited.getTranslators().contains(input)) {
                edited.addTranslator(input);
                JSON.updateJsonFile();
            }
        }
    }
    public static void removeTranslator(String input, Book edited) {
        if (books.contains(edited)) {
            if (!edited.getTranslators().contains(input)) {
                edited.removeTranslator(input);
                JSON.updateJsonFile();
            }
        }
    }

    //JSON classındaki addbook ve remowe book metodları buraya direk taşınabilir.
    public static void addBooks(String jsonFilePath) {
        JSON.addBooks(jsonFilePath);
    }
    public static void removeBook(Book removed) {
        JSON.removeBook(removed);
    }

    //şevval seachbarı static class yap bence daha temiz durur.
    {/*public static void main(String[] args) {
        Gson gson = new Gson();
    }
    private ArrayList<Book> books = new ArrayList<Book>();
    void searchBook(){
        ArrayList <String> arrayList=  new ArrayList<>();
        /*
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
    }*/}
}
