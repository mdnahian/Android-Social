package activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aqurytech.pinetree.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;

import core.InputFilter;
import data.User;

/**
 * Created by Md Islam on 5/26/2016.
 */
public class SignupActivity extends ParentActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");

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

                        progressDialog.show();

                        ParseUser parseUser = new ParseUser();
                        parseUser.setUsername(username.getText().toString());
                        parseUser.setEmail(email.getText().toString());
                        parseUser.setPassword(password.getText().toString());
                        parseUser.put("fname", fname.getText().toString());
                        parseUser.put("lname", lname.getText().toString());

                        parseUser.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e != null){
                                    progressDialog.dismiss();

                                    alertDialog = null;
                                    alertDialog = buildDialog("Sign Up Failed", e.getMessage(), getString(R.string.try_again), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alertDialog.dismiss();
                                        }
                                    });
                                    alertDialog.show();
                                } else {
                                    progressDialog.dismiss();

                                    alertDialog = null;
                                    alertDialog = buildDialog("Sign Up Successful", "A confirmation email has been sent to "+email.getText().toString()+".", "Login", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alertDialog.dismiss();
                                            finish();
                                        }
                                    });
                                    alertDialog.show();
                                }
                            }
                        });

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




}
