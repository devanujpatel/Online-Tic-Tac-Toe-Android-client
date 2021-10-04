package com.example.tic_tac_toe_online;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class PlayActivity extends AppCompatActivity {

    Socket mSocket = SocketHandler.getSocket();
    String mark;
    String chance = "X";

    public void buttonClick(String btn_pos) {
        mSocket.emit("button click", btn_pos);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mark = getIntent().getStringExtra("chance");

        mSocket.on("btn clicked", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];

                        try {
                            String buttonPosition = data.getString("btn position");
                            String buttonMark = data.getString("btn mark");
                            chance = data.getString("chance");
                            updateButton(buttonPosition, buttonMark);

                        } catch (JSONException ignored) {
                        }
                    }
                });
            }
        });

        mSocket.on("result", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];

                        try {
                            String result = data.getString("winner");
                            System.out.println("got winner"  + result);
                            updateScreenForWinner(result);
                        } catch (JSONException ignored) {
                        }
                    }
                });
            }
        });

        Button btn_a1 = (Button) findViewById(R.id.a1);
        btn_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark.equals(chance)) {
                    buttonClick("a1");
                }
            }
        });

        Button btn_a2 = (Button) findViewById(R.id.a2);
        btn_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark.equals(chance)) {
                    buttonClick("a2");
                }
            }
        });

        Button btn_a3 = (Button) findViewById(R.id.a3);
        btn_a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark.equals(chance)) {
                    buttonClick("a3");
                }
            }
        });

        Button btn_b1 = (Button) findViewById(R.id.b1);
        btn_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark.equals(chance)) {
                    buttonClick("b1");
                }
            }
        });

        Button btn_b2 = (Button) findViewById(R.id.b2);
        btn_b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark.equals(chance)) {
                    buttonClick("b2");
                }
            }
        });

        Button btn_b3 = (Button) findViewById(R.id.b3);
        btn_b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark.equals(chance)) {
                    buttonClick("b3");
                }
            }
        });

        Button btn_c1 = (Button) findViewById(R.id.c1);
        btn_c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark.equals(chance)) {
                    buttonClick("c1");
                }
            }
        });

        Button btn_c2 = (Button) findViewById(R.id.c2);
        btn_c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark.equals(chance)) {
                    buttonClick("c2");
                }
            }
        });

        Button btn_c3 = (Button) findViewById(R.id.c3);
        btn_c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark.equals(chance)) {
                    buttonClick("c3");
                }else{
                    Toast.makeText(getApplicationContext(), "Wait for your chance", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void updateScreenForWinner(String result) {
        System.out.println("updating screen for winner"  + result);
        Intent intent = new Intent(this, WinnerScreen.class);
        intent.putExtra("result", result);
        this.startActivity(intent);
    }


    public void updateButton(String btn_position, String btn_mark) {
        Button btn;
        switch (btn_position) {
            //Case statements
            case "a1":
                btn = (Button) findViewById(R.id.a1);
                btn.setEnabled(false);
                btn.setText(btn_mark);
                break;
            case "a2":
                btn = (Button) findViewById(R.id.a2);
                btn.setEnabled(false);
                btn.setText(btn_mark);
                break;
            case "a3":
                btn = (Button) findViewById(R.id.a3);
                btn.setEnabled(false);
                btn.setText(btn_mark);
                break;
            case "b1":
                btn = (Button) findViewById(R.id.b1);
                btn.setEnabled(false);
                btn.setText(btn_mark);
                break;
            case "b2":
                btn = (Button) findViewById(R.id.b2);
                btn.setEnabled(false);
                btn.setText(btn_mark);
                break;
            case "b3":
                btn = (Button) findViewById(R.id.b3);
                btn.setEnabled(false);
                btn.setText(btn_mark);
                break;
            case "c1":
                btn = (Button) findViewById(R.id.c1);
                btn.setEnabled(false);
                btn.setText(btn_mark);
                break;
            case "c2":
                btn = (Button) findViewById(R.id.c2);
                btn.setEnabled(false);
                btn.setText(btn_mark);
                break;
            case "c3":
                btn = (Button) findViewById(R.id.c3);
                btn.setEnabled(false);
                btn.setText(btn_mark);
                break;
        }

    }

}