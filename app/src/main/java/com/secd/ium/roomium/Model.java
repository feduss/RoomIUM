package com.secd.ium.roomium;

/**
 * Created by Tommaso on 12/02/2018.
 */

public class Model {
    String name;
    int value;

    Model(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return this.name;
    }

    public int getValue(){
        return this.value;
    }
}
