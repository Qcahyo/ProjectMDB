package com.training.mdb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AboutActivity extends AppCompatActivity {

	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		getActionBar().hide();

		webView = findViewById(R.id.wvAbout);
		ambilWebView();

	}

	public void ambilWebView(){

		final ProgressDialog progressDialog = new ProgressDialog(AboutActivity.this,R.style.AlerDialog);
		progressDialog.setMessage("Loading...");
		progressDialog.show();

		String url = "https://jamlima.multiintifinancialteknologi.co.id:8443/api/Training/about_apps";
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {

					try {
						JSONArray jsonArray = response.getJSONArray("result");
						for (int i = 0; i < jsonArray.length() ; i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							String ambilWeb = jsonObject.getString("about_apps");
							webView.loadData(ambilWeb,"text/html", null);
							progressDialog.dismiss();

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
		};

		MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
	}
}
