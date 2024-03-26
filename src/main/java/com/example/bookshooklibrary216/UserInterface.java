package com.example.bookshooklibrary216;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

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
        TextArea textArea1 = new TextArea();
        VBox.setVgrow(textArea1, Priority.ALWAYS);

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



        vbox1.getChildren().addAll(menuBar1,textArea1);
        Scene scene = new Scene(vbox1, 600, 600);
        stage.setScene(scene);
        stage.show();


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