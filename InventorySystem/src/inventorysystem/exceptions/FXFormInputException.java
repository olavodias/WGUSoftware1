/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.exceptions;

import javafx.scene.control.TextField;

/**
 * Represents an exception thrown when a field input is invalid in a JavaFX form
 * 
 * @author Olavo Henrique Dias
 */
public class FXFormInputException extends Exception {
    
    private final TextField _textField;
    /**
     * Returns the TextField with an invalid value
     * @return A reference to the JavaFX TextField where the error happened
     */
    public TextField getTextField() {
        return _textField;
    }
    
    private final String _headerText;

    /**
     * Returns the text to be added to the header of a screen displaying this error message.
     * @return A String containing the Header Text
     */
    public String getHeaderText() {
        return _headerText;
    }
    
    /**
     * Initializes a new instance of a FXFormInputException
     * @param message The Error Message
     */
    public FXFormInputException(String message) {
        this(message, "Form Input Exception");
    }

    /**
     * Initializes a new instance of a FXFormInputException
     * @param message       The Error Message
     * @param headerText    The Text to be displayed at the header of the screen
     */
    public FXFormInputException(String message, String headerText) {
        this(message, headerText, null);
    }
    
    /**
     * Initializes a new instance of a FXFormInputException
     * @param message       The Error Message
     * @param headerText    The Text to be displayed at the header of the screen
     * @param textField     Reference to the JavaFX TextField where the error happened
     */
    public FXFormInputException(String message, String headerText, TextField textField) {
        super(message);
        
        _headerText = headerText;
        _textField = textField;
    }
}
