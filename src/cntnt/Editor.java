package cntnt;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class Editor{


    void init(ChoiceBox eKategorien, VBox eVorVBox, VBox eHauVBox, /*VBox eDesVBox*/ VBox eGetVBox){

        new Thread(new Task<Void>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> {

                    eKategorien.getItems().addAll(Categories.values());

                    eKategorien.setValue(eKategorien.getItems().get(0));

                    loadProducts(eVorVBox, eHauVBox,  eGetVBox);

                });
                return null;
            }
        }).start();



    }

    void loadProducts(VBox eVorVBox, VBox eHauVBox, /*VBox eDesVBox,*/ VBox eGetVBox){


        for(String str : Controller.menu.get(Categories.values()[0].toString()).keySet()){//Vorspeisen

            eVorVBox.getChildren().add(new Label(str));

        }

        for(String str : Controller.menu.get(Categories.values()[1].toString()).keySet()){//Hauptspeisen

            eHauVBox.getChildren().add(new Label(str));

        }

        for(String str : Controller.menu.get(Categories.values()[2].toString()).keySet()){//Dessert

            //eDesVBox.getChildren().add(new Label(str));

        }

        for(String str : Controller.menu.get(Categories.values()[3].toString()).keySet()){//Getraenke

            eGetVBox.getChildren().add(new Label(str));

        }

    }

}
