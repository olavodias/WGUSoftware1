/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.screens;

import javafx.stage.Stage;

/**
 * Represents the Base Class for a JavaFX Screen
 *
 * @author Olavo Henrique Dias
 */
public abstract class FXScreen {
    
    /* The Current Stage where the form is being displayed */
    private Stage _currentStage;
    
    /**
     * Gets the Current Stage
     * 
     * @return      The Stage where the form is being displayed
     */
    public Stage getCurrentStage()
    {
        return _currentStage;
    }
    
    /**
     * Sets the Current Stage
     * @param newStage      The stage where the form is being displayed
     */
    public void setCurrentStage(Stage newStage)
    {
        _currentStage = newStage;
    }
    
    /**
     * Initializes a new FXScreen
     * 
     * @param currentStage      The Stage where the Screen will run
     */
    public FXScreen(Stage currentStage)
    {
        _currentStage = currentStage;
    }
}
