package com.example.fake2edison.test5;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private static TextView textView;
    private SockClient sockClient;
    private String temp;
    private Button but;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        sockClient = new SockClient(textView);
        sockClient.start();
        editText = (EditText)findViewById(R.id.sendMessage);
        but = (Button) findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (R.id.but) {
                    case R.id.but:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String content = editText.getText().toString();
                                if(!content.equals("")) {
                                    sockClient.setOutput(content);
                                }
                            }
                        }).start();
                        break;
                    default:
                        break;
                }
                editText.setText("");
            }
        });
    }

}
