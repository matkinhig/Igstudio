package com.example.googleservicefoody.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.googleservicefoody.Controller.Interfaces.DiaDiemInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuanAnModel implements Serializable {

    private boolean giaohang;
    private String giodongcua, giomocua, tenquanan, videogioithieu, maquanan;
    private ArrayList<String> tienich;


    private ArrayList<String> hinhAnhQuanAn;

    public QuanAnModel(boolean giaohang, String giodongcua, String giomocua, String tenquanan, String videogioithieu, String maquanan, ArrayList<String> tienich, ArrayList<String> hinhAnhQuanAn, long luotthich, DatabaseReference nodeRoot) {
        this.giaohang = giaohang;
        this.giodongcua = giodongcua;
        this.giomocua = giomocua;
        this.tenquanan = tenquanan;
        this.videogioithieu = videogioithieu;
        this.maquanan = maquanan;
        this.tienich = tienich;
        this.hinhAnhQuanAn = hinhAnhQuanAn;
        this.luotthich = luotthich;
    }

    private long luotthich;

    DatabaseReference nodeRoot;


    public ArrayList<String> getHinhAnhQuanAn() {
        return hinhAnhQuanAn;
    }

    public void setHinhAnhQuanAn(ArrayList<String> hinhAnhQuanAn) {
        this.hinhAnhQuanAn = hinhAnhQuanAn;
    }
    @Override
    public String toString() {
        return "QuanAnModel{" +
                "giaohang=" + giaohang +
                ", giodongcua='" + giodongcua + '\'' +
                ", giomocua='" + giomocua + '\'' +
                ", tenquanan='" + tenquanan + '\'' +
                ", videogioithieu='" + videogioithieu + '\'' +
                ", maquanan='" + maquanan + '\'' +
                ", tienich=" + tienich +
                ", luotthich=" + luotthich +
                '}';
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public QuanAnModel() {
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }

    public ArrayList<String> getTienich() {
        return tienich;
    }

    public void setTienich(ArrayList<String> tienich) {
        this.tienich = tienich;
    }

    public void getDanhSachQuanAn(final DiaDiemInterface diaDiemInterface) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataQuanAn = dataSnapshot.child("quanans");
                for (DataSnapshot d : dataQuanAn.getChildren()) {
                    QuanAnModel quanAnModel = d.getValue(QuanAnModel.class);
                    quanAnModel.setMaquanan(d.getKey());
                    diaDiemInterface.getDanhSachQuanAnModel(quanAnModel);
                    DataSnapshot dataHinhAnhQuanAn = dataSnapshot.child("hinhanhquanans").child(d.getKey() );
                    ArrayList<String> hinhAnhList = new ArrayList<>();
                    for (DataSnapshot m : dataHinhAnhQuanAn.getChildren()){
                        hinhAnhList.add(m.getValue().toString());
                    }
                    quanAnModel.setHinhAnhQuanAn(hinhAnhList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }
}
