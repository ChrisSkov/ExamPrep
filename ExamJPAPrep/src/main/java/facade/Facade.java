/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import entity.ItemType;
import entity.OrderToRuleThemAll;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stoff
 */
public class Facade {

    private static Facade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private Facade()
    {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Facade getFacade(EntityManagerFactory _emf)
    {
        if (instance == null)
        {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public List<Customer> getAllCustomers()
    {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Customer.getAll").getResultList();
    }

    public Customer addCustomer(Customer customer)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            em.close();
        }
        return customer;
    }

    public Customer addCustomer(String email, String name)
    {
        Customer customer = new Customer(email, name);
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            em.close();
        }
        return customer;
    }

    public Customer getCustomerByName(String name) throws Exception
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.createQuery("SELECT m FROM Customer m WHERE m.name = :name", Customer.class).getSingleResult();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("Non found by that name");
        } finally
        {
            em.close();
        }

    }

    public Customer getCustomerById(long id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return (Customer) em.createQuery("SELECT m FROM Customer m WHERE m.id =:id").setParameter("id", id).getSingleResult();
        } finally
        {
            em.close();
        }

    }

    public void populateCustomers()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(new Customer("Satan", "ja@nej.dk"));
            em.persist(new Customer("Ib-Rene", "mail@mail.dk"));
            em.persist(new Customer("Warløkke", "si@MurderHobo.dk"));
            em.persist(new Customer("Chris", "ja@gmail.com"));
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
    }

    public List<ItemType> getAllItemTypes()
    {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("ItemType.getAll").getResultList();
    }

    public ItemType addItemType(ItemType item)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
            return item;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            em.close();
        }
        return item;
    }

    public ItemType addItemType(String name, String description, int price)
    {
        ItemType item = new ItemType(name, description, price);
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
            return item;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            em.close();
        }
        return item;
    }

    public OrderToRuleThemAll addOrder(int id)
    {
        OrderToRuleThemAll order = new OrderToRuleThemAll();
        EntityManager em = emf.createEntityManager();
        Customer cust = getCustomerById(id);
        order.setCustomer(cust);
        try
        {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
            return order;
        } finally
        {
            em.close();
        }

    }

    public ItemType getItemTypeByName(String name) throws Exception
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.createQuery("SELECT m FROM  ItemType m WHERE m.name =: name", ItemType.class).setParameter("name", name).getSingleResult();
        } catch (Exception e)
        {
            throw new Exception("Non found by that name");
        } finally
        {
            em.close();
        }

    }

    public void populateItemType()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(new ItemType("Glaive", "Big heavy dmg boi", 3));
            em.persist(new ItemType("Soul Lantern", "Warløkkes signature weapon", 2));
            em.persist(new ItemType("Short sword", "weapon for pussies", 1));
            em.persist(new ItemType("Hand Axe", "AARRGGGHH!!", 1));
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
    }

}
