/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.stage.Stage;

/**
 * Represents a Form that has multiple modes
 * @author Olavo Henrique Dias
 */
public abstract class FXMultiModeScreen extends FXScreen {
    
    /* The Internal Storage for the Mode */
    private final FXMultiModes _mode;
    
    /**
     * Returns the Form Mode
     * @return      The Form Mode
     */
    public FXMultiModes getMode()
    {
        return _mode;
    }
    
    /**
     * Initializes a new instance of a FXMultiModeScreen
     * 
     * @param currentStage      The Current Stage where the form will be presented
     * @param mode              The form mode
     */
    public FXMultiModeScreen(Stage currentStage, FXMultiModes mode) {
        super(currentStage);
        
        _mode = mode;
    }
    
}


