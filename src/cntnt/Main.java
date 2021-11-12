package cntnt;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private Pane root;

    private Parent createContent(){



        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return root;
    }


    @Override
    public void start(Stage primaryStage){

        primaryStage.setWidth(1040);//1024
        primaryStage.setHeight(640);//600
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
