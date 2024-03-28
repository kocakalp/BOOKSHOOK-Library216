package Library;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;


//gson failed
public class UserInterface extends Application {

    public static void main(String[] args) {
        launch();
    }
    public static void newFile() {

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

        TextField searchBar = new TextField("===>  Book Name, Author, ISBN etc. ");
        searchBar.setPrefSize(1000,50);

        Region spacer1 = new Region();
        spacer1.setPrefWidth(20); // 20 birim genişlikte bir boşluk ekleyin

        Button helpButton = new Button("?");
        helpButton.setStyle("-fx-background-radius: 50em; -fx-min-width: 30px; -fx-min-height: 30px; -fx-max-width: 30px; -fx-max-height: 30px; -fx-background-color: #c4d5fc;");
        helpButton.setPrefSize(50,50);//Bu satır işe yarıyormu bilmiyorum. kontrol edeceğim,
        //helpButton.setStyle("-fx-background-color: #c4d5fc"); // Arkaplan rengini ayarla.
        helpButton.setTextFill(Color.rgb(214,55,55));
        helpButton.setOnAction(e -> HelpMenu());

        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(searchBar,spacer1,helpButton);
        hBox2.setPadding(new Insets(0, 0, 100, 0));


        //
        HBox hBox3 = new HBox();
        Button searchButton = new Button("SEARCH");
        searchButton.setStyle("-fx-background-color: #c4d5fc"); // Arkaplan rengini ayarla.
        Button addButton = new Button("ADD");
        addButton.setStyle("-fx-background-color: #c4d5fc"); // Arkaplan rengini ayarla.
        searchButton.setPrefSize(150,50);
        addButton.setPrefSize(150,50);
        Region spacer2 = new Region();
        spacer2.setPrefWidth(250); // 100 birim genişlikte bir boşluk ekleyin
        hBox3.getChildren().addAll(searchButton,spacer2,addButton);
        hBox3.setAlignment(Pos.CENTER);


        vbox1.getChildren().addAll(hBox1,hBox2,hBox3);

        Scene scene = new Scene(vbox1, 1200, 800);

        stage.setScene(scene);
        stage.alwaysOnTopProperty();//POPUP ı hep en üste çıkartacak.
        stage.show();


    }


    public static void HelpMenu() {
        //Vbox opened
        Stage helpStage1 = new Stage();
        VBox vHelp1= new VBox();
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

        Scene helpScene1 = new Scene(vHelp1,600,600);
        //setOnAction
        helpStage1.setScene(helpScene1);
        helpStage1.alwaysOnTopProperty();//POPUP ı hep en üste çıkartacak.
        helpStage1.show();
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