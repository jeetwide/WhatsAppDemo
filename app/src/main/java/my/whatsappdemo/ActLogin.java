package my.whatsappdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;

public class ActLogin extends AppCompatActivity {

    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);


    }
    @Override
    protected void onStart() {
        super.onStart();
        if(currentUser != null){
            SendUserToMainActivity();
        }

    }

    private void SendUserToMainActivity() {
        startActivity(new Intent(ActLogin.this,MainActivity.class));
    }
}
