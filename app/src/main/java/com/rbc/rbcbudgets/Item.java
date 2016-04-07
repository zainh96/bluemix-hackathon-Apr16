package com.rbc.rbcbudgets;

/**
 * Created by platohe on 2016-04-07.
 */
public class Item {
    private int categoryIndex = 0;
    private String item = "";

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Item(int index, String item){
        this.categoryIndex = index;
        this.item = item;
    }
}
