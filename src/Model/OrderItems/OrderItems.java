/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.OrderItems;

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
    private Orders order;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private final Connection reader = new Connection("products");

    public OrderItems(int ID, Products product, Orders order, LocalDateTime createdAt, LocalDateTime updatedAt) {
        
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

    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }

    public void setUpdateAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
            case "createdAt":
                i = 3;
                break;
            case "updatedAt":
                i = 4;
                break;
            default:
                System.out.println("Type not specificied");
                break;
        }
                List<String> fromFile = reader.getFromFile();
        for (int j = 1; j < fromFile.size(); j++) {
            String[] split = fromFile.get(j).split(",");
            if (split[i].equals(queryString)) {
                return new OrderItems(
                    Integer.valueOf(split[0]),
                    new Products().where("product_id", split[1]),
                    new Orders().where("order_id", split[2]),
                    LocalDateTime.parse(split[3]),
                    LocalDateTime.parse(split[4])
                );
            }
        }
        return null;

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
            case "createdAt":
                i = 3;
                break;
            case "updatedAt":
                i = 4;
                break;
            default:
                System.out.println("Type not specificied");
                break;
        }

        ArrayList<OrderItems> temp = new ArrayList();
        List<String> fromFile = reader.getFromFile();

        if ((i == 0) || (i == 1)) {
            int query = Integer.valueOf(queryString);
            for (int j = 1; j < fromFile.size(); j++) {
                String[] split = fromFile.get(j).split(",");
                Double queryInFile = Double.valueOf(split[i]);
                switch (queryOperator.toLowerCase()) {
                    case ">":
                        if (queryInFile > query) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case ">=":
                        if (queryInFile >= query) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "<":
                        if (queryInFile < query) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "<=":
                        if (queryInFile <= query) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "=":
                    case "==":
                    case "===":
                        if (queryInFile == query) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                }
            }
        }
        if ((i == 2) || (i == 3)) {
            LocalDateTime queryTime = LocalDateTime.parse(queryString);
            for (int j = 1; j < fromFile.size(); j++) {
                String[] split = fromFile.get(j).split(",");
                LocalDateTime fileTime = LocalDateTime.parse(split[i]);
                switch (queryOperator.toLowerCase()) {
                    case ">":
                        if (fileTime.isAfter(queryTime)) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
                            ));
                        }

                        break;
                    case ">=":
                        if (fileTime.isAfter(queryTime) || fileTime.isEqual(queryTime)) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "<":
                        if (fileTime.isBefore(queryTime)) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "<=":
                        if (fileTime.isBefore(queryTime) || fileTime.isEqual(queryTime)) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "=":
                    case "==":
                    case "===":
                        if (fileTime.isEqual(queryTime)) {
                            temp.add(new OrderItems(
                                Integer.valueOf(split[0]),
                                new Products().where("product_id", split[1]),
                                new Orders().where("order_id", split[2]),
                                LocalDateTime.parse(split[3]),
                                LocalDateTime.parse(split[4])
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
        String orderID = this.order == null ? "0" : String.valueOf(this.order.getID());
        String productID = this.product == null ? "0" : String.valueOf(this.product.getID());

    private String format(boolean isCreating) {
        return isCreating
                ? reader.getNewID() + "," + this.product + "," + this.order + "," + this.createdAt + "," + this.updatedAt
                : this.getID() + "," + this.product + "," + this.order + "," + this.createdAt + "," + this.updatedAt;
    }


}
