package Library;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    //JSON.updateJsonFile(); Test it may be dysfunctional.
    public Book(String title, ArrayList<String> tags, String subtitle, String author, String publisher, String date, String isbn, String edition, String language ,ArrayList<String> translators, String rating, String coverPath) {

        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setDate(date);
        setIsbn(isbn);
        setEdition(edition);
        setLanguage(language);
        setRating(rating);
        setSubtitle(subtitle);
        addTags(tags);
        addTranslators(translators);
        setCoverPath(coverPath);
    }

    public Book() {
        this("title", new ArrayList<>(), "subtitle", "author","publisher", "1", "0000000000", "1", "language" ,new ArrayList<>(),"1","default.png");
    }


    //Getters
    public Image getCover() { return cover;}
    public String getCoverPath() {return coverPath;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getPublisher() {return publisher;}
    public String getDate() {return date;}
    public String getIsbn() {return isbn;}
    public String getEdition() {return edition;}
    public String getRating() {return rating;}
    public String getLanguage() {return language;}
    public String getSubtitle(){return subtitle;}

    public ArrayList<String> getTags() {return tags;}

    public ArrayList<String> getTranslators() {return translators;}

    //Setters

    //Change the Else parts to call the edit method in the future.(?)

    public void setCover(Image cover) {
        Image image = new Image("default.png");
        if (isValidCover(cover)) {
            this.cover = cover;
        } else {
            setCover(image);
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
    public void setPublisher(String publisher) {
        if(isValidPublisher(publisher)){
            this.publisher = publisher;
        } else {
            System.out.println("invalid publisher");
            setPublisher("publisher");
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

    public void setSubtitle(String subtitle) {
        if(isValidSubtitle(subtitle)){
            this.subtitle = subtitle;
        } else {
            System.out.println("invalid subtitle");
            setSubtitle("subtitle");
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

    public void setRating(String rating) {
        if (isValidRating(rating)){
            this.rating = rating;
        } else {
            System.out.println("invalid rating");
            setRating("0");
        }
    }

    //Handling Arrays
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
    public void addTag(String tag) {if(isValidTags(tag) && !tags.contains(tag))tags.add(tag);}
    public void addTranslator(String translator) {if(isValidTranslators(translator) && !translators.contains(translator))translators.add(translator);}
    public void addTags(ArrayList<String> tags) {
        for (String tag : tags) {
            addTag(tag);
        }
    }
    public void addTranslators(ArrayList<String> translators) {
        for (String translator : translators) {
            addTranslator(translator);
        }
    }
    public void removeTag(String tag) {tags.remove(tag);}
    public void removeTranslator(String translator) {translators.remove(translator);}

    //Validation Methods.
    public boolean isValidCover(Image input) {
        return !input.isError();
    }
    public  boolean isValidCoverPath(String input) {
        try {
            setCover(new Image(input));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
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
    public  Boolean isValidLanguage(String input) {
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
            return input != null && !input.isBlank();
        } catch(Exception E){
            return false;
        }
    }
    public  boolean isValidDate(String input) {
        try{
            int i = Integer.parseInt(input);
            return !input.isBlank() && i > 0;
        } catch(Exception E) {
            return false;
        }
    }
    public   boolean isValidISBN(String input) {
        try{
            Integer.parseInt(input);
            return !input.isBlank() && (input.length() == 13 || input.length() ==10);
        } catch(Exception E) {
            return false;
        }
    }
    public  boolean isValidEdition(String input) {
        try{
            int i = Integer.parseInt(input);
            return !input.isBlank() && i > 0;
        } catch(Exception E) {
            return false;
        }
    }
    public  boolean isValidRating(String input) {
        try {
            int i = Integer.parseInt(input);
            return !input.isBlank() && (input.length() == 1) && (i >= 0 && i <= 5);
        } catch (Exception E) {
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
    public  boolean isValidTags(String input) {
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
        return Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getTags(), book.getTags()) && Objects.equals(getSubtitle(),
                book.getSubtitle())&& Objects.equals(getAuthor(), book.getAuthor())  && Objects.equals(getPublisher(),
                book.getPublisher()) && Objects.equals(getDate(), book.getDate()) && Objects.equals(getIsbn(),
                book.getIsbn()) && Objects.equals(getEdition(), book.getEdition()) && Objects.equals(getLanguage(),
                book.getLanguage()) && Objects.equals(getTranslators(), book.getTranslators()) && Objects.equals(getRating(), book.getRating());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getTags(), getSubtitle(), getAuthor(), getPublisher(), getDate(), getIsbn(), getEdition(), getLanguage(), getTranslators(), getRating());
    }
}
