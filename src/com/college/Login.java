package com.college;

import java.util.ArrayList;  
import org.apache.http.NameValuePair;  
import org.apache.http.message.BasicNameValuePair;  
import android.app.Activity;  
import android.content.Intent;
import android.os.Bundle;  
import android.view.View;  
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.TextView;  
  
public class Login extends Activity {  
	EditText un,pw;  
	TextView error;  
	Button ok;  
	/** Called when the activity is first created. */  
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.login);
		
		un=(EditText)findViewById(R.id.user);  
		pw=(EditText)findViewById(R.id.pass);  
		ok=(Button)findViewById(R.id.login);  
		error=(TextView)findViewById(R.id.error);  
		ok.setOnClickListener(new View.OnClickListener() {  

			@Override  

			public void onClick(View v) {  
				// TODO Auto-generated method stub  
				ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();  
				postParameters.add(new BasicNameValuePair("username", un.getText().toString()));  
				postParameters.add(new BasicNameValuePair("password", pw.getText().toString()));  

				//String valid = "1";  
				String response = null;  
				try {  
					response = CustomHttpClient.executeHttpPost("http://192.168.1.26/check.php", postParameters);  //Enetr Your remote PHP,ASP, Servlet file link  
					String res=response.toString();  
					// res = res.trim();  
					res= res.replaceAll("\\s+","");  
					//error.setText(res);  
					if(res.equals("1"))  
					{
						Intent in=new Intent(Login.this,Tab.class);
						startActivity(in);
					}
					else  
						error.setText("Sorry!! Incorrect Username or Password");  

				} catch (Exception e) {  

					un.setText(e.toString());  
				}}  
		});  
	}}  