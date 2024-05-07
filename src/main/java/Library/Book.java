package Library;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Book implements Comparable<Book>  {
    private String title;
    private String author;
    private String subtitle;
    private String publisher;
    private String date;
    private String isbn;
    private String edition;
    private String language;
    private String rating;
    private final ArrayList<String> tags = new ArrayList<>();
    private final ArrayList<String> translators = new ArrayList<>();
    private String coverPath;
    public Image cover;

    //Constructors
    public Book(String title, String author, String subtitle, String publisher, String date, String isbn, String edition, String language, String rating, ArrayList<String> tags ,ArrayList<String> translators, String coverPath) {

        setTitle(title);
        setAuthor(author);
        setSubtitle(subtitle);
        setPublisher(publisher);
        setDate(date);
        setIsbn(isbn);
        setEdition(edition);
        setLanguage(language);
        setRating(rating);
        addTags(tags);
        addTranslators(translators);
        setCoverPath(coverPath);
    }
    public Book() {
        this("title", "author", "subtitle", "publisher", "1", "0000000000", "1", "language", "1", new ArrayList<>(), new ArrayList<>(), "default.png");
    }

    //Getters
    public String getCoverPath() {return coverPath;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getSubtitle(){return subtitle;}
    public String getPublisher() {return publisher;}
    public String getDate() {return date;}
    public String getIsbn() {return isbn;}
    public String getEdition() {return edition;}
    public String getLanguage() {return language;}
    public String getRating() {return rating;}
    public ArrayList<String> getTags() {return tags;}
    public ArrayList<String> getTranslators() {return translators;}
    public Image getCover() { return cover;}

    //Setters
    //Change the Else parts to call the edit method in the future.(?)
    public void setTitle(String title) {
        if(isValidTitle(title)){
            this.title = title;
        } else {
            System.out.println("invalid title");
            setTitle("title");
        }
    }
    public void setAuthor(String author) {
        if(isValidAuthor(author)){
            this.author = author;
        } else {
            System.out.println("invalid author");
            setAuthor("author");
        }
    }
    public void setSubtitle(String subtitle) {
        if(isValidSubtitle(subtitle)){
            this.subtitle = subtitle;
        } else {
            System.out.println("invalid subtitle");
            setSubtitle("subtitle");
        }
    }
    public void setPublisher(String publisher) {
        if(isValidPublisher(publisher)){
            this.publisher = publisher;
        } else {
            System.out.println("invalid publisher");
            setPublisher("publisher");
        }
    }
    public void setDate(String date) {
        if(isValidDate(date)){
            this.date = date;
        } else {
            System.out.println("invalid publication year");
            setDate("1");
        }
    }
    public void setIsbn(String isbn) {
        if(isValidISBN(isbn)){
            this.isbn = isbn;
        } else {
            System.out.println("invalid isbn");
            setIsbn("0000000000");
        }
    }
    public void setEdition(String edition) {
        if(isValidEdition(edition)){
            this.edition = edition;
        } else {
            System.out.println("invalid edition");
            setEdition("1");
        }
    }
    public void setLanguage(String language) {
        if(isValidLanguage(publisher)){
            this.language = language;
        } else {
            System.out.println("invalid language");
            setLanguage("language");
        }
    }
    public void setRating(String rating) {
        if (isValidRating(rating)){
            this.rating = rating;
        } else {
            System.out.println("invalid rating");
            setRating("0");
        }
    }
    public void setTags(String in) {
        String[] tags = in.split(",");
        if(!in.isBlank() && tags.length == 0) return;
        this.tags.clear();
        for (String t : tags) {
            addTag(t);
        }
    }
    public void setTranslators(String in) {
        String[] translators = in.split(",");
        if(!in.isBlank() && translators.length == 0) return;
        this.translators.clear();
        for (String t : translators) {
            addTranslator(t);
        }
    }
    public void setCoverPath(String coverPath) {
        if (isValidCoverPath(coverPath)) {
            this.coverPath = coverPath;

        } else {
            System.out.println("invalid Cover path");
            setCoverPath("default.png");
        }
    }
    public void setCover(Image cover) {
        Image image = new Image("default.png");
        if (isValidCover(cover)) {
            this.cover = cover;
        } else {
            setCover(image);
        }
    }

    //Handling Arrays
    public void addTags(ArrayList<String> tags) {
        for (String tag : tags) {
            addTag(tag);
        }
    }
    public void addTag(String tag) {if(isValidTags(tag) && !tags.contains(tag))tags.add(tag);}
    public void removeTag(String tag) {tags.remove(tag);}
    public void addTranslators(ArrayList<String> translators) {
        for (String translator : translators) {
            addTranslator(translator);
        }
    }
    public void addTranslator(String translator) {if(isValidTranslators(translator) && !translators.contains(translator))translators.add(translator);}
    public void removeTranslator(String translator) {translators.remove(translator);}

    //Validation Methods.
    public  Boolean isValidTitle(String input) {
        try {
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public  Boolean isValidAuthor(String input) {
        try {
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public  Boolean isValidSubtitle(String input) {
        try {
            return input != null && !input.isBlank();
        } catch (Exception E){
            return false;
        }
    }
    public  boolean isValidPublisher(String input) {
        try {
            return input != null && !input.isBlank() && !isNumber(input);
        } catch(Exception E){
            return false;
        }
    }
    public  boolean isValidDate(String input) {
        try{
            int i = Integer.parseInt(input);
            return !input.isBlank() && i > 0 && signCheck(input);
        } catch(Exception E) {
            return false;
        }
    }
    public   boolean isValidISBN(String input) {
        try{
            return isNumber(input) && !input.isBlank() && (input.length() == 13 || input.length() ==10) && signCheck(input);
        } catch(Exception E) {
            return false;
        }
    }
    public  boolean isValidEdition(String input) {
        try{
            int i = Integer.parseInt(input);
            return !input.isBlank() && i > 0 && signCheck(input);
        } catch(Exception E) {
            return false;
        }
    }
    public  Boolean isValidLanguage(String input) {
        try {
            return input != null && !input.isBlank() && !isNumber(input);
        } catch(Exception E){
            return false;
        }
    }
    public  boolean isValidRating(String input) {
        try {
            int i = Integer.parseInt(input);
            return !input.isBlank() && (input.length() == 1) && (i >= 0 && i <= 5) && signCheck(input);
        } catch (Exception E) {
            return false;
        }
    }
    public  boolean isValidTags(String input) {
        try {
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public  boolean isValidTranslators(String input) {
        try {
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public  boolean isValidCoverPath(String input) {
        try {
            setCover(new Image(input));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public boolean isValidCover(Image input) {
        return !input.isError();
    }
    private boolean isNumber(String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean signCheck(String input) {
        char sign = input.charAt(0);
        return !(sign == '+' || sign == '-');
    }

    public String edit(){Scanner sc = new Scanner(System.in);return sc.nextLine();}

    //toString
    @Override
    public String toString() {
        if(tags.isEmpty() && translators.isEmpty()) return getTitle()+", "+getSubtitle()+", "+getAuthor()+", "+getPublisher()+", "+getDate()+", "+getIsbn()+", " +getEdition()+", " +getLanguage()+", "+getRating();
        if(tags.isEmpty()) return getTitle()+", "+getSubtitle()+", "+getAuthor()+", "+getPublisher()+", "+getDate()+", "+getIsbn()+", "+getEdition()+", "+getLanguage()+", "+getTranslatorAsString()+", "+getRating();
        if(translators.isEmpty()) return getTitle()+", "+getTagsAsString()+", "+getSubtitle()+", "+getAuthor()+", "+getPublisher()+", "+getDate()+", "+getIsbn()+", "+getEdition()+", "+getLanguage()+", "+getRating();
        return getTitle()+", "+getTagsAsString()+", "+getSubtitle()+", "+getAuthor()+", "+getPublisher()+", "+getDate()+", "+getIsbn()+", "+getEdition()+", "+getLanguage()+","+getTranslatorAsString()+", "+getRating();
    }
    public String getTagsAsString(){
        if (tags.isEmpty()) return "";
        StringBuilder tagsAsString = new StringBuilder();
        for (int i = 0; i < tags.size() - 1; i++) {
            tagsAsString.append(tags.get(i)).append(", ");
        }
        return tagsAsString.append(tags.get(tags.size()-1)).toString();
    }
    public String getTranslatorAsString(){
        if (translators.isEmpty()) return "";
        StringBuilder translatorAsString = new StringBuilder();
        for (int i = 0; i < translators.size() - 1; i++) {
            translatorAsString.append(translators.get(i)).append(", ");
        }
        return translatorAsString.append(translators.get(translators.size()-1)).toString();
    }

    //check it.
    @Override
    public int compareTo(Book book) {
        String thisTitle = this.title.toLowerCase();
        String otherTitle = book.getTitle().toLowerCase();
        // Capital letters must come first
        int compareResult = thisTitle.compareTo(otherTitle);

        if (compareResult == 0) {
            // If the uppercase letters are the same, compare in original uppercase/lowercase order
            compareResult = this.title.compareTo(book.getTitle());
        }

        return compareResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthor(), book.getAuthor()) &&
                Objects.equals(getSubtitle(), book.getSubtitle()) &&
                Objects.equals(getPublisher(), book.getPublisher()) &&
                Objects.equals(getDate(), book.getDate()) &&
                Objects.equals(getIsbn(), book.getIsbn()) &&
                Objects.equals(getEdition(), book.getEdition()) &&
                Objects.equals(getLanguage(), book.getLanguage()) &&
                Objects.equals(getRating(), book.getRating()) &&
                Objects.equals(getTags(), book.getTags()) &&
                Objects.equals(getTranslators(), book.getTranslators());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getSubtitle(), getPublisher(), getDate(), getIsbn(), getEdition(), getLanguage(), getRating(), getTags(), getTranslators());
    }
}
