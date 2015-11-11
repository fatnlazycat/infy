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
		content = new ArrayList<Contact>();
		Context context=MainActivity.context;
		for (int i=0;i<15;i++) {
			Contact c = new Contact();
			c.name="Name "+i;
			c.imSource="IMsource "+i;

			Random rand=new Random();
			switch (rand.nextInt(4)){
				case 0: c.picture=ContextCompat.getDrawable(context, R.drawable.logo); break;
				case 1: c.picture=ContextCompat.getDrawable(context, R.drawable.ic_launcher); break;
				case 2: c.picture=ContextCompat.getDrawable(context, R.drawable.btn_rating_star_off_disabled_focused_holo_dark); break;
				default: c.picture=ContextCompat.getDrawable(context, R.drawable.start_activity_background);					
			}
			content.add(c);
		}
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
