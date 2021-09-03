/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

import Helper.BinarySearch;
import Helper.Connection;
import Helper.Validator;
import java.util.List;

/**
 *
 * @author CYH & GJH
 */
public class Admin extends User {

    private int ID;
    private Double Salary;
    private String Position;
    private User user;

    protected final Connection con = new Connection("users/admin");
    protected final Validator valid = new Validator();

    public Admin() {
    }

    public Admin(Double Salary) {
        this.Salary = Salary;
    }

    public Admin(int ID, String Position, Double Salary, User user) {
        super(user.getId());
        this.user = user;
        this.ID = ID;
        this.Salary = Salary;
        this.Position = Position;
    }

    public Admin(String Position, Double Salary, User user) {
        super(user.getId());
        this.Salary = Salary;
        this.Position = Position;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double Salary) {
        this.Salary = Salary;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Admin where(String type, String queryString) {

        int i = 0;
        switch (type.toLowerCase()) {
            case "id":
                i = 0;
                break;
            case "position":
                i = 1;
                break;
            case "salary":
                i = 2;
                break;
            case "user_id":
                i = 3;
                break;
            default:
                System.out.println("Type not specificied");
                break;
        }

        List<String> fromFile = con.getFromFile();
                String[] result = new BinarySearch().bsearch(fromFile, 1, fromFile.size() - 1, Integer.parseInt(queryString), i);
        if (result == null) {
            return null;
        } 
 return new Admin(Integer.valueOf(result[0]), result[1], Double.valueOf(result[2]),
                        new User().where("id", result[3])
                );
    }

    @Override
    public boolean create() {
        if (!valid.isValidString(this.Position)) {
            return false;
        }

        List<String> fromFile = con.getFromFile();
        fromFile.add(this.format(true));
        return con.reWrite(con.listToString(fromFile));
    }

    @Override
    public boolean update() {
        if (!valid.isValidString(this.Position)) {
            return false;
        }
        List<String> fromFile = con.getFromFile();
        fromFile.set(getIndex(), this.format(false));
        return con.reWrite(con.listToString(fromFile));
    }

    private int getIndex() {
        List<String> fromFile = con.getFromFile();
        int userID = super.getId();
        for (int i = 0; i < fromFile.size(); i++) {
            if (fromFile.get(i).split(",")[3].equals(String.valueOf(userID))) {
                return i;
            }
        }
        return 0;
    }

    private String format(boolean isCreating) {
        if (!valid.isValidString(this.getPosition())) {
            return null;
        }
        return isCreating
                ? con.getNewID() + "," + this.getPosition() + "," + this.getSalary() + "," + this.user.getId()
                : this.ID + "," + this.getPosition() + "," + this.getSalary() + "," + this.user.getId();
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

}
