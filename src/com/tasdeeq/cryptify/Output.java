package com.tasdeeq.cryptify;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

public class Output extends Activity {

	Button showPass, copyButt, sms, email, help;
	int password;
	String output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output);

		TextView display = (TextView) findViewById(R.id.tOutput);
		Bundle extras = getIntent().getExtras();
		output = extras.getString("output");
		password = extras.getInt("password");
		display.setText(output);

		showPass = (Button) findViewById(R.id.bPass);
		copyButt = (Button) findViewById(R.id.bCopy);
		sms = (Button) findViewById(R.id.bSMS);
		email = (Button) findViewById(R.id.bEmail);
		help = (Button) findViewById(R.id.bHelp);

		help.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openHelp = new Intent(Output.this, HelpMenu.class);
				startActivity(openHelp);
			}
		});

		copyButt.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {

				int sdk = android.os.Build.VERSION.SDK_INT;
				if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
					android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					clipboard.setText(output);
				} else {
					android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					android.content.ClipData clip = android.content.ClipData
							.newPlainText(output, output);
					clipboard.setPrimaryClip(clip);
				}

			}
		});
		
		showPass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Output.this);
				builder.setMessage("Password: " + password);
				builder.setPositiveButton("OK", null);
				AlertDialog dialog = builder.show();

				TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
				messageView.setGravity(Gravity.CENTER);
			}
		});
		
		
		email.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_TEXT   , output);
				try {
				    startActivity(Intent.createChooser(i, "Send mail..."));
				} catch (android.content.ActivityNotFoundException ex) {
				    Toast.makeText(Output.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		sms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent sendIntent = new Intent(Intent.ACTION_VIEW);         
				sendIntent.setData(Uri.parse("sms:"));
				sendIntent.putExtra("sms_body", output); 
				try{
				startActivity(Intent.createChooser(sendIntent, "Send message..."));
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(Output.this, "There is SMS client installed.", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

}
