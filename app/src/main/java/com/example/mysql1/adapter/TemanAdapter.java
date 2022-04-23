package com.example.mysql1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysql1.MainActivity;
import com.example.mysql1.R;
import com.example.mysql1.database.Teman;
import com.example.mysql1.EditTeman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context control;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman, parent, false);
        control = parent.getContext();
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TemanAdapter.TemanViewHolder holder, int position) {
        String nm,tlp;

        //id = listData.get(position).getId();
        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelpon();
        //BDController db = new BDController(control);

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setText(nm);
        holder.namaTxt.setTextSize(30);
        holder.telponTxt.setText(tlp);

        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(control, holder.cardku);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()){
//                            case R.id.mnEdit:
//                                Intent i = new Intent(control, EditTeman.class);
//                                i.putExtra("id",id);
//                                i.putExtra("nama",nm);
//                                i.putExtra("telpon",tlp);
//                                control.startActivity(i);
//                                break;
//
//                            case R.id.mnHapus:
//                                HashMap<String,String> values = new HashMap<>();
//                                values.put("id",id);
//                                db.DeleteData(values);
//                                Intent j = new Intent(control, MainActivity.class);
//                                control.startActivity(j);
//                                break;
//
//                        }
                        return true;
                    }

                });
//                popupMenu.show();
//                return;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt, telponTxt;

        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);
        }
    }
}
