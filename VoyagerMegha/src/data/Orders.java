package data;

public class Orders
{
  private String email;
  
  private int product_id;
  
  private int quantity;
  
  private int orderId;
  

  public Orders() {}
  

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
  
  public int getOrderId()
  {
    return orderId;
  }
  
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
}