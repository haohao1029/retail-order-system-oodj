/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.OrderItems;

import Helper.BinarySearch;
import Model.Interface.Creatable;
import Model.Interface.Updatable;
import Model.Interface.Queryable;
import Model.Interface.Validable;
import Helper.Connection;
import Model.Orders.Orders;
import Model.Products.Products;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GJH
 */
public class OrderItems implements Creatable, Updatable, Queryable, Validable {
    private int ID;
    private Products product;
    private int quantity;
    private Orders order;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

    private final Connection reader = new Connection("orders/orderitems");

    public OrderItems(int ID, Products product, Orders order, int quantity, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted) {
        this.ID = ID;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }
    
    public OrderItems() {
        
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getID() {
        return ID;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Products getProduct() {
        return product;
    }
    public void setProduct(Products product) {
        this.product = product;
    }

    public Orders getOrder() {
        return order;
    }
    public void setOrder(Orders order) {
        this.order = order;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }

    public void setUpdateAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public Connection getReader() {
        return reader;
    }
    @Override
    public boolean create() {
        List<String> fromFile = reader.getFromFile();
        this.createdAt = LocalDateTime.now();
        fromFile.add(this.format(true));
        return reader.reWrite(reader.listToString(fromFile));
    }

    @Override
    public boolean update() {
        List<String> fromFile = reader.getFromFile();
        fromFile.set(this.getID(), this.format(false));
        return reader.reWrite(reader.listToString(fromFile));
    }


    @Override
    public OrderItems where(String type, String queryString) {
        int i = 0;
        switch (type.toLowerCase()) {
            case "id":
                i = 0;
                break;
            case "product_id":
                i = 1;
                break;
            case "order_id":
                i = 2;
                break;
            case "quantity":
                i = 3;
                break;

            case "createdAt":
                i = 4;
                break;
            case "updatedAt":
                i = 5;
                break;
            case "isDeleted":
                i = 6;
                break;
            default:
                System.out.println("Type not specificied");
                break;
        }
        List<String> fromFile = reader.getFromFile();
        
        String[] result = new BinarySearch().bsearch(fromFile, 1, fromFile.size() - 1, Integer.parseInt(queryString), i);
        if (result == null) {
            return null;
        }        
        return new OrderItems(
                        Integer.valueOf(result[0]),
                        new Products().where("id", result[1]),
                        new Orders().where("id", result[2]),
                        Integer.valueOf(result[3]),
                        LocalDateTime.parse(result[4]),
                        LocalDateTime.parse(result[5]) ,
                        Boolean.parseBoolean(result[6])
                );

       
    }
    
    public ArrayList<OrderItems> getAllByOrder(String id) {
        ArrayList<OrderItems> temp = new ArrayList();
        List<String> fromFile = reader.getFromFile();

        for (int j = 1; j < fromFile.size(); j++) {
           String[] split = fromFile.get(j).split(",");
           if (id.equals(split[2]) && !Boolean.parseBoolean(split[6])) {
               Orders p = new Orders().where("order_id", split[2]);                
               
               OrderItems orderitemss = new OrderItems(
                        Integer.valueOf(split[0]),
                        new Products().where("id", split[1]),
                        new Orders().where("id", split[2]),
                        Integer.valueOf(split[3]),
                        LocalDateTime.parse(split[4]),
                        LocalDateTime.parse(split[5]) ,
                        Boolean.parseBoolean(split[6])
                );
                temp.add(orderitemss);  
            }
        }
        
        return temp;
    }
    
    @Override
    public ArrayList<OrderItems> where(String type, String queryOperator, String queryString) {
        int i = 0;
        switch (type.toLowerCase()) {
            case "id":
                i = 0;
                break;
            case "product_id":
                i = 1;
                break;
            case "order_id":
                i = 2;
                break;
            case "quantity":
                i = 3;
                break;
            case "createdAt":
                i = 4;
                break;
            case "updatedAt":
                i = 5;
                break;
            case "isDeleted":
                i = 6;
                break;                
            default:
                System.out.println("Type not specificied");
                break;
        }

        ArrayList<OrderItems> temp = new ArrayList();
        List<String> fromFile = reader.getFromFile();

            int query = Integer.valueOf(queryString);
            for (int j = 1; j < fromFile.size(); j++) {
                System.out.println(fromFile.get(j));
                String[] split = fromFile.get(j).split(",");
                Double queryInFile = Double.valueOf(split[i]);
                if (!Boolean.parseBoolean(split[6])) {
                    switch (queryOperator.toLowerCase()) {
                        case ">":
                            if (queryInFile > query) {
                                temp.add(new OrderItems(
                            Integer.valueOf(split[0]),
                            new Products().where("id", split[1]),
                            new Orders().where("id", split[2]),
                            Integer.valueOf(split[3]),
                            LocalDateTime.parse(split[4]),
                            LocalDateTime.parse(split[5]) ,
                            Boolean.parseBoolean(split[6])
                                ));
                            }
                            break;
                        case ">=":
                            if (queryInFile >= query) {
                                OrderItems orderitem = new OrderItems(
                            Integer.valueOf(split[0]),
                            new Products().where("id", split[1]),
                            new Orders().where("id", split[2]),
                            Integer.valueOf(split[3]),
                            LocalDateTime.parse(split[4]),
                            LocalDateTime.parse(split[5]) ,
                            Boolean.parseBoolean(split[6])
                                );
                                temp.add(orderitem);
                            }
                            break;
                        case "<":
                            if (queryInFile < query) {
                                temp.add(new OrderItems(
                            Integer.valueOf(split[0]),
                            new Products().where("id", split[1]),
                            new Orders().where("id", split[2]),
                            Integer.valueOf(split[3]),
                            LocalDateTime.parse(split[4]),
                            LocalDateTime.parse(split[5]) ,
                            Boolean.parseBoolean(split[6])
                                ));
                            }
                            break;
                        case "<=":
                            if (queryInFile <= query) {
                                temp.add(new OrderItems(
                            Integer.valueOf(split[0]),
                            new Products().where("id", split[1]),
                            new Orders().where("id", split[2]),
                            Integer.valueOf(split[3]),
                            LocalDateTime.parse(split[4]),
                            LocalDateTime.parse(split[5]) ,
                            Boolean.parseBoolean(split[6])
                                ));
                            }
                            break;
                        case "=":
                        case "==":
                        case "===":
                            if (queryInFile == query) {
                                temp.add(new OrderItems(
                            Integer.valueOf(split[0]),
                            new Products().where("id", split[1]),
                            new Orders().where("id", split[2]),
                            Integer.valueOf(split[3]),
                            LocalDateTime.parse(split[4]),
                            LocalDateTime.parse(split[5]) ,
                            Boolean.parseBoolean(split[6])
                                ));
                            }
                            break;
                    }
                }
        }
        return temp;

    }
    

    @Override
    public ArrayList<OrderItems> all() {
        return this.where("id", ">=", "1");
    }

    private String format(boolean isCreating) {
        String orderID = this.order == null ? "0" : String.valueOf(this.order.getID());
        String productID = this.product == null ? "0" : String.valueOf(this.product.getID());
        return isCreating
                ? reader.getNewID() + "," + productID + "," + orderID + "," + this.quantity + "," + this.createdAt + "," + this.updatedAt + "," + this.isDeleted
                : this.getID() + "," + productID + "," + orderID + "," + this.quantity + "," +  this.createdAt + "," + this.updatedAt + "," + this.isDeleted;
    }


}
