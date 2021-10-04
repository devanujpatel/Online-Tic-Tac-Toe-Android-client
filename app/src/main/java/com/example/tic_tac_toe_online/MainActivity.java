 package com.example.tic_tac_toe_online;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;


import java.security.spec.ECField;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            try {
                mSocket = IO.socket("http://192.168.29.201:9999");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Cannot connect", Toast.LENGTH_SHORT).show();
            }
        }
        try {
            mSocket.connect();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Cannot connect", Toast.LENGTH_SHORT).show();
        }
        SocketHandler.setSocket(mSocket);

        //Toast.makeText(getApplicationContext(), "Connection established", Toast.LENGTH_SHORT).show();

        Button play_btn = (Button) findViewById(R.id.play);
        play_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                play_btn.setEnabled(false);
                try {
                    callPlay();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void callPlay() throws JSONException {
        mSocket.emit("play", "");
        //Toast.makeText(getApplicationContext(), "Matchmaking started", Toast.LENGTH_SHORT).show();
        mSocket.on("game on", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                JSONObject data = (JSONObject) args[0];
                String chance;
                try {
                    int id = data.getInt("id");
                    int game_id = data.getInt("game_id");
                    chance = data.getString("chance");
                    callPlayActivity(chance);
                } catch (JSONException ignored) {
                }
            }
        });

    }
    public void callPlayActivity(String chance){
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("chance", chance);
        startActivity(intent);
    }

}