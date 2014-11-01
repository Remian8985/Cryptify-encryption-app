package com.tasdeeq.cryptify;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HelpMenu extends ListActivity
{

	final String items[] = {"Why Cryptify", "How to encrypt or decrypt a text", "Password regulations", "Tips and tricks", "About"};

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent helpActual = new Intent(HelpMenu.this, HelpPage.class);
		helpActual.putExtra("itemId", position);
		startActivity(helpActual);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(HelpMenu.this,R.layout.help_menu,items);
		setListAdapter(ad);
	}
	
}
