package com.training.mdb;

import android.os.Parcel;
import android.os.Parcelable;

public class KaryawanConstruct implements Parcelable {

	String namaKaryawan;
	String NIK;
	String Alamat;
	String Gender;
	String fotoKaryawan;
	String tempatLahir;
	String tanggalLahir;
	String golonganDarah;
	String Agama;
	String statusPerkawinan;
	String Kewarganegaraan;
	String berlakuHingga;
	String tampatBuat;
	String tanggalBuat;

	public KaryawanConstruct(String namaKaryawan, String NIK, String alamat, String gender,
							 String fotoKaryawan, String tempatLahir, String tanggalLahir,
							 String golonganDarah, String agama, String statusPerkawinan,
							 String kewarganegaraan, String berlakuHingga, String tampatBuat,
							 String tanggalBuat) {

		this.namaKaryawan = namaKaryawan;
		this.NIK = NIK;
		Alamat = alamat;
		Gender = gender;
		this.fotoKaryawan = fotoKaryawan;
		this.tempatLahir = tempatLahir;
		this.tanggalLahir = tanggalLahir;
		this.golonganDarah = golonganDarah;
		Agama = agama;
		this.statusPerkawinan = statusPerkawinan;
		Kewarganegaraan = kewarganegaraan;
		this.berlakuHingga = berlakuHingga;
		this.tampatBuat = tampatBuat;
		this.tanggalBuat = tanggalBuat;
	}

	protected KaryawanConstruct(Parcel in) {
		namaKaryawan = in.readString();
		NIK = in.readString();
		Alamat = in.readString();
		Gender = in.readString();
		fotoKaryawan = in.readString();
		tempatLahir = in.readString();
		tanggalLahir = in.readString();
		golonganDarah = in.readString();
		Agama = in.readString();
		statusPerkawinan = in.readString();
		Kewarganegaraan = in.readString();
		berlakuHingga = in.readString();
		tampatBuat = in.readString();
		tanggalBuat = in.readString();
	}

	public static final Creator<KaryawanConstruct> CREATOR = new Creator<KaryawanConstruct>() {
		@Override
		public KaryawanConstruct createFromParcel(Parcel in) {
			return new KaryawanConstruct(in);
		}

		@Override
		public KaryawanConstruct[] newArray(int size) {
			return new KaryawanConstruct[size];
		}
	};

	public String getNamaKaryawan() {
		return namaKaryawan;
	}

	public String getNIK() {
		return NIK;
	}

	public String getAlamat() {
		return Alamat;
	}

	public String getGender() {
		return Gender;
	}

	public String getFotoKaryawan() {
		return fotoKaryawan;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public String getTanggalLahir() {
		return tanggalLahir;
	}

	public String getGolonganDarah() {
		return golonganDarah;
	}

	public String getAgama() {
		return Agama;
	}

	public String getStatusPerkawinan() {
		return statusPerkawinan;
	}

	public String getKewarganegaraan() {
		return Kewarganegaraan;
	}

	public String getBerlakuHingga() {
		return berlakuHingga;
	}

	public String getTampatBuat() {
		return tampatBuat;
	}

	public String getTanggalBuat() {
		return tanggalBuat;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(namaKaryawan);
		dest.writeString(NIK);
		dest.writeString(Alamat);
		dest.writeString(Gender);
		dest.writeString(fotoKaryawan);
		dest.writeString(tempatLahir);
		dest.writeString(tanggalLahir);
		dest.writeString(golonganDarah);
		dest.writeString(Agama);
		dest.writeString(statusPerkawinan);
		dest.writeString(Kewarganegaraan);
		dest.writeString(berlakuHingga);
		dest.writeString(tampatBuat);
		dest.writeString(tanggalBuat);
	}
}
