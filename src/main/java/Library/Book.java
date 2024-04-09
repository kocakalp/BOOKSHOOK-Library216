package Library;
import javafx.scene.control.TableRow;

import java.util.ArrayList;
import java.util.Scanner;


public class Book implements Comparable<Book>  {

    public Scanner sc = new Scanner(System.in);
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private String isbn;
    private String edition;
    private ArrayList<String> translators;
    private ArrayList<String> tags = new ArrayList<>();

    //For Test.
    public Book(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }
    public Book(String title, String author, String publisher, String publicationYear, String isbn, String edition, ArrayList<String> translators) {

        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.edition = edition;
        this.translators = translators;
    }

    //For listView test
    public Book(String title, String author, String publisher, String publicationYear, String isbn, String edition) {

        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.edition = edition;
    }



    //Getters
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getPublisher() {return publisher;}
    public String getPublicationYear() {return publicationYear;}
    public String getIsbn() {return isbn;}
    public String getEdition() {return edition;}
    public ArrayList<String> getTranslators() {return translators;}
    public ArrayList<String> getTags() {return tags;}

    //make functions that adds arrays
    public void addTag(String tag) {if(isValidTags(tag))tags.add(tag);}
    public void addTranslator(String translator) {if(isValidTranslators(translator))translators.add(translator);}

    public void removeTag(String tag) {tags.remove(tag);}
    public void removeTranslator(String translator) {translators.remove(translator);}

    //Setters
    public void setTitle(String title) {
        if(isValidTitle(title)){
            this.title = title;
        } else {
            System.out.println("invalid title");
            setTitle(edit());
        }
    }
    public void setAuthor(String author) {
        if(isValidAuthor(author)){
            this.author = author;
        } else {
            System.out.println("invalid author");
            setAuthor(edit());
        }
    }
    public void setPublisher(String publisher) {
        if(isValidPublisher(publisher)){
            this.publisher = publisher;
        } else {
            System.out.println("invalid publisher");
            setPublisher(edit());
        }
    }
    public void setPublicationYear(String publicationYear) {
        if(isValidPublicationYear(publicationYear)){
            this.publicationYear = publicationYear;
        } else {
            System.out.println("invalid publication year");
            setPublicationYear(edit());
        }
    }
    public void setIsbn(String isbn) {
        if(isValidISBN(isbn)){
            this.isbn = isbn;
        } else {
            System.out.println("invalid isbn");
            setIsbn(edit());
        }
    }
    public void setEdition(String edition) {
        if(isValidEdition(edition))this.edition = edition;}

    // unfinished
    public void setTranslators(ArrayList<String> translators) {this.translators = translators;}
    public void setTags(ArrayList<String> tags) {this.tags = tags;}


    //Edit Methods.
    public Boolean isValidTitle(String input) {
        try {
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public Boolean isValidAuthor(String input) {
        try {
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public boolean isValidPublisher(String input) {
        try {
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public boolean isValidPublicationYear(String input) {
        try{
            int i = Integer.parseInt(input);
            return !input.isBlank() && i > 0;
        } catch(Exception E) {
            return false;
        }
    }
    public boolean isValidISBN(String input) {
        try{
            Integer.parseInt(input);
            return !input.isBlank() && (input.length() == 13 || input.length() ==10);
        } catch(Exception E) {
            return false;
        }
    }
    public boolean isValidEdition(String input) {
        try{
            int i = Integer.parseInt(input);
            return !input.isBlank() && i > 0;
        } catch(Exception E) {
            return false;
        }
    }
    public boolean isValidTranslators(String input) {
        try {
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public boolean isValidTags(String input) {
        try {
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public String edit(){return sc.nextLine();}

    @Override
    public String toString() {
        return getTitle()+", "+getAuthor()+", "+getPublisher()+", "+getPublicationYear()+", "+getIsbn()+", "+getEdition()+", "+getTranslatorAsString()+", "+getTagsAsString();
    }
    private String getTranslatorAsString(){
        StringBuilder translatorAsString = new StringBuilder();
        for (String i : translators) {
            translatorAsString.append(", ").append(i);
        }
        return translatorAsString.toString();
    }
    private String getTagsAsString(){
        StringBuilder tagsAsString = new StringBuilder();
        for (String i : translators) {
            tagsAsString.append(", ").append(i);
        }
        return tagsAsString.toString();
    }

    @Override
    public int compareTo(Book book) {
        String thisTitle = this.title.toLowerCase();
        String otherTitle = book.getTitle().toLowerCase();
        // Büyük harfler önce gelmeli
        int compareResult = thisTitle.compareTo(otherTitle);

        if (compareResult == 0) {
            // Büyük harflerin aynı olduğu durumda, orijinal büyük/küçük harf sırasına göre karşılaştırma yap
            compareResult = this.title.compareTo(book.getTitle());
        }

        return compareResult;
    }
}
