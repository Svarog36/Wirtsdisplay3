package cntnt;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPage {

    private static final Map<String, List<Node>> orders = new HashMap<>();


    void initButtons(GridPane mGrid, GridPane mOrder, Button mVorspeise, Button mHauptgang, Button mDessert, Button mGetraenke, Button mTotalButton, Label mTotalPrice) {

        mVorspeise.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> loadGird(mGrid, mOrder, "Vorspeisen", mTotalButton, mTotalPrice));
        mHauptgang.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> loadGird(mGrid, mOrder, "Hauptspeisen", mTotalButton, mTotalPrice));
        mDessert.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> loadGird(mGrid, mOrder, "Dessert", mTotalButton, mTotalPrice));
        mGetraenke.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> loadGird(mGrid, mOrder, "Getraenke", mTotalButton, mTotalPrice));

        mTotalButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            mOrder.getChildren().clear();
            orders.clear();
            mOrder.getRowConstraints().remove(mOrder.getRowConstraints().get(0));

            mTotalPrice.setText("0,00");

        });

    }

    private void loadGird(GridPane mGrid, GridPane mOrder, String cat, Button mTotalButton, Label mTotalPrice) {

        mGrid.getChildren().clear();

        Map<String, Double> items = Controller.menu.get(cat);

        int i = 0, j = 0;

        for (String key : items.keySet()) {

            if (i % mGrid.getColumnCount() == 0 && i != 0) {
                j++;
                i = 0;
                mGrid.getRowConstraints().add(new RowConstraints());
            }

            mGrid.add(addProductWithHandler(mGrid, mOrder, key, cat, Controller.menu.get(cat).get(key), mTotalButton, mTotalPrice), i, j);
            i++;
        }


    }

    private Product addProductWithHandler(GridPane mGrid, GridPane mOrder, String key, String cat, Double price, Button mTotalButton, Label mTotalPrice) {

        Product product = new Product(key, cat, price);

        product.setPrefWidth(mGrid.getCellBounds(0, 0).getWidth());
        product.setPrefHeight(mGrid.getCellBounds(0, 0).getHeight());

        product.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {


            if (!orders.containsKey(product.getText())) {

                Product order = new Product(key, cat, price);

                order.setPrefWidth(218);
                order.setPrefHeight(82);
                order.setMinHeight(order.getPrefHeight());

                addOrderHandler(order, mOrder, mTotalPrice);

                Label amount = new Label("1");
                amount.setPrefWidth(100);
                amount.setPrefHeight(82);
                amount.setAlignment(Pos.CENTER);
                amount.setStyle("-fx-border-color: black");

                orders.put(order.getText(), new ArrayList<>(List.of(order, amount)));

                mOrder.add(order, 0, mOrder.getRowCount() - 1);
                mOrder.add(orders.get(order.getText()).get(1), 1, mOrder.getRowCount() - 1);

                mOrder.getRowConstraints().add(new RowConstraints());


                adjustPrice(mTotalPrice, price);


            } else {

                ((Label) orders.get(product.getText()).get(1)).setText(String.valueOf(Integer.parseInt(((Label) orders.get(product.getText()).get(1)).getText()) + 1));
                adjustPrice(mTotalPrice, price);

            }


        });

        return product;
    }

    private void adjustPrice(Label mTotalPrice, double price){

        mTotalPrice.setText(String.valueOf(new DecimalFormat("0.00").format(Double.parseDouble(mTotalPrice.getText().replace(",", ".")) + price)).replace(".", ","));

    }

    private void addOrderHandler(Product order, GridPane mOrder, Label mTotalPrice) {

        order.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            ((Label) orders.get(order.getText()).get(1)).setText(String.valueOf(Integer.parseInt(((Label) orders.get(order.getText()).get(1)).getText()) - 1));

            if (Integer.parseInt(((Label) orders.get(order.getText()).get(1)).getText()) <= 0) {

                mOrder.getChildren().removeAll(orders.get(order.getText()));
                orders.remove(order.getText());
                mOrder.getRowConstraints().remove(mOrder.getRowConstraints().get(0));

            }

            adjustPrice(mTotalPrice, -order.price);

        });

    }


}
