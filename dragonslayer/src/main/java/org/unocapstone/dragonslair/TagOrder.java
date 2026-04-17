package org.unocapstone.dragonslair;

import java.util.ArrayList;

import javafx.collections.ObservableList;

/**
 * An Order relating a Title and a customer. Every order has a customer
 * that is requesting it and a title that is to be requested. Every
 * order must also have a specified quantity and issue #.
 */
public class TagOrder implements OrderDisplay {

    private int customerId;
    private String tagQuery;
    private int quantity;
    private int issue;


    /**
     * Constructor. Sets the values for the Order equal to the values provided.
     * @param customerId ID of the customer requesting the order
     * @param tagQuery ID of the Title to be requested
     * @param quantity Number of copies of the title that are requested
     * @param issue Specific issue number to request
     */
    public TagOrder(int customerId, String tagQuery, int quantity, int issue) {
        this.customerId = customerId;
        this.tagQuery = tagQuery;
        this.quantity = quantity;
        this.issue = issue;
    }

    public ArrayList<Integer> getTargets(ObservableList<Title> titles) {
        ArrayList<Integer> returnarr = new ArrayList<>();
        for (Title title : titles) {

        }
        return returnarr;
    }

    public String getTargetDisplay() {
        return "Tag Search: " + this.tagQuery;
    }

    /**
     * Gets ID of Customer for this Order
     * @return Customer ID for this Order
     */
    public int getCustomerId(){
        return this.customerId;
    }

    /**
     * Gets ID of Title for this order
     * @return Title ID for this order
     */
    public String getTitleIds() {
        return this.tagQuery;
    }

    /**
     * Gets name of the Title for this order
     * @return Name of the Title for this order
     */
    public String getTag() {
        return this.tagQuery;
    }

    /**
     * Gets the quantity of copies for this order
     * @return Quantity for this order
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Gets the specific issue # for this order
     * @return Issue # for this order
     */
    public int getIssue() {
        return this.issue;
    }

    /**
     * Sets the quantity of copies for this order
     * @param quantity Quantity for this order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

