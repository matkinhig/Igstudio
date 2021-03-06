package com.example.googleservicefoody.Controller;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleservicefoody.Adapter.AdapterRecycleDiaDiem;
import com.example.googleservicefoody.Controller.Interfaces.DiaDiemInterface;
import com.example.googleservicefoody.Model.QuanAnModel;
import com.example.googleservicefoody.R;

import java.util.ArrayList;

public class DiaDiemController {
    ArrayList<QuanAnModel> arrQuanAn;
    AdapterRecycleDiaDiem adapterRecycleDiaDiem;
    Context context;
    QuanAnModel quanAnModel;

    public DiaDiemController(Context context) {
        this.context = context;
        quanAnModel = new QuanAnModel(); //contructor
    }

    public void getDanhSachQuanAncontroller(RecyclerView recyclerViewDiaDiem, final ProgressBar p, Location vitrihientai) {
        arrQuanAn = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewDiaDiem.setLayoutManager(layoutManager);
        adapterRecycleDiaDiem = new AdapterRecycleDiaDiem(arrQuanAn, R.layout.layout_custom_recycleview_diadiem);
        recyclerViewDiaDiem.setAdapter(adapterRecycleDiaDiem);
        DiaDiemInterface diaDiemInterface = new DiaDiemInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                arrQuanAn.add(quanAnModel);
                adapterRecycleDiaDiem.notifyDataSetChanged();
                p.setVisibility(View.GONE);
            }
        };
        quanAnModel.getDanhSachQuanAn(diaDiemInterface,vitrihientai);
    }
}
