package org.unocapstone.dragonslair.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import org.unocapstone.dragonslair.Log;
import org.unocapstone.dragonslair.PhoneNumberFilter;

/**
 * This Controller controls the New Customer window. It allows the window
 * to get the text that is entered in the fields and save it in the
 * database.
 */
public class SearchCustomerOptionsController implements Initializable {

    public boolean customerWasAdded = false;

    private Connection conn;

    int rowsAffected = 0;

    private Controller parent;

    @FXML
    private CheckBox FirstNameCheckbox;
    @FXML
    private CheckBox LastNameCheckbox;
    @FXML
    private CheckBox FullNameCheckbox;
    @FXML
    private CheckBox PhoneNumberCheckbox;
    @FXML
    private CheckBox EmailCheckbox;
    @FXML
    private CheckBox NotesCheckbox;
    @FXML
    private Button AcceptButton;

    /**
     * Initialize the window by setting a TextFormatter for the phone number
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFormatter<String> textFormatter = new TextFormatter<String>(new DefaultStringConverter(), "", new PhoneNumberFilter());

    }

    @FXML
    private void handleAcceptButton(ActionEvent event) {
        this.parent.passCustomerSearchOptions(FirstNameCheckbox.isSelected(),LastNameCheckbox.isSelected(),FullNameCheckbox.isSelected(),PhoneNumberCheckbox.isSelected(),EmailCheckbox.isSelected(),NotesCheckbox.isSelected());
        Stage window = (Stage) AcceptButton.getScene().getWindow();
        window.close();
    }

    public void setParent(Controller parent) {
        this.parent = parent;
    }

    public void getCurrent() {
        FirstNameCheckbox.setSelected(      this.parent.customerSearchFirstName);
        LastNameCheckbox.setSelected(       this.parent.customerSearchLastName);
        FullNameCheckbox.setSelected(       this.parent.customerSearchFullName);
        PhoneNumberCheckbox.setSelected(    this.parent.customerSearchPhoneNumber);
        EmailCheckbox.setSelected(          this.parent.customerSearchEmail);
        NotesCheckbox.setSelected(          this.parent.customerSearchNotes);
    }

    /**
     * Sets the database connection for this controller
     * @param conn Connection to set for this controller
     */
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

}
