package com.example.infy;

import java.util.ArrayList;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

public class ReplicaFavouritesImageAdapter extends BaseAdapter {
	protected static ArrayList<Contact> content;

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public Object getItem(int position) {
		return content.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent.getContext());
		if (convertView==null){
			convertView=inflater.inflate(R.layout.favourites_item_replica, parent, false);
		}
		RelativeLayout layout=(RelativeLayout)convertView.findViewById(R.id.favourites_replica_layout);
		
		layout.setBackground(content.get(position).picture);		
		return convertView;
	}

}
