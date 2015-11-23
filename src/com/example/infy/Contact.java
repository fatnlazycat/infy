package com.example.infy;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class Contact {
	String name;
	String imSource;
	Drawable picture;
	int drawableId;
}

class ContactsFactory {
	
	static ArrayList<Contact> makeContacts(){
		Context context=MainActivity.context;
		ArrayList<Contact> myContacts=new ArrayList<Contact>();
		int len=15; //we'll make ArrayList<Contact> of 15 persons
		String[] pictures={"anderson","blackmore","dylan","harrison","jagger","lennon","manzarek","mccartney",
				"mercury","morrison","plant","starkey","vicious","waits","zappa"};
		String[] names={"Ian Anderson", "Ritchie Blackmore", "Bob Dylan", "George Harrison", "Mick Jagger",
				"John Lennon", "Ray Manzarek", "Paul McCartney", "Freddie Mercury", "Jim Morrison", "Robert Plant",
				"Ringo Starr", "Sid Vicious", "Tom Waits", "Frank Zappa"};
		for (int i=0; i<len; i++){
			Contact c=new Contact();
			c.name=names[i];
			c.drawableId=context.getResources().getIdentifier(pictures[i],"drawable",
					context.getPackageName());
			c.picture=ContextCompat.getDrawable(context, c.drawableId);
			c.imSource="IMsource "+i;
			myContacts.add(c);
		}
		return myContacts;
	}
	
}