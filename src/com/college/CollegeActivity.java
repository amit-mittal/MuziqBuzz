package com.college;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CollegeActivity extends Activity {
    /** Called when the activity is first created. */
	Button submit;
	ToggleButton tb;
	EditText username,password;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        submit=(Button)findViewById(R.id.submit);
        tb=(ToggleButton)findViewById(R.id.tb);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        
        
        tb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			if(tb.isChecked())
			{password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);}
			else{
				password.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			}
		});
        
        
        submit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str=username.getText().toString();
				Bundle bl=new Bundle();
				bl.putString("username",str);
				Intent in= new Intent("com.college.AFTERLOGIN");
				in.putExtras(bl);
				startActivity(in);
			}
		});
        }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mi=getMenuInflater();
		mi.inflate(R.menu.menu, menu);
		
		return true;
		}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.about:
			Intent in= new Intent(CollegeActivity.this,AboutMe.class);
			startActivity(in);
			break;

		case R.id.prefs:
			Intent intent= new Intent(CollegeActivity.this,Sound_setting.class);
			startActivity(intent);
			break;
		
		case R.id.exit:
			AlertDialog.Builder builder= new AlertDialog.Builder(CollegeActivity.this);
			builder.setMessage("Are you sure you want to Exit");
			builder.setCancelable(false);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					CollegeActivity.this.finish();
				}
			});
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			builder.show();
		}
		return false;
	}
    
	


}