package com.tasdeeq.cryptify;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.Gravity;

public class MainUI extends Activity {

	Button encrypt, decrypt, paste, help;
	private final int MIN_LENGTH = 24;
	boolean padded = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_ui);

		encrypt = (Button) findViewById(R.id.bEncrypt);
		decrypt = (Button) findViewById(R.id.bDecrypt);
		paste = (Button) findViewById(R.id.bPaste);
		help = (Button) findViewById(R.id.bHelp);

		final EditText input = (EditText) findViewById(R.id.tField1);
		final EditText pass = (EditText) findViewById(R.id.pField1);
		final EditText confirmPass = (EditText) findViewById(R.id.pField2);

		pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
		confirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////
		encrypt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				boolean emptyField = false;
				int password = -1;
				int conPass = -2;

				try {
					password = Integer.parseInt(pass.getText().toString());
					conPass = Integer
							.parseInt(confirmPass.getText().toString());
				} catch (NumberFormatException e) {
					emptyField = true;
					
					AlertDialog.Builder builder = new AlertDialog.Builder(MainUI.this);
					builder.setMessage("One or both password fields are empty!");
					builder.setPositiveButton("OK", null);
					AlertDialog dialog = builder.show();

					TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
					messageView.setGravity(Gravity.CENTER);
				}

				if (emptyField) { }	//
				else if (password != conPass) {
					AlertDialog.Builder builder = new AlertDialog.Builder(MainUI.this);
					builder.setMessage("Passwords don't match");
					builder.setPositiveButton("OK", null);
					AlertDialog dialog = builder.show();

					// Must call show() prior to fetching text view
					TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
					messageView.setGravity(Gravity.CENTER);

				} else  if (password <1000){
					AlertDialog.Builder builder = new AlertDialog.Builder(MainUI.this);
					builder.setMessage("Please enter a password of 4 digits without preceding zeroes");
					builder.setPositiveButton("OK", null);
					AlertDialog dialog = builder.show();

					TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
					messageView.setGravity(Gravity.CENTER);
					
				} else{
					final String rawData = input.getText().toString();
					final int passtmp = password;
					if (rawData.length() < MIN_LENGTH){
						AlertDialog.Builder builder = new AlertDialog.Builder(MainUI.this);
						builder.setTitle("Warning: Too short message");
						builder.setMessage("You must have a minimum of "+ MIN_LENGTH + " characters.\nDo you wish to have spaces as placeholders?");
						builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					               Cipher c = new Cipher(rawData, passtmp);
					               String output = c.cipher_this();
					               Intent openOutput = new Intent(MainUI.this, Output.class);
					               openOutput.putExtra("output", output);
					               openOutput.putExtra("password", passtmp);
					               startActivity(openOutput);
					           }
					       });
						builder.setNegativeButton("No", null);
						builder.setCancelable(false);
						AlertDialog dialog = builder.show();

						TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
						messageView.setGravity(Gravity.CENTER);
					}
					if (rawData.length() >= MIN_LENGTH){
					Cipher c = new Cipher(rawData, password);
					String output = c.cipher_this();
					Intent openOutput = new Intent(MainUI.this, Output.class);
					openOutput.putExtra("output", output);
					openOutput.putExtra("password", password);
					startActivity(openOutput);
					}
				}

			}
		});
////////////////////////////////////////////////////////////////////////////////////////////
		
		decrypt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				boolean emptyField = false;
				int password = -1;
				int conPass = -2;
				
				try {
					password = Integer.parseInt(pass.getText().toString());
					conPass = Integer
							.parseInt(confirmPass.getText().toString());
				} catch (NumberFormatException e) {
					emptyField = true;
					AlertDialog.Builder builder = new AlertDialog.Builder(MainUI.this);
					builder.setMessage("One or both password fields are empty!");
					builder.setPositiveButton("OK", null);
					AlertDialog dialog = builder.show();

					TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
					messageView.setGravity(Gravity.CENTER);
				}

				if (emptyField) { }

				else if (password != conPass) {
					AlertDialog.Builder builder = new AlertDialog.Builder(MainUI.this);
					builder.setMessage("Passwords don't match");
					builder.setPositiveButton("OK", null);
					AlertDialog dialog = builder.show();

					TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
					messageView.setGravity(Gravity.CENTER);

				} else if (password <1000){
					AlertDialog.Builder builder = new AlertDialog.Builder(MainUI.this);
					builder.setMessage("Please enter a password of 4 digits without preceding zeroes");
					builder.setPositiveButton("OK", null);
					AlertDialog dialog = builder.show();

					TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
					messageView.setGravity(Gravity.CENTER);

				} else{
					final String rawData = input.getText().toString();
					final int passtmp = password;
					if (rawData.length() < MIN_LENGTH){
						AlertDialog.Builder builder = new AlertDialog.Builder(MainUI.this);
						builder.setTitle("Warning: Too short message");
						builder.setMessage("You must have a minimum of "+ MIN_LENGTH + " characters.\nDo you wish to have spaces as placeholders?");
						builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					           
							public void onClick(DialogInterface dialog, int id) {
					               Decipher d = new Decipher(rawData, passtmp);
									String output = d.decipher_this();
									Intent openOutput = new Intent(MainUI.this, Output.class);
									openOutput.putExtra("output", output);
									openOutput.putExtra("password", passtmp);
									startActivity(openOutput);
					           }
					       });
						builder.setNegativeButton("No", null);
						builder.setCancelable(false);
						AlertDialog dialog = builder.show();

						TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
						messageView.setGravity(Gravity.CENTER);
					}
					if (rawData.length() >= MIN_LENGTH){
					Decipher d = new Decipher(rawData, password);
					String output = d.decipher_this();
					Intent openOutput = new Intent(MainUI.this, Output.class);
					openOutput.putExtra("output", output);
					openOutput.putExtra("password", password);
					startActivity(openOutput);
					}
				}
			}
		});
		
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////

		help.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openHelp = new Intent(MainUI.this, HelpMenu.class);
				startActivity(openHelp);
			}
		});

		
		paste.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				String pasted;
			    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				pasted = clipboard.getText().toString();
				input.setText(pasted);
			}
		});
	}
}
