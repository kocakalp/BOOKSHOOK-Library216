package Library;


import java.util.ArrayList;

public  class SearchBar {
    ArrayList<String> arrayList=  new ArrayList<>();
    static ArrayList <Book> books = new ArrayList<>();
    public static void searchBookByTitle(String title){
        boolean isFound=false;
        for(Book book:books){
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
                System.out.println("Book Title :"+book.getTitle());
                isFound=true;
            }
        }
        if(!isFound){
            System.out.println("Book can not found for the title:"+title);
        }
    }
    public static void searchBookByAuthor(String authorName){
        boolean isFound=false;
        for(Book book :books){
            if(book.getAuthor().equalsIgnoreCase(authorName)){
                System.out.println("Book Title :"+book.getTitle());
                isFound=true;
            }
        }
        if(!isFound){
            System.out.println("Book can not found for the author:"+authorName);
        }
    }
    public static void searchBookByPublisher(String publisher){
        boolean isFound=false;
        for(Book book :books){
            if(book.getPublisher().equalsIgnoreCase(publisher)){
                System.out.println("Book Title :"+book.getTitle());
                isFound=true;
            }
        }
        if(!isFound){
            System.out.println("Book can not found for the publisher:"+publisher);
        }
    }
    public static void searchBookByLanguage(String language){
        boolean isFound=false;
        for(Book book :books){
            if(book.getLanguage().equalsIgnoreCase(language)){
                System.out.println("Book Title :"+book.getTitle());
                isFound=true;
            }
        }
        if(!isFound){
            System.out.println("Book can not found for the language:"+language);
        }
    }
    public static void searchBookByRating(String rating){
        boolean isFound=false;
        for(Book book :books){
            if(book.getRating().equalsIgnoreCase(rating)){
                System.out.println("Book Title :"+book.getTitle());
                isFound=true;
            }
        }
        if(!isFound){
            System.out.println("Book can not found for the rating:"+rating);
        }
    }
    public static void searchBookByDate(String year){
        boolean isFound=false;
        for(Book book:books){
            if(book.getDate().equals(year)){
                System.out.println("Book Title:"+book.getTitle());
                isFound=true;
            }
        }
        if(!isFound){
            System.out.println("Book can not for the publication year:"+year);
        }
    }
    public static void searchBookByISBN(String isbn){
        boolean isFound=false;
        for(Book book:books){
            if(book.getIsbn().equals(isbn)){
                System.out.println("Book Title:"+book.getTitle());
                isFound=true;
            }
        }
        if(!isFound){
            System.out.println("Book can not for the ISBN:"+isbn);
        }
    }
    public static void searchBookByEdition(String edition){
        boolean isFound=false;
        for(Book book:books){
            if(book.getEdition().equals(edition)){
                System.out.println("Book Title:"+book.getTitle());
                isFound=true;
            }
        }
        if(!isFound){
            System.out.println("Book can not for the edition:"+edition);
        }
    }
    public static void searchBookByTranslator(String translatorName){
        boolean isFound=false;
        for(Book book :books){
            for(String translator :book.getTranslators()){
                if(translator.equalsIgnoreCase(translatorName)){
                    System.out.println("Book Title :"+book.getTitle());
                    isFound=true;
                    break;
                }
            }
        }
        if(!isFound){
            System.out.println("Book can not for the translator:"+translatorName);
        }
    }
    public static void searchBookByTags(String tag){
        boolean isFound=false;
        for(Book book :books){
            for(String tags :book.getTags()){
                if(tag.equalsIgnoreCase(tag)){
                    System.out.println("Book Title :"+book.getTitle());
                    isFound=true;
                    break;
                }
            }
        }
        if(!isFound){
            System.out.println("Book can not for the tag:"+tag);
        }
    }


    public static ArrayList<Book> search(String in) {
        ArrayList<Book> show = new ArrayList<>();
        for (Book b : JSON.getBooks()) {
            if(b.toString().toLowerCase().contains(in.toLowerCase())) show.add(b);
        }
        return show;
    }
    public static ArrayList<Book> searchByTag(String in) {
        ArrayList<Book> show = new ArrayList<>();
        for (Book b : JSON.getBooks()) {
            if(b.getTagsAsString().equals(in)) show.add(b);
        }
        return show;
    }
}
