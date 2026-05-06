package org.unocapstone.dragonslair;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public interface OrderDisplay {
    /**
     * Gets ID of Customer for this Order
     * @return Customer ID for this Order
     */
    public int getCustomerId();

    /**
     * Gets ID of Title for this order
     * @return Title ID for this order
     */
    public ArrayList<Integer> getTargets(ObservableList<Title> titles);

    /**
     * Gets name of the Title for this order
     * @return Name of the Title for this order
     */
    public String getTargetDisplay();

    /**
     * Gets the quantity of copies for this order
     * @return Quantity for this order
     */
    public int getQuantity();

    /**
     * Gets the specific issue # for this order
     * @return Issue # for this order
     */
    public int getIssue();

    /**
     * Sets the quantity of copies for this order
     * @param quantity Quantity for this order
     */
    public void setQuantity(int quantity);

}
