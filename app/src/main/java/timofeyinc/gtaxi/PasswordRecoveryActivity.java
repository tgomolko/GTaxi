package timofeyinc.gtaxi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordRecoveryActivity extends AppCompatActivity {
    private EditText mRecoveryEmail;
    private Button mSendPasswordRecoveryEmail, mBackBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        mRecoveryEmail = (EditText) findViewById(R.id.recoveryEmail);
        mSendPasswordRecoveryEmail = (Button) findViewById(R.id.sendResetEmail);
        mBackBtn = (Button) findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();

        mSendPasswordRecoveryEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mRecoveryEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(PasswordRecoveryActivity.this,"Check your email to reset password!", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(PasswordRecoveryActivity.this,"Fail to send email!" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
