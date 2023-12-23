package com.example.nova_marketproject;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Products{
    static String name;



    static void nameset (String nam)
    {
        name=nam;
        System.out.println(name);
    }
    static File myObj,orderedit;

    static String DATA_FILE_PATH =name+"_items.txt";


    void crtfile() throws IOException
    {
        myObj = new File(name+"_items.txt");
        orderedit = new File(name+"_ordereditems.txt");
    }
    public static void filecreation() throws IOException {
        try {
            if (myObj.createNewFile() && orderedit.createNewFile()) {
                System.out.println("File created: " + myObj.getName()+ " "+ orderedit.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }



    private List<Product> loadProductData() {
        List<Product> products = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String price = parts[1];
                    String seller = parts[2];
                    String imagePath = parts[3];
                    products.add(new Product(name, price, seller, new File(imagePath)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }



}

