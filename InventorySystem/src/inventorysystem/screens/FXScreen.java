/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.screens;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Represents the Base Class for a JavaFX Screen
 *
 * @author Olavo Henrique Dias
 */
public abstract class FXScreen {

    private String _title;
    
    /**
     * Returns the Screen Title
     * @return A string containing the Screen Title
     */
    public String getTitle() {
        return _title;
    }
    
    /**
     * Sets the Screen Title
     * 
     * @param title A string containing the Screen Title
     */
    public void setTitle(String title) {
        _title = title;
    }
    
    private String _cssPath;
    
    /**
     * Returns the Style Sheet Path
     * @return A string containing the style sheet path
     */
    public String getCssPath() {
        return _cssPath;
    }
    
    /**
     * Sets the Style Sheet Path
     * @param cssPath A string containing the style sheet path
     */
    public void setCssPath(String cssPath) {
        String _previousCssPath = _cssPath;
        _cssPath = cssPath;
        
        /* Check scene to add the css */
        if (scene != null)
        {
            /* Make sure to remove existing stylesheet */
            if (scene.getStylesheets().contains(_previousCssPath))
                scene.getStylesheets().remove(_previousCssPath);            
            
            /* Add new stylesheet, if not there yet */            
            if (!scene.getStylesheets().contains(_cssPath))
                scene.getStylesheets().add(_cssPath);
        }
    }
    
    private FXScreenResult _result;
    
    /**
     * Returns the Screen Result
     * @return A FXScreenResult containing the result of the screen
     */
    public FXScreenResult getResult() {
        return _result;
    }
    
    /**
     * Sets the Screen Result.
     * This method is only available for classes inheriting from this class
     * 
     * @param result A FXScreenResult containing the result of the screen
     */
    protected void setResult(FXScreenResult result) {
        _result = result;
    }
    
    private boolean _resizable;

    /**
     * Returns whether the form is resizable or not
     * @return A boolean value to define whether the form is resizable or not
     */
    public boolean getResizable() {
        return _resizable;
    }
    
    /**
     * Defines whether the form is resizable or not
     * @param resizable A boolean value to define whether the form is resizable or not
     */
    public void setResizable(boolean resizable) {
        _resizable = resizable;
    }
    
    private double _width;
    
    /**
     * Returns the form width
     * @return A double representing the form width
     */
    public double getWidth() {
        return _width;
    }
    
    /**
     * Sets the form width
     * @param width A double representing the form width
     */
    public void setWidth(double width) {
        _width = Math.max(1, width);
    }
    
    private double _height;
    
    /**
     * Returns the form height
     * @return A double representing the form height
     */
    public double getHeight() {
        return _height;
    }
    
    /**
     * Sets the form height
     * @param height A double representing the form height
     */
    public void setHeight(double height) {
        _height = Math.max(1, height);
    }
    
    /**
     * Sets the form size
     * @param width     A double representing the form width
     * @param height    A double representing the form height
     */
    public void setSize(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }
    
    private Stage _currentStage;
    
    /**
     * Returns the stage where the form is being displayed.
     * There will be no value if the method show has not been called.
     * @return A Stage containing the current stage
     */
    public Stage getCurrentStage() {
        return _currentStage;
    }
    
    /**
     * Sets the Current Stage
     * @param currentStage A Stage containing the current stage
     */
    public void setCurrentStage(Stage currentStage) {
        _currentStage = currentStage;
    }
    
    /**
     * A flag indicating the screen is styled
     * @return A boolean defining if the screen is styled or not
     */
    public boolean isStyled() {
        return (!_cssPath.equals(""));
    }
    
    /**
     * The scene to be created
     */
    protected Scene scene;
    
    /**
     * Returns the Scene for the Form
     * 
     * @return A Scene containing the form elements
     */
    public Scene getScene() {
        return scene;
    }
    
    /**
     * Initializes a new FXScreen
     * @param cssPath           The CSS Path to Style the Screen
     */
    public FXScreen(String cssPath) {
        this(cssPath, "");
    }

    /**
     * Initializes a new FXScreen
     * @param cssPath           The CSS Path to Style the Screen
     * @param title             The Screen Title
     */    
    public FXScreen(String cssPath, String title) {
        _cssPath = cssPath;
        _title = title;
        _resizable = true;
        
        /* Set a default width & height */
        _width = 500;
        _height = 500;
    }
    
    /**
     * Creates the Form Scene
     */
    protected abstract void createScene();
    
    /**
     * Applies the CSS if there is one
     */
    protected void applyCss()
    {
        if (this.scene != null)
            this.setCssPath(_cssPath);
    }
    
    public FXScreenResult show() {
        return show(null);
    }
    
    public FXScreenResult show(Stage parentStage) {
        
        /* Make sure there is a valid scene */
        if (this.scene == null) return FXScreenResult.NONE;
        
        /* Set Scene Size */
        
        /* Create the Stage */
        _currentStage = new Stage();
        _currentStage.setTitle(this.getTitle());
        
        /* Create the Scene */
        _currentStage.setScene(this.getScene());
        _currentStage.setResizable(this.getResizable());
        
        /* Set Modal */
        if (parentStage != null)
        {
            _currentStage.initOwner(parentStage);
            _currentStage.initModality(Modality.WINDOW_MODAL);
            
            _currentStage.showAndWait();
        }                   
        else
        {
            _currentStage.show();
        }
        
        /* Set the Screen Stage */
        return this.getResult();
    }
                
}
