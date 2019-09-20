/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author stoff
 */
@Entity
@NamedQuery(name = "OrderToRuleThemAll.deleteAllRows", query = "DELETE from OrderToRuleThemAll")
public class OrderToRuleThemAll implements Serializable {

    @ManyToOne
    private Customer customer;

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int orderID;
    
    public OrderToRuleThemAll()
    {
        
    }
    public Integer getId()
    {
        return id;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public OrderToRuleThemAll(int orderID)
    {
        this.orderID = orderID;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderToRuleThemAll))
        {
            return false;
        }
        OrderToRuleThemAll other = (OrderToRuleThemAll) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Order[ id=" + id + " ]";
    }
    
}
