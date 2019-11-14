package com.training.mdb;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class KantorAdapter extends RecyclerView.Adapter<KantorAdapter.KantorAdapterViewHolder> {

	private ArrayList<KantorConstruct> mKantor;

	public static class KantorAdapterViewHolder extends RecyclerView.ViewHolder {

		CardView cvDetailKantor;
		TextView tvNamaKantor;
		TextView tvAlamatKantor;
		CircleImageView imgKantor;

		public KantorAdapterViewHolder(@NonNull View itemView) {
			super(itemView);

			cvDetailKantor = itemView.findViewById(R.id.cvDetailKantor);
			tvNamaKantor = itemView.findViewById(R.id.tvNamaKantor);
			tvAlamatKantor = itemView.findViewById(R.id.tvAlamatKantor);
			imgKantor = itemView.findViewById(R.id.img_kantor);
		}
	}

	public KantorAdapter(ArrayList<KantorConstruct> kantorList){
		mKantor = kantorList;
	}
	@NonNull
	@Override
	public KantorAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kantor, null);
		KantorAdapterViewHolder kavh = new KantorAdapterViewHolder(view);
		return kavh;
	}

	@Override
	public void onBindViewHolder(@NonNull final KantorAdapterViewHolder holder, int position) {

		final KantorConstruct kantorConstruct = mKantor.get(position);
		Picasso.with(holder.itemView.getContext()).load(kantorConstruct.getFotoKantor()).into(holder.imgKantor);
		holder.tvNamaKantor.setText(kantorConstruct.getNamaKantor());
		holder.tvAlamatKantor.setText(kantorConstruct.getAlamatKantor());

		holder.cvDetailKantor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), DetailKantorActivity.class);
				intent.putExtra("detailKantor", kantorConstruct);
				v.getContext().startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mKantor.size();
	}
}
