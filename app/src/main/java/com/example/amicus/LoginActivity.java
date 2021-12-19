package com.example.amicus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amicus.ui.ConfirmationActivity;
import com.example.amicus.ui.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout login_email_et;
    TextInputLayout very_code;
    private TextView tw_phone;
    private TextView code;
    private Button login_bt;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private EditText number_textview;
    private EditText code_veryfic;

    String smscode;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        pd = new ProgressDialog(this);
        pd.setTitle("Хагрузка");
        pd.setCanceledOnTouchOutside(false);


        TextView registration_link = findViewById(R.id.registration_link);
        tw_phone = findViewById(R.id.tw_phone);
        number_textview = findViewById(R.id.number_textview);
        very_code = findViewById(R.id.very_code);
        code = findViewById(R.id.code);
        login_email_et = findViewById(R.id.login_email_et);
        code_veryfic = findViewById(R.id.code_veryfic);
        Button login_bt = findViewById(R.id.login_bt);


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                code_veryfic.setText(credential.getSmsCode());
                signInWithPhoneAuthCredential(credential);

                smscode = credential.getSmsCode();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

            }
        };


        registration_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Registration.class);
                startActivity(intent);
            }
        });

        registration_link.setPaintFlags(registration_link.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_bt.getText().toString().equals("Получить код")) {
                    String phone = number_textview.getText().toString().trim();
                    if (TextUtils.isEmpty(phone)) {
                        Toast.makeText(LoginActivity.this, "Введите номер телефона", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        login_bt.setText("Войти");
                        tw_phone.setVisibility(View.GONE);
                        login_email_et.setVisibility(View.GONE);
                        code.setVisibility(View.VISIBLE);
                        very_code.setVisibility(View.VISIBLE);
                        startPhoneNumberVerification(phone);
                    }
                }else if (login_bt.getText().toString().equals("Войти")){
                    String code = code_veryfic.getText().toString().trim();
                    verifyPhoneNumberWithCode(mVerificationId,code);
                    if (smscode.equals(code_veryfic.getText())) {
                        Toast.makeText(LoginActivity.this, "Код праавильный", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "кОД НЕПРАВИЛЬНЫЙ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        // [END start_phone_auth]
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }
}