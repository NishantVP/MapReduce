package com.v1_0.coen317dc.sneha.nishant.www.mapreduce;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReduceService extends Service {
    public ReduceService() {
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful

        Toast.makeText(this, "This is from Reduce Service", Toast.LENGTH_LONG).show();

        sharedpreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        // Reading from SharedPreferences
        MyPortInt = sharedpreferences.getInt("SERVER_TO_CLIENT_PORT", 9999);
        serverIP = sharedpreferences.getString("SERVER_IP", "");
        serverPort = sharedpreferences.getString("SERVER_PORT", "");
        ParseObjID = sharedpreferences.getString("MY_PARSE_OBJ_ID", "");

        Log.d("SocketService IP ", Integer.toString(MyPortInt));

        //ip = RunSocketClient();
        ReduceTask task = new ReduceTask();
        task.execute();

        return Service.START_NOT_STICKY;
    }

    private class ReduceTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {

                client = new Socket(serverIP, MyPortInt);  //connect to server
                Log.d("ClientApp", "Started");

                printwriter = new PrintWriter(client.getOutputStream(),true);

                // receiving from server ( receiveRead  object)
                InputStream istream = client.getInputStream();
                BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

                String receiveMessage;

                String ReceivedFile="";
                while(true)
                {
                    if((receiveMessage = receiveRead.readLine()) != null) {
                        System.out.println(receiveMessage);
                        if(receiveMessage.equals("---fileSendingFinishedByServer---")){
                            System.out.println("End");
                            break;
                        }
                        ReceivedFile = ReceivedFile + receiveMessage;

                        //System.out.println("From PC - " + receiveMessage); // displaying at DOS prompt

                    }
                }

                //System.out.println("Out of while");
                System.out.println("Received File - " +ReceivedFile);


                double count = 0.0;
                long count2 = ReceivedFile.length();

                System.out.println("String Length " +ReceivedFile.length());

                //System.out.println("Ready to send");
                String FifthReducerOP = "";
                FifthReducerOP = ReducerJob.reduceAndroid(ReceivedFile);

                System.out.println("output of Reduce: " +FifthReducerOP);

                PrintWriter printwriter;
                printwriter = new PrintWriter(client.getOutputStream(),true);
                printwriter.println(FifthReducerOP);       // sending to server
                printwriter.flush();               // flush the data
                System.out.println("File sent: " + count2);

                System.out.println("Reduce Service Stopped");
                stopSelf();

                return "done";
            } catch (IOException e) {
                return "Not done";
            }
            finally {
//                try {
//                    //client.close();   //closing the connection
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

        }
    }

    private void sendBroadcastMessage(String messageFromPC) {

        Intent intent = new Intent(ACTION_BROADCAST);
        intent.putExtra("Message", messageFromPC);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
