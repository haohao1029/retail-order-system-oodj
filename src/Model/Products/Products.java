/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Products;

import Helper.BinarySearch;
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
     * @param isDeleted
     */
    
    private int ID;
    private Double price;
    private int balance;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
    private int sales;

    private final Connection reader = new Connection("products");

    public Products(int ID, String name, Double price, int balance, LocalDateTime createdAt, LocalDateTime updatedAt, int sales, boolean isDeleted) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sales = sales;
        this.isDeleted = isDeleted;
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

    
        public boolean getIsdeleted() {
        return isDeleted;
    }

    public void setSales(int sales) {
        this.sales = sales;
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

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

        public Connection getReader() {
        return reader;
    }
    
    public int getSales() {
        return sales;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
            case "product_id":
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
        return new Products(
                        Integer.valueOf(result[0]),
                        String.valueOf(result[1]),
                        Double.valueOf(result[2]),
                        Integer.valueOf(result[3]),
                        LocalDateTime.parse(result[4]),
                        LocalDateTime.parse(result[5]),
                        Integer.valueOf(result[6]),
                        Boolean.parseBoolean(result[7])
                );
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
            if (!Boolean.parseBoolean(split[7])) {
                switch (queryOperator) {
                    case ">":
                        if (idFile > queryID) {
                                     temp.add(new Products(
                                    Integer.valueOf(split[0]),
                                    String.valueOf(split[1]),
                                    Double.valueOf(split[2]),
                                    Integer.valueOf(split[3]),
                                    LocalDateTime.parse(split[4]),
                                    LocalDateTime.parse(split[5]),
                                    Integer.valueOf(split[6]),
                                    Boolean.parseBoolean(split[7])
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
                            LocalDateTime.parse(split[5]),
                            Integer.valueOf(split[6]),
                            Boolean.parseBoolean(split[7])
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
                            LocalDateTime.parse(split[5]),
                            Integer.valueOf(split[6]),
                            Boolean.parseBoolean(split[7])
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
                                    LocalDateTime.parse(split[5]),
                                    Integer.valueOf(split[6]),
                                    Boolean.parseBoolean(split[7])
                                ));
                        }
                        break;
                }
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
                ? reader.getNewID() + "," + this.name + "," + this.price + "," + this.balance + "," + this.createdAt + "," + this.updatedAt + "," + this.sales + "," + this.isDeleted
                : this.getID() + "," + this.name + "," + this.price + "," + this.balance + "," + this.createdAt + "," + this.updatedAt + "," + this.sales + "," + this.isDeleted;
    }
    
    @Override
    public boolean delete() {
        List<String> fromFile = reader.getFromFile();
        System.out.println(ID);
        fromFile.remove(ID);
        return reader.reWrite(reader.listToString(fromFile));
    }
}
