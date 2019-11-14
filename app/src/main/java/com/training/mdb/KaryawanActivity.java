package com.training.mdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class KaryawanActivity extends AppCompatActivity {

	private ArrayList<KaryawanConstruct> mKaryawan;

	private static final String TAG = "KaryawanActivity";
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_karyawan);

		//ArrayList<KaryawanConstruct> mKaryawan = getIntent().getParcelableArrayListExtra("arrayList");

		mKaryawan = new ArrayList<>();

		mRecyclerView = findViewById(R.id.recyclerKaryawan);

		dataKaryawan();
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setHasFixedSize(true);
		mAdapter = new KaryawanAdapter(mKaryawan);
		mRecyclerView.setAdapter(mAdapter);
		mRecyclerView.setLayoutManager(mLayoutManager);

	}

	public void dataKaryawan(){

		final ProgressDialog progressDialog = new ProgressDialog(KaryawanActivity.this,R.style.AlerDialog);
		progressDialog.setMessage("Loading...");
		progressDialog.show();

		String url = "https://jamlima.multiintifinancialteknologi.co.id:8443/api/Training/employee";
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {

					try {
						JSONArray jsonArray =  response.getJSONArray("result");
						for (int i = 0; i < jsonArray.length() ; i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);

							String namaKaryawan = jsonObject.getString("employee_name");
							String NIK = jsonObject.getString("nomor_induk_pegawai");
							String Alamat = jsonObject.getString("address");
							String Gender = jsonObject.getString("gender");
							String fotoKaryawan = jsonObject.getString("base_url");
							String tempatLahir = jsonObject.getString("tempat_lahir");
							String tanggalLahir = jsonObject.getString("tanggal_lahir");
							String golonganDarah = jsonObject.getString("gol_darah");
							String Agama = jsonObject.getString("agama");
							String statusPerkawinan = jsonObject.getString("status_perkawinan");
							String Kewarganegaraan = jsonObject.getString("kewarganegaraan");
							String berlakuHingga = jsonObject.getString("berlaku_hingga");
							String tempatBuat = jsonObject.getString("tempat_buat");
							String tanggalBuat = jsonObject.getString("tanggal_buat");

							mKaryawan.add(new KaryawanConstruct(namaKaryawan,NIK,Alamat,Gender,fotoKaryawan,tempatLahir,tanggalLahir,golonganDarah,Agama,statusPerkawinan,Kewarganegaraan,berlakuHingga,tempatBuat,tanggalBuat));
							progressDialog.dismiss();

							//Toast.makeText(HomeActivity.this,fotoKaryawan,Toast.LENGTH_LONG).show();
						}
					} catch (JSONException e) {
						e.printStackTrace();
						progressDialog.dismiss();
					}
					mAdapter.notifyDataSetChanged();
					//progressDialog.dismiss();
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
