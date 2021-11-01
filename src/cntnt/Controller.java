package cntnt;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Map;


public class Controller {

    @FXML
    private Button produkteBearbeiten;
    @FXML
    private Button backToMain;

    @FXML
    private Pane layer1, layer2;


    @FXML
    TextField eProduktName, ePreis;

    @FXML
    Button eProduktLoeschen, eSpeichern, eHinzufuegen;

    @FXML
    ChoiceBox eKategorien;

    @FXML
    TabPane eTabPane;

    @FXML
    VBox eVorVBox, eHauVBox, eDesVBox, eGetVBox;

    @FXML
    GridPane mGrid, mOrder;

    @FXML
    Button mVorspeise, mHauptgang, mDessert, mGetraenke, mTotalButton;

    @FXML
    Label mTotalPrice;



    private final Editor editor = new Editor();
    private final MainPage mainPage = new MainPage();
    static Map<String, Map<String, Double>> menu;

    public void initialize(){

        menu = ReaderWriter.read("./data/Products.txt");

        initButtons();

        mainPage.initButtons(mGrid, mOrder, mVorspeise, mHauptgang, mDessert, mGetraenke, mTotalButton, mTotalPrice);

    }

    private void initButtons(){

        produkteBearbeiten.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showEditor());
        backToMain.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showHome());
    }



    private void showEditor(){

        layer1.setVisible(false);
        layer1.setDisable(true);

        layer2.setVisible(true);
        layer2.setDisable(false);

        editor.init(eKategorien,eVorVBox, eHauVBox, eDesVBox, eGetVBox, eProduktName, ePreis, eProduktLoeschen, eSpeichern, eHinzufuegen);

    }

    private void showHome(){

        layer1.setVisible(true);
        layer1.setDisable(false);

        layer2.setVisible(false);
        layer2.setDisable(true);

    }




}
