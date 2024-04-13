package Library;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.util.Callback;
import javafx.util.Duration;


public class UserInterface extends Application {

    private final TableView<Book> table = new TableView<>();

    private Stage helpStage = new Stage();
    private Stage listStage = new Stage();
    private Stage addStage = new Stage();

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {

        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);

        //Label Hbox.
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.TOP_CENTER);
        Label labelBookShook = new Label("BOOKSHOOK");
        labelBookShook.setWrapText(true);
        labelBookShook.setTextFill(Color.rgb(87,22,95));
        hBox1.setPadding(new Insets(-100, 0, 100, 0)); // Sağa 10 birim boşluk ekle

        Font labelFont = new Font(75);
        labelBookShook.setFont(labelFont);
        labelBookShook.setLayoutY(10);

        hBox1.getChildren().addAll(labelBookShook);


        //SearchBar and HelpButton Hbox.
        HBox hBox2 = new HBox();

        TextField searchBar = new TextField();
        searchBar.setPromptText("===>  Book Name, Author, ISBN etc.");
        searchBar.setPrefSize(1000,50);
        searchBar.setFocusTraversable(false);


        Region spacer1 = new Region();
        spacer1.setPrefWidth(20); // 20 birim genişlikte bir boşluk ekleyin

        Button helpButton = new Button("?");
        helpButton.setStyle("-fx-background-radius: 50em; -fx-min-width: 30px; -fx-min-height: 30px; -fx-max-width: 30px; -fx-max-height: 30px; -fx-background-color: #c4d5fc;");
        helpButton.setPrefSize(50,50);//Bu satır işe yarıyormu bilmiyorum. kontrol edeceğim,
        //helpButton.setStyle("-fx-background-color: #c4d5fc"); // Arkaplan rengini ayarla.
        helpButton.setTextFill(Color.rgb(214,55,55));
        helpButton.setOnAction(e -> helpMenu());

        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(searchBar,spacer1,helpButton);
        hBox2.setPadding(new Insets(0, 0, 100, 0));


        //SearchButton And AddButton
        HBox hBox3 = new HBox();

        //SEARCH
        Button searchButton = new Button("SEARCH");
        searchButton.setStyle("-fx-background-color: #c4d5fc"); // Arkaplan rengini ayarla.
        searchButton.setOnAction(e -> listTab());
        searchButton.setPrefSize(150,50);

        //ADD
        Button addButton = new Button("ADD");
        addButton.setStyle("-fx-background-color: #c4d5fc"); // Arkaplan rengini ayarla.
        addButton.setOnAction(e -> addTab());
        addButton.setPrefSize(150,50);

        Region spacer2 = new Region();
        spacer2.setPrefWidth(250); // 100 birim genişlikte bir boşluk ekleyin
        hBox3.getChildren().addAll(searchButton,spacer2,addButton);
        hBox3.setAlignment(Pos.CENTER);


        vbox1.getChildren().addAll(hBox1,hBox2,hBox3);

        Scene scene = new Scene(vbox1, 1200, 800);

        stage.setScene(scene);
        stage.alwaysOnTopProperty();//POPUP ı hep en üste çıkartacak.
        stage.setTitle("BOOKSHOOK");
        stage.show();

    }


    public void helpMenu() {
        //Vbox opened
        VBox vHelp1 = new VBox();
        vHelp1.setAlignment(Pos.CENTER);

        //Hbox
        HBox hHelp = new HBox();
        hHelp.setAlignment(Pos.CENTER);

        //Lab için yazdığım programdan aldım özelleştiricem
        Text text = new Text("For Save file press save or Ctrl+S\n" +
                "For Open file press Open or Ctrl+O\n" +
                "For Quit  press Quit or Ctrl+Q\n");


        hHelp.getChildren().addAll(text);
        vHelp1.getChildren().addAll(text);

        Scene helpScene = new Scene(vHelp1, 600, 600);

        //setOnAction
        helpStage.alwaysOnTopProperty();//POPUP ı hep en üste çıkartacak.
        helpStage.setScene(helpScene);
        helpStage.setTitle("Help Menu");

        //sekmenin açıklık kontrolü, açıksa en üste çıkartır.
        if (helpStage.isShowing()){
            helpStage.toFront();
        }
        else {
            helpStage.show();
        }
    }

    private ObservableList<Book> getBookData() {
        ObservableList<Book> data = FXCollections.observableArrayList();
        JSON.addBooks("mainJson");
        // Kitap verilerinizi buraya ekleyin
        /*
        data.add(new Book("Kitap Başlığı","Yazarr","publisher","basmacı","aaaa","aadfa"));
        data.add(new Book("sevAL kİTAP","Yazar2","basımcı1","1994", "1000", "new"));
        data.add(new Book("eNreDİZKitap","Yazar1","basımcı2","1994", "1000", "new"));
         */
        for (int i = 0 ; i < JSON.getBooks().size() ; i++ ) {
            data.add(SearchBar.search("").get(i));
        }

        // Daha fazla kitap ekleyin
        return data;
    }

    private void addButtonToTable() {
        TableColumn<Book, Void> delButtonColumn = new TableColumn<>("D");
        TableColumn<Book, Void> edtButtonColumn = new TableColumn<>("E");

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> delCellFactory = param -> new TableCell<Book, Void>() {
            private final Button delButton = new Button();

            {
                delButton.setOnAction(event -> {
                    //getTableView().getItems().remove(getIndex());
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


        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> edtCellFactory = param -> new TableCell<Book, Void>() {
            private final Button edtButton = new Button();

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    ImageView edtView = new ImageView(new Image("edit.png"));
                    edtView.setFitWidth(25);
                    edtView.setFitHeight(30);
                    edtButton.setGraphic(edtView);
                    setGraphic(edtButton);

                    edtButton.setOnAction(event -> {
                        Book book = getTableView().getItems().get(getIndex());
                        // Bu satırdaki tüm hücreleri düzenlenebilir hale getir
                        table.edit(getIndex(),  table.getColumns().get(0));
                    });
                }
            }
        };

        delButtonColumn.setCellFactory(delCellFactory);
        edtButtonColumn.setCellFactory(edtCellFactory);

        table.getColumns().add(delButtonColumn);
        table.getColumns().add(edtButtonColumn);
    }

    public void listTab() {
        table.setEditable(true);
        table.getColumns().clear(); // it S clears all the raws and collumns inside of the listTab before items are added
        table.getItems().clear(); // this way we will not see double items and columns
        ObservableList<Book> data = getBookData();

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setPrefWidth(150);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getTitle()));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setOnEditCommit(event -> {
            Library.editTitle(event.getNewValue(), event.getTableView().getItems().get(event.getTablePosition().getRow()) );
        });


        //Arraylist alıyor nasıl yapacaımı bilemedim.
        TableColumn<Book, String> tagColumn = new TableColumn<>("Tag");
        tagColumn.setPrefWidth(100);
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));
        tagColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getTagsAsString()));
        tagColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tagColumn.setOnEditCommit(event -> {
            //add TAG değil setTag lazım
            Library.editTags(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });


        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setPrefWidth(100);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getAuthor()));
        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorColumn.setOnEditCommit(event -> {
            Library.editAuthor(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });

        TableColumn<Book, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setPrefWidth(100);
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        publisherColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getPublisher()));
        publisherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        publisherColumn.setOnEditCommit(event -> {
            Library.editPublisher(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()) );
        });

        TableColumn<Book, String> dateColumn = new TableColumn<>("Publication Date");
        dateColumn.setPrefWidth(150);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        dateColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getPublicationYear()));
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setOnEditCommit(event -> {
            Library.editPublicationYear(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });

        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setPrefWidth(100);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbnColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getIsbn()));
        isbnColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        isbnColumn.setOnEditCommit(event -> {
            Library.editIsbn(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });

        //Arraylist alıyor nasıl yapacaımı bilemedim.
        TableColumn<Book, String> translatorColumn = new TableColumn<>("Translator");
        translatorColumn.setPrefWidth(100);
        translatorColumn.setCellValueFactory(new PropertyValueFactory<>("translators"));
        translatorColumn.setCellValueFactory(data1 -> new SimpleStringProperty(data1.getValue().getTranslatorAsString()));
        translatorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        translatorColumn.setOnEditCommit(event -> {
            //add translator değil setTranslator lazım ve tam düzgün çalışmıyor
            Library.editTranslators(event.getNewValue(),event.getTableView().getItems().get(event.getTablePosition().getRow()));
        });



        //EDIT buttonu için deneme.
        /*
        // Düzenleme butonunu içeren kolon
        TableColumn<Book, Boolean> editCol = new TableColumn<>("Edit");
        editCol.setCellValueFactory(data1 -> new SimpleBooleanProperty(true));
        editCol.setCellFactory(col -> {
            Button editButton = new Button("Edit");
            TableCell<Book, Boolean> cell = new TableCell<>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                        editButton.setOnAction(event -> {
                            Book book = getTableView().getItems().get(getIndex());
                            // Bu satırdaki tüm hücreleri düzenlenebilir hale getir
                            table.edit(getIndex(),  table.getColumns().get(0));
                            table.edit(getIndex(), table.getColumns().get(2));
                            table.edit(getIndex(), table.getColumns().get(3));

                        });
                    }
                }
            };
            return cell;
        });
         */

        table.getColumns().addAll(titleColumn,tagColumn,authorColumn,publisherColumn,dateColumn,isbnColumn,translatorColumn);
        addButtonToTable();
        table.setItems(data);



        VBox vbox = new VBox(table);
        vbox.setVgrow(table,Priority.ALWAYS);

        Scene scene = new Scene(vbox,1000,750);
        listStage.setScene(scene);
        listStage.setTitle("Book List");

        if (listStage.isShowing()){
            listStage.toFront();
        }
        else {
            listStage.show();
        }





        /*
         class XCell extends ListCell<String> {
            HBox hbox = new HBox();
            Label label = new Label("");
            Pane pane = new Pane(); //boşluk
            Button delButton = new Button("Del");
            Button editButton = new Button("Edt");

            public XCell() {
                super();

                hbox.getChildren().addAll(label,pane,delButton,editButton);
                HBox.setHgrow(pane, Priority.ALWAYS);
                delButton.setOnAction(event -> getListView().getItems().remove(getItem()));
                editButton.setOnAction(event -> {
                    String currentItem = getItem();
                    TextField textField = new TextField(currentItem);
                    textField.positionCaret(currentItem.length());
                    hbox.getChildren().set(0, textField);
                    textField.requestFocus();

                    textField.setOnAction(actionEvent -> updateItem(textField.getText()));
                    textField.setOnMouseClicked(mouseEvent -> updateItem(textField.getText()));
                    textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue) {
                            updateItem(textField.getText());
                        }
                    });
                });


            }
             private void updateItem(String newText) {
                 getListView().getItems().set(getIndex(), newText);
                 hbox.getChildren().set(0, label);
             }
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(null);
                setGraphic(null);

                if (item != null && !empty) {
                    label.setText(item);
                    setGraphic(hbox);
                }
            }
        }



        StackPane listPane = new StackPane();
        Scene scene = new Scene(listPane, 1000, 750);
        listStage.setScene(scene);
        ObservableList<String> list = FXCollections.observableArrayList(
                "Item 1", "Item 2", "Item 3", "Item 4","Item 5", "Item 6", "Item 7", "Item 8");
        ListView<String> lv = new ListView<>(list);
        lv.setCellFactory(param -> new XCell());
        listPane.getChildren().add(lv);
        listStage.setTitle("Book List");
        listStage.show();

         */

    }


    public void addTab() {
        //Vbox opened
        VBox vAdd = new VBox();
        vAdd.setPadding(new Insets(10));
        vAdd.setSpacing(10);

        String[] labels = {"Book Title:", "Author:", "Publisher:", "Publication Date:", "ISBN", "Edition:", "Translator:", "Tags:"};
        ArrayList<TextField> textFieldArrayList = new ArrayList<>();

        for (String labelText : labels) {
            if(labelText.equals("Translator:")) { continue; } // continues the translator's text field because it can be empty
            Label label = new Label(labelText);
            TextField textField = new TextField();
            Label hover = new Label("*");
            Font hoverFont = new Font(22);
            hover.setFont(hoverFont);
            hover.setTextFill(Color.rgb(208, 90 ,90));
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

        Label translatorLaber = new Label("Translator:");
        TextField translatorTextField = new TextField();
        HBox translatorHBox = new HBox();
        translatorHBox.getChildren().addAll(translatorLaber, translatorTextField);
        vAdd.getChildren().add(translatorHBox);

        Button addButton = new Button("ADD");
        addButton.setStyle("-fx-background-color: #c4d5fc"); // Arkaplan rengini ayarla.
        addButton.setOnAction(e ->  { // when add button is pressed its cheks all the text fields excepts translator's textfield by adding them into a arraylist
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
            tags.add(textFieldArrayList.get(6).getText());
            ArrayList<String> trans = new ArrayList<>();
            trans.add(translatorTextField.getText());
            Book book = new Book(textFieldArrayList.get(0).getText(), textFieldArrayList.get(1).getText(), textFieldArrayList.get(2).getText(), textFieldArrayList.get(3).getText(), textFieldArrayList.get(4).getText(),textFieldArrayList.get(5).getText(),tags,trans);
            JSON.getBooks().add(book);
            JSON.updateJsonFile();
            addTab();
        });
        addButton.setPrefSize(150,50);

        vAdd.getChildren().add(addButton);
        Scene listScene = new Scene(vAdd, 600, 600);

        //setOnAction
        addStage.alwaysOnTopProperty();//POPUP ı hep en üste çıkartacak.
        addStage.setScene(listScene);
        addStage.setTitle("ADD MENU");

        //sekmenin açıklık kontrolü, açıksa en üste çıkartır.
        if (addStage.isShowing()){
            addStage.toFront();
        }
        else {
            addStage.show();
        }
    }

}