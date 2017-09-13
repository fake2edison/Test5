package com.example.fake2edison.test5;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by fake2edison on 2017/9/12.
 */

public class SockClient {

    private Socket socket = null;
    private BufferedReader br = null;
    private PrintWriter write = null;
    private BufferedReader in = null;
    private String temp1 = null;
    public String recive = null;
    public int count = 0;
    public Message message;
    public TextView textView;
    public SockClient(TextView textView){
        this.textView = textView;
    }




    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            try {
                socket = new Socket("47.93.99.95", 5209);
                System.out.println("客户端启动成功");
                br = new BufferedReader(new InputStreamReader(System.in));
                write = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (Exception e) {
                System.out.println("服务器连接失败");
                System.out.println(e);
            }
        }
    };



    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message age){
            switch ((message.what)){
                case 1:
                    Log.i("info","1");
                    textView.setText(recive);
                    break;
                default:
                    Log.i("info","default");
                    break;
            }
        }
    };

    //final Handler handler = new Handler(){
//    @Override
//    public void handleMessage(Message msg) {
//        textView.setText(msg.arg1+"");
//    }
//};

    public void start(){
        new Thread(networkTask).start();
    }

    final Runnable returnTask = new Runnable() {

        @Override
        public void run() {
            try {
                Log.i("info","run");
                write.println(temp1);
                write.flush();
                recive = in.readLine();
                recive = recive.replace("%robotname%","Chris");
                Log.i("info",recive.toString());
            }catch (IOException e){
                Log.i("info","Exc");
                System.out.print(e);
            }
            message = new Message();
            message.what = 1;
            Log.i("info","sendmessage");
            Log.i("info",message.what+"222");
            handler.sendMessage(message);
        }
    };


    public void setOutput(String content){
        try {
            temp1 = content;
            Log.i("info",temp1);
            new Thread(returnTask).start();
        }catch (Exception e){
            System.out.print(e);
        }
    }
}
