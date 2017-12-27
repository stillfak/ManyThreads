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
 *
 */

public class Activity extends Application {


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
        url.setStyle("-fx-background-radius: 10; " +
                "-fx-background-color: mistyrose;-fx-text-fill:gray;" +
                "-fx-border-style:solid;-fx-border-radius: 10;" +
                " -fx-border-color: wheat; -fx-border-width: 4;");
        btn1.setStyle("-fx-border-radius: 20;-fx-background-radius: 20; " +
                "-fx-text-fill: white; -fx-color: rosybrown;");
        nameAndExpansion.setStyle("-fx-background-radius: 10; " +
                "-fx-background-color: mistyrose;-fx-text-fill:gray;" +
                "-fx-border-style:solid;-fx-border-radius: 10;" +
                " -fx-border-color: wheat; -fx-border-width: 4;");

        GridPane.setConstraints(title,0,0);
        GridPane.setConstraints(url, 0, 0);
        GridPane.setConstraints(nameAndExpansion,0,0);

//        GridPane.setMargin(title,new Insets(0,0,0,0));
//        GridPane.setMargin(urlName,new Insets(50,50,0,50));

        title.setTranslateX(30);
        url.setTranslateX(30);
        nameAndExpansion.setTranslateX(30);
        btn1.setTranslateX(30);

        title.setTranslateY(0);
        url.setTranslateY(30);
        nameAndExpansion.setTranslateY(65);
        btn1.setTranslateY(100);

        GridPane.setHalignment(btn1,HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(url,HPos.CENTER);

        btn1.setText("DOWNLOAD");
        url.setText("Insert Your url or path");
        title.setText("Url to a file or path to a file with urls");
        nameAndExpansion.setText("name and expansion");

        btn1.setOnAction(event -> {
            Stage stage = new Stage();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select directory for save and expansion");
            fileChooser.setInitialFileName(String.valueOf(nameAndExpansion.getCharacters()));

            new UrlInFiles(fileChooser.showSaveDialog(stage).getPath(),String.valueOf(url.getCharacters())).start();


        });



        root.getChildren().addAll(url, btn1,title,nameAndExpansion);

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setResizable(false);

        primaryStage.show();

    }
}
