/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Orders;

import Helper.BinarySearch;
import Model.Interface.Creatable;
import Model.Interface.Updatable;
import Model.Interface.Queryable;
import Model.Interface.Validable;
import Helper.Connection;
import Model.User.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GJH
 */
public class Orders implements Updatable, Validable, Queryable {
       
    private int ID;
    private User user;
    private Double totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
    private final Connection reader = new Connection("/orders/orders");
    
    public Orders(int ID, User user, Double totalAmount, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted) {
        this.ID = ID;
        this.user = user;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
      
    }
    public Orders() {
    }
    
    public int getID() {
        return ID;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

        public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdateAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public boolean getIsdeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
            case "user_id":
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
            case "isDeleted":
                i = 5;
                break;
            case "order_id":
                i = 0;
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
        Orders order = new Orders(
                Integer.valueOf(result[0]),
                new User().where("id", result[1]),
                Double.valueOf(result[2]),
                LocalDateTime.parse(result[3]),
                LocalDateTime.parse(result[4]),           
                Boolean.parseBoolean(result[5])     
        );

        return order;   
    }
    
    public int create() {
        List<String> fromFile = reader.getFromFile();
        this.createdAt = LocalDateTime.now();
        fromFile.add(this.format(true));
        reader.reWrite(reader.listToString(fromFile));
        return reader.getNewID();
    }
    

    
    @Override
    public ArrayList<Orders> where(String type, String queryOperator, String queryString) {
        int i = 0;
        switch (type.toLowerCase()) {
            case "id":
                i = 0;
                break;
            case "user_id":
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
            case "isDeleted":
                i = 5;
                break;
            default:
                System.out.println("Type not specificied");
                break;
        }

        ArrayList<Orders> temp = new ArrayList();
        List<String> fromFile = reader.getFromFile();

            int query = Integer.valueOf(queryString);
            for (int j = 1; j < fromFile.size(); j++) {
                String[] split = fromFile.get(j).split(",");
                Double queryInFile = Double.valueOf(split[i]);
                if (!Boolean.parseBoolean(split[5])) {
                    switch (queryOperator.toLowerCase()) {
                        case ">":
                            if (queryInFile > query) {
                                temp.add(new Orders(
                            Integer.valueOf(split[0]),
                            new User().where("id", split[1]),
                            Double.valueOf(split[2]),
                            LocalDateTime.parse(split[3]),
                            LocalDateTime.parse(split[4]),
                            Boolean.parseBoolean(split[5])     
                                ));
                            }
                            break;
                        case ">=":
                            if (queryInFile >= query) {
                                Orders order = new Orders(
                            Integer.valueOf(split[0]),
                            new User().where("id", split[1]),
                            Double.valueOf(split[2]),
                            LocalDateTime.parse(split[3]),
                            LocalDateTime.parse(split[4]),
                            Boolean.parseBoolean(split[5])     
                                );
                                temp.add(order);
                            }
                            break;
                        case "<":
                            if (queryInFile < query) {
                                temp.add(new Orders(
                            Integer.valueOf(split[0]),
                            new User().where("id", split[1]),
                            Double.valueOf(split[2]),
                            LocalDateTime.parse(split[3]),
                            LocalDateTime.parse(split[4]),
                            Boolean.parseBoolean(split[5])     
                                ));
                            }
                            break;
                        case "<=":
                            if (queryInFile <= query) {
                                temp.add(new Orders(
                            Integer.valueOf(split[0]),
                            new User().where("id", split[1]),
                            Double.valueOf(split[2]),
                            LocalDateTime.parse(split[3]),
                            LocalDateTime.parse(split[4]),
                            Boolean.parseBoolean(split[5])     
                                ));
                            }
                            break;
                        case "=":
                        case "==":
                        case "===":
                            if (queryInFile == query) {
                                temp.add(new Orders(
                            Integer.valueOf(split[0]),
                            new User().where("id", split[1]),
                            Double.valueOf(split[2]),
                            LocalDateTime.parse(split[3]),
                            LocalDateTime.parse(split[4]),
                            Boolean.parseBoolean(split[5])     
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
        
        String userId = this.user == null ? "0" : String.valueOf(this.user.getId());

        return isCreating
                ? reader.getNewID() + "," + userId + "," + this.totalAmount + "," + this.createdAt + "," + this.updatedAt + "," + this.isDeleted
                : this.getID() + "," + userId + "," + this.totalAmount + "," + this.createdAt + "," + this.updatedAt + "," + this.isDeleted;
    }

}
