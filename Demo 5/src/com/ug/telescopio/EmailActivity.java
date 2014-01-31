package com.ug.telescopio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class EmailActivity extends ListActivity implements OnClickListener {
    private final static String EMAIL = "email";
    private final static String DATE_ADDED = "date";
    private List<HashMap<String, String>> emails = new ArrayList<HashMap<String, String>>();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_list);
		
		Button btnAdd = (Button)findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);
		
		SimpleAdapter adapter = new SimpleAdapter(this, 
				                                  emails, 
				                                  android.R.layout.simple_list_item_2, 
				                                  new String[]{EMAIL, DATE_ADDED},
				                                  new int[]{android.R.id.text1,
				                                            android.R.id.text2});
		
		setListAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		EditText editTextEmail = (EditText)findViewById(R.id.editTextEmail);
		String email = editTextEmail.getText().toString();
		String date = new SimpleDateFormat("dd/MM/yyyy HH:mm",
		                                   Locale.getDefault())
		                                   .format(Calendar.getInstance().getTime());
		HashMap<String, String> element = new HashMap<String, String>();
		element.put(EMAIL, email);
		element.put(DATE_ADDED, date);
		
		if (!email.trim().equals("")){
			if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
				emails.add(element);
				SimpleAdapter adapter = (SimpleAdapter)getListAdapter();
				adapter.notifyDataSetChanged();
			} else {
				Toast.makeText(this, "Ocurri� un error", Toast.LENGTH_SHORT).show();
			}
		}
		
		emails.add(element);
		SimpleAdapter adapter = (SimpleAdapter)getListAdapter();
		adapter.notifyDataSetChanged();
		// TODO Auto-generated method stub

	}

}
