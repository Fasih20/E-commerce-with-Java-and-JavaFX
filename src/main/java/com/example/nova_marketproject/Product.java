package com.example.nova_marketproject;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Product {
    private final String name;
    private final String price;
    private final String seller;
    private final String imagePath;

    public Product(String name, String price,String seller, File imageFile) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        String IMAGES_DIRECTORY = "E:\\Coding files\\Practice programs\\Projectuitest";
        this.imagePath = IMAGES_DIRECTORY + File.separator + "product_" + System.currentTimeMillis() + ".jpg";

        // Save the product image to the specified directory
        File imageDirectory = new File(IMAGES_DIRECTORY);
        if (!imageDirectory.exists()) {
            imageDirectory.mkdirs();
        }

        File outputFile = new File(imagePath);
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            ImageIO.write(bufferedImage, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Image getImage() {
        return new Image("file:" + imagePath);
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (Seller: " + seller + ")";
    }
}




