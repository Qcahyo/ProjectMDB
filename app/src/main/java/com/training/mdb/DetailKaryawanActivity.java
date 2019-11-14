package com.training.mdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailKaryawanActivity extends AppCompatActivity {

	private static final String TAG = "DetailKaryawanActivity";

	TextView tvDetailNama, tvDetailNik, tvDetailAlamat, tvDetailGender;
	CircleImageView imgFotoProfil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_karyawan);

		tvDetailNama = findViewById(R.id.tvDetailNama);
		tvDetailNik = findViewById(R.id.tvDetailNik);
		tvDetailAlamat = findViewById(R.id.tvDetailAlamat);
		tvDetailGender = findViewById(R.id.tvDetailGender);
		imgFotoProfil = findViewById(R.id.imgFotoProfil);

		Intent intent = getIntent();
		KaryawanConstruct karyawanConstruct = intent.getParcelableExtra("detailKaryawan");

		String namaKaryawan = karyawanConstruct.getNamaKaryawan();
		String NIK = karyawanConstruct.getNIK();
		String Alamat = karyawanConstruct.getAlamat();
		String Gender = karyawanConstruct.getGender();
		String fotoKaryawan = karyawanConstruct.getFotoKaryawan();
		String tempatLahir = karyawanConstruct.getTempatLahir();
		String tanggalLahir = karyawanConstruct.getTanggalLahir();
		String golonganDarah = karyawanConstruct.getGolonganDarah();
		String Agama = karyawanConstruct.getAgama();
		String statusPerkawinan = karyawanConstruct.getStatusPerkawinan();
		String Kewarganegaraan = karyawanConstruct.getKewarganegaraan();
		String berlakuHingga = karyawanConstruct.getBerlakuHingga();
		String tampatBuat = karyawanConstruct.getTampatBuat();
		String tanggalBuat = karyawanConstruct.getTanggalBuat();

		tvDetailNama.setText(namaKaryawan);
		tvDetailNik.setText(NIK);
		tvDetailAlamat.setText(Alamat);
		tvDetailGender.setText(Gender);
		Picasso.with(this).load(fotoKaryawan).into(imgFotoProfil);

	}
}
