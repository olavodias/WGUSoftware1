/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents an Outsourced Part
 * 
 * @author Olavo Henrique Dias
 */
public class OutsourcedPart extends Part {
    
    /******************************************
     * Define Properties for Data Binding
     ******************************************/
    public final SimpleStringProperty companyName = new SimpleStringProperty("");
    
    /******************************************
     * Property Getters/Setters
     ******************************************/
    
    /**
     * Retrieves the Company Name
     * @return      A String containing the Company Name
     */
    public String getCompanyName()
    {
        return companyName.get();
    }
    
    /**
     * Sets the Machine ID
     * @param _companyName   A String containing the Company Name
     */
    public final void setCompanyName(String _companyName)
    {
        companyName.set(_companyName);
    }

    /******************************************
     * Constructors
     ******************************************/
    /**
     * Creates a new outsourced Part
     */
    public OutsourcedPart()
    {
        this(0, "");
    }
    
    /**
     * Creates a new outsourced Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     */
    public OutsourcedPart(int _id, String _name)
    {
        this(_id, _name, 0);
    }

    /**
     * Creates a new outsourced Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     */
    public OutsourcedPart(int _id, String _name, double _price)
    {
        this(_id, _name, _price, 0);
    }

    /**
     * Creates a new outsourced Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     * @param _inStock          The Part Inventory in Stock
     */
    public OutsourcedPart(int _id, String _name, double _price, int _inStock)
    {
        this(_id, _name, _price, _inStock, 0);
    }
    
    /**
     * Creates a new outsourced Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     * @param _inStock          The Part Inventory in Stock
     * @param _min              The Part Minimum Stock
     */
    public OutsourcedPart(int _id, String _name, double _price, int _inStock, int _min)
    {
        this(_id, _name, _price, _inStock, _min, 0);
    }
    
    /**
     * Creates a new outsourced Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     * @param _inStock          The Part Inventory in Stock
     * @param _min              The Part Minimum Stock
     * @param _max              The Part Maximum Stock
     */
    public OutsourcedPart(int _id, String _name, double _price, int _inStock, int _min, int _max)
    {
        this(_id, _name, _price, _inStock, _min, _max, "");
    }    

    /**
     * Creates a new outsourced Part
     * 
     * @param _id               The Part ID
     * @param _name             The Part Name
     * @param _price            The Part Price
     * @param _inStock          The Part Inventory in Stock
     * @param _min              The Part Minimum Stock
     * @param _max              The Part Maximum Stock
     * @param _companyName      The Part Company Name
     */
    public OutsourcedPart(int _id, String _name, double _price, int _inStock, int _min, int _max, String _companyName)
    {
        super(_id, _name, _price, _inStock, _min, _max);
        setCompanyName(_companyName);
    }    
    
}
