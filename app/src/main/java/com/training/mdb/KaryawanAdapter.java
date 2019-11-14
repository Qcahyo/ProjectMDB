package com.training.mdb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class KaryawanAdapter extends RecyclerView.Adapter<KaryawanAdapter.KaryawanAdapterViewHolder> {

	private ArrayList<KaryawanConstruct> mKaryawan;

	public static class KaryawanAdapterViewHolder extends RecyclerView.ViewHolder {

		CardView cvDetail;
		TextView tvNama;
		TextView tvAlamat;
		CircleImageView img_employee;

		public KaryawanAdapterViewHolder(@NonNull View itemView) {
			super(itemView);

			tvNama = itemView.findViewById(R.id.tvNamaList);
			tvAlamat = itemView.findViewById(R.id.tvAlamatList);
			img_employee = itemView.findViewById(R.id.img_employee);
			cvDetail = itemView.findViewById(R.id.cvDetail);

		}
	}

	public KaryawanAdapter(ArrayList<KaryawanConstruct> mKaryawan2){
		mKaryawan = mKaryawan2;
	}

	@NonNull
	@Override
	public KaryawanAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_karyawan, null);
		KaryawanAdapterViewHolder kavh = new KaryawanAdapterViewHolder(view);
		return kavh;
	}

	@Override
	public void onBindViewHolder(@NonNull final KaryawanAdapterViewHolder holder, int position) {

		final KaryawanConstruct karyawanConstruct = mKaryawan.get(position);

		Picasso.with(holder.itemView.getContext()).load(karyawanConstruct.fotoKaryawan).into(holder.img_employee);
		holder.tvNama.setText(karyawanConstruct.getNamaKaryawan());
		holder.tvAlamat.setText(karyawanConstruct.getAlamat());

		holder.img_employee.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//holder.itemView.getContext().startActivity(intent);
			}
		});

		holder.cvDetail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(holder.itemView.getContext(), DetailKaryawanActivity.class);
				intent.putExtra("detailKaryawan",karyawanConstruct);
				v.getContext().startActivity(intent);
			}
		});

	}

	@Override
	public int getItemCount() {
		return mKaryawan.size();
	}

}
