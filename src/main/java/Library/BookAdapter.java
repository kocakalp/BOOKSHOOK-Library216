package Library;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

public class BookAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        Book book = (Book) value;
        out.beginObject();
        out.name("title");
        out.value(book.getTitle());
        out.name("author");
        out.value(book.getAuthor());
        out.name("publisher");
        out.value(book.getPublisher());
        out.name("publicationYear");
        out.value(book.getPublicationYear());
        out.name("isbn");
        out.value(book.getIsbn());
        out.name("edition");
        out.value(book.getEdition());
        out.name("tags");
        out.beginArray();
        for (String tag: book.getTags()) {
            out.value(tag);
        }
        out.endArray();
        out.name("translator");
        out.beginArray();
        for (String translator: book.getTranslators()) {
            out.value(translator);
        }
        out.endArray();
        out.endObject();
    }

    //GPTle yazdın bi ara çalış.
    @Override
    public Book read(JsonReader in) throws IOException {
        String title = null;
        String author = null;
        String publisher = null;
        String publicationYear = null;
        String isbn = null;
        String edition = null;
        ArrayList<String> translators = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            if (name.equals("title")) {
                title = in.nextString();
            } else if (name.equals("author")) {
                author = in.nextString();
            } else if (name.equals("publisher")) {
                publisher = in.nextString();
            } else if (name.equals("publicationYear")) {
                publicationYear = in.nextString();
            } else if (name.equals("isbn")) {
                isbn = in.nextString();
            } else if (name.equals("edition")) {
                edition = in.nextString();
            } else if (name.equals("translators")) {
                if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    in.beginArray();
                    while (in.hasNext()) {
                        translators.add(in.nextString());
                    }
                    in.endArray();
                } else {
                    // Handle the case where 'translators' is not an array
                    translators.add(in.nextString());
                }
            } else if (name.equals("tags")) {
                if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    in.beginArray();
                    while (in.hasNext()) {
                        tags.add(in.nextString());
                    }
                    in.endArray();
                } else {
                    // Handle the case where 'tags' is not an array
                    tags.add(in.nextString());
                }
            } else {
                in.skipValue(); // Ignore unknown fields
            }
        }
        in.endObject();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublicationYear(publicationYear);
        book.setIsbn(isbn);
        book.setEdition(edition);
        book.addTranslators(translators);
        book.addTags(tags);

        return book;
    }
}
