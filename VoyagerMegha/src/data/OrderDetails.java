package data;

import java.sql.Timestamp;

public class OrderDetails
{
  private String email;
  private int orderId;
  private double total;
  private Timestamp orderDate;
  
  public OrderDetails() {}
  
  public String getEmail()
  {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public int getOrderId() {
    return orderId;
  }
  
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
  
  public double getTotal() {
    return total;
  }
  
  public void setTotal(double total) {
    this.total = total;
  }
  
  public Timestamp getOrderDate() {
    return orderDate;
  }
  
  public void setOrderDate(Timestamp orderDate) {
    this.orderDate = orderDate;
  }
}