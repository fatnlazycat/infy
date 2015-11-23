package com.example.infy;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

public class SearchableActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable);
		Intent intent=getIntent();
		if (intent.ACTION_SEARCH.equals(intent.getAction())){
			String query=intent.getStringExtra(SearchManager.QUERY);
			for (int i=0; i<FavouritesAdapter.content.size(); i++){
				
			}
		}
	}
}
