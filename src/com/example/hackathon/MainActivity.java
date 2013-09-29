package com.example.hackathon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
    Button oneplayer,twoplayer,leaderboard;
    private String isLoggedIn = "Login";
    private String hasAccount = "Account";
    private String password= "", username = "", emailID="";
    SharedPreferences sp;

    protected void onCreate(Bundle savedInstanceState) {

    	sp = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        boolean account = sp.getBoolean("Account", false), login = sp.getBoolean("Login", false);

        if(account == false) {

            Context context = getApplicationContext();
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Create a User");

            final EditText inputUser = new EditText(this);
            inputUser.setHint("Username");
            inputUser.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_DATETIME_VARIATION_NORMAL);
            layout.addView(inputUser);

            final EditText inputPassword = new EditText(this);
            inputPassword.setHint("Password");
            inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            layout.addView(inputPassword);

            final EditText inputEmailID = new EditText(this);
            inputEmailID.setHint("EmailID");
            inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            layout.addView(inputEmailID);

            builder.setView(layout)
                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            username = inputUser.getText().toString();
                            password = inputPassword.getText().toString();
                            emailID = inputEmailID.getText().toString();
                            CreateUser createUser = new CreateUser(username, password, emailID);
                            createUser.run();
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putBoolean(hasAccount, true);    //to true
                            
                            editor.putString("UserName", username);   //
                            editor.putString("Password", password);   //
                            editor.putString("EmailId", emailID);
                            editor.commit();
                        }
                   }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        inputUser.setHint("Can not Cancel :P !");
                        inputPassword.setHint("Can not Cancel !");
                        inputEmailID.setHint("Can not Cancel !");
                    }
                    }).show();

        } else if (login == false){


            Log.v("Ayush", "GG");
            username = sp.getString("UserName", "error");
            password = sp.getString("Password", "error");

            if(password.equals("error") || username.equals("error")) {} //TODO

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Verify Password");

            final EditText inputPassword = new EditText(this);
            inputPassword.setHint("Password");
            inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(inputPassword);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(password.equals(inputPassword.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT);
                        dialog.dismiss();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    inputPassword.setHint("Can not Cancel !");
                }
            });
            builder.show();
        } else {

            username = sp.getString("UserName", "error");
            password = sp.getString("Password", "error");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oneplayer = (Button)findViewById(R.id.boneplayer);
        twoplayer = (Button)findViewById(R.id.btwoplayer);
        leaderboard = (Button)findViewById(R.id.bleaderboard);
        oneplayer.setOnClickListener(this);
        twoplayer.setOnClickListener(this);
        leaderboard.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		
		case R.id.boneplayer:
			
			Intent i = new Intent(getApplication(), OnePlayer.class);
			startActivity(i);
			break;
			
		case R.id.btwoplayer:

			break;
			
		case R.id.bleaderboard:
			
			break;
		}
	}
}