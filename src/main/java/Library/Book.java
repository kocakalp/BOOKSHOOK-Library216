package Library;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Book implements Comparable<Book>  {
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private String isbn;
    private String edition;
    private final ArrayList<String> translators = new ArrayList<>();
    private final ArrayList<String> tags = new ArrayList<>();

    public Book(String title, String author, String publisher, String publicationYear, String isbn, String edition,ArrayList<String> tags,ArrayList<String> translators) {

        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setPublicationYear(publicationYear);
        setIsbn(isbn);
        setEdition(edition);
        addTags(tags);
        addTranslators(translators);
        JSON.updateJsonFile();
    }

    public Book() {
        this("title","author","publisher","1","0000000000","1",new ArrayList<>(), new ArrayList<>());
    }

    //for test remove in th future
    public Book(String title, String author, String publisher, String publicationYear, String isbn, String edition,ArrayList<String> translators) {

        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.edition = edition;
    }
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

    //Handling Arrays
    public void addTag(String tag) {
        if(isValidTags(tag))tags.add(tag);
        JSON.updateJsonFile();
    }
    public void addTags(ArrayList<String> tags) {
        for (String tag : tags) {
            addTag(tag);
        }
    }
    public void removeTag(String tag) {
        tags.remove(tag);
        JSON.updateJsonFile();
    }
    public void addTranslator(String translator) {
        if(isValidTranslators(translator))translators.add(translator);
        JSON.updateJsonFile();
    }
    public void addTranslators(ArrayList<String> translators) {
        for (String translator : translators) {
            addTranslator(translator);
        }
    }
    public void removeTranslator(String translator) {
        translators.remove(translator);
        JSON.updateJsonFile();
    }

    //Setters
    //Else kısımları ilerde edit metodunu çağırcak şekilde değiştir.
    //json update ilerde taşınabilir.
    public void setTitle(String title) {
        if(isValidTitle(title)){
            this.title = title;
        } else {
            System.out.println("invalid title");
            setTitle("title");
        }
        JSON.updateJsonFile();
    }
    public void setAuthor(String author) {
        if(isValidAuthor(author)){
            this.author = author;
        } else {
            System.out.println("invalid author");
            setAuthor("author");
        }
        JSON.updateJsonFile();
    }
    public void setPublisher(String publisher) {
        if(isValidPublisher(publisher)){
            this.publisher = publisher;
        } else {
            System.out.println("invalid publisher");
            setPublisher("publisher");
        }
    }
    public void setPublicationYear(String publicationYear) {
        if(isValidPublicationYear(publicationYear)){
            this.publicationYear = publicationYear;
        } else {
            System.out.println("invalid publication year");
            setPublicationYear("0");
        }
        JSON.updateJsonFile();
    }
    public void setIsbn(String isbn) {
        if(isValidISBN(isbn)){
            this.isbn = isbn;
        } else {
            System.out.println("invalid isbn");
            setIsbn("0000000000");
        }
        JSON.updateJsonFile();
    }
    public void setEdition(String edition) {
        if(isValidEdition(edition)){
            this.edition = edition;
        } else {
            System.out.println("invalid edition");
            setEdition("0");
        }
        JSON.updateJsonFile();
    }

    public void setTags(String in) {
        String[] tags = in.split(",");
        if (tags.length > 0) {
            this.tags.clear();
            for (String t : tags) {
                addTag(t);
            }
        }
    }
    public void setTranslators(String in) {
        String[] translator = in.split(",");
        if (translator.length > 0) {
            this.translators.clear();
            for (String t : tags) {
                addTag(t);
            }
        }
    }
    //Validation Methods.
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
    public String edit(){Scanner sc = new Scanner(System.in);return sc.nextLine();}

    //toString
    @Override
    public String toString() {
        return getTitle()+", "+getAuthor()+", "+getPublisher()+", "+getPublicationYear()+", "+getIsbn()+", "+getEdition()+getTagsAsString() +getTranslatorAsString();
    }
    private String getTranslatorAsString(){
        StringBuilder translatorAsString = new StringBuilder();
        for (String i : translators) {
            translatorAsString.append(i).append(", ");
        }
        return translatorAsString.toString();
    }
    private String getTagsAsString(){
        StringBuilder tagsAsString = new StringBuilder();

        for (String i : tags) {
            tagsAsString.append(i).append(", ");
        }
        return tagsAsString.toString();
    }

    //bi ara kontrol et
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getPublisher(), book.getPublisher()) && Objects.equals(getPublicationYear(), book.getPublicationYear()) && Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getEdition(), book.getEdition()) && Objects.equals(getTranslators(), book.getTranslators()) && Objects.equals(getTags(), book.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getPublisher(), getPublicationYear(), getIsbn(), getEdition(), getTranslators(), getTags());
    }
}
