package cntnt;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


class Editor{


    private Product selectedProduct;
    private VBox[] vBoxes;

    void init(ChoiceBox eKategorien, VBox eVorVBox, VBox eHauVBox, VBox eDesVBox, VBox eGetVBox, TextField eProductName, TextField ePreis, Button eProduktLoeschen, Button eSpeichern, Button eHinzufuegen){

        new Thread(new Task<Void>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> {


                    vBoxes = new VBox[]{eVorVBox, eHauVBox, eDesVBox, eGetVBox};

                    eKategorien.getItems().addAll(Categories.values());

                    eKategorien.setValue(eKategorien.getItems().get(0));


                    loadProducts(eKategorien, vBoxes, eProductName, ePreis);

                    initButtons(eProduktLoeschen, eSpeichern, eHinzufuegen, eKategorien, eProductName, ePreis);

                });
                return null;
            }
        }).start();



    }

    void loadProducts(ChoiceBox eKategorien, VBox[] vBoxes, TextField eProductName, TextField ePreis){

        Product button;

        for(VBox vb : vBoxes){
            vb.getChildren().clear();
        }


        for(String str : Controller.menu.get(Categories.values()[0].toString()).keySet()){//Vorspeisen

            button = new Product(str, Categories.values()[0].toString(), Controller.menu.get(Categories.values()[0].toString()).get(str));

            addEventhandler(eKategorien, button, eProductName, ePreis);

            vBoxes[0].getChildren().add(button);

        }

        for(String str : Controller.menu.get(Categories.values()[1].toString()).keySet()){//Hauptspeisen

            button = new Product(str, Categories.values()[1].toString(), Controller.menu.get(Categories.values()[1].toString()).get(str));

            addEventhandler(eKategorien, button, eProductName, ePreis);

            vBoxes[1].getChildren().add(button);

        }

        for(String str : Controller.menu.get(Categories.values()[2].toString()).keySet()){//Dessert

            button = new Product(str, Categories.values()[2].toString(), Controller.menu.get(Categories.values()[2].toString()).get(str));

            addEventhandler(eKategorien, button, eProductName, ePreis);

            vBoxes[2].getChildren().add(button);

        }

        for(String str : Controller.menu.get(Categories.values()[3].toString()).keySet()){//Getraenke

            button = new Product(str, Categories.values()[3].toString(), Controller.menu.get(Categories.values()[3].toString()).get(str));

            addEventhandler(eKategorien, button, eProductName, ePreis);

            vBoxes[3].getChildren().add(button);

        }




    }

    void addEventhandler(ChoiceBox eKategorien, Product product, TextField eProductName, TextField ePreis){

        product.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            eProductName.setText(product.getText());
            ePreis.setText(String.valueOf(product.price).replace("." ,","));

            eKategorien.setValue(Categories.valueOf(product.cat));

            selectedProduct = product;

        });

    }


    void initButtons(Button eProduktLoeschen, Button eSpeichern, Button eHinzufuegen, ChoiceBox eKategorien, TextField eProductName, TextField ePreis) {

        eSpeichern.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {


            Controller.menu.get(selectedProduct.cat).remove(selectedProduct.getText());

            Controller.menu.get(selectedProduct.cat).put(eProductName.getText(), Double.parseDouble(ePreis.getText().replace(",", ".")));
            updateMenu(eKategorien, vBoxes, eProductName, ePreis);


        });

        eHinzufuegen.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            Controller.menu.get(eKategorien.getValue().toString()).put(eProductName.getText(), Double.parseDouble(ePreis.getText().replace(",", ".")));
            updateMenu(eKategorien, vBoxes, eProductName, ePreis);

        });

        eProduktLoeschen.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            if (selectedProduct != null) {
                Controller.menu.get(selectedProduct.cat).remove(selectedProduct.getText());
                updateMenu(eKategorien, vBoxes, eProductName, ePreis);
            }

        });

    }

    private void updateMenu(ChoiceBox eKategorien, VBox[] vBoxes, TextField eProductName, TextField ePreis){

        ReaderWriter.write(Controller.menu, "./data/Products.txt");

        loadProducts(eKategorien, vBoxes, eProductName, ePreis);

    }


}
