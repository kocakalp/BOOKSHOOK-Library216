package Library;
import java.util.ArrayList;

public class Book implements Comparable<Book>  {

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

    /* Method allInformation adinda yeni bir string arraylisti olusturarak butun kitap bilgilerini for-each loop da translartor arraylistinden değerleri alıp birlestirir ve allInformation'i returnler */
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

    //Getters
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getPublisher() {return publisher;}
    public String getPublicationYear() {return publicationYear;}
    public String getIsbn() {return isbn;}
    public String getEdition() {return edition;}
    public ArrayList<String> getTranslators() {return translators;}
    public ArrayList<String> getTags() {return tags;}

    public void addTag(String tag) {tags.add(tag);}
    public void addTranslator(String translator) {translators.add(translator);}
    public void removeTag(String tag) {tags.remove(tag);}
    public void removeTranslator(String translator) {translators.remove(translator);}

    //Setters
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setPublisher(String publisher) {this.publisher = publisher;}
    public void setPublicationYear(String publicationYear) {this.publicationYear = publicationYear;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    public void setEdition(String edition) {this.edition = edition;}
    public void setTranslators(ArrayList<String> translators) {this.translators = translators;}
    public void setTags(ArrayList<String> tags) {this.tags = tags;}
    //Edit Methods.

    // "               " boşluk ve null kontorlu yapılmalı.
    public void editTitle(String titleNew) {setTitle(titleNew);}
    public void editAuthor(String authorNew) {setAuthor(authorNew);}
    public void editPublisher(String publisherNew) {setPublisher(publisherNew);}
    public void editPublicationYear(String publicationYearNew){setPublicationYear(publicationYearNew);}
    public void editISBN(String isbnNew) {setIsbn(isbnNew);}
    public void editEdition(String editionNew) {setEdition(editionNew);}
    //public void editBook(Book bookNew) {setBook(bookNew);}

    //Prototype -- çalışırmı bilemedim yorgun kafa ile yazıyorum.
    public void editTranslators(ArrayList<String> translatorsNew) {
        translators.clear();
        for (int i = 0 ; i < translatorsNew.size() ; i++) {
            translators.add(translatorsNew.get(i).toString());
        }
    }
    public void  editTags(ArrayList<String> tagsNew) {
        tags.clear();;
        for (int i = 0 ; i < tagsNew.size() ; i++) {
            tags.add(tagsNew.get(i));
        }
    }


}
