package com.coderulez.senai.leansurvey.model;

/**
 * Created by SENAI on 03/12/2016.
 */

public class Option
{
    int ordem;
    String description;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    boolean selected;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int order) {
        this.ordem = order;
    }
}
