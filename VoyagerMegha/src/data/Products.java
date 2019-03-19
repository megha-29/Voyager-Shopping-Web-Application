/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Base64;

public class Products {
    
    private int product_id;
    private String product_name;
    private double price;
    private int availableQuantity;
    private String description;
    private String added_by;
    private byte[] image;
    private String imageData;
    
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int poduct_id) {
        this.product_id = poduct_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
        this.imageData = Base64.getEncoder().encodeToString(image);
    }
    
    public String getImageData() {
        return this.imageData;
    }

    public void setImageData() {
        if(image!=null){
            this.imageData = Base64.getEncoder().encodeToString(this.image);
        }
    }
}
