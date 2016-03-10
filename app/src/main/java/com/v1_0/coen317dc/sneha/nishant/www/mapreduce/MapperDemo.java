package com.v1_0.coen317dc.sneha.nishant.www.mapreduce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nishant on 10/3/16.
 */
public class MapperDemo {

   // public String chunkString;
    private Thread t;
    public ArrayList<MapOutputClass> mylist;
    public HashMap<String,Integer> myMap;
    public StringBuilder mapStream;

    public MapperDemo(){

    }


    public String mapAndriod(String mapInput){

        mylist = new ArrayList<MapOutputClass>();
        myMap = new HashMap<String,Integer>();
        mapStream = new StringBuilder();
        System.out.println("MAP INPUT STREAM = "+mapInput);
        String[] buffer = mapInput.split(" ");
        for(int i=0;i<buffer.length;i++){
            mylist.add(new MapOutputClass(buffer[i],new Integer(1)));
            //	System.out.println(mylist.get(i).getStringKey() +":" + mylist.get(i).getIntegerValue());
            String key = mylist.get(i).getStringKey();

            if(key.contains(",")) {
                continue;
            }
            else {
                mapStream.append(key+"="+mylist.get(i).getIntegerValue()+",");
            }

        }


        System.out.println("MAP OUTPUT STREAM = "+mapStream);
        return mapStream.toString();
    }

}
