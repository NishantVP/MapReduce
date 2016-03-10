package com.v1_0.coen317dc.sneha.nishant.www.mapreduce;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import java.io.PrintWriter;
import java.net.Socket;

public class MapService extends Service {
    public MapService() {
    }

    String ip = "nothingYet";
    private Socket client;
    private PrintWriter printwriter;

    private String MyPort;
    private int MyPortInt;
    private SharedPreferences sharedpreferences;
    private String serverIP;
    private String serverPort;
    private String ParseObjID;


    //private DataInputStream in;
    //private DataOutputStream out;
    public static final String ACTION_BROADCAST = MapService.class.getName() + "Broadcast";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
