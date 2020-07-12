package com.compsci702.compsci702app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.compsci702.compsci702app.R;

import java.util.ArrayList;
import java.util.HashMap;


import android.widget.BaseExpandableListAdapter;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<String>> _listDataChild;

    private String buttonImage;

    public ExpandableListAdapter(Context context, ArrayList<String> listDataHeader,
                                 HashMap<String, ArrayList<String>> listChildData, String buttonImage) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.buttonImage = buttonImage;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        String childText =  (String) getChild(groupPosition, childPosition);
        String[] textArray = childText.split("-");

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_list_child, null);
        }

        TextView exampleText = convertView.findViewById(R.id.exampleText);
        TextView definitionText = convertView.findViewById(R.id.definitionText);

        exampleText.setText(textArray[1]);
        definitionText.setText(textArray[0]);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        System.out.println("list child " + _listDataHeader.get(groupPosition));
        return this._listDataChild.get(_listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        String[] headerArray = headerTitle.split("-");
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_list_parent, null);
        }

        TextView headerText = convertView.findViewById(R.id.phraseText);
        headerText.setText(headerArray[0]);

        ImageButton favouriteButton = convertView.findViewById(R.id.favouritedButton);
        favouriteButton.setFocusable(false);
        favouriteButton.setTag(groupPosition);

        if(buttonImage.equals("heart")){
            if(headerArray[1].equals("0")){
                favouriteButton.setImageResource(R.drawable.heart_empty);
            }else{
                favouriteButton.setImageResource(R.drawable.heart_full);
            }
        }else{
            favouriteButton.setImageResource(R.drawable.bin);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
