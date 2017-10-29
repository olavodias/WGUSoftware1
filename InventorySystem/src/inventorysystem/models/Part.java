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
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleDoubleProperty price = new SimpleDoubleProperty(0);
    private final SimpleIntegerProperty inStock = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty min = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty max = new SimpleIntegerProperty(0);
    
    /******************************************
     * Property Getters/Setters
     ******************************************/
    
    /**
     * Retrieves the Part ID
     * @return      An integer containing the Part ID
     */
    public int getPartID()
    {
        return id.get();
    }
    
    /**
     * Sets the Part ID
     * @param _id   An integer containing the Part ID
     */
    public final void setPartID(int _id)
    {
        id.set(_id);
    }

    /**
     * Retrieves the Part Name
     * @return      A string containing the Part Name
     */
    public String getName()
    {
        return name.get();
    }
    
    /**
     * Sets the Part Name
     * @param _name     A string containing the Part Name
     */
    public final void setName(String _name)
    {
        name.set(_name);
    }
    
    /**
     * Retrieves the Part Price
     * @return      A double containing the Part Price
     */
    public double getPrice()
    {
        return price.get();
    }
    
    /**
     * Sets the Part Price
     * @param _price    A double containing the Part Price 
     */
    public final void setPrice(double _price)
    {
        price.set(_price);
    }

    /**
     * Retrieves the Part Quantity In Stock 
     * @return      An integer containing the quantity in stock
     */
    public int getInStock()
    {
        return inStock.get();
    }
    
    /**
     * Sets the Part Quantity In Stock
     * @param _inStock  An integer containing the quantity in stock
     */
    public final void setInStock(int _inStock)
    {
        inStock.set(_inStock);
    }

    /**
     * Returns the Part Maximum Stock Quantity
     * @return  An integer containing the Maximum Stock Quantity
     */
    public int getMax()
    {
        return max.get();
    }
    
    /**
     * Sets the Part Maximum Stock Quantity
     * @param _max  An integer containing the Maximum Stock Quantity
     */
    public final void setMax(int _max)
    {
        max.set(_max);
    }

    /**
     * Returns the Part Minimum Stock Quantity
     * @return  An integer containing the Minimum Stock Quantity
     */
    public int getMin()
    {
        return min.get();
    }
    
    /**
     * Sets the Part Minimum Stock Quantity
     * @param _min  An integer containing the Minimum Stock Quantity
     */
    public final void setMin(int _min)
    {
        min.set(_min);
    }

    /******************************************
     * Constructors
     ******************************************/
    public Part()
    {
        this(0, "");
    }
    
    public Part(int _id, String _name)
    {
        this(_id, _name, 0);
    }

    public Part(int _id, String _name, double _price)
    {
        this(_id, _name, _price, 0);
    }

    public Part(int _id, String _name, double _price, int _inStock)
    {
        this(_id, _name, _price, _inStock, 0);
    }
    
    public Part(int _id, String _name, double _price, int _inStock, int _min)
    {
        this(_id, _name, _price, _inStock, _min, 0);
    }
    
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
