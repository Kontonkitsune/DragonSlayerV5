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
public class SearchTitleOptionsController implements Initializable {

    public boolean customerWasAdded = false;

    private Connection conn;

    int rowsAffected = 0;

    private Controller parent;

    @FXML
    private CheckBox TitleCheckBox;
    @FXML
    private CheckBox TagCheckBox;
    @FXML
    private CheckBox NotesCheckBox;
    @FXML
    private CheckBox AliasesCheckBox;
    @FXML
    private CheckBox IDCheckBox;
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
        this.parent.passTitleSearchOptions(TitleCheckBox.isSelected(),TagCheckBox.isSelected(),NotesCheckBox.isSelected(),AliasesCheckBox.isSelected(),IDCheckBox.isSelected());
        Stage window = (Stage) AcceptButton.getScene().getWindow();
        window.close();
    }

    @FXML
    private void handleEnableAllButton(ActionEvent event) {
        TitleCheckBox.setSelected(      true);
        TagCheckBox.setSelected(        true);
        NotesCheckBox.setSelected(      true);
        AliasesCheckBox.setSelected(    true);
        IDCheckBox.setSelected(         true);
    }
    
    @FXML
    private void handleDisableAllButton(ActionEvent event) {
        TitleCheckBox.setSelected(      false);
        TagCheckBox.setSelected(        false);
        NotesCheckBox.setSelected(      false);
        AliasesCheckBox.setSelected(    false);
        IDCheckBox.setSelected(         false);
    }

    public void setParent(Controller parent) {
        this.parent = parent;
    }

    public void getCurrent() {
        TitleCheckBox.setSelected(this.parent.titleSearchTitles);
        TagCheckBox.setSelected(this.parent.titleSearchTags);
        NotesCheckBox.setSelected(this.parent.titleSearchNotes);
        AliasesCheckBox.setSelected(this.parent.titleSearchAliases);
        IDCheckBox.setSelected(this.parent.titleSearchIDs);
    }

    /**
     * Sets the database connection for this controller
     * @param conn Connection to set for this controller
     */
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

}
