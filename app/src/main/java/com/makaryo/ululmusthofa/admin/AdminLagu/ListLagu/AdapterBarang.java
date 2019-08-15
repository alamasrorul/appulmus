package com.makaryo.ululmusthofa.admin.AdminLagu.ListLagu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.makaryo.ululmusthofa.R;
import com.makaryo.ululmusthofa.admin.AdminLagu.ListLagu.tampilan.Main2Activity;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


/**
 * Created by Hafizh Herdi on 10/8/2017.
 */

class AdapterBarangRecyclerView extends RecyclerView.Adapter<AdapterBarangRecyclerView.ViewHolder> {

    private ArrayList<PojoLagu> daftarBarang;
    private Context context;

    private DatabaseReference database;
    public AdapterBarangRecyclerView(ArrayList<PojoLagu> barangs, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */

        daftarBarang = barangs;
        context = ctx;

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namabarang);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //------------------ on klik -------------------------//

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */

        final String name = daftarBarang.get(position).getJudulLagu();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(Main2Activity.getActIntent((Activity) context).putExtra("date", daftarBarang.get(position)));

            }
        });
        /*holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();

                                context.startActivity(EditListLagu.getActIntent((Activity) context).putExtra("date", daftarBarang.get(position)));
                            }
                        }
                );

                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {



                                dialog.dismiss();
                               // int position = getAdapterPosition();

                                }
                        }
                );
                return true;
            }
        });*/
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarBarang.size();
    }



}