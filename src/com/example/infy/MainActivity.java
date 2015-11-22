package com.example.infy;

import java.util.ArrayList;

import android.app.Activity;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static Context context;
	GridView gridViewFavourites;
	GridView gridViewFavouritesReplica;
	TabHost tabs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=this;
		setContentView(R.layout.activity_main);
		
		//initializing Contacts
		ArrayList<Contact> myContacts=ContactsFactory.makeContacts();
		
		//initializing the search activity
		SearchManager searchManager=(SearchManager)getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView=(SearchView)findViewById(R.id.searchView);
		AutoCompleteTextView searchViewTextPart=(AutoCompleteTextView)searchView.findViewById
				(searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null));
		searchViewTextPart.setTextSize(12);
	    // Assumes current activity is the searchable activity
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchableActivity.class)));
	    searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

	    //initializing tabs
	    tabs=(TabHost)findViewById(android.R.id.tabhost);
	    tabs.setup();
		
	    setupTab(R.id.tab1, getString(R.string.favourites));
	    setupTab(R.id.tab2, getString(R.string.messages));
	    setupTab(R.id.tab3, getString(R.string.contacts));
	    
	    tabs.setCurrentTab(0);
	    
	    //initiating & populating the favourites grid view (& replica too)
	    gridViewFavourites=(GridView)findViewById(R.id.gridViewFavourites);
	    gridViewFavouritesReplica=(GridView)findViewById(R.id.gridViewFavouritesReplica);
	    	//here we create initial content for both grid view & replica
	    ImageAdapter.makeContent();
	    ReplicaFavouritesImageAdapter.content=new ArrayList<Contact>();
	    ReplicaFavouritesImageAdapter.content.add(ImageAdapter.content.get(0));
	    ReplicaFavouritesImageAdapter.content.add(ImageAdapter.content.get(1));
	    gridViewFavourites.setAdapter(new ImageAdapter());
	    gridViewFavouritesReplica.setAdapter(new ReplicaFavouritesImageAdapter());

	    registerForContextMenu(gridViewFavourites);	    
	    gridViewFavourites.setOnScrollListener(new AbsListView.OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				int pos=firstVisibleItem+visibleItemCount-1;
				if (!(pos>=1 && pos<=totalItemCount)) pos=1;
			    ReplicaFavouritesImageAdapter.content.set(0, ImageAdapter.content.get(pos-1));
			    ReplicaFavouritesImageAdapter.content.set(1, ImageAdapter.content.get(pos));
			    ((BaseAdapter) gridViewFavouritesReplica.getAdapter()).notifyDataSetChanged();
			}
		});
	}

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.favourites_context_menu, menu);
	}
	
	public void showFavouritesContextMenu(View v){
	    this.openContextMenu(v);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()){
		case R.id.itemUnfave: {
			ImageAdapter.content.remove(info.position);
			((BaseAdapter) gridViewFavourites.getAdapter()).notifyDataSetChanged();
			return true;
		}
		case R.id.itemSendMessage: {
			tabs.setCurrentTab(1);
			return true;
		}
		}
		return false;
	}
	
	private void setupTab(final int contentViewId, String tag){
		LayoutInflater inflater= (LayoutInflater)LayoutInflater.from(tabs.getContext());
		View customTab=inflater.inflate(R.layout.layout_custom_tabs, null, false);
		TextView textView=(TextView)customTab.findViewById(R.id.oneOfCustomTabs);
		textView.setText(tag);
		
		TabSpec tabBuilder=tabs.newTabSpec(tag);
		tabBuilder.setIndicator(customTab);
		tabBuilder.setContent(new TabHost.TabContentFactory(){
			public View createTabContent(String tag) {return findViewById(contentViewId);}
		});
		tabs.addTab(tabBuilder);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
	    if (event.getAction() == MotionEvent.ACTION_DOWN) {
	        View v = getCurrentFocus();
	        if ( v instanceof EditText) {
	            Rect outRect = new Rect();
	            v.getGlobalVisibleRect(outRect);
	            if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
	                v.clearFocus();
	                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	            }
	        }
	    }
	    return super.dispatchTouchEvent( event );
	}
	
	public void showSearchPopup(View v) {
	    PopupMenu popup = new PopupMenu(this, v);
	    MenuInflater inflater = popup.getMenuInflater();
	    inflater.inflate(R.menu.search_popup_menu, popup.getMenu());
	    popup.show();
	}
	
	public boolean onMenuItemClick(MenuItem item) {
	    	
	        return false;
	    }

}

