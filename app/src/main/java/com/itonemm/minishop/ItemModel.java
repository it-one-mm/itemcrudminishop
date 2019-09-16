package com.itonemm.minishop;

public class ItemModel {

    public String itemName;
    public String itemImagePath;

    public ItemModel(String itemName, String itemImagePath) {
        this.itemName = itemName;
        this.itemImagePath = itemImagePath;
    }

    public ItemModel() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImagePath() {
        return itemImagePath;
    }

    public void setItemImagePath(String itemImagePath) {
        this.itemImagePath = itemImagePath;
    }
}
