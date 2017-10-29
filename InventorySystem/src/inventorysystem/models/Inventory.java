/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author java
 */
public class Inventory {
    
    /***************************************************************************
     * Singleton Design Pattern Implementation 
     **************************************************************************/
    private static Inventory _instance;
    
    public static Inventory getInstance() {
        if (_instance == null)
            _instance = new Inventory();
        
        return _instance;
    }

    /***************************************************************************
     * Inventory Class Implementation
     **************************************************************************/
    private final ObservableList<Product> _products;

    /**
     * Returns the collection of products
     * @return An ObservableList of type Product
     */
    public ObservableList<Product> getProducts() {
        return _products;
    }
    
    private final ObservableList<Part> _parts;
    
    /**
     * Returns the collection of parts
     * @return An ObservableList of type Part
     */
    public ObservableList<Part> getAllParts() {
        return _parts;
    }
    
    /**
     * Initializes a new instance of the Inventory Management
     * This is a private constructor, because this object will be used following
     * the singleton design pattern
     */
    private Inventory() {
        _products = FXCollections.observableArrayList();
        _parts = FXCollections.observableArrayList();
    }
    
    public void addProduct(Product product) {
        
    }
    
    public boolean removeProduct(int id) {
        return false;
    }
    
    public Product lookupProduct(int id) {
        return null;
    }
    
    /**
     * Updates a product
     *
     * @deprecated
     * @param id The id of the product being updated
     */
    public void updateProduct(int id) {
        /* This method has no use */
    }

    public void addPart(Part part) {
        _parts.add(part);
    }
    
    public boolean deletePart(Part part) {
        /* Check if part exists on the list */
        if (_parts.contains(part)) 
        {
            /* Part Exists, remote it */
            _parts.remove(part);
            return true;
        }
        else
        {
            /* Part could not be found */
            return false;    
        }
    }
    
    public Part lookupPart(int id) 
    {
        /* Reference to the part found on the search */
        Part foundPart = null;
        
        /* Loop thru all rows inside the parts collection */
        for (int i = 0; i < _parts.size(); i++)
        {
            if (_parts.get(i).getPartID() == id)
            {
                foundPart = _parts.get(i);
                break;
            }
        }
        
        return foundPart;
    }
    
    public Part lookupPart(String name)
    {
        /* Reference to the part found on the search */
        Part foundPart = null;
        
        /* Loop thru all rows inside the parts collection */
        for (int i = 0; i < _parts.size(); i++)
        {
            if (_parts.get(i).getName().equals(name))
            {
                foundPart = _parts.get(i);
                break;
            }
        }
        
        return foundPart;
    }
    
    /**
     * Updates a part
     * 
     * @deprecated
     * @param id The id of the part being updated
     */
    public void updatePart(int id) {
        /* This method has no use */
    }
    
}
