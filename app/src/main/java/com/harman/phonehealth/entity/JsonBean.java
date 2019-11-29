package com.harman.phonehealth.entity;

public class JsonBean  implements Comparable<JsonBean>{
    public String name;

    public double num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    @Override
    public int compareTo(JsonBean o) {

        if (this.num>(o).num){
            return -1;
        }else if(this.num<(o).num){
            return 1;
        }
        return 0;
    }
}
