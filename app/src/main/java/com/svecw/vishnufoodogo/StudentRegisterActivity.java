package com.svecw.vishnufoodogo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class StudentRegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference reference;
    MaterialEditText username,email,password;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_register);


        username = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        b1 = findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(StudentRegisterActivity.this, "All fields are required to be filled", Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length() < 6) {
                    Toast.makeText(StudentRegisterActivity.this, "Password Length Should Be greater than 6", Toast.LENGTH_SHORT).show();
                }
                else{
                    register(txt_username, txt_email,txt_password);
                }
            }
        });
    }
    private void register(final String username, String email, String password) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Log.v("pd", task.getException().getMessage());

                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            Log.v("t", "HAI");
                            HashMap<String, String> hashmap = new HashMap<>();
                            hashmap.put("id", userid);
                            hashmap.put("username", username);
                            hashmap.put("imageURL", "default");


                            reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(StudentRegisterActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        //finish();
                                    }
                                }
                            });
                        } else {
                            //Toast.makeText(RegisterActivity.this, "you can't register with this email or password", Toast.LENGTH_SHORT).show();
                            try {
                                throw task.getException();
                            }
                            catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                            }
                            catch (FirebaseAuthEmailException e){
                                Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                            }
                            catch (FirebaseAuthException e){
                                Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
