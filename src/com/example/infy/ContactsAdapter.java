package com.example.infy;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ContactsAdapter extends BaseAdapter {
	protected static ArrayList<Contact> content;
	boolean isBuilt;
	
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
		//Context context=parent.getContext();
		LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent.getContext());
		if (convertView==null){
			convertView=inflater.inflate(R.layout.contacts_item, parent, false);
		}
		
		/*here we can set the background
		 * LinearLayout layout=(LinearLayout)convertView.findViewById(R.id.contactsLayout);
		layout.setBackground(content.get(position).picture);*/
		
		TextView firstLetter=(TextView)convertView.findViewById(R.id.firstLetter);
		//firstLetter.setText(content.get(position).name.charAt(0));
		ImageView smallContactPicture=(ImageView)convertView.findViewById(R.id.smallContactPicture);
		smallContactPicture.setImageResource(content.get(position).drawableId);
		TextView contactName=(TextView)convertView.findViewById(R.id.contactName);
		contactName.setText(content.get(position).name);
		
		
		return convertView;
	}

}
