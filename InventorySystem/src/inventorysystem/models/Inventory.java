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
    
    private int _nextPartID;
    
    /**
     * Returns the next part id to use and increment the next number
     * @return An integer containing the next part ID
     */
    public int getNextPartID() {
        return getNextPartID(true);
    }
    
    /**
     * Returns the next part id to use
     * @param autoIncrement A boolean to define whether to increment the next number or not
     * @return An integer containing the next part ID
     */
    public int getNextPartID(boolean autoIncrement) {
        
        /* Checks whether to increment or not the part id */
        if (autoIncrement)
            return _nextPartID++;
        else
            return _nextPartID;
    }

    private int _nextProductID;
    
    /**
     * Returns the next product id to use and increment the next number
     * @return An integer containing the next part ID
     */
    public int getNextProductID() {
        return getNextProductID(true);
    }
    
    /**
     * Returns the next product id to use
     * @param autoIncrement A boolean to define whether to increment the next number or not
     * @return An integer containing the next part ID
     */
    public int getNextProductID(boolean autoIncrement) {
        
        /* Checks whether to increment or not the part id */
        if (autoIncrement)
            return _nextProductID++;
        else
            return _nextProductID;
    }
    
    /**
     * Initializes a new instance of the Inventory Management
     * This is a private constructor, because this object will be used following
     * the singleton design pattern
     */
    private Inventory() {
        /* Initialize Collections */
        _products = FXCollections.observableArrayList();
        _parts = FXCollections.observableArrayList();
        
        /* Initialize the Next Numbers */
        _nextPartID = 1;
        _nextProductID = 1;
    }
    
    /**
     * Adds a product to the collection
     * @param product The Product to be added
     */
    public void addProduct(Product product) {
        _products.add(product);
    }
    
    /**
     * Removes a product from the Products Collection
     * @param id        The Product ID
     * @return          A boolean to define whether the product was deleted or not
     */
    public boolean removeProduct(int id) {
         Product product = lookupProduct(id);
         
         if (product == null)
             return false;
         else
             return removeProduct(product);
    }
    
    /**
     * Removes a product from the Products Collection
     * @param product   The Product to be removed
     * @return          A boolean to define whether the product was deleted or not
     */
    public boolean removeProduct(Product product) {

        /* Check if product exists on the list */
        if (_products.contains(product)) 
        {
            /* Product Exists, remote it */
            _products.remove(product);
            return true;
        }
        else
        {
            /* Product could not be found */
            return false;    
        }
    }
    
    /**
     * Look for a product by the given ID
     * @param id        The Product ID
     * @return An instance of the Product found or null if not found
     */
    public Product lookupProduct(int id) {

        /* Reference to the product found on the search */
        Product foundProduct = null;
        
        /* Loop thru all rows inside the parts collection */
        for (int i = 0; i < _products.size(); i++)
        {
            if (_products.get(i).getProductID()== id)
            {
                foundProduct = _products.get(i);
                break;
            }
        }
        
        return foundProduct;
    }

    /**
     * Look for a product by the given ID
     * @param name      The Product Name (partial values are accepted, no wildcard needed)
     * @return An instance of the Product found or null if not found
     */
    public Product lookupProduct(String name) {

        /* Reference to the product found on the search */
        Product foundProduct = null;
        
        for (int i = 0; i < _products.size(); i++)
        {
            if (_products.get(i).getName().toUpperCase().contains(name.toUpperCase()))
            {
                foundProduct = _products.get(i);
                break;
            }
        }
        
        return foundProduct;
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

    /**
     * Replaces a product in the Product Collection
     * @param originalProduct      Reference to the Original Part
     * @param newProduct           Reference to the New Part
     */
    public void replaceProduct(Product originalProduct, Product newProduct)
    {
        _products.add(_products.indexOf(originalProduct), newProduct);
        _products.remove(originalProduct);
    }
    
    /**
     * Add a part to the Parts Collection
     * 
     * @param part The Part to be added
     */
    public void addPart(Part part) {
        _parts.add(part);
    }
    
    /**
     * Removes a part from the Parts Collection
     * @param part The Part to be removed
     * @return A Boolean to define whether the part was deleted or not
     */
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
    
    /**
     * Look for a part in the parts collection
     * @param id The Part ID
     * @return The Part found in the collection or null when the part is not found
     */
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
    
    /**
     * Look for a part in the parts collection
     * @param name The Part Name (a partial description is also accepted, no wildcards needed)
     * @return The Part found in the collection or null when the part is not found
     */
    public Part lookupPart(String name)
    {
        /* Reference to the part found on the search */
        Part foundPart = null;
        
        /* Loop thru all rows inside the parts collection */
        for (int i = 0; i < _parts.size(); i++)
        {
            /* Check if the description contains the input string */
            if (_parts.get(i).getName().toUpperCase().contains(name.toUpperCase()))
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
    
    /**
     * Replaces a part in the Part Collection
     * @param originalPart      Reference to the Original Part
     * @param newPart           Reference to the New Part
     */
    public void replacePart(Part originalPart, Part newPart)
    {
        _parts.add(_parts.indexOf(originalPart), newPart);
        _parts.remove(originalPart);
    }
}
