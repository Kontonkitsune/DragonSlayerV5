package org.unocapstone.dragonslair;

import java.util.ArrayList;

import javafx.collections.ObservableList;

/**
 * An Order relating a Title and a customer. Every order has a customer
 * that is requesting it and a title that is to be requested. Every
 * order must also have a specified quantity.
 */
public class TagOrder implements OrderDisplay {

    private int customerId;
    private String orIncludeTags;
    private String andIncludeTags;
    private String excludeTags;
    private int quantity;


    /**
     * Constructor. Sets the values for the Order equal to the values provided.
     * @param customerId ID of the customer requesting the order
     * @param quantity Number of copies of the title that are requested
     * @param issue Specific issue number to request
     */
    public TagOrder(int customerId, String orIncludeTags, String andIncludeTags, String excludeTags, int quantity) {
        this.customerId = customerId;
        this.orIncludeTags = orIncludeTags;
        this.andIncludeTags = andIncludeTags;
        this.excludeTags = excludeTags;
        this.quantity = quantity;
    }

    public ArrayList<Integer> getTargets(ObservableList<Title> titles) {
        ArrayList<Integer> returnarr = new ArrayList<>();
        boolean includetitle;
        String[] titletagslist;
        String[] ordertagslist;
        boolean temp;

        for (Title title : titles) {
            includetitle = true;
            titletagslist = title.getTags().split(";");

            // AND logic
            ordertagslist = this.andIncludeTags.split(";");
            if (ordertagslist.length > 0 && !ordertagslist[0].equals("")) {
                for (String currenttag : ordertagslist) {
                    temp = false;
                    for (String titletag : titletagslist) {
                        if (titletag.trim().toLowerCase().equals(currenttag.trim().toLowerCase())) {
                            temp = true;
                        }
                    }
                    if (!temp) {
                        includetitle = false;
                        break;
                    }
                }
            }

            // OR logic
            ordertagslist = this.orIncludeTags.split(";");
            if (ordertagslist.length > 0 && !ordertagslist[0].equals("")) {
                temp = false;
                for (String currenttag : ordertagslist) {
                    for (String titletag : titletagslist) {
                        if (titletag.trim().toLowerCase().equals(currenttag.trim().toLowerCase())) {
                            temp = true;
                        }
                    }
                }
                if (!temp) includetitle = false;
            }

            // EXCLUSION logic
            ordertagslist = this.excludeTags.split(";");
            if (ordertagslist.length > 0 && !ordertagslist[0].equals("")) {
                for (String currenttag : ordertagslist) {
                    for (String titletag : titletagslist) {
                        if (titletag.trim().toLowerCase().equals(currenttag.trim().toLowerCase())) {
                            includetitle = false;
                        }
                    }
                }
            }
            if (includetitle) {
                returnarr.add(title.getId());
            }


        }
        return returnarr;
    }

    public String getTargetDisplay() {
        String returnstr = "Tags: ";
        returnstr += this.orIncludeTags;
        returnstr += " AND ";
        returnstr += this.andIncludeTags;
        returnstr += " NOT ";
        returnstr += this.excludeTags;
        return returnstr;
    }

    /**
     * Gets ID of Customer for this Order
     * @return Customer ID for this Order
     */
    public int getCustomerId(){
        return this.customerId;
    }

    /**
     * Gets name of the Title for this order
     * @return Name of the Title for this order
     */
    public String getTags() {
        return "OR " + this.orIncludeTags + " AND " + this.andIncludeTags + " NOT " + this.excludeTags;
    }


    /**
     * Gets name of the Title for this order
     * @return Name of the Title for this order
     */
    public String getAndIncludeTags() {
        return this.andIncludeTags;
    }
    /**
     * Gets name of the Title for this order
     * @return Name of the Title for this order
     */
    public String getOrIncludeTags() {
        return this.orIncludeTags;
    }
    /**
     * Gets name of the Title for this order
     * @return Name of the Title for this order
     */
    public String getExcludeTags() {
        return this.excludeTags;
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
        return -9;
    }

    /**
     * Sets the quantity of copies for this order
     * @param quantity Quantity for this order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

