package com.example.infy;
import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ImageAdapter extends BaseAdapter {
	protected static ArrayList<Contact> content;
	
	public static void makeContent(){
		content = ContactsFactory.makeContacts();
	}

	@Override
	public int getCount() {
		return content.size();
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
			convertView=inflater.inflate(R.layout.favourites_item, parent, false);
		}
		
		RelativeLayout layout=(RelativeLayout)convertView.findViewById(R.id.favourites_view_layout);
		layout.setBackground(content.get(position).picture);
		TextView favouritesName=(TextView)convertView.findViewById(R.id.favourites_name);
		favouritesName.setText(content.get(position).name);
		TextView favouritesIM=(TextView)convertView.findViewById(R.id.favourites_im);
		favouritesIM.setText(content.get(position).imSource);
		
		return convertView;
	}

}
