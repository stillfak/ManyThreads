package ru.gva.demo.downloadAllFiles;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Содержит класс реализующий графический интерфейс
 *
 * @author Gavrikov V 15it18.
 */

public class Activity extends Application {
    private static final String CSS = "-fx-background-radius: 10; " +
            "-fx-background-color: mistyrose;-fx-text-fill:gray;" +
            "-fx-border-style:solid;-fx-border-radius: 10;" +
            " -fx-border-color: wheat; -fx-border-width: 4;"; // css for TextField`s


    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        Button btn1 = new Button();
        TextField url = new TextField();
        TextField nameAndExpansion = new TextField();
        Label title = new Label();

        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(5);
        root.setVgap(5);

        root.setStyle("-fx-background-color:mistyrose;");
        title.setStyle("-fx-text-fill:gray;");
        url.setStyle(CSS);
        nameAndExpansion.setStyle(CSS);
        btn1.setStyle("-fx-border-radius: 20;-fx-background-radius: 20; " +
                "-fx-text-fill: white; -fx-color: rosybrown;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");


        GridPane.setConstraints(title,0,0);
        GridPane.setConstraints(url, 0, 0);
        GridPane.setConstraints(nameAndExpansion,0,0);

        title.setTranslateX(30);
        url.setTranslateX(30);
        nameAndExpansion.setTranslateX(30);
        btn1.setTranslateX(30);

        title.setTranslateY(0);
        url.setTranslateY(30);
        nameAndExpansion.setTranslateY(65);
        btn1.setTranslateY(100);

        GridPane.setHalignment(btn1,HPos.CENTER);

        btn1.setText("DOWNLOAD");
        url.setPromptText("Insert Your url or path");
        title.setText("Url to a file or path to a file with urls");
        nameAndExpansion.setPromptText("name and expansion");

        btn1.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select directory for save");
            fileChooser.setInitialFileName(String.valueOf(nameAndExpansion.getCharacters()));

            new UrlInFiles(fileChooser.showSaveDialog(new Stage()).getPath(),String.valueOf(url.getCharacters())).start();

        });



        root.getChildren().addAll(url, btn1,title,nameAndExpansion);// we connect blocks

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setResizable(false); // block the window size change

        primaryStage.show();

    }
}
