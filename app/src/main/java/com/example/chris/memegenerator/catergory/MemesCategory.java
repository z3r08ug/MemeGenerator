package com.example.chris.memegenerator.catergory;

/**
 * Created by  Admin on 12/22/2017.
 */

public class MemesCategory {
    String category;
    boolean check;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public MemesCategory(String category, boolean check) {

        this.category = category;
        this.check = check;
    }
}
