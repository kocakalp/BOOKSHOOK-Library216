package Library;


import java.util.ArrayList;

public  class SearchBar {
    ArrayList<String> arrayList=  new ArrayList<>();
    ArrayList <Book> books = new ArrayList<>();
    void searchBookByTitle(String title){
        boolean isFound=false;
        for(Book book:books){
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
                System.out.println("Book Title :"+book.getTitle());
                isFound=true;
            }
        }
        if(isFound=false){
            System.out.println("Book can not found for the title:"+title);
        }
    }
    void searchBookByAuthor(String authorName){
        boolean isFound=false;
        for(Book book :books){
            if(book.getAuthor().equalsIgnoreCase(authorName)){
                System.out.println("Book Title :"+book.getTitle());
                isFound=true;
            }
        }
        if(isFound=false){
            System.out.println("Book can not found for the author:"+authorName);
        }
    }
    void searchBookByPublisher(String publisher){
        boolean isFound=false;
        for(Book book :books){
            if(book.getPublisher().equalsIgnoreCase(publisher)){
                System.out.println("Book Title :"+book.getTitle());
                isFound=true;
            }
        }
        if(isFound=false){
            System.out.println("Book can not found for the publisher:"+publisher);
        }
    }
    void searchBookByPublicationYear(String year){
        boolean isFound=false;
        for(Book book:books){
            if(book.getPublicationYear().equals(year)){
                System.out.println("Book Title:"+book.getTitle());
                isFound=true;
            }
        }
        if(isFound=false){
            System.out.println("Book can not for the publication year:"+year);
        }
    }
    void searchBookByISBN(String isbn){
        boolean isFound=false;
        for(Book book:books){
            if(book.getIsbn().equals(isbn)){
                System.out.println("Book Title:"+book.getTitle());
                isFound=true;
            }
        }
        if(isFound=false){
            System.out.println("Book can not for the ISBN:"+isbn);
        }
    }
    void searchBookByEdition(String edition){
        boolean isFound=false;
        for(Book book:books){
            if(book.getEdition().equals(edition)){
                System.out.println("Book Title:"+book.getTitle());
                isFound=true;
            }
        }
        if(isFound=false){
            System.out.println("Book can not for the edition:"+edition);
        }
    }
    void searchBookByTranslator(String translatorName){
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
        if(isFound=false){
            System.out.println("Book can not for the translator:"+translatorName);
        }
    }
    void searchBookByTags(String tag){
        boolean isFound=false;
        for(Book book :books){
            for(String translator :book.getTranslators()){
                if(translator.equalsIgnoreCase(tag)){
                    System.out.println("Book Title :"+book.getTitle());
                    isFound=true;
                    break;
                }
            }
        }
        if(isFound=false){
            System.out.println("Book can not for the tag:"+tag);
        }
    }


}
