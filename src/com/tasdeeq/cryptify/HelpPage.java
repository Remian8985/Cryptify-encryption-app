package com.tasdeeq.cryptify;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

public class HelpPage extends Activity {

	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		String formattedText="";

		Bundle extras = getIntent().getExtras();
		int itemId = extras.getInt("itemId");
		display = (TextView) findViewById(R.id.tvHelp);
		
		switch(itemId){
		case 0:
			formattedText = "<br><b>Why <i>Cryptify</i>?</b><br><br>Need a way to hide a message? No worries! With <i>Cryptify</i>, you can now encrypt a message in one touch and send it right under the noses of unwanted people. <i>Cryptify</i> allows you to transform a text into a seemingly meaningless combination of letters, numbers and symbols. And with the correct four digit password, you can decipher the hidden message in no time! " ;
			break;
			
		case 1:
			formattedText = "<br><b>How to encrypt/decrypt a text</b> <br><br>Simply type your original or encrypted message. You can also paste a copied text by pressing the \'Paste from clipboard\' button. For encrypting, set your own password and press \'Encrypt\'. For decrypting, enter the right password and press \'Decrypt\'. <br><br>Remember, you must type/paste the exact encrypted message in order to get the correct deciphered message. Otherwise, you may just end up with an even more scrambled and unrecoverable message. <br><br>Your input must be at least 24 characters long. If it isn't, you will have an option to use padding with spaces. " ;
			break;
			
		case 2:
			formattedText = "<br><b>Password regulations</b> <br><br>You must enter a password of four digits with no preceding zeroes. For example, 1234 is acceptable but 0234 or 234 are not. " ;
			break;
			
		case 3:
			formattedText = "<br><b>Tips and tricks</b> <br><br>You may just keep repeating the encryption process for extra protection. You can encrypt a message, copy it to the main screen and use a different password to encrypt again. You will just have to do it in reverse order with the correct passwords to uncover the original message. <br><br>You can use the \'Decrypt\' button to encrypt your message too! You will just have to use \'Encrypt\' to reverse it into the orignial then. <br><br>You can send your encrypted/decrypted message directly to text message or email. Or you can just copy it and paste anywhere." ;
			break;
			
		case 4:{
			String versionName=" *unable to retrieve version name*";
			try {
				versionName = getPackageManager()
					    .getPackageInfo(getPackageName(), 0).versionName;
			} catch (NameNotFoundException e) {
				
			}
			formattedText = "<br><b><i>Cryptify</b></i>  version "+ versionName +"  released on January 19, 2014" ;
			break;
		  }
		
		default:
			formattedText = "<br>Unable to load help text";
			break;
		}

		Spanned result = Html.fromHtml(formattedText);		
		display.setText(result);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
