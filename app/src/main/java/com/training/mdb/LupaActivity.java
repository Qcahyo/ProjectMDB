package com.training.mdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LupaActivity extends AppCompatActivity {
    String var_new,var_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa);
        setTitle("Forgot Password");
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button reset = findViewById( R.id.reset );
        final EditText new_password = findViewById( R.id.et_new_password );
        final EditText ok_password = findViewById( R.id.et_ok_password );

        reset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var_new=new_password.getText().toString();
                var_ok=ok_password.getText().toString();
                if (var_new.length()<4){
                    Toast.makeText( LupaActivity.this, "Password baru kurang dari 4", Toast.LENGTH_LONG).show();
                } else if (var_new.equals( var_ok )){
                    Intent ok = new Intent( LupaActivity.this,LoginActivity.class );
                    startActivity( ok );
                } else if (var_new.length()==0){
                    Toast.makeText( LupaActivity.this, "Password baru kosong", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText( LupaActivity.this, "Maaf Paswword tidak cocok", Toast.LENGTH_LONG).show();
                }
            }
        } );
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
