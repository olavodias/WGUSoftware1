/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.screens;

import javafx.stage.Stage;

/**
 * Represents a Form that has multiple modes
 * @author Olavo Henrique Dias
 */
public abstract class FXMultiModeScreen extends FXScreen {
    
    /* The Internal Storage for the Mode */
    private final FXMode _mode;
    
    /**
     * Returns the Form Mode
     * @return      The Form Mode
     */
    public FXMode getMode() {
        return _mode;
    }
    
    /**
     * Initializes a new instance of a FXMultiModeScreen
     * 
     * @param mode              The form mode
     */
    public FXMultiModeScreen(FXMode mode) {
        this(mode, "");
    }

    /**
     * Initializes a new instance of a FXMultiModeScreen
     * 
     * @param mode              The form mode
     * @param cssPath           The CSS Path
     */
    public FXMultiModeScreen(FXMode mode, String cssPath) {
        this(mode, cssPath, "MultiMode Screen");
    }

    /**
     * Initializes a new instance of a FXMultiModeScreen
     * 
     * @param mode              The form mode
     * @param cssPath           The CSS Path
     * @param title             The Screen Title
     */
    public FXMultiModeScreen(FXMode mode, String cssPath, String title) {
        super(cssPath, title);
        
        _mode = mode;
    }
}


