package my.whatsappdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActLogin extends AppCompatActivity {

    private FirebaseUser currentUser;
    private Button login_button, phone_login_button;
    private EditText user_email, user_pass;
    private TextView need_new_account, forget_pass_link;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);

        mAuth=FirebaseAuth.getInstance();

        InitializeFields();

        need_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToRegisterActivity();
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogin();
            }
        });

    }

    private void AllowUserToLogin() {

        String email= user_email.getText().toString();
        String password= user_pass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter the Email", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter the Password", Toast.LENGTH_SHORT).show();
        }
        else{

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    SendUserToMainActivity();
                                    Toast.makeText(ActLogin.this, "Logged in Successfully!!!", Toast.LENGTH_SHORT).show();
                                }else{
                                    String msg= task.getException().toString();
                                    Toast.makeText(ActLogin.this, "Error :"+msg, Toast.LENGTH_SHORT).show();

                                }
                        }
                    });
        }
    }

    private void InitializeFields() {
        login_button = findViewById(R.id.login_button);
        phone_login_button = findViewById(R.id.phone_button);
        user_email = findViewById(R.id.login_email);
        user_pass = findViewById(R.id.login_password);
        need_new_account = findViewById(R.id.need_new_account);
        forget_pass_link = findViewById(R.id.forget_password_link);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser != null) {
            SendUserToMainActivity();
        }

    }

    private void SendUserToMainActivity() {
        startActivity(new Intent(ActLogin.this, MainActivity.class));
    }

    private void SendUserToRegisterActivity() {
        startActivity(new Intent(ActLogin.this, ActRegister.class));
    }
}
