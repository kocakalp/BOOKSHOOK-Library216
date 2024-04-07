package Library;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;


public class UserInterface extends Application {

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
        //TextArea textArea1 = new TextArea();
        //VBox.setVgrow(textArea1, Priority.ALWAYS);

        /*
        MenuBar menuBar1 = new MenuBar();
        Menu mFile = new Menu("File");


        //NEW
        MenuItem mNewFile = new MenuItem("New");
        mNewFile.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        mNewFile.setOnAction(e -> newFile());

        //OPEN
        MenuItem mOpenFile = new MenuItem("Open");
        mOpenFile.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        mOpenFile.setOnAction(e ->openFile("wasd") );

        //SAVE
        MenuItem mSaveFile = new MenuItem("Save");
        mSaveFile.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

        //QUIT
        SeparatorMenuItem SQuit = new SeparatorMenuItem();
        MenuItem mQuit = new MenuItem("Quit");
        mQuit.setOnAction(e -> System.exit(0));

        Menu mHelp = new Menu("Help");



        mFile.getItems().addAll(mNewFile,mOpenFile,mSaveFile,SQuit,mQuit);
        menuBar1.getMenus().addAll(mFile,mHelp);
         */

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

    public void listTab() {

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

        /*
        //Vbox opened
        VBox vList = new VBox();
        vList.setAlignment(Pos.CENTER);

        //Hbox
        HBox hList = new HBox();
        hList.setAlignment(Pos.CENTER);

        ObservableList<String> items = FXCollections.observableArrayList(
                "Öğe 1", "Öğe 2", "Öğe 3", "Öğe 4", "Öğe 5"
        );
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(items);

        hList.getChildren().addAll(listView);
        vList.getChildren().addAll(hList);

        Scene listScene = new Scene(vList, 1000, 800);

        //setOnAction
        listStage.alwaysOnTopProperty();//POPUP ı hep en üste çıkartacak.
        listStage.setScene(listScene);
        listStage.setTitle("Book List");

        //sekmenin açıklık kontrolü, açıksa en üste çıkartır.
        //(search stage açık iken add stage yi açtırmamayıda ekleyebiliriz.)
        if (listStage.isShowing()){
            listStage.toFront();
        }
        else {
            listStage.show();
        }

         */
    }


    public void addTab() {
        //Vbox opened
        VBox vAdd = new VBox();
        vAdd.setPadding(new Insets(10));
        vAdd.setSpacing(10);

        String[] labels = {"Book Name:", "Tags:", "Author:", "Publication Date:", "ISBN:", "Translator:"};

        boolean textFieldEmpty = false; // will chek if textfields are empty or not

        for (String labelText : labels) {
            if(labelText.equals("Translator:")) { continue; }
            Label label = new Label(labelText);
            TextField textField = new TextField();
            Label hover = new Label("*");
            Font hoverFont = new Font(22);
            hover.setFont(hoverFont);
            hover.setTextFill(Color.rgb(208, 90 ,90));
            Tooltip tooltip = new Tooltip("this area needs to be filled");
            tooltip.setShowDelay(Duration.millis(3));
            Font tooltipFont = new Font(10);
            tooltip.setFont(tooltipFont);
            Tooltip.install(hover, tooltip);


            HBox hbox = new HBox();
            hbox.getChildren().addAll(label, textField, hover);
            vAdd.getChildren().add(hbox);

            if(textField.getText().isEmpty() || textField.getText().isBlank()) {
                textFieldEmpty = true; /// cheks if text field is empty or not
            }
        }

        if(textFieldEmpty) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();

        }

        Button addButton = new Button("ADD");
        addButton.setStyle("-fx-background-color: #c4d5fc"); // Arkaplan rengini ayarla.
        addButton.setOnAction(e -> addTab());
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

    public static void openFile(String fileName) {
        File fileOpened = new File(fileName + ".txt");

        //Vbox opened
        Stage oStage1 = new Stage();

        VBox vOpen1 = new VBox();
        vOpen1.setAlignment(Pos.CENTER);
        HBox hOpen = new HBox();
        hOpen.setAlignment(Pos.CENTER);
        vOpen1.getChildren().addAll(hOpen);
        Button addButton = new Button("KİTAPEKLE.EXE");
        addButton.setAlignment(Pos.CENTER);
        hOpen.getChildren().addAll(addButton);
        Scene oScene1 = new Scene(vOpen1,600,600);
        oStage1.setScene(oScene1);
        oStage1.show();
    }

}