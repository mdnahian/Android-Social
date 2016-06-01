package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aqurytech.pinetree.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

import core.InputFilter;
import data.User;

/**
 * Created by Md Islam on 5/26/2016.
 */
public class SignupActivity extends ParentActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {

                    firebaseUser.updateProfile(new UserProfileChangeRequest());

                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    finish();
                }
            }
        };


        final EditText fname = (EditText) findViewById(R.id.fname);
        final EditText lname = (EditText) findViewById(R.id.lname);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText confirm = (EditText) findViewById(R.id.confirm);



        Button sign_up_btn = (Button) findViewById(R.id.sign_up_btn);
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<EditText> inputs = new ArrayList<>();
                inputs.add(fname);
                inputs.add(lname);
                inputs.add(username);
                inputs.add(email);
                inputs.add(password);
                inputs.add(confirm);

                if (InputFilter.checkInputsEmpty(inputs)) {
                    if (password.getText().toString().equals(confirm.getText().toString())) {
                        User user = new User(fname.getText().toString(), lname.getText().toString(), username.getText().toString(), email.getText().toString(),
                                password.getText().toString());
                        signup(user);
                    } else {

                        alertDialog = null;

                        alertDialog = buildDialog("Sign Up Failed", "Password and confirmation password do not match.", getString(R.string.try_again), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();

                        password.setText("");
                        confirm.setText("");

                    }
                } else {

                    alertDialog = null;

                    alertDialog = buildDialog("Sign Up Failed", "Please fill all fields.", getString(R.string.try_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();

                }
            }
        });


        findViewById(R.id.sign_in_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }







    public void signup(User user){

        this.user = user;

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            alertDialog = null;

                            alertDialog = buildDialog("Sign Up Failed", "Email or username is already in use.", "Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });
                            alertDialog.show();

                        }


                    }
                });

    }





}
