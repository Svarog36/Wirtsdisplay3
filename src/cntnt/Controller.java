package cntnt;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Map;


public class Controller {

    @FXML
    private Button produkteBearbeiten;
    @FXML
    private Button backToLayer1;

    @FXML
    private Pane layer1, layer2;


    @FXML
    TextField eProduktName, ePreis;

    @FXML
    Button eProduktLoeschen, eProduktHinzufuegen;

    @FXML
    ChoiceBox eKategorien;

    @FXML
    TabPane eTabPane;

    @FXML
    VBox eVorVBox, eHauVBox,  eGetVBox;

    Editor editor = new Editor();
    static Map<String, Map<String, Double>> menu;

    public void initialize(){

        menu = ReaderWriter.read("./data/Products.txt");

        ReaderWriter.write(menu, "./data/Products.txt");

        editor.init(eKategorien, eVorVBox, eHauVBox,  eGetVBox);

    }




    public void showEditor(){

        layer1.setVisible(false);
        layer1.setDisable(true);

        layer2.setVisible(true);
        layer2.setDisable(false);


    }

    public void showHome(){

        layer1.setVisible(true);
        layer1.setDisable(false);

        layer2.setVisible(false);
        layer2.setDisable(true);

    }




}
