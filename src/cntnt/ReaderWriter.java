package cntnt;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReaderWriter {

    static Map<String, Map<String, Double>> read(String path){

        Map<String, Map<String, Double>>productsInCategories = new HashMap<>();

        String currentCategory, line;

        for (Categories cat : Categories.values())
            productsInCategories.put(cat.toString(), new HashMap<>());


        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            line = br.readLine();

            if(line == null){
                throw new IOException("Products.txt is empty");
            }

            currentCategory = line.substring(line.indexOf("!") + 1);


            while ((line = br.readLine()) != null){

                if(line.contains("!")){
                    currentCategory = line.substring(line.indexOf("!") + 1);
                    continue;
                }

                String productName = line.substring(0,line.indexOf("#"));
                Double productPrice  = Double.parseDouble(line.substring(line.indexOf("#") + 1));

                productsInCategories.get(currentCategory).put(productName, productPrice);

            }



        } catch (IOException e){
            e.printStackTrace();
        }
        return productsInCategories;

    }


    static void write(Map<String, Map<String, Double>> menu, String path){

        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter(path));

            for (String cat : menu.keySet()){

                wr.write("!"+cat);
                wr.newLine();

                for(String item : menu.get(cat).keySet()){

                    wr.write(item+"#"+menu.get(cat).get(item).toString());
                    wr.newLine();

                }

            }

            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
