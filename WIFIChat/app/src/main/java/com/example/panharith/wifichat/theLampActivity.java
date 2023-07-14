package com.example.panharith.wifichat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class theLampActivity extends AppCompatActivity
    implements View.OnClickListener

{
    com.github.angads25.toggle.LabeledSwitch thelebelSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thelamp_activity);

        Thread serverThread = new Thread(new serverThread());
        serverThread.start();
        thelebelSwitch = (com.github.angads25.toggle.LabeledSwitch) findViewById(R.id.the_switch);

        thelebelSwitch.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn)
            {
                if (isOn) //so let's turn if off
                {
                    SendToPi_TurnOffLamp1();
                    //Thread serverThread = new Thread(new serverThread());
                    //serverThread.start();
                }
                else // it's on. now let's turn it on
                {
                    SendToPi_TurnOnLamp1();
                }
            }

        });

    }



    @Override
    public void onClick(View v)
    {
        int id = v.getId();

    }
    ////////////////
    // non-override functions
    ///////////////////

    private void SendToPi_TurnOnLamp1()
    {
        Toast.makeText(this, "Turn on", Toast.LENGTH_SHORT).show();
        try {

              message = "L.1.1";
            sendToPi_On send = new sendToPi_On();
          send.execute();
          message="";

        }
        catch (Exception e)
        {

        }

    }

    private void SendToPi_TurnOffLamp1()
    {
        Toast.makeText(this, "Turn off", Toast.LENGTH_SHORT).show();

        try {

            sendToPi_Off sendOff = new sendToPi_Off();
            sendOff.execute();
            message="";

        }
        catch (Exception e)
        {

        }    }
   // sendToPi Class
   private static Socket s;
    private static ServerSocket ss;
    private static InputStreamReader isr;
    private static BufferedReader br;
    private static PrintWriter printWriter;
    String message , messageSend = "";

    private static String ip = "192.168.0.125";

    class sendToPi_On extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {
                message="x.1";
                s= new Socket(ip,8888);
              //  DataOutputStream dout=new DataOutputStream(s.getOutputStream());
               // dout.writeUTF("Hello Server");
                //dout.flush();

                  printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(message);
               // printWriter.write('0');
                printWriter.flush();
                printWriter.close();
                  s.close();
                s.close();
                message="";

            }
            catch (Exception e)
            {
            }
            return null;
        }
    }
    class sendToPi_Off extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {
                message="1234";
                s= new Socket(ip,8888);
                //  DataOutputStream dout=new DataOutputStream(s.getOutputStream());
                // dout.writeUTF("Hello Server");
                //dout.flush();

                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(message);
                // printWriter.write('0');
                printWriter.flush();
                printWriter.close();
                s.close();
                s.close();
                message="";

            }
            catch (Exception e)
            {
            }
            return null;
        }
    }


    // end of send to PI

    // working as a server
    Button button_sent;
    EditText smessage;
    TextView chat,display_status;
    String str,msg="";
    int serverport = 9999;
    ServerSocket serverSocket;
    Handler handler = new Handler();

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
                                Toast.makeText(theLampActivity.this, msg, Toast.LENGTH_SHORT).show();
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

}
