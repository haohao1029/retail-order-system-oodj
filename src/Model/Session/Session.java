/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Session;

import Helper.Connection;
import Model.User.Customers;
import Model.User.Admin;
import Model.User.User;

/**
 *
 * @author GJH
 */
public class Session {

    Connection session = new Connection("users/session");

    public Session() {
    }

    public void setUser(String userId) {
        session.reWrite(userId);
    }

    public User getUser() {
        return new User().where("id", this.getID());
    }

    public Admin getAdmin() {
        return new Admin().where("user_id", this.getID());
    }
    public Customers getCustomer() {
        Customers c = new Customers().where("user_id", this.getID());
        return c;
    }

    private String getID() {
        return session.getFromFile().get(0);
    }

}
