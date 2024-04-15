package Library;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
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
        out.name("date");
        out.value(book.getDate());
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
        out.name("translators");
        out.beginArray();
        for (String translator: book.getTranslators()) {
            out.value(translator);
        }
        out.endArray();
        out.name("imagePath");
        out.value(book.getImagePath());
        out.endObject();
    }

    //Check spelling, methods and verify functionality.
    @Override
    public Book read(JsonReader in) throws IOException {
        Book book = new Book();
        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "title":
                    book.setTitle(in.nextString());
                    break;
                case "author":
                    book.setAuthor(in.nextString());
                    break;
                case "publisher":
                    book.setPublisher(in.nextString());
                    break;
                case "date":
                    book.setDate(in.nextString());
                    break;
                case "isbn":
                    book.setIsbn(in.nextString());
                    break;
                case "edition":
                    book.setEdition(in.nextString());
                    break;
                case "tags":
                    in.beginArray();
                    ArrayList<String> tags = new ArrayList<>();
                    while (in.hasNext()) {
                        tags.add(in.nextString());
                    }
                    in.endArray();
                    book.addTags(tags);
                    break;
                case "translators":
                    in.beginArray();
                    ArrayList<String> translators = new ArrayList<>();
                    while (in.hasNext()) {
                        translators.add(in.nextString());
                    }
                    in.endArray();
                    book.addTranslators(translators);
                    break;
                case "imagePath":
                    book.setImagePath(in.nextString());
                    break;
                default:
                    in.skipValue(); // Ignore unrecognized fields
                    break;
            }
        }
        in.endObject();
        return book;
    }
}
