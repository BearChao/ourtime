package org.zynick.ourtime.ourtime;

/**
 * Created by zynick on 2014/12/30.
 */
public class event {
    private String name;
    private boolean loop;
    private  String date;

    public String getName(){
        return name;
    }
    public  void setName(String n){
        name = n;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String d){
        date = d;
    }

    public boolean getLoop(){
        return loop;
    }
    public void setLoop(boolean l){
        loop = l;
    }

    public String toString(){
        return  "name:"+ name +",date"+ date +",loop"+ loop;
    }

}
