package com.example.infy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
	}
	
	public void gotoMainActivity(View view){
		String email, password;
		EditText edittext = (EditText)findViewById(R.id.editTextEmailAddress);
		email=edittext.getText().toString();
		edittext = (EditText)findViewById(R.id.editTextPassword);
		password=edittext.getText().toString();
		if ((email.equals("a@b.com"))&&(password.equals("qwerty"))){
			startActivity(new Intent(this,MainActivity.class));
			overridePendingTransition(R.animator.slide_down_info,R.animator.slide_up_info);
		} else
			Toast.makeText(getApplicationContext(), getString(R.string.wrongPassword), Toast.LENGTH_SHORT).show();
	}
}
