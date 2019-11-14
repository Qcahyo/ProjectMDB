package com.training.mdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KantorActivity extends AppCompatActivity {

	private static final String TAG = "KantorActivity";
	private ArrayList<KantorConstruct> mKantor;
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kantor);

		mKantor = new ArrayList<>();

		mRecyclerView = findViewById(R.id.recyclerKantor);

		dataKantor();
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setHasFixedSize(true);
		mAdapter = new KantorAdapter(mKantor);
		mRecyclerView.setAdapter(mAdapter);
		mRecyclerView.setLayoutManager(mLayoutManager);

	}

	public void dataKantor(){

		final ProgressDialog progressDialog = new ProgressDialog(KantorActivity.this,R.style.AlerDialog);
		progressDialog.setMessage("Loading...");
		progressDialog.show();

		String url = "https://jamlima.multiintifinancialteknologi.co.id:8443/api/Training/office";
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					try {
						JSONArray jsonArray = response.getJSONArray("result");
						for (int i = 0; i < jsonArray.length() ; i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);

							String namaKantor = jsonObject.getString("office_name");
							String alamatKantor = jsonObject.getString("office_address");
							String deskripsiKantor = jsonObject.getString("office_description");
							String nomorKantor = jsonObject.getString("cell_phone");
							String emailKantor = jsonObject.getString("email");
							String lokasiKantor = jsonObject.getString("location_gps");
							String fotoKantor = jsonObject.getString("base_url");

							mKantor.add(new KantorConstruct(namaKantor,alamatKantor,deskripsiKantor,
								nomorKantor,emailKantor,lokasiKantor,fotoKantor));

							progressDialog.dismiss();

						}
					} catch (JSONException e) {
						e.printStackTrace();
						progressDialog.dismiss();
					}
					mAdapter.notifyDataSetChanged();

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
