package Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library.addBooks("mainJson");
        for (Book b : JSON.getBooks()) {
            System.out.println(b);
        }
        ArrayList<Book> books = SearchBar.search("3");
        System.out.println("Start");
        for (Book b : books) {
            System.out.println(b);
        }
    }
}
