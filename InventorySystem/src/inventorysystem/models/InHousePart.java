/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an InHouse Part
 * @author Olavo Henrique Dias
 */
public class InHousePart extends Part {
    
    /******************************************
     * Define Properties for Data Binding
     ******************************************/
    public final SimpleIntegerProperty machineID = new SimpleIntegerProperty(0);
    
    /******************************************
     * Property Getters/Setters
     ******************************************/
    
    /**
     * Retrieves the Machine ID
     * @return      An integer containing the Machine ID
     */
    public int getMachineID()
    {
        return machineID.get();
    }
    
    /**
     * Sets the Machine ID
     * @param _id   An integer containing the Machine ID
     */
    public final void setMachineID(int _id)
    {
        machineID.set(_id);
    }

    /******************************************
     * Constructors
     ******************************************/
    /**
     * Creates a new InHouse Part
     */
    public InHousePart()
    {
        this(0, "");
    }
    
    /**
     * Creates a new InHouse Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     */
    public InHousePart(int _id, String _name)
    {
        this(_id, _name, 0);
    }

    /**
     * Creates a new InHouse Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     */
    public InHousePart(int _id, String _name, double _price)
    {
        this(_id, _name, _price, 0);
    }

    /**
     * Creates a new InHouse Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     * @param _inStock          The Part Inventory in Stock
     */
    public InHousePart(int _id, String _name, double _price, int _inStock)
    {
        this(_id, _name, _price, _inStock, 0);
    }
    
    /**
     * Creates a new InHouse Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     * @param _inStock          The Part Inventory in Stock
     * @param _min              The Part Minimum Stock
     */
    public InHousePart(int _id, String _name, double _price, int _inStock, int _min)
    {
        this(_id, _name, _price, _inStock, _min, 0);
    }
    
    /**
     * Creates a new InHouse Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     * @param _inStock          The Part Inventory in Stock
     * @param _min              The Part Minimum Stock
     * @param _max              The Part Maximum Stock
     */
    public InHousePart(int _id, String _name, double _price, int _inStock, int _min, int _max)
    {
        this(_id, _name, _price, _inStock, _min, _max, 0);
    }    

    /**
     * Creates a new InHouse Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     * @param _inStock          The Part Inventory in Stock
     * @param _min              The Part Minimum Stock
     * @param _max              The Part Maximum Stock
     * @param _machineID        The Part Machine ID
     */
    public InHousePart(int _id, String _name, double _price, int _inStock, int _min, int _max, int _machineID)
    {
        super(_id, _name, _price, _inStock, _min, _max);
        setMachineID(_machineID);
    }    

}
