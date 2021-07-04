/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Products;

import Model.Interface.Creatable;
import Model.Interface.Updatable;
import Model.Interface.Queryable;
import Model.Interface.Validable;
import Helper.Connection;
import Model.Interface.Deletable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GJH
 */
public class Products implements Creatable, Deletable, Updatable, Validable, Queryable {

    /**
     *
     * @param ID
     * @param name
     * @param price
     * @param balance
     * @param createdAt
     * @param updatedAt
     */
    
    private int ID;
    private Double price;
    private int balance;
    private String name;
     private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private final Connection reader = new Connection("products");

    public Products(int ID, String name, Double price, int balance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Products() {
    }

    public int getID() {
        return ID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

        public Connection getReader() {
        return reader;
    }
    
    @Override
    public Products where(String type, String queryString) {
        int i = 0;
        switch (type.toLowerCase()) {
            case "id":
                i = 0;
                break;
            case "name":
                i = 1;
                break;
            case "price":
                i = 2;
                break;
            case "balance":
                i = 3;
                break;
            case "createdAt":
                i = 4;
                break;
            case "updatedAt":
                i = 5;
                break;
            default:
                System.out.println("Type not specificied");
                break;
        }
        List<String> fromFile = reader.getFromFile();
        for (int j = 1; j < fromFile.size(); j++) {
            String[] split = fromFile.get(j).split(",");
            if (split[i].equals(queryString)) {
                return new Products(
                        Integer.valueOf(split[0]),
                        String.valueOf(split[1]),
                        Double.valueOf(split[2]),
                        Integer.valueOf(split[3]),
                        LocalDateTime.parse(split[4]),
                        LocalDateTime.parse(split[5]));
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
    public boolean update() {
        List<String> fromFile = reader.getFromFile();
        for (int i = 1; i < fromFile.size(); i ++ ) {
            String[] split = fromFile.get(i).split(",");
            int idFile = Integer.valueOf(split[0]);
            if (idFile == this.getID()) {
                fromFile.set(i, this.format(false));
                return reader.reWrite(reader.listToString(fromFile));
            }
        }
        return false;
    }

    @Override
    public ArrayList<Products> where(String type, String queryOperator, String queryString) {
        ArrayList<Products> temp = new ArrayList<>();
        int queryID = Integer.valueOf(queryString);
        List<String> fromFile = reader.getFromFile();
        
        for (int i = 1; i < fromFile.size(); i++) {
            String[] split = fromFile.get(i).split(",");
            int idFile = Integer.valueOf(split[0]);
            switch (queryOperator) {
                case ">":
                    if (idFile > queryID) {
                                 temp.add(new Products(
                                Integer.valueOf(split[0]),
                                String.valueOf(split[1]),
                                Double.valueOf(split[2]),
                                Integer.valueOf(split[3]),
                                LocalDateTime.parse(split[4]),
                                LocalDateTime.parse(split[5])
                            ));
                    }
                    break;
                case ">=":
                    if (idFile >= queryID) {
                                 temp.add(new Products(
                                Integer.valueOf(split[0]),
                                String.valueOf(split[1]),
                                Double.valueOf(split[2]),
                                Integer.valueOf(split[3]),
                                LocalDateTime.parse(split[4]),
                                LocalDateTime.parse(split[5])
                            ));
                    }
                    break;
                case "<":
                    if (idFile < queryID) {
                                 temp.add(new Products(
                                Integer.valueOf(split[0]),
                                String.valueOf(split[1]),
                                Double.valueOf(split[2]),
                                Integer.valueOf(split[3]),
                                LocalDateTime.parse(split[4]),
                                LocalDateTime.parse(split[5])
                            ));
                    }
                    break;
                case "<=":
                    if (idFile <= queryID) {
                                 temp.add(new Products(
                                Integer.valueOf(split[0]),
                                String.valueOf(split[1]),
                                Double.valueOf(split[2]),
                                Integer.valueOf(split[3]),
                                LocalDateTime.parse(split[4]),
                                LocalDateTime.parse(split[5])
                            ));
                    }
                    break;
            }
        }
        return temp;
    }

    @Override
    public ArrayList<Products> all() {
        return this.where("id", ">=", "1");
    }
    
    private String format(boolean isCreating) {

        return isCreating
                ? reader.getNewID() + "," + this.name + "," + this.price + "," + this.balance + "," + this.createdAt + "," + this.updatedAt
                : this.getID() + "," + this.name + "," + this.price + "," + this.balance + "," + this.createdAt + "," + this.updatedAt;
    }
    @Override
    public boolean delete() {
        List<String> fromFile = reader.getFromFile();
        System.out.println(ID);
        fromFile.remove(ID);
        return reader.reWrite(reader.listToString(fromFile));
    }
    

}
