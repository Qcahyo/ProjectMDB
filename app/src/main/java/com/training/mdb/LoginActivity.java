package com.training.mdb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

	private static final String TAG = "LoginActivity";

	LinearLayout mLinearLayout;

	EditText etUserName;
	EditText etPassword;
	Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		getSupportActionBar().setTitle("Login User");

		mLinearLayout = findViewById(R.id.lyLogin);

		TextView lupa = findViewById(R.id.tvLupaPassword);
		etUserName = findViewById(R.id.etUserName);
		etPassword = findViewById(R.id.etPassword);
		btnLogin = findViewById(R.id.btnLogin);

		lupa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
				startActivity(intent);
			}
		});
			btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (etUserName.length() == 0){
					etUserName.setError("Username harus diisi !");
				}

				if (etPassword.length() < 4) {
					etPassword.setError("Harap isi 4 digit password");
				}

				String username = String.valueOf(etUserName.getText());
				String password = String.valueOf(etPassword.getText());

				RestLogin(username,password);
			}
		});

	}
	public void RestLogin(final String var_cell_phone, final String var_password ){

		String url = "https://jamlima.multiintifinancialteknologi.co.id:8443/api/Training/auth";
		final StringRequest request = new StringRequest(Request.Method.POST, url,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					JSONObject jsonObject = null;
					try {

						jsonObject = new JSONObject(response);
						String hasilRespon = jsonObject.getString("var_result");
						String hasilPesan = jsonObject.getString("var_message");

						Log.d(TAG, "onResponse: hasilRespon" + hasilRespon);

						if (hasilRespon.equals("1")){
							Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
							startActivity(intent);
						} else {
							Snackbar snackbar = Snackbar
									.make(mLinearLayout,hasilPesan, Snackbar.LENGTH_LONG);
							snackbar.show();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		}) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers =  new HashMap<String, String>();
				String creds = String.format("%s:%s","apitraining","password");
				String encoded = "Basic "+ Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
				headers.put("Authorization", encoded);
				return headers;
			}

			protected Map<String,String> getParams(){
				Map<String,String> MyData = new HashMap<String,String>();
				MyData.put("var_cell_phone", var_cell_phone);
				MyData.put("var_password", var_password);

				return MyData;
			}
		};

		MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
	}

	public void RestLogin2( final String var_cell_phone, final String var_password){

		String url = "https://jamlima.multiintifinancialteknologi.co.id:8443/api/Training/auth";
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						JSONObject jsonObject = null;

						try {

							jsonObject = new JSONObject(String.valueOf(response));
							String hasilRespon = jsonObject.getString("var_result");
							String hasilPesan = jsonObject.getString("var_message");

							if (hasilRespon.equals("1")){
								Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
								startActivity(intent);

							} else {

								Snackbar snackbar = Snackbar
										.make(mLinearLayout,hasilPesan, Snackbar.LENGTH_LONG);
								snackbar.show();
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		}){

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers =  new HashMap<String, String>();
				String creds = String.format("%s:%s","apitraining","password");
				String encoded = "Basic "+ Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
				headers.put("Authorization", encoded);
				return headers;
			}

			protected Map<String,String> getParams(){
				Map<String,String> MyData = new HashMap<String,String>();
				MyData.put("var_cell_phone", var_cell_phone);
				MyData.put("var_password", var_password);

				return MyData;
			}
		};
		MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
	}

}
