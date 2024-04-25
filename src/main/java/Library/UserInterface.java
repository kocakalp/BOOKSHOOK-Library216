package Library;

import javafx.application.Application;
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
import java.util.ArrayList;
import javafx.util.Callback;
import javafx.util.Duration;


public class UserInterface extends Application {

    private final TableView<Book> table = new TableView<>();

    private Stage helpStage = new Stage();
    private Stage listStage = new Stage();
    private Stage addStage = new Stage();
    private boolean searchAll = true;
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        JSON.addBooks("mainJson.json");
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

        hBox1.getChildren().addAll(labelBookShook);


        //SearchBar and HelpButton H-box.
        HBox hBox2 = new HBox();

        TextField searchBar = new TextField();
        searchBar.setPromptText("===>  Book Name, Author, ISBN etc.");
        searchBar.setPrefSize(1000,50);
        searchBar.setFocusTraversable(false);


        Region spacer1 = new Region();
        spacer1.setPrefWidth(20); //Add a space 20 units wide

        Button helpButton = new Button("?");
        helpButton.setStyle("-fx-background-radius: 50em; -fx-min-width: 30px; -fx-min-height: 30px; -fx-max-width: 30px; -fx-max-height: 30px; -fx-background-color: #c4d5fc;");
        helpButton.setOnMouseEntered(e -> helpButton.setStyle("-fx-background-radius: 50em; -fx-min-width: 30px; -fx-min-height: 30px; -fx-max-width: 30px; -fx-max-height: 30px; -fx-background-color: #a5d9be"));
        helpButton.setOnMouseExited(e -> helpButton.setStyle("-fx-background-radius: 50em; -fx-min-width: 30px; -fx-min-height: 30px; -fx-max-width: 30px; -fx-max-height: 30px; -fx-background-color: #c4d5fc"));
        helpButton.setPrefSize(50,50);
        helpButton.setTextFill(Color.rgb(214,55,55)); //Set RGB color.
        helpButton.setOnAction(e -> helpMenu());

        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(searchBar,spacer1,helpButton);
        hBox2.setPadding(new Insets(0, 0, 100, 0));


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
        });

        searchTag.setPrefSize(150,50);

        //ADD
        Button addButton = new Button("ADD");
        addButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        addButton.setOnMouseEntered(e -> addButton.setStyle("-fx-background-color: #a5d9be"));
        addButton.setOnMouseExited(e -> addButton.setStyle("-fx-background-color: #c4d5fc"));
        addButton.setOnAction(e -> addTab());
        addButton.setPrefSize(150,50);

        spacer1 = new Region();
        spacer1.setPrefWidth(250); //Add a space 100 units wide.
        Region spacer2 = new Region();
        spacer2.setPrefWidth(250); //Add a space 100 units wide.
        hBox3.getChildren().addAll(searchButton,spacer1,searchTag,spacer2,addButton);
        hBox3.setAlignment(Pos.CENTER);


        vbox1.getChildren().addAll(hBox1,hBox2,hBox3);

        Scene scene = new Scene(vbox1, 1200, 800);

        stage.setScene(scene);
        stage.alwaysOnTopProperty(); //It will always push POPUP to the top.
        stage.setTitle("BOOKSHOOK");
        stage.setResizable(false);
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




        hHelp.getChildren().addAll(text);
        HBox.setHgrow(hHelp,Priority.ALWAYS);

        vHelp1.getChildren().addAll(text);

        Scene helpScene = new Scene(vHelp1, 900, 900);

        //setOnAction
        helpStage.alwaysOnTopProperty(); //It will always push POPUP to the top.
        helpStage.setScene(helpScene);
        helpStage.setTitle("Help Menu");
        helpStage.setResizable(false);

        //The tab's openness control raises it to the top if it's open.
        if (helpStage.isShowing()){
            helpStage.toFront();
        }
        else {
            helpStage.show();
        }
    }

    //Method to take the data of the books required for the library from the json file and transfer it to the table.
    private ObservableList<Book> getBookData(String text) {
        ObservableList<Book> data = FXCollections.observableArrayList();
        JSON.addBooks("mainJson.json");
        if(searchAll) {
            for (int i = 0 ; i < JSON.getBooks().size() ; i++ ) {
                try {
                    data.add(SearchBar.search(text).get(i));

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            return data;
        } else {
            for (int i = 0 ; i < JSON.getBooks().size() ; i++ ) {
                try {
                    data.add(SearchBar.searchByTag(text).get(i));

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            return data;
        }
    }

    //It is the function unit where you add buttons to each row in the table and gain the functionality of the buttons.
    private void addButtonToTable() {
        TableColumn<Book, Void> delButtonColumn = new TableColumn<>("D");
        TableColumn<Book, Void> bookButtonColumn = new TableColumn<>("B");
        TableColumn<Book, Void> imageColumn = new TableColumn<>("Cover");
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> delCellFactory = param -> new TableCell<Book, Void>() {
            private final Button delButton = new Button();

            {
                delButton.setOnAction(event -> {
                    JSON.removeBook(getTableView().getItems().remove(getIndex()));
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
                        //Book book = getTableView().getItems().get(getIndex());
                        //Make all cells in this row editable.
                        //table.edit(getIndex(),  table.getColumns().get(0));
                        ImageView selectedBookView = new ImageView(table.getItems().get(getIndex()).getCover());
                        VBox vBoxCover = new VBox();
                        selectedBookView.setFitHeight(550);
                        selectedBookView.setFitWidth(550);

                        HBox hBox1 = new HBox(selectedBookView);
                        hBox1.setAlignment(Pos.CENTER);
                        HBox.setHgrow(hBox1,Priority.ALWAYS);

                        Button PathButton = new Button("SELECT COVER");
                        PathButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
                        PathButton.setOnMouseEntered(e -> PathButton.setStyle("-fx-background-color: #a5d9be"));
                        PathButton.setOnMouseExited(e -> PathButton.setStyle("-fx-background-color: #c4d5fc"));
                        PathButton.setOnAction(e -> { // when add button is pressed its opens file explorer
                            FileChooser fc = new FileChooser();
                            fc.setTitle("Select File to open");
                            File f = fc.showOpenDialog(addStage);
                            s[0] =(String.valueOf(f.toPath()));
                            Library.editCoverPath(s[0],table.getItems().get(getIndex()));
                            table.getItems().get(getIndex()).setCoverPath(s[0]);
                            selectedBookView.setImage(table.getItems().get(getIndex()).getCover());
                            Library.editCover(selectedBookView.getImage(),table.getItems().get(getIndex()));
                        });
                        HBox hBox2 = new HBox();
                        hBox2.setAlignment(Pos.CENTER);
                        hBox2.getChildren().add(PathButton);
                        vBoxCover.getChildren().addAll(hBox1,hBox2);
                        Scene sceneCover = new Scene(vBoxCover,600,600);
                        Stage stage = new Stage();
                        stage.setScene(sceneCover);
                        stage.setTitle("Book Cover");
                        stage.show();

                    });
                }
            }
        };

        delButtonColumn.setCellFactory(delCellFactory);
        bookButtonColumn.setCellFactory(bookCellFactory);

        table.getColumns().add(delButtonColumn);
        table.getColumns().add(bookButtonColumn);

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
        ObservableList<Book> data = getBookData(text);


        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setPrefWidth(150);
        titleColumn.setResizable(false);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getTitle()));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setOnEditCommit(event -> {
            Library.editTitle(event.getNewValue(), event.getTableView().getItems().get(event.getTablePosition().getRow()) );
        });


        TableColumn<Book, String> tagColumn = new TableColumn<>("Tag");
        tagColumn.setPrefWidth(150);
        tagColumn.setResizable(false);
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));
        tagColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getTagsAsString()));
        tagColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tagColumn.setOnEditCommit(event -> {
            Library.editTags(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });


        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setPrefWidth(110);
        authorColumn.setResizable(false);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getAuthor()));
        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorColumn.setOnEditCommit(event -> {
            Library.editAuthor(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });


        TableColumn<Book, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setPrefWidth(100);
        publisherColumn.setResizable(false);
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        publisherColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getPublisher()));
        publisherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        publisherColumn.setOnEditCommit(event -> {
            Library.editPublisher(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()) );
        });


        TableColumn<Book, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setPrefWidth(110);
        dateColumn.setResizable(false);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getDate()));
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setOnEditCommit(event -> {
            Library.editDate(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });


        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setPrefWidth(80);
        isbnColumn.setResizable(false);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbnColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getIsbn()));
        isbnColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        isbnColumn.setOnEditCommit(event -> {
            Library.editIsbn(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });


        TableColumn<Book, String> editionColumn = new TableColumn<>("Edition");
        editionColumn.setPrefWidth(85);
        editionColumn.setResizable(false);
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));
        editionColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getEdition()));
        editionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        editionColumn.setOnEditCommit(event -> {
            Library.editEdition(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });

        TableColumn<Book, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setPrefWidth(85);
        ratingColumn.setResizable(false);
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getRating()));
        ratingColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ratingColumn.setOnEditCommit(event -> {
            Library.editRating(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });

        TableColumn<Book, String> subtitleColumn = new TableColumn<>("Subtitle");
        subtitleColumn.setPrefWidth(85);
        subtitleColumn.setResizable(false);
        subtitleColumn.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        subtitleColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getSubtitle()));
        subtitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        subtitleColumn.setOnEditCommit(event -> {
            Library.editSubtitle(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });

        TableColumn<Book, String> languageColumn = new TableColumn<>("Language");
        languageColumn.setPrefWidth(85);
        languageColumn.setResizable(false);
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        languageColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getLanguage()));
        languageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        languageColumn.setOnEditCommit(event -> {
            Library.editLanguage(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });


        TableColumn<Book, String> translatorColumn = new TableColumn<>("Translator");
        translatorColumn.setPrefWidth(100);
        translatorColumn.setResizable(false);
        translatorColumn.setCellValueFactory(new PropertyValueFactory<>("translators"));
        translatorColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getTranslatorAsString()));
        translatorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        translatorColumn.setOnEditCommit(event -> {
            Library.editTranslators(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });


        table.getColumns().addAll(titleColumn,tagColumn,subtitleColumn,authorColumn,publisherColumn,dateColumn,isbnColumn,editionColumn,languageColumn,translatorColumn,ratingColumn);
        addButtonToTable();
        table.setItems(data);


        VBox vbox = new VBox(table);
        vbox.setVgrow(table,Priority.ALWAYS);


        Scene scene = new Scene(vbox,1325,900);
        listStage.alwaysOnTopProperty();
        listStage.setScene(scene);
        listStage.setTitle("Book List");
        listStage.setResizable(false);

        //The tab's openness control raises it to the top if it's open.
        if (listStage.isShowing()){
            listStage.toFront();
        }
        else {
            listStage.show();
        }

    }

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
            Label label = new Label(labelText);
            label.setTextFill(Color.rgb( 255,  215,  0)); //Set RGB color.
            TextField textField = new TextField();
            Label hover = new Label("*");
            Font hoverFont = new Font(22);
            hover.setFont(hoverFont);
            hover.setTextFill(Color.rgb(208, 90 ,90)); //Set RGB color.
            Tooltip tooltip = new Tooltip("This field is required");
            tooltip.setShowDelay(Duration.millis(3));
            Font tooltipFont = new Font(10);
            tooltip.setFont(tooltipFont);
            Tooltip.install(hover, tooltip);


            textFieldArrayList.add(textField);

            HBox hbox = new HBox();
            hbox.getChildren().addAll(label, textField, hover);
            vAdd.getChildren().add(hbox);
        }

        Label translatorLabeler = new Label("Translator:");
        TextField translatorTextField = new TextField();
        HBox translatorHBox = new HBox();
        translatorHBox.getChildren().addAll(translatorLabeler, translatorTextField);

        int clickCount = 0;

        Label textLabel = new Label("Text");
        vAdd.getChildren().add(textLabel);


        vAdd.getChildren().add(translatorHBox);

        Button addButton = new Button("CREATE BOOK");
        addButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        addButton.setOnMouseEntered(e -> addButton.setStyle("-fx-background-color: #a5d9be"));
        addButton.setOnMouseExited(e -> addButton.setStyle("-fx-background-color: #c4d5fc"));
        addButton.setOnAction(e ->  { // when add button is pressed its checks all the text fields excepts translator's textField by adding them into arraylist.
            for (TextField tf : textFieldArrayList) {
                if(tf.getText().isEmpty() || tf.getText().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill in all fields.");
                    alert.showAndWait();
                    break;
                }
            }
            ArrayList<String> tags = new ArrayList<>();
            tags.add(textFieldArrayList.get(1).getText());
            ArrayList<String> trans = new ArrayList<>();
            trans.add(translatorTextField.getText());
            Book book = new Book(textFieldArrayList.get(0).getText(), tags, textFieldArrayList.get(2).getText(), textFieldArrayList.get(3).getText(),
                    textFieldArrayList.get(4).getText(), textFieldArrayList.get(5).getText(), textFieldArrayList.get(6).getText(), textFieldArrayList.get(7).getText(),
                    textFieldArrayList.get(8).getText() , trans, textFieldArrayList.get(9).getText() ,s[0]);
            if (!JSON.getBooks().contains(book)) {
                JSON.getBooks().add(book);
                JSON.updateJsonFile();
            } else {
                System.out.println("Book already exist");
            }
            addTab();
        });
        addButton.setPrefSize(150,50);




        Button selecthPathButton = new Button("SELECT PATH");
        selecthPathButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
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

        coverButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        coverButton.setOnMouseEntered(e -> coverButton.setStyle("-fx-background-color: #a5d9be"));
        coverButton.setOnMouseExited(e -> coverButton.setStyle("-fx-background-color: #c4d5fc"));
        coverButton.setOnAction(e -> { // when add button is pressed its opens file explorer
            FileChooser fc = new FileChooser();
            fc.setTitle("Select Cover to open");
            File f = fc.showOpenDialog(addStage);
            s[0] =(String.valueOf(f.toPath()));
        });

        HBox addPathHbox = new HBox();
        TextField pathField = new TextField();
        pathField.setPrefWidth(250);
        pathField.setPromptText("\"C:\\Users\\Bowie\\Desktop\\SpaceOddity.json\"");
        Button addPathButton = new Button("ADD PATH");
        addPathButton.setStyle("-fx-background-color: #c4d5fc"); //Set background color.
        addPathButton.setOnMouseEntered(e -> addPathButton.setStyle("-fx-background-color: #a5d9be"));
        addPathButton.setOnMouseExited(e -> addPathButton.setStyle("-fx-background-color: #c4d5fc"));

        addPathButton.setOnAction(e -> { // when add button is pressed it's  the text field for a path if its empty its show a warning.
            if (pathField.getText().isEmpty() || pathField.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in field.");
                alert.showAndWait();
            }
            Library.addBooks(pathField.getText());
            addTab();
        });

        vAdd.getChildren().add(coverButton);
        vAdd.getChildren().add(addButton);
        vAdd.getChildren().add(selecthPathButton);

        Region spacer4 = new Region();
        spacer4.setPrefWidth(10);
        addPathHbox.getChildren().addAll(pathField, spacer4, addPathButton);
        vAdd.getChildren().add(addPathHbox);


        Scene listScene = new Scene(vAdd, 700, 700);

        //setOnAction
        addStage.alwaysOnTopProperty();//It will always push POPUP to the top.
        addStage.setScene(listScene);
        addStage.setTitle("ADD MENU");
        addStage.setResizable(false);

        //The tab's openness control raises it to the top if it's open.
        if (addStage.isShowing()){
            addStage.toFront();
        }
        else {
            addStage.show();
        }
    }

}