/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Customer;
import entity.ItemType;
import entity.OrderToRuleThemAll;
import facade.Facade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author stoff
 */
public class Tester {

    public static void main(String[] args) 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        Facade facade = Facade.getFacade(emf);
        Customer cust = new Customer("Satan","a@b.dk");
        Customer c1 = facade.addCustomer(cust);
        System.out.println(c1.getId());
        Customer c2 = facade.addCustomer( "s@d.dk","dinmor");
        System.out.println(c2.getName());
        ItemType glaive = facade.addItemType("Glaive of Doom", "It's a glaive...", 9001);
        System.out.println(glaive.getId());
        System.out.println(facade.getAllCustomers());
        Customer cus = facade.getCustomerById(1);
        System.out.println(cus.getName());
        //OrderToRuleThemAll order = facade.addOrder(1);
    }
}
