/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

import Helper.BinarySearch;
import Helper.Connection;
import java.util.List;

/**
 *
 * @author GJH
 */
public class Customers extends User{
    private int ID;
    private User user;
    protected Connection con = new Connection("users/customers");
    
    public Customers() {
    }

    public Customers(int ID) {
        this.ID = ID;
    }
    public Customers(int ID, User user) {
        super(user.getId());
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public Customers where(String type, String queryString) {
        int i = 0;
        switch (type.toLowerCase()) {
            case "id":
                i = 0;
                break;
            case "user_id":
                i = 1;
                break;
            case "customer_id":
                i = 0;
            default:
                System.out.println("Type not specificied");
                break;
        }
        List<String> fromFile = con.getFromFile();
        for (int j = 1; j < fromFile.size(); j++) {
            String[] split = fromFile.get(j).split(",");
            if (split[i].equals(String.valueOf(queryString))) {
                return new Customers(Integer.valueOf(split[0]),
                        new User().where("id", split[1])
                );
            }

        }
        String[] result = new BinarySearch().bsearch(fromFile, 1, fromFile.size() - 1, Integer.parseInt(queryString), i);
        if (result == null) {
            return null;
        } 
        return new Customers(Integer.valueOf(result[0]),
                                new User().where("id", result[1])
                        );
    }
    
    @Override
    public boolean create() {
        List<String> fromFile = con.getFromFile();

        fromFile.add(this.format(true));
        return con.reWrite(con.listToString(fromFile));

    }

    @Override
    public boolean update() {

        List<String> fromFile = con.getFromFile();
        fromFile.set(getIndex(), this.format(false));

        return con.reWrite(con.listToString(fromFile));
    }

    private String format(boolean isCreating) {
        return (isCreating)
                ? con.getNewID() + "," + this.user.getId()
                : this.ID + "," + this.user.getId();
    }
    private int getIndex() {
        List<String> fromFile = con.getFromFile();
        int userID = super.getId();
        for (int i = 0; i < fromFile.size(); i++) {
            if (fromFile.get(i).split(",")[4].equals(String.valueOf(userID))) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

}
