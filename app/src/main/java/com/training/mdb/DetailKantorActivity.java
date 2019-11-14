package com.training.mdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailKantorActivity extends AppCompatActivity implements OnMapReadyCallback {

	private GoogleMap mMaps;
	//private MapFragment mapFragment;

	CardView cvPetunjukArah, cvTelepon, cvEmail;
	CircleImageView imgFotoKantor;
	TextView tvDetailNamaKantor, tvDetailAlamatKantor, tvDeskripsi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_kantor);

		imgFotoKantor = findViewById(R.id.imgFotoKantor);
		tvDetailNamaKantor = findViewById(R.id.tvDetailNamaKantor);
		tvDetailAlamatKantor = findViewById(R.id.tvDetailAlamatKantor);
		tvDeskripsi = findViewById(R.id.tvDeskripsi);
		cvEmail = findViewById(R.id.cvEmail);
		cvTelepon = findViewById(R.id.cvTelepon);
		cvPetunjukArah = findViewById(R.id.cvPetunjukArah);

		Intent intent = getIntent();
		KantorConstruct kantorConstruct = intent.getParcelableExtra("detailKantor");

		String namaKantor = kantorConstruct.getNamaKantor();
		String alamatKantor = kantorConstruct.getAlamatKantor();
		String deskripsiKantor = kantorConstruct.getDeskripsiKantor();
		final String teleponKantor = kantorConstruct.getTeleponKantor();
		String emailKantor = kantorConstruct.getEmailKantor();
		String fotoKantor = kantorConstruct.getFotoKantor();
		String lokasiKantor = kantorConstruct.getLokasiKantor();

		Picasso.with(this).load(fotoKantor).into(imgFotoKantor);

		tvDetailNamaKantor.setText(namaKantor);
		tvDetailAlamatKantor.setText(alamatKantor);
		tvDeskripsi.setText(deskripsiKantor);

		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.maps);
		mapFragment.getMapAsync(this);

		cvTelepon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent pangilan = new Intent(Intent.ACTION_DIAL);
				pangilan.setData(Uri.parse("tel:+6281515151622"));
				startActivity(pangilan);
			}
		});

		cvEmail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		cvPetunjukArah.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				double Lat;
				double Lng;
				//String[] result = lokasiKantor.split(",");
				//Lat = Double.parseDouble(result[0]);
				//Lng = Double.parseDouble(result[1]);

				Uri gmmIntentUri = Uri.parse("google.navigation:q=-6.205897, 106.822091");
				Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
				mapIntent.setPackage("com.google.android.apps.maps");
				startActivity(mapIntent);

			}
		});

	}

	@Override
	public void onMapReady(GoogleMap googleMap) {

		Intent intent = getIntent();
		KantorConstruct kantorConstruct = intent.getParcelableExtra("detailKantor");

		String lokasiKantor = kantorConstruct.getLokasiKantor();
		double Lat;
		double Lng;
		String[] result = lokasiKantor.split(",");
		Lat = Double.parseDouble(result[0]);
		Lng = Double.parseDouble(result[1]);

		float zoomLevel = 14.0f;
		LatLng kantor = new LatLng(Lat,Lng);
		googleMap.addMarker(new MarkerOptions().position(kantor).title("Lokasi Kantor"));
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kantor, zoomLevel));
	}
}
