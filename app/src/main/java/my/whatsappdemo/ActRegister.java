package my.whatsappdemo;

import android.app.ProgressDialog;
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

public class ActRegister extends AppCompatActivity {

    private Button create_account_btn;
    private EditText user_email, user_pass;
    private TextView already_have_account;
    private FirebaseAuth mAuth;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_register);

        mAuth=FirebaseAuth.getInstance();
        InitializeFields();

        already_have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToLoginActivity();
            }
        });
        create_account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });

    }

    private void CreateNewAccount() {
        String email= user_email.getText().toString();
        String password= user_pass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter the Email", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter the Password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Creating new Account");
            loadingBar.setMessage("Please Wait while we are creating an account for you");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        SendUserToLoginActivity();
                        Toast.makeText(ActRegister.this, "Account Created", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }else{
                        String msg= task.getException().toString();
                        Toast.makeText(ActRegister.this, "Error :"+msg, Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }


    private void InitializeFields() {
        create_account_btn = findViewById(R.id.register_button);

        user_pass = findViewById(R.id.register_password);
        user_email = findViewById(R.id.register_email);
        already_have_account = findViewById(R.id.already_have_account);
        loadingBar=new ProgressDialog(this);


    }

    private void SendUserToLoginActivity() {
        startActivity(new Intent(ActRegister.this, ActLogin.class));
    }
}
