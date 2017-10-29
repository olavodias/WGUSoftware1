/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a Part
 * 
 * @author Olavo Henrique Dias
 */
public abstract class Part {
    
    /******************************************
     * Define Properties for Data Binding
     ******************************************/
    public final SimpleIntegerProperty idProperty = new SimpleIntegerProperty(0);
    public final SimpleStringProperty nameProperty = new SimpleStringProperty("");
    public final SimpleDoubleProperty priceProperty = new SimpleDoubleProperty(0);
    public final SimpleIntegerProperty inStockProperty = new SimpleIntegerProperty(0);
    public final SimpleIntegerProperty minProperty = new SimpleIntegerProperty(0);
    public final SimpleIntegerProperty maxProperty = new SimpleIntegerProperty(0);
    
    /******************************************
     * Property Getters/Setters
     ******************************************/
    
    /**
     * Retrieves the Part ID
     * @return      An integer containing the Part ID
     */
    public int getPartID()
    {
        return idProperty.get();
    }
    
    /**
     * Sets the Part ID
     * @param _id   An integer containing the Part ID
     */
    public final void setPartID(int _id)
    {
        idProperty.set(_id);
    }

    /**
     * Retrieves the Part Name
     * @return      A string containing the Part Name
     */
    public String getName()
    {
        return nameProperty.get();
    }
    
    /**
     * Sets the Part Name
     * @param _name     A string containing the Part Name
     */
    public final void setName(String _name)
    {
        nameProperty.set(_name);
    }
    
    /**
     * Retrieves the Part Price
     * @return      A double containing the Part Price
     */
    public double getPrice()
    {
        return priceProperty.get();
    }
    
    /**
     * Sets the Part Price
     * @param _price    A double containing the Part Price 
     */
    public final void setPrice(double _price)
    {
        priceProperty.set(_price);
    }

    /**
     * Retrieves the Part Quantity In Stock 
     * @return      An integer containing the quantity in stock
     */
    public int getInStock()
    {
        return inStockProperty.get();
    }
    
    /**
     * Sets the Part Quantity In Stock
     * @param _inStock  An integer containing the quantity in stock
     */
    public final void setInStock(int _inStock)
    {
        inStockProperty.set(_inStock);
    }

    /**
     * Returns the Part Maximum Stock Quantity
     * @return  An integer containing the Maximum Stock Quantity
     */
    public int getMax()
    {
        return maxProperty.get();
    }
    
    /**
     * Sets the Part Maximum Stock Quantity
     * @param _max  An integer containing the Maximum Stock Quantity
     */
    public final void setMax(int _max)
    {
        maxProperty.set(_max);
    }

    /**
     * Returns the Part Minimum Stock Quantity
     * @return  An integer containing the Minimum Stock Quantity
     */
    public int getMin()
    {
        return minProperty.get();
    }
    
    /**
     * Sets the Part Minimum Stock Quantity
     * @param _min  An integer containing the Minimum Stock Quantity
     */
    public final void setMin(int _min)
    {
        minProperty.set(_min);
    }

    /******************************************
     * Constructors
     ******************************************/
    
    /**
     * Creates a new Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     * @param _inStock          The Part Inventory in Stock
     * @param _min              The Part Minimum Stock
     * @param _max              The Part Maximum Stock
     */
    public Part(int _id, String _name, double _price, int _inStock, int _min, int _max)
    {
        this.setPartID(_id);
        this.setName(_name);
        this.setPrice(_price);
        this.setInStock(_inStock);
        this.setMin(_min);
        this.setMax(_max);
    }
}
