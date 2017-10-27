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
    public int getID()
    {
        return id.get();
    }
    
    public void setID(int _id)
    {
        id.set(_id);
    }

    public String getName()
    {
        return name.get();
    }
    
    public void setName(String _name)
    {
        name.set(_name);
    }
    
    
}
