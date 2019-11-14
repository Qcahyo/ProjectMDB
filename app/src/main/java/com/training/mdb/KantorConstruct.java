package com.training.mdb;

import android.os.Parcel;
import android.os.Parcelable;

public class KantorConstruct implements Parcelable {

	String namaKantor;
	String alamatKantor;
	String deskripsiKantor;
	String teleponKantor;
	String emailKantor;
	String lokasiKantor;
	String fotoKantor;

	public KantorConstruct(String namaKantor, String alamatKantor, String deskripsiKantor,
						   String teleponKantor, String emailKantor, String lokasiKantor,
						   String fotoKantor) {
		this.namaKantor = namaKantor;
		this.alamatKantor = alamatKantor;
		this.deskripsiKantor = deskripsiKantor;
		this.teleponKantor = teleponKantor;
		this.emailKantor = emailKantor;
		this.lokasiKantor = lokasiKantor;
		this.fotoKantor = fotoKantor;
	}

	protected KantorConstruct(Parcel in) {
		namaKantor = in.readString();
		alamatKantor = in.readString();
		deskripsiKantor = in.readString();
		teleponKantor = in.readString();
		emailKantor = in.readString();
		lokasiKantor = in.readString();
		fotoKantor = in.readString();
	}

	public static final Creator<KantorConstruct> CREATOR = new Creator<KantorConstruct>() {
		@Override
		public KantorConstruct createFromParcel(Parcel in) {
			return new KantorConstruct(in);
		}

		@Override
		public KantorConstruct[] newArray(int size) {
			return new KantorConstruct[size];
		}
	};

	public String getNamaKantor() {
		return namaKantor;
	}

	public String getAlamatKantor() {
		return alamatKantor;
	}

	public String getDeskripsiKantor() {
		return deskripsiKantor;
	}

	public String getTeleponKantor() {
		return teleponKantor;
	}

	public String getEmailKantor() {
		return emailKantor;
	}

	public String getLokasiKantor() {
		return lokasiKantor;
	}

	public String getFotoKantor() {
		return fotoKantor;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(namaKantor);
		dest.writeString(alamatKantor);
		dest.writeString(deskripsiKantor);
		dest.writeString(teleponKantor);
		dest.writeString(emailKantor);
		dest.writeString(lokasiKantor);
		dest.writeString(fotoKantor);
	}
}
