package com.example.saurabhpathak.tiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by saurabhpathak on 08/11/2016.
 */

public class ButtonAdapter extends BaseAdapter {
    ArrayList<Tile> tileList;
    Context context;

    private static LayoutInflater inflater=null;
    public ButtonAdapter(Context context, ArrayList<Tile> itemList) {
        this.tileList = itemList;
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tileList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.tile_layout, null);
        final Button tile = (Button) rowView.findViewById(R.id.tileBtn);
        tile.setText(tileList.get(position).getVisibleValueValue());

        // event handler when a tile is clicked
        tile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAnimations.rotateAnimation(tile);
                tile.setText(tileList.get(position).getValue());
            }
        });

        return rowView;
    }
}
