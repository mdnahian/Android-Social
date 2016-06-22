package activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aqurytech.pinetree.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.stephentuso.welcome.WelcomeScreenHelper;

/**
 * Created by Md Islam on 5/25/2016.
 */
public class LoginActivity extends ParentActivity {

    private ProgressDialog progressDialog;

    private WelcomeScreenHelper welcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(new Parse.Configuration.Builder(this)
                            .applicationId(getString(R.string.parseAppId))
                            .clientKey(null)
                            .server(getString(R.string.parseUrl)).build()
            );
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e){
            Log.d("Crash", "Application Crashed. Error Report Sent");
        }

        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");

        welcomeScreen = new WelcomeScreenHelper(this, WelcomeActivity.class);
        welcomeScreen.show(savedInstanceState);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        Button sign_in_btn = (Button) findViewById(R.id.sign_in_btn);
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().length() == 0 || password.getText().length() == 0) {
                    alertDialog = null;
                    alertDialog = buildDialog("Login Failed", "Username or Password Incorrect", getString(R.string.try_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();

                } else {
                    progressDialog.show();

                    ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, com.parse.ParseException e) {
                            if (user != null) {
                                progressDialog.dismiss();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Log.d("Crash", e.getMessage());
                                alertDialog = null;
                                alertDialog = buildDialog("Login Failed", "Username or Password Incorrect", getString(R.string.try_again), new View.OnClickListener() {
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

        });

        Button sign_up_btn = (Button) findViewById(R.id.sign_up_btn);
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }


}
