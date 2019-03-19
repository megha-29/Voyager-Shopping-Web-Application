package data;

import utility.ProductDB;

public class Cart
{
  private String email;
  private int product_id;
  private int quantity;
  private double price;
  
  public Cart() {}
  
  public String getEmail()
  {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public int getProduct_id() {
    return product_id;
  }
  
  public void setProduct_id(int product_id) {
    this.product_id = product_id;
  }
  
  public int getQuantity() {
    return quantity;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  public double getPrice() {
    return price;
  }
  
  public void setPrice() {
    Products product = ProductDB.selectProduct(product_id);
    price = (product.getPrice() * quantity);
  }
}