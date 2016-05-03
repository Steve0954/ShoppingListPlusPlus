package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by Steve on 4/27/2016.
 */
public class ShoppingList {
    String listName;
    String owner;

    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner) {
        this.owner = owner;
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }
}
