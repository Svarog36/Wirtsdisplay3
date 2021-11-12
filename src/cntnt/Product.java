package cntnt;


import javafx.scene.control.Button;

public class Product extends Button {

    String cat;
    double price;


    public Product(String name, String cat, double price){
        super(name);
        this.cat = cat;
        this.price = price;
    }

    public String getNamePlusPrice(){
        return super.getText() + "\n" + price + "€";
    }

}
