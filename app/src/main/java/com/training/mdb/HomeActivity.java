package com.training.mdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

	private static final String TAG = "HomeActivity";

	private ArrayList<KantorConstruct> mKantor;

	CardView cvMeluncur;
	CardView cvDaftarKantor;
	CardView cvDaftarKaryawan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mKantor = new ArrayList<>();

		cvMeluncur = findViewById(R.id.cvMeluncur);
		cvDaftarKantor = findViewById(R.id.cvDaftarKantor);
		cvDaftarKaryawan = findViewById(R.id.cvDaftarKaryawan);

		cvMeluncur.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Uri gmmIntentUri = Uri.parse("google.navigation:q=-6.205897, 106.822091");
				Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
				mapIntent.setPackage("com.google.android.apps.maps");
				startActivity(mapIntent);

			}
		});

		cvDaftarKantor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,KantorActivity.class);
				startActivity(intent);
			}
		});

		cvDaftarKaryawan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(HomeActivity.this,KaryawanActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){

			case R.id.tentangApps:
				startActivity(new Intent(this,AboutActivity.class));
				break;
			case R.id.keluar:
				Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
				startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
