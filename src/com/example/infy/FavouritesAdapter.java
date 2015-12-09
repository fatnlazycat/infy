package com.example.infy;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class FavouritesAdapter extends BaseAdapter {
	protected static ArrayList<Contact> content;
	boolean isBuilt;
	
	public static void makeContent(){
		content=new ArrayList<Contact>();
		Contact c;
		int size=ContactsAdapter.content.size();
		for (int i=0; i<size; i++){
			c=ContactsAdapter.content.get(i);
			if (c.inFavourites) content.add(c);
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
