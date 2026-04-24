package org.unocapstone.dragonslair.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ResourceBundle;

import org.unocapstone.dragonslair.Customer;
import org.unocapstone.dragonslair.FxUtilTest;
import org.unocapstone.dragonslair.Log;
import org.unocapstone.dragonslair.NewCustomerTitleManager;
import org.unocapstone.dragonslair.Title;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This Controller controls the New Order window. It allows the window
 * to get the text that is entered in the fields and save it in the
 * database.
 */
public class NewTagOrderController implements Initializable{

    public boolean orderWasAdded = false;
    public int lastTitleAdded;
    private Connection conn;
    private int customerId;
    private String customer;
    private String title;
    private String notes;
    private boolean noRequestsFlag;

    @FXML private Button addTagOrderButton;
    @FXML private ComboBox<String> setName;
    @FXML private TextField setQuantity;
    @FXML private TextField setAndIncludeTags;
    @FXML private TextField setOrIncludeTags;
    @FXML private TextField setExcludeTags;
    @FXML private TextField setNotes;

    @FXML private Text orderTitleErrorText;
    @FXML private Text orderCustomerErrorText;
    @FXML private Text orderQuantityErrorText;

    private ObservableList<Title> titles  = FXCollections.observableArrayList();
    private ObservableList<String> titlesStr  = FXCollections.observableArrayList();
    
    private ObservableList<Customer> customers  = FXCollections.observableArrayList();
    private ObservableList<String> customerNames = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setName.focusedProperty().addListener((obs, oldval, newval) -> {
            System.out.println("Selecting all New Order Title Text");
            // setTitle.getEditor().selectAll();

            Platform.runLater(() -> {
                if ((setName.getEditor().isFocused() || setName.isFocused()) && !setName.getEditor().getText().isEmpty()) {
                    setName.getEditor().selectAll();
                }
            });
        });
    }

    /**
     * Creates an order based on the fields and ComboBox and adds it
     * to the database
     * @param event Event that triggered this method
     */
    @FXML
    void newTagOrder(ActionEvent event) {
        PreparedStatement s = null;
        String sql = "INSERT INTO TagOrders (customerId, quantity, AND_INCLUDE_TAGS, OR_INCLUDE_TAGS, EXCLUDE_TAGS) VALUES (?, ?, ?, ?, ?)";
        orderQuantityErrorText.setVisible(false);
        orderTitleErrorText.setVisible(false);
        int chosenCustomerID = getChoice2(setName);

        if (chosenCustomerID == -1) {
            orderCustomerErrorText.setVisible(true);
            return;
        }
        else if (setQuantity.getText().equals("")) {
            orderQuantityErrorText.setVisible(true);
            return;
        }
        else {
            int customerID = chosenCustomerID;
            String quantity = setQuantity.getText();
            String addIncludeTags = setAndIncludeTags.getText();
            String orIncludeTags = setOrIncludeTags.getText();
            String excludeTags = setExcludeTags.getText();
            Statement get = null;
            try {
                get = conn.createStatement();
                s = conn.prepareStatement(sql);
                s.setString(1, Integer.toString(customerID));
                s.setString(2, quantity);
                s.setString(3, addIncludeTags);
                s.setObject(4, orIncludeTags);
                s.setObject(5, excludeTags);


                int rowsAffected = s.executeUpdate();

                if (rowsAffected == 0) {
                    System.err.println("Zero rows effected on new order add, this should not happen.");
                } else if (rowsAffected > 1) {
                    // Nothing should happen here
                }
                s.close();

                orderWasAdded = true;

                Log.LogEvent("New Tag Order", "Added order - Customer: " + customer + " - Query: " + "AND " + addIncludeTags + " OR " + orIncludeTags + " NOT " + excludeTags);
            } catch (SQLException sqlExcept) {
                Log.LogEvent("SQL Exception", sqlExcept.getMessage());
                sqlExcept.printStackTrace();
            }
        }
        Stage window = (Stage) addTagOrderButton.getScene().getWindow();
        window.close();
    }

    /**
     * Populate the ComboBox with the titles in titlesStr, add listener to handle typing over selection
     */
    public void setNewOrderCustomers(){
        setName.setItems(this.customerNames);
        setName.getSelectionModel().selectFirst();
        setName.setEditable(true);
        
    }

    /**
     * Sets the connection for this controller
     * @param conn the connection for this controller
     */
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    /**
     * Sets the customer ID for this controller
     * @param id ID of the customer to set
     */
    public void setCustomerID(int id) {
        this.customerId = id;
    }

    /**
     * Sets the customer for this controller
     * @param customer name of the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
        setName.setValue(customer);
    }

    /**
     * Populates titlesStr based off of an ObservableList of Titles
     * @param getTitles ObservableList of Titles to add to titlesStr
     */
    public void populate(ObservableList<Title> getTitles){
        this.titles = getTitles;
        for(int i=0; i < titles.size(); i++){
            titlesStr.add(titles.get(i).getTitle());
        }
    }

    /**
     * Populates customerNames based off of an ObservableList of Titles
     * @param getNames ObservableList of Customers to add to customerNames
     */
    public void populateCustomers(ObservableList<Customer> getNames){
        this.customers = getNames;
        for(int i=0; i < customers.size(); i++){
            customerNames.add(customers.get(i).getFullName());
        }
    }

    /**
     * Gets the choice from the ComboBox
     * @param setTitle ComboBox containing the title value
     * @return the ID of the title selected
     */
    public int getChoice(ComboBox<String> setTitle ){
        String name = FxUtilTest.getComboBoxValue(setTitle);

        for(int i=0; i < titles.size(); i++){
            if (titles.get(i).getTitle().equals(name)){
                return titles.get(i).getId();
            }
        }
        return -1;
    }

    /**
     * Gets the choice from the ComboBox
     * @param setTitle ComboBox containing the title value
     * @return the ID of the title selected
     */
    public int getChoice2(ComboBox<String> setName ){
        String name = FxUtilTest.getComboBoxValue(setName);

        for(int i=0; i < customers.size(); i++){
            if (customers.get(i).getFullName().equals(name)){
                return customers.get(i).getId();
            }
        }
        return -1;
    }
}
