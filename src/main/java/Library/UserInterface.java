package Library;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javafx.util.Callback;
import javafx.util.Duration;


public class UserInterface extends Application {

    private final TableView<Book> table = new TableView<>();
    private boolean isButtonOneActive=true;

    private Stage helpStage = new Stage();
    private Stage listStage = new Stage();
    private Stage addStage = new Stage();
    private Stage bookStage = new Stage();
    private static ObservableList<Book> data = FXCollections.observableArrayList();
    private static final ArrayList<Book> books = JSON.getBooks();
    private boolean searchAll = true;
    public static ObservableList<Book> getData() {
        return data;
    }
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        Library.addBooks("mainJson.json");
        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);

        //Label H-box.
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.TOP_CENTER);
        Label labelBookShook = new Label("BOOKSHOOK");
        labelBookShook.setWrapText(true);
        labelBookShook.setTextFill(Color.rgb(87,22,95)); //Set RGB color.
        hBox1.setPadding(new Insets(-100, 0, 100, 0)); //Add 10 units of space to the right.

        Font labelFont = new Font(75);
        labelBookShook.setFont(labelFont);
        labelBookShook.setLayoutY(10);

        HBox.setHgrow(labelBookShook,Priority.ALWAYS);
        hBox1.getChildren().addAll(labelBookShook);


        //SearchBar and HelpButton H-box.
        HBox hBox2 = new HBox();

        TextField searchBar = new TextField();
        searchBar.setPromptText("===>  Book Name, Author, ISBN etc.");
        searchBar.setPrefSize(1100,50);
        searchBar.setFocusTraversable(false);
        HBox.setHgrow(searchBar,Priority.ALWAYS);


        Region spacer = new Region();
        spacer.setPrefWidth(20); //Add a space 20 units wide

        Button helpButton = new Button("?");
        helpButton.setStyle("-fx-background-radius: 50em; -fx-min-width: 30px; -fx-min-height: 30px; -fx-max-width: 30px; -fx-max-height: 30px; -fx-background-color: #c4d5fc;");
        helpButton.setOnMouseEntered(e -> helpButton.setStyle("-fx-background-radius: 50em; -fx-min-width: 30px; -fx-min-height: 30px; -fx-max-width: 30px; -fx-max-height: 30px; -fx-background-color: #a5d9be"));
        helpButton.setOnMouseExited(e -> helpButton.setStyle("-fx-background-radius: 50em; -fx-min-width: 30px; -fx-min-height: 30px; -fx-max-width: 30px; -fx-max-height: 30px; -fx-background-color: #c4d5fc"));
        helpButton.setPrefSize(50,50);
        helpButton.setTextFill(Color.rgb(214,55,55)); //Set RGB color.
        helpButton.setOnAction(e -> helpMenu());

        hBox2.setAlignment(Pos.CENTER);
        HBox.setHgrow(searchBar,Priority.ALWAYS);
        HBox.setHgrow(helpButton,Priority.ALWAYS);
        hBox2.getChildren().addAll(searchBar,spacer,helpButton);
        hBox2.setPadding(new Insets(0, 35, 100, 35));


        //SearchButton And AddButton
        HBox hBox3 = new HBox();

        //SEARCH
        Button searchButton = new Button("SEARCH");
        searchButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        searchButton.setOnMouseEntered(e -> searchButton.setStyle("-fx-background-color: #a5d9be"));
        searchButton.setOnMouseExited(e -> searchButton.setStyle("-fx-background-color: #c4d5fc"));
        searchButton.setOnAction(e -> {
            searchAll = true;
            listTab(searchBar.getText()); // Call the listTab method with the text from searchBar
            searchBar.clear();
        });
        searchButton.setPrefSize(150,50);

        //SEARCH BY TAG
        Button searchTag = new Button("SEARCH by TAG");
        searchTag.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        searchTag.setOnMouseEntered(e -> searchTag.setStyle("-fx-background-color: #a5d9be"));
        searchTag.setOnMouseExited(e -> searchTag.setStyle("-fx-background-color: #c4d5fc"));
        searchTag.setOnAction(e -> {
            searchAll = false;
            listTab(searchBar.getText()); // Call the listTab method with the text from searchBar
            searchBar.clear();
        });

        searchTag.setPrefSize(150,50);


        //ADD
        Button addButton = new Button("ADD");
        addButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        addButton.setOnMouseEntered(e -> addButton.setStyle("-fx-background-color: #a5d9be"));
        addButton.setOnMouseExited(e -> addButton.setStyle("-fx-background-color: #c4d5fc"));
        addButton.setOnAction(e -> addTab());
        addButton.setPrefSize(150,50);

        Region spacer1 = new Region();
        spacer1.setPrefWidth(150); //Add a space 100 units wide.
        Region spacer2 = new Region();
        spacer2.setPrefWidth(150); //Add a space 100 units wide.


        Region spacer3 = new Region();
        spacer3.setPrefWidth(150);
        Button exportButton = new Button();
        exportButton.setText("EXPORT");
        exportButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        exportButton.setOnMouseEntered(e -> exportButton.setStyle("-fx-background-color: #a5d9be"));
        exportButton.setOnMouseExited(e -> exportButton.setStyle("-fx-background-color: #c4d5fc"));
        exportButton.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Select Directory to save as a JSON File!");
            File f = fc.showSaveDialog(stage);
            try {
                Library.exportBook(f.toPath().toString());
                table.refresh();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        exportButton.setPrefSize(150,50);


        hBox3.getChildren().addAll(searchButton,spacer1,searchTag,spacer2,addButton,spacer3,exportButton);
        hBox3.setAlignment(Pos.CENTER);
        HBox.setHgrow(exportButton,Priority.ALWAYS);
        VBox.setVgrow(vbox1,Priority.ALWAYS);
        vbox1.getChildren().addAll(hBox1,hBox2,hBox3);

        Scene scene = new Scene(vbox1, 1200, 800);
        stage.setScene(scene);
        stage.alwaysOnTopProperty(); //It will always push POPUP to the top.
        stage.setTitle("BOOKSHOOK");
        stage.setResizable(true);
        stage.show();
    }


    //HelpMenu function for users to use the application without difficulty.
    public void helpMenu() {
        //V-box opened
        VBox vHelp1 = new VBox();
        vHelp1.setAlignment(Pos.CENTER);

        //H-box
        HBox hHelp = new HBox();
        hHelp.setAlignment(Pos.TOP_CENTER);

        //Needs to be customized according to the project.
        Text text = new Text("STAGE 1 - ADDING A LIBRARY OR BOOK\n\n" +

                "There are two methods to integrate the library into the application.\n" +
                "The first method involves selecting the desired library from the explorer tab,\n" +
                "which appears upon clicking the 'SELECT PATH' button. Then, proceed by clicking the 'Open' button to import it.\n\n" +

                "The second approach entails copying the path of the desired library and pasting it into the textField located above the 'ADD PATH' button.\n" +
                "This textField includes an example path. Once pasted, click the 'ADD PATH' button to include the library in BOOKSHOOK.\n" +
                "To add a book to the library, fill in the book requirements on the ADD Menu, then simply click the 'ADD' button.\n" +
                "If you have already installed a library, the book you added will be registered within it.\n" +
                "If not, the application will automatically create an empty library for you.\n" +
                "To add book information, simply enter the details. If you'd like to include a cover image,\n"+
                "press the 'SELECT COVER' button, then proceed to press the 'CREATE BOOK' button.\n\n\n" +


                "STAGE 2 - SEARCHING, SORTING BOOKS\n\n" +

                "After adding the library to BOOKSHOOK or creating a new one, finding a specific book is easy.\n" +
                "Simply type one of the book attributes, such as Book Name, Tags, or Author Name, into the SearchBar.\n" +
                "Then, press the 'SEARCH' button below the SearchBar. This allows you to quickly access the books you're looking for in your library.\n" +
                "Or if you want to search by the tag enter the tag then press the SEARCH by TAG button \n"+
                "Once the Book List opens, you can sort the displayed books alphabetically, in reverse alphabetical order, numerically\n" +
                "or vice versa by clicking on the column names at the top of the columns.\n\n\n" +


                "STAGE 3 - EDITING, DELETING THE BOOKS\n\n" +

                "After the books appear in your library, each book row is accompanied by two buttons at the end.\n" +
                "Clicking the button with the trash can symbol deletes the corresponding book\n" +
                "(Note: There's only one warning message upon deleting a book, so exercise caution).\n\n" +

                "To edit the attributes of a book, simply double-click on the attribute itself. This action opens an edit text field where you can make your changes,\n" +
                "followed by pressing the 'Enter' button to confirm.\n\n" +
                "To change book cover, press the book button and select desired image ");




        HBox.setHgrow(hHelp,Priority.ALWAYS);
        hHelp.getChildren().addAll(text);

        VBox.setVgrow(vHelp1,Priority.ALWAYS);
        vHelp1.getChildren().addAll(text);

        Scene helpScene = new Scene(vHelp1, 900, 900);

        //setOnAction
        helpStage.alwaysOnTopProperty(); //It will always push POPUP to the top.
        helpStage.setScene(helpScene);
        helpStage.setTitle("Help Menu");
        //helpStage.setResizable(false);

        //The tab's openness control raises it to the top if it's open.
        if (helpStage.isShowing()){
            helpStage.toFront();
        }
        if (helpStage.isIconified()) {
            helpStage.setIconified(false);
        }
        else {
            helpStage.show();
        }
    }

    //Method to take the data of the books required for the library from the json file and transfer it to the table.
    private ObservableList<Book> getBookData(String text) {
        data.clear();
        Library.addBooks("mainJson.json");
        if(searchAll) {
            SearchBar.search(text);
            return data;
        } else {
            SearchBar.searchByTag(text);
            return data;
        }
    }

    //It is the function unit where you add buttons to each row in the table and gain the functionality of the buttons.
    private void addButtonToTable() {
        TableColumn<Book, Void> delButtonColumn = new TableColumn<>("D");
        TableColumn<Book, Void> bookButtonColumn = new TableColumn<>("B");
        TableColumn<Book, Void> imageColumn = new TableColumn<>("Cover");
        TableColumn<Book, Void> checkColumn = new TableColumn<>("C");
        delButtonColumn.setResizable(false);
        delButtonColumn.setReorderable(false);
        delButtonColumn.setPrefWidth(60);
        bookButtonColumn.setResizable(false);
        bookButtonColumn.setReorderable(false);
        bookButtonColumn.setPrefWidth(60);
        imageColumn.setResizable(false);
        imageColumn.setReorderable(false);
        imageColumn.setPrefWidth(63);
        checkColumn.setResizable(false);
        checkColumn.setReorderable(false);
        checkColumn.setPrefWidth(60);
        delButtonColumn.setStyle("-fx-alignment: CENTER;");
        bookButtonColumn.setStyle("-fx-alignment: CENTER;");
        imageColumn.setStyle("-fx-alignment: CENTER;");
        checkColumn.setStyle("-fx-alignment: CENTER;");
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> delCellFactory = param -> new TableCell<Book, Void>() {
            private final Button delButton = new Button();

            {
                delButton.setOnAction(event -> {
                    Library.removeBook(getTableView().getItems().remove(getIndex()));
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    ImageView delView = new ImageView(new Image("delete.png"));
                    delView.setFitWidth(25);
                    delView.setFitHeight(30);
                    delButton.setGraphic(delView);
                    setGraphic(delButton);
                }
            }
        };


        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> bookCellFactory = param -> new TableCell<Book, Void>() {
            private final Button bookButton = new Button();

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    ImageView bookView = new ImageView(new Image("book.png"));
                    bookView.setFitWidth(25);
                    bookView.setFitHeight(30);
                    bookButton.setGraphic(bookView);
                    setGraphic(bookButton);

                    bookButton.setOnAction(event -> {
                        String[] s = new String[1];
                        Book book = getTableView().getItems().get(getIndex());
                        //Make all cells in this row editable.
                        //table.edit(getIndex(),  table.getColumns().get(0));
                        ImageView selectedBookView = new ImageView(book.getCover());
                        VBox vBoxCover = new VBox();
                        selectedBookView.setFitHeight(550);
                        selectedBookView.setFitWidth(550);

                        Label lTitle = new Label(book.getTitle().toUpperCase(Locale.ROOT));
                        lTitle.setFont(new Font(20));
                        HBox hTitle = new HBox();
                        hTitle.setAlignment(Pos.CENTER);
                        hTitle.getChildren().add(lTitle);
                        hTitle.setPadding(new Insets(10,0,10,0));

                        HBox hBox1 = new HBox(selectedBookView);
                        hBox1.setAlignment(Pos.CENTER);
                        HBox.setHgrow(hBox1,Priority.ALWAYS);
                        hBox1.setPadding(new Insets(0,0,10,0));

                        Button selectCoverButton = new Button("SELECT COVER");
                        selectCoverButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
                        selectCoverButton.setPrefSize(120,30);
                        selectCoverButton.setOnMouseEntered(e -> selectCoverButton.setStyle("-fx-background-color: #a5d9be"));
                        selectCoverButton.setOnMouseExited(e -> selectCoverButton.setStyle("-fx-background-color: #c4d5fc"));

                        selectCoverButton.setOnAction(e -> { // when add button is pressed its opens file explorer
                            FileChooser fc = new FileChooser();
                            fc.setTitle("Select Cover to open");
                            File f = fc.showOpenDialog(bookStage);
                            s[0] =(String.valueOf(f.toPath()));
                            Library.editCoverPath(s[0],book);
                            book.setCoverPath(s[0]);
                            selectedBookView.setImage(book.getCover());
                            Library.editCover(selectedBookView.getImage(),book);
                            table.refresh();//-----------------When the book cover changes, the thumbnail in the first column is refreshed.!!!!-------------
                        });
                        HBox hBox2 = new HBox();
                        hBox2.setAlignment(Pos.CENTER);
                        HBox.setHgrow(selectCoverButton,Priority.ALWAYS);
                        hBox2.getChildren().add(selectCoverButton);
                        VBox.setVgrow(vBoxCover,Priority.ALWAYS);
                        vBoxCover.getChildren().addAll(hTitle,hBox1,hBox2);
                        Scene sceneCover = new Scene(vBoxCover,600,650);
                        //Stage bookStage = new Stage();
                        bookStage.setScene(sceneCover);
                        bookStage.setTitle("Book Cover");
                        //bookStage.setResizable(false);

                        //The tab's openness control raises it to the top if it's open.
                        if (bookStage.isShowing()){
                            bookStage.toFront();
                        }
                        if (bookStage.isIconified()) {
                            bookStage.setIconified(false);
                        }
                        else {
                            bookStage.show();
                        }
                    });
                }
            }
        };

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> checkCellFactory = param -> new TableCell<Book, Void>() {
            private final CheckBox checkBox = new CheckBox();


            {
                checkBox.setOnAction(event -> {
                    Library.addExportedBook(getTableView().getItems().get(getIndex()));
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(checkBox);
                    if (Library.getExportedBooks().contains(table.getItems().get(getIndex()))) {
                        checkBox.setSelected(true);
                    }
                }
                Tooltip a =new Tooltip("To EXPORT the book, check the box");
                a.setShowDelay(Duration.millis(3));
                Font afont = new Font(10);
                a.setFont(afont);
                Tooltip.install(checkBox,a);
            }
        };

        delButtonColumn.setCellFactory(delCellFactory);
        bookButtonColumn.setCellFactory(bookCellFactory);
        checkColumn.setCellFactory(checkCellFactory);

        table.getColumns().add(delButtonColumn);
        table.getColumns().add(bookButtonColumn);
        table.getColumns().add(checkColumn);

        //Add Cover

       Callback<TableColumn<Book,Void>,TableCell<Book,Void>>cellFactory= param -> new TableCell<Book,Void>(){
           private final ImageView imageView = new ImageView();
           {
               imageView.setFitHeight(50);
               imageView.setFitWidth(50);
           }
           public void updateItem(Void item,boolean empty){
               super.updateItem(item,empty);
               if(empty){
                   setGraphic(null);
               }else {
                   imageView.setImage(table.getItems().get(getIndex()).getCover());
                   setGraphic(imageView);
               }
           }

        };
        imageColumn.setCellFactory(cellFactory);
        table.getColumns().add(0,imageColumn);


    }


    //Function that provides a list view of books in a table.
    public void listTab(String text) {
        table.setEditable(true);
        table.getColumns().clear(); //it S clears all the rows and columns inside the listTab before items are added
        table.getItems().clear(); //this way we will not see double items and columns
        //ObservableList<Book> data = getBookData(text);
        data = getBookData(text);

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setPrefWidth(150);
        //titleColumn.setResizable(false);
        titleColumn.setMaxWidth(170);
        titleColumn.setMinWidth(130);
        titleColumn.setReorderable(false);
        titleColumn.setStyle("-fx-alignment: CENTER_LEFT;");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getTitle()));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setOnEditCommit(event -> {
            Library.editTitle(event.getNewValue(), event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        // new for tag
        TableColumn<Book, String> tagColumn = new TableColumn<>("Tag");
        tagColumn.setPrefWidth(150);
        tagColumn.setReorderable(false);
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));
        tagColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getTagsAsString()));
        tagColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tagColumn.setOnEditCommit(event -> {
            Library.editTags(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        // new for author
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setPrefWidth(110);
        authorColumn.setReorderable(false);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getAuthor()));
        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorColumn.setOnEditCommit(event -> {
            Library.editAuthor(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        // new for publisher
        TableColumn<Book, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setPrefWidth(120);
        publisherColumn.setReorderable(false);
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        publisherColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getPublisher()));
        publisherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        publisherColumn.setOnEditCommit(event -> {
            Library.editPublisher(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        TableColumn<Book, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setPrefWidth(80);
        //dateColumn.setResizable(false);
        dateColumn.setReorderable(false);
        dateColumn.setStyle("-fx-alignment: CENTER_LEFT;");
        dateColumn.setMaxWidth(100);
        dateColumn.setMinWidth(60);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getDate()));
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setOnEditCommit(event -> {
            Library.editDate(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setPrefWidth(85);
        //isbnColumn.setResizable(false);
        isbnColumn.setReorderable(false);
        isbnColumn.setStyle("-fx-alignment: CENTER_LEFT;");
        isbnColumn.setMaxWidth(105);
        isbnColumn.setMinWidth(65);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbnColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getIsbn()));
        isbnColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        isbnColumn.setOnEditCommit(event -> {
            Library.editIsbn(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        TableColumn<Book, String> editionColumn = new TableColumn<>("Edition");
        editionColumn.setPrefWidth(65);
        //editionColumn.setResizable(false);
        editionColumn.setReorderable(false);
        editionColumn.setStyle("-fx-alignment: CENTER_LEFT;");
        editionColumn.setMaxWidth(85);
        editionColumn.setMinWidth(45);
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));
        editionColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getEdition()));
        editionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        editionColumn.setOnEditCommit(event -> {
            Library.editEdition(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        TableColumn<Book, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setPrefWidth(75);
        //ratingColumn.setResizable(false);
        ratingColumn.setReorderable(false);
        ratingColumn.setStyle("-fx-alignment: CENTER_LEFT;");
        ratingColumn.setMaxWidth(95);
        ratingColumn.setMinWidth(55);
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getRating()));
        ratingColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ratingColumn.setOnEditCommit(event -> {
            Library.editRating(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        //new for subtitle
        TableColumn<Book, String> subtitleColumn = new TableColumn<>("Subtitle");
        subtitleColumn.setPrefWidth(105);
        subtitleColumn.setReorderable(false);
        subtitleColumn.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        subtitleColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getSubtitle()));
        subtitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        subtitleColumn.setOnEditCommit(event -> {
            Library.editSubtitle(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        // new for language
        TableColumn<Book, String> languageColumn = new TableColumn<>("Language");
        languageColumn.setPrefWidth(110);
        languageColumn.setReorderable(false);
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        languageColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getLanguage()));
        languageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        languageColumn.setOnEditCommit(event -> {
            Library.editLanguage(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });
        // new for translator
        TableColumn<Book, String> translatorColumn = new TableColumn<>("Translator");
        translatorColumn.setPrefWidth(100);
        translatorColumn.setReorderable(false);
        translatorColumn.setCellValueFactory(new PropertyValueFactory<>("translators"));
        translatorColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getTranslatorAsString()));
        translatorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        translatorColumn.setOnEditCommit(event -> {
            Library.editTranslators(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
            table.refresh();
        });

        table.getColumns().addAll(titleColumn,tagColumn,subtitleColumn,authorColumn,publisherColumn,dateColumn,isbnColumn,editionColumn,languageColumn,translatorColumn,ratingColumn);
        addButtonToTable();
        table.setItems(data);

        VBox vbox = new VBox(table);
        vbox.setVgrow(table,Priority.ALWAYS);


        Scene listscene = new Scene(vbox,1385,900);
        listStage.alwaysOnTopProperty();
        listStage.setScene(listscene);
        listStage.setTitle("Book List");
        //listStage.setResizable(false);

        //The tab's openness control raises it to the top if it's open.
        if (listStage.isShowing()){
            listStage.toFront();
        }
        if (listStage.isIconified()) {
            listStage.setIconified(false);
        }
        else {
            listStage.show();
        }

    }

private boolean isWarningShown=false;
    //A method that allows adding books to the library or transferring an entire library.
    public void addTab() {
        String[] s = new String[1];
        //Vbox opened
        VBox vAdd = new VBox();
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("cover.png", 1000, 1000, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        vAdd.setBackground(new Background(backgroundImage));


        vAdd.setPadding(new Insets(10));
        vAdd.setSpacing(10);

        String[] labels = {"Title:", "Tags:", "Subtitle:", "Author:", "Publisher:", "Date:", "ISBN", "Edition:", "Language:", "Translator:", "Rating:"};
        ArrayList<TextField> textFieldArrayList = new ArrayList<>();

        for (String labelText : labels) {
            if(labelText.equals("Translator:")) { continue; } // continues the translator's text field because it can be empty
            //Label label = new Label(labelText);
            TextField textField = new TextField();
            textField.setPromptText(labelText);
            Label hover = new Label("*");
            Font hoverFont = new Font(22);
            hover.setFont(hoverFont);
            hover.setTextFill(Color.rgb(231, 219 ,21)); //Set RGB color.
            Tooltip tooltip = new Tooltip("This field is required");
            tooltip.setShowDelay(Duration.millis(3));
            Font tooltipFont = new Font(10);
            tooltip.setFont(tooltipFont);
            Tooltip.install(hover, tooltip);


            textFieldArrayList.add(textField);

            HBox hbox = new HBox();
            HBox.setHgrow(hbox,Priority.ALWAYS);
            hbox.getChildren().addAll(textField, hover);
            hbox.setAlignment(Pos.CENTER);
            vAdd.getChildren().add(hbox);
        }

        //Label translatorLabeler = new Label("Translator:");
        TextField translatorTextField = new TextField();
        translatorTextField.setPromptText("Translator:");
        HBox translatorHBox = new HBox();
        Label a= new Label("   ");
        translatorHBox.getChildren().addAll(translatorTextField, a);





        translatorHBox.setAlignment(Pos.CENTER);
        vAdd.getChildren().add(translatorHBox);

        Button addButton = new Button("CREATE BOOK");
        addButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        addButton.setOnMouseEntered(e -> addButton.setStyle("-fx-background-color: #a5d9be"));
        addButton.setOnMouseExited(e -> addButton.setStyle("-fx-background-color: #c4d5fc"));
        addButton.setOnAction(e ->  { // when add button is pressed its checks all the text fields excepts translator's textField by adding them into arraylist.
            boolean allFieldsFilled = true;

            for (TextField tf : textFieldArrayList) {
                if (tf.getText().isEmpty() || tf.getText().isBlank()) {
                    allFieldsFilled = false;
                    break;
                }
            }

            if (!allFieldsFilled) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
            } else {
                ArrayList<String> tags = new ArrayList<>();
                tags.add(textFieldArrayList.get(1).getText());

                ArrayList<String> trans = new ArrayList<>();
                trans.add(translatorTextField.getText());

                Book book = new Book(
                        textFieldArrayList.get(0).getText(),
                        tags,
                        textFieldArrayList.get(2).getText(),
                        textFieldArrayList.get(3).getText(),
                        textFieldArrayList.get(4).getText(),
                        textFieldArrayList.get(5).getText(),
                        textFieldArrayList.get(6).getText(),
                        textFieldArrayList.get(7).getText(),
                        textFieldArrayList.get(8).getText(),
                        trans,
                        textFieldArrayList.get(9).getText(),
                        s[0]
                );

                if (!books.contains(book)) {
                    books.add(book);
                    JSON.updateJsonFile();
                    data.add(book);
                    table.refresh();
                } else {
                    System.out.println("Book already exists");
                }
            }


            addTab();
        });
        addButton.setPrefSize(150,50);




        Button selecthPathButton = new Button("SELECT PATH");
        selecthPathButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        selecthPathButton.setPrefWidth(100);
        selecthPathButton.setOnMouseEntered(e -> selecthPathButton.setStyle("-fx-background-color: #a5d9be"));
        selecthPathButton.setOnMouseExited(e -> selecthPathButton.setStyle("-fx-background-color: #c4d5fc"));
        selecthPathButton.setOnAction(e -> { // when add button is pressed its opens file explorer
            FileChooser fc = new FileChooser();
            fc.setTitle("Select File to open");
            File f = fc.showOpenDialog(addStage);
            Library.addBooks(String.valueOf(f.toPath()));
        });


        Button coverButton = new Button("SELECT COVER");
        ImageView bookView = new ImageView(new Image("book.png"));
        bookView.setFitWidth(25);
        bookView.setFitHeight(30);
        coverButton.setGraphic(bookView);
        //setGraphic(coverButton);
        ImageView imageView = new ImageView(new Image("default.png"));
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        coverButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        coverButton.setOnMouseEntered(e -> coverButton.setStyle("-fx-background-color: #a5d9be"));
        coverButton.setOnMouseExited(e -> coverButton.setStyle("-fx-background-color: #c4d5fc"));
        coverButton.setOnAction(e -> { // when add button is pressed its opens file explorer
            FileChooser fc = new FileChooser();
            fc.setTitle("Select Cover to open");
            File f = fc.showOpenDialog(addStage);
            s[0] =(String.valueOf(f.toPath()));
            if(s[0].endsWith(".jpg") || s[0].toLowerCase().endsWith(".png"))  {
                imageView.setImage(new Image(new File(s[0]).toURI().toString()));
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please select an image in PNG or JPEG format.");
                alert.showAndWait();
            }
        });

        HBox addPathHbox = new HBox();
        //
        HBox.setHgrow(addPathHbox,Priority.ALWAYS);
        addPathHbox.setAlignment(Pos.CENTER);
        TextField pathField = new TextField();
        pathField.setPrefWidth(250);
        //
        pathField.setAlignment(Pos.CENTER);
        pathField.setPromptText("\"C:\\Users\\Bowie\\Desktop\\SpaceOddity.json\"");
        Button addPathButton = new Button("ADD PATH");
        //
        addPathButton.setAlignment(Pos.CENTER);
        addPathButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        addPathButton.setPrefWidth(80);
        //

        addPathButton.setOnMouseEntered(e -> addPathButton.setStyle("-fx-background-color: #a5d9be"));
        addPathButton.setOnMouseExited(e -> addPathButton.setStyle("-fx-background-color: #c4d5fc"));



        addPathButton.setOnAction(e -> { // when add button is pressed it's  the text field for a path if its empty its show a warning.
            boolean addField=true;

            if (pathField.getText().isEmpty() || pathField.getText().isBlank()) {
                addField= false;

                if(!addField) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill in field.");
                    alert.showAndWait();

                    isWarningShown = true;
                }
            }else {
                if(isWarningShown){
                    return;
                }
                for (TextField tf : textFieldArrayList) {
                    if (tf.getText().isEmpty() || tf.getText().isBlank()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill in all fields.");
                        alert.showAndWait();
                        isWarningShown = true;
                        return;
                    }
                }

                Library.addBooks(pathField.getText());
                addTab();
               // addPathButton.setAlignment(Pos.CENTER);

                addPathButton.setDisable(true);
                isWarningShown=false;
            }
        });




        //**********************
        Region spacer7 = new Region();
        spacer7.setPrefWidth(15);
        VBox.setVgrow(vAdd,Priority.ALWAYS);
        HBox cover = new HBox();
        cover.getChildren().addAll(coverButton, spacer7, imageView);
        cover.setAlignment(Pos.CENTER);
        vAdd.getChildren().addAll(cover);
        vAdd.getChildren().add(addButton);
        vAdd.setAlignment(Pos.CENTER);


        Region spacer6 = new Region();
        spacer6.setPrefWidth(15);
        Region spacer4 = new Region();
        spacer4.setPrefWidth(10);
        Region spacer5 = new Region();
        spacer5.setPrefWidth(200);
        //
        HBox.setHgrow(addPathHbox,Priority.ALWAYS);
        addPathHbox.getChildren().addAll(spacer6, pathField, spacer4, addPathButton, spacer5, selecthPathButton);
        //
        VBox.setVgrow(vAdd,Priority.ALWAYS);
        vAdd.getChildren().addAll(addPathHbox);


        Scene addScene = new Scene(vAdd, 650, 700);

        //setOnAction
        addStage.alwaysOnTopProperty();//It will always push POPUP to the top.
        addStage.setScene(addScene);
        addStage.setTitle("ADD MENU");
        //addStage.setResizable(true);

        //The tab's openness control raises it to the top if it's open.
        if (addStage.isShowing()){
            addStage.toFront();
        }
        if (addStage.isIconified()) {
            addStage.setIconified(false);
        }
        else {
            addStage.show();
        }
    }
    //show alert
    public static void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Warning!");
        alert.setHeaderText(null);
        alert.setContentText("Invalid Input!Please try again...");
        alert.showAndWait();
    }
    }