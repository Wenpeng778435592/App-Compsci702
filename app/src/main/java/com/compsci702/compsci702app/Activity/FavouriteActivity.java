package com.compsci702.compsci702app.Activity;

import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.compsci702.compsci702app.Adapters.ExpandableListAdapter;
import com.compsci702.compsci702app.Data.Phrase;
import com.compsci702.compsci702app.R;
import com.compsci702.compsci702app.Tools.DBHelper;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;

public class FavouriteActivity extends AppCompatActivity {

    private ArrayList<Phrase> favouritedPhrases = new ArrayList<>();
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;

    private TextView noFavouritesText;

    private HashMap<String, ArrayList<String>> listChildData = new HashMap<>();
    private ArrayList<String> listHeaderData = new ArrayList<>();

    private DBHelper dbHelper;
//    private AdView adBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

//        adBannerView = findViewById(R.id.adBannerView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adBannerView.loadAd(adRequest);

        dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getFavouritePhrases();
        noFavouritesText = findViewById(R.id.noFavouritesText);

        while (cursor.moveToNext()){
            Phrase phrase = new Phrase();
            phrase.setPhrase(cursor.getString(cursor.getColumnIndex("Sentence")));
            phrase.setMeaning(cursor.getString(cursor.getColumnIndex("Meaning")));
            phrase.setExample(cursor.getString(cursor.getColumnIndex("Example")));
            phrase.setFavourited(cursor.getInt(cursor.getColumnIndex("Favourited")));
            favouritedPhrases.add(phrase);
        }

        expandableListView = findViewById(R.id.expandableListView);

        expandableListAdapter = new ExpandableListAdapter(this, listHeaderData, listChildData, "bin");
        prepareAdapterList();
        expandableListView.setAdapter(expandableListAdapter);

        if(favouritedPhrases.size() == 0){
            noFavouritesText.setVisibility(View.VISIBLE);
        }
    }

    private void prepareAdapterList(){
        for (int i = 0; i < favouritedPhrases.size(); i++){
            String phrase = favouritedPhrases.get(i).getPhrase() + "-" + favouritedPhrases.get(i).getFavourited();
            listHeaderData.add(phrase);

            ArrayList<String> definitionList = new ArrayList<>();
            definitionList.add(favouritedPhrases.get(i).getMeaning() +
                    "-" + favouritedPhrases.get(i).getExample());
            listChildData.put(phrase, definitionList);
        }
    }

    public void favouriteClicked(View view){
        int position = (int)view.getTag();
        Phrase phrase = favouritedPhrases.get(position);
        dbHelper.updateFavourited(phrase,0);

        listChildData.remove(position);
        listHeaderData.remove(position);
        favouritedPhrases.remove(position);
        expandableListAdapter.notifyDataSetChanged();

        if(favouritedPhrases.size() == 0){
            noFavouritesText.setVisibility(View.VISIBLE);
        }

        Toast toast;
        toast = Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void homeClicked(View view){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }
}
