package com.example.saurabhpathak.tiles;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

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

        if (position == tileList.size() - 1) {
            tile.setVisibility(View.GONE);
        } else {
            tile.setText(tileList.get(position).getVisibleValue());

            // event handler when a tile is clicked
            tile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomAnimations.rotateAnimation(tile);
                    tile.setText(tileList.get(position).getValue());
                    Toast.makeText(context, tileList.get(position).getStatus().toString(), Toast.LENGTH_SHORT);

                    // if this is the first click case
                    if(tileList.get(tileList.size() - 1).getValue() == null) {
                        tileList.set(tileList.size() - 1, tileList.get(position));
                        tileList.get(tileList.size() - 1).setVisibleValue(String.valueOf(position));
                    }
                    // if this is the second click case
                    else {
                        // if the two clicked tiles match
                        if (tileList.get(tileList.size() - 1).getValue() == tile.getText()) {
                            tileList.get(position).setStatus(Tile.Status.unlocked);
                            int pos = Integer.parseInt(tileList.get(tileList.size() - 1).getVisibleValue());
                            tileList.get(pos).setStatus(Tile.Status.unlocked);
                            tileList.set(tileList.size() - 1, new Tile(Tile.Status.locked, null, null));
                        }
                        // if the two clicked tiles do not match
                        else {
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tile.setText("PATHAK");
                                    CustomAnimations.rotateAnimation(tile);
                                }
                            }, 500);
                            tileList.set(tileList.size() - 1, new Tile(Tile.Status.locked, null, null));
                        }
                    }
                }
            });
        }

        return rowView;
    }
}
