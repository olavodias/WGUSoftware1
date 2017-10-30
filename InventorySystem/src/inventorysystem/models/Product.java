/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Represents a Product
 * @author Olavo Henrique Dias
 */
public class Product implements Cloneable {

    /******************************************
     * Define Properties for Data Binding
     ******************************************/
    private final SimpleIntegerProperty idProperty = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nameProperty = new SimpleStringProperty("");
    private final SimpleDoubleProperty priceProperty = new SimpleDoubleProperty(0);
    private final SimpleIntegerProperty inStockProperty = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty minProperty = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty maxProperty = new SimpleIntegerProperty(0);
    
    /******************************************
     * Property Getters/Setters
     ******************************************/
    private final ObservableList<Part> _associatedParts;
    
    /**
     * Returns the Collection of Associated Parts
     * @return A ObservableList of Parts Associated
     */
    public ObservableList<Part> getAssociatedParts() {
        return _associatedParts;
    }
    
    /**
     * Retrieves the Product ID
     * @return      An integer containing the Product ID
     */
    public int getProductID()
    {
        return idProperty.get();
    }
    
    /**
     * Sets the Product ID
     * @param _id   An integer containing the Product ID
     */
    public final void setProductID(int _id)
    {
        idProperty.set(_id);
    }
    
    /**
     * Retrieves the Product Name
     * @return      A string containing the Product Name
     */
    public String getName()
    {
        return nameProperty.get();
    }
    
    /**
     * Sets the Product Name
     * @param _name     A string containing the Product Name
     */
    public final void setName(String _name)
    {
        nameProperty.set(_name);
    }
    
    /**
     * Retrieves the Product Price
     * @return      A double containing the Product Price
     */
    public double getPrice()
    {
        return priceProperty.get();
    }
    
    /**
     * Sets the Product Price
     * @param _price    A double containing the Product Price 
     */
    public final void setPrice(double _price)
    {
        priceProperty.set(_price);
    }

    /**
     * Retrieves the Product Quantity In Stock 
     * @return      An integer containing the quantity in stock
     */
    public int getInStock()
    {
        return inStockProperty.get();
    }
    
    /**
     * Sets the Product Quantity In Stock
     * @param _inStock  An integer containing the quantity in stock
     */
    public final void setInStock(int _inStock)
    {
        inStockProperty.set(_inStock);
    }

    /**
     * Returns the Product Maximum Stock Quantity
     * @return  An integer containing the Maximum Stock Quantity
     */
    public int getMax()
    {
        return maxProperty.get();
    }
    
    /**
     * Sets the Product Maximum Stock Quantity
     * @param _max  An integer containing the Maximum Stock Quantity
     */
    public final void setMax(int _max)
    {
        maxProperty.set(_max);
    }

    /**
     * Returns the Product Minimum Stock Quantity
     * @return  An integer containing the Minimum Stock Quantity
     */
    public int getMin()
    {
        return minProperty.get();
    }
    
    /**
     * Sets the Product Minimum Stock Quantity
     * @param _min  An integer containing the Minimum Stock Quantity
     */
    public final void setMin(int _min)
    {
        minProperty.set(_min);
    }
    
    /**
     * Return the total price of all the components inside the Associated Parts collection.
     * @return 
     */
    public double getTotalComponentsPrice() {

        /* Initialize the internal variable */
        double _totalComponentsPrice = 0;

        /* Loop thru all items in the associated parts collection */
        for (Part part : _associatedParts)
            _totalComponentsPrice += part.getPrice();
        
        return _totalComponentsPrice;
    }

    /******************************************
     * Collection Methods
     ******************************************/
    
    /**
     * Adds a new part to the Product
     * @param part  The Part to be added
     */
    public void addAssociatedPart(Part part) 
    {
        _associatedParts.add(part);
    }
    
    /**
     * Removes the Associated Part from the Parts Collection
     * @param partID    The Part ID to be removed
     * @return          A boolean to define whether the removal was successfull or not
     */
    public boolean removeAssociatedPart(int partID) 
    {
        /* Look for Part ID in the array */
        Part part = lookupAssociatedPart(partID);
        
        /* Check if part is found, if yes then remove it */
        if (part != null)
            _associatedParts.remove(part);
        
        /* Part ID could not be found */
        return false;
    }
    
    /**
     * Look for the Part ID on the Parts Collection
     * @param partID    The Part ID to be searched
     * @return          The Part when found or null when not found
     */
    public Part lookupAssociatedPart(int partID)
    {
        /* Look for Part ID in the array */
        for (Part part : _associatedParts) {
            
            /* Check Part ID */
            if (part.getPartID() == partID)
            {
                /* Part was found, return it then */
                return part;
            }
        }
        
        /* Part was not found */
        return null;
    }
    
    /******************************************
     * Constructors
     ******************************************/
    
    /**
     * Initializes a new Product
     */
    public Product()
    {
        this(0, "", 0.0, 0, 0, 0);
    }
    
    /**
     * Initializes a new Product 
     * @param _id           The Product ID
     * @param _name         The Product Name
     * @param _price        The Product Price
     * @param _inStock      The Product Inventory Quantity
     * @param _min          The Product Minimum Inventory Quantity
     * @param _max          The Product Maximum Inventory Quantity
     * @param _parts        Array of Parts that compose the product
     */
    public Product(int _id, String _name, double _price, int _inStock, int _min, int _max, Part... _parts)
    {
        /* Initialize Collections */
        _associatedParts = FXCollections.observableArrayList();
        
        /* Add elements to the collection if provided in the function */
        if (_parts != null)
            _associatedParts.addAll(_parts);

        /* Initialize Properties */
        this.setProductID(_id);
        this.setName(_name);
        this.setPrice(_price);
        this.setInStock(_inStock);
        this.setMax(_max);
        this.setMin(_min);
    }
    
    /**
     * Makes a copy of the existing object
     * @return A clone of the existing object
     */
    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException e) 
        {
            return null;
        }
        
    }
}
