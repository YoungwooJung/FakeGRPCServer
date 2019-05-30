package com.tomjung.fakegrpcserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText portEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        portEdit = findViewById(R.id.port_edit_text);
        Button startgRPCServerBtn = findViewById(R.id.start_service);
        startgRPCServerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gRPCService = new Intent(getApplicationContext(), ServerService.class);
                stopService(gRPCService);

                gRPCService.putExtra("port", Integer.parseInt(portEdit.getText().toString()));
                startService(gRPCService);

                finish();
            }
        });
    }
}
