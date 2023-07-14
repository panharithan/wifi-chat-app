package com.example.panharith.wifichat;


import android.annotation.SuppressLint;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class MainActivity extends AppCompatActivity {


    Button button_sent;
    EditText smessage;
    TextView chat,display_status;
    String str,msg="";
    int serverport = 9999;
    ServerSocket serverSocket;
    Socket client;
    Handler handler = new Handler();
    WifiManager wmanager;

    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        wmanager = (WifiManager) getSystemService(WIFI_SERVICE);
        //String ip =
        //        Formatter.formatIpAddress(wmanager.getConnectionInfo().getIpAddress());
        String ip= "192.168.1.113";
        smessage = (EditText) findViewById(R.id.smessage);
        chat = (TextView) findViewById(R.id.chat);
        display_status = (TextView)
                findViewById(R.id.display_status);
        display_status.setText("Server hosted on " + ip);
        Thread serverThread = new Thread(new serverThread());
        serverThread.start();
        button_sent = (Button) findViewById(R.id.button_sent);
        button_sent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {

                    //  message = "a";
                    sendToPi mt = new sendToPi();
                    mt.execute();

                }
                catch (Exception e)
                {

                }
            }
        });



    }


    class sentMessage implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                Socket client = serverSocket.accept();
                DataOutputStream os = new
                        DataOutputStream(client.getOutputStream());
                str = smessage.getText().toString();
                msg = msg + "\n Server : " + str;
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        chat.setText(msg);
                    }
                });
                os.writeBytes(str);
                os.flush();
                os.close();
                client.close();
            }
            catch(IOException e)
            {
            }
        }
    }

    public class serverThread implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                while(true)
                {
                    serverSocket = new ServerSocket(serverport);
                    Socket client = serverSocket.accept();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            display_status.setText("Connected");
                        }
                    });
/*******************************************
 setup i/p streams
 ******************************************/
                    DataInputStream in = new
                            DataInputStream(client.getInputStream());
                    String line = null;
                    while((line = in.readLine()) != null)
                    {
                        msg = msg + "\n Client : " + line;
                        handler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                chat.setText(msg);
                            }
                        });
                    }
                    in.close();
                    client.close();
                    Thread.sleep(100);
                }
            }
            catch (Exception e)
            {
            }
        }
    }
    // sendToPi class

    private static Socket s;
    private static ServerSocket ss;
    private static InputStreamReader isr;
    private static BufferedReader br;
    private static PrintWriter printWriter;
    String message = "";
    ServerSocket serverSocketX;
    Thread myThread;
    private static String ip = "192.168.1.113";

    class sendToPi extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {

                message = "The Man is sending to you , bitch";
                s= new Socket(ip,8888);
                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(message);
                printWriter.flush();
                printWriter.close();
                s.close();
                message="";

            }
            catch (Exception e)
            {
            }
            return null;
        }
    }
    /// end of sendToPi class



}
