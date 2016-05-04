package com.udacity.firebase.shoppinglistplusplus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firebase.client.ServerValue;

/**
 * Created by Steve on 4/27/2016.
 */
public class ShoppingList {
    String listName;
    String owner;
    @JsonProperty
    private Object created;

    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner) {
        this.owner = owner;
        this.listName = listName;
        this.created = ServerValue.TIMESTAMP;
    }

    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }

    @JsonIgnore
    public  Long getCreatedTimeStamp() {
        if (created instanceof Long) {
            return (Long) created;
        }
        else {
            return null;
        }
    }

    @Override
    public String toString() {
        return listName + " by " + owner;
    }
}
