package com.example.engl_testing;

public class State {

    private int flagResource; // ресурс картинки

    public State(int flag){


        this.flagResource=flag;
    }


    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

}
