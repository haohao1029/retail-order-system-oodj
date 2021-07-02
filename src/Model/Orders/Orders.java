/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Orders;

import Model.Interface.Creatable;
import Model.Interface.Updatable;
import Model.Interface.Queryable;
import Model.Interface.Validable;
import Helper.Connection;
import Model.User.Customers;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GJH
 */
public class Orders implements Creatable, Updatable, Validable, Queryable {
       
    private int ID;
    private Customers customer;
    private Double totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private final Connection reader = new Connection("/orders/orders");
    
    public Orders(int ID, Customers customer, Double totalAmount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.ID = ID;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        
    }
    public Orders() {
    }
    
    public int getID() {
        return ID;
    }
    public Customers getCustomer() {
        return customer;
    }

    public void setCustomerId(Customers customer) {
        this.customer = customer;
    }
        public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
    public Orders where(String type, String queryString) {
        int i = 0;
        switch (type.toLowerCase()) {
            case "id":
                i = 0;
                break;
            case "customer_id":
                i = 1;
                break;
            case "totalAmount":
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
                return new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
                        LocalDateTime.parse(split[3]),
                        LocalDateTime.parse(split[4])            
                );
            }
        }
        return null;
    }
    
    @Override
    public boolean create() {
        List<String> fromFile = reader.getFromFile();
        this.createdAt = LocalDateTime.now();
        fromFile.add(this.format(true));
        return reader.reWrite(reader.listToString(fromFile));
    }
    @Override
    public ArrayList<Orders> where(String type, String queryOperator, String queryString) {
        int i = 0;
        switch (type.toLowerCase()) {
            case "id":
                i = 0;
                break;
            case "customerId":
                i = 1;
                break;
            case "totalAmount":
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

        ArrayList<Orders> temp = new ArrayList();
        List<String> fromFile = reader.getFromFile();

        if ((i == 0) || (i == 1)) {
            int query = Integer.valueOf(queryString);
            for (int j = 1; j < fromFile.size(); j++) {
                String[] split = fromFile.get(j).split(",");
                Double queryInFile = Double.valueOf(split[i]);
                switch (queryOperator.toLowerCase()) {
                    case ">":
                        if (queryInFile > query) {
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
                        LocalDateTime.parse(split[3]),
                        LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case ">=":
                        if (queryInFile >= query) {
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
                        LocalDateTime.parse(split[3]),
                        LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "<":
                        if (queryInFile < query) {
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
                        LocalDateTime.parse(split[3]),
                        LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "<=":
                        if (queryInFile <= query) {
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
                        LocalDateTime.parse(split[3]),
                        LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "=":
                    case "==":
                    case "===":
                        if (queryInFile == query) {
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
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
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
                        LocalDateTime.parse(split[3]),
                        LocalDateTime.parse(split[4])
                            ));
                        }

                        break;
                    case ">=":
                        if (fileTime.isAfter(queryTime) || fileTime.isEqual(queryTime)) {
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
                        LocalDateTime.parse(split[3]),
                        LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "<":
                        if (fileTime.isBefore(queryTime)) {
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
                        LocalDateTime.parse(split[3]),
                        LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "<=":
                        if (fileTime.isBefore(queryTime) || fileTime.isEqual(queryTime)) {
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
                        LocalDateTime.parse(split[3]),
                        LocalDateTime.parse(split[4])
                            ));
                        }
                        break;
                    case "=":
                    case "==":
                    case "===":
                        if (fileTime.isEqual(queryTime)) {
                            temp.add(new Orders(
                        Integer.valueOf(split[0]),
                        new Customers().where("user_id", split[1]),
                        Double.valueOf(split[2]),
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
    public boolean update() {
        List<String> fromFile = reader.getFromFile();
        fromFile.set(this.getID(), this.format(false));
        return reader.reWrite(reader.listToString(fromFile));
    }
    
    
     @Override
    public ArrayList<Orders> all() {
        return this.where("id", ">=", "1");
    }
    
    private String format(boolean isCreating) {
        
        String userId = this.customer == null ? "0" : String.valueOf(this.customer.getID());

        return isCreating
                ? reader.getNewID() + "," + userId + "," + this.totalAmount + "," + this.createdAt + "," + this.updatedAt
                : this.getID() + "," + userId + "," + this.totalAmount + "," + this.createdAt + "," + this.updatedAt;
    }

}
