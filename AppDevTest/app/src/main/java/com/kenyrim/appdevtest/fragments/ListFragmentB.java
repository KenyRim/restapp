package com.kenyrim.appdevtest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kenyrim.appdevtest.R;
import com.kenyrim.appdevtest.adapter.AdapterRecycler;
import com.kenyrim.appdevtest.model.Model;

import java.util.ArrayList;

public class ListFragmentB extends Fragment {

    private int scrollPos;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_list_layout, container, false);

        Bundle b = getArguments();
        assert b != null;
        ArrayList<Model> myArray = b.getParcelableArrayList("MY_ARRAY");


        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView,int dx,int dy) {
                super.onScrolled(recyclerView,dx,dy);
                scrollPos = dy;

            }
        });


        AdapterRecycler adapter = new AdapterRecycler(getActivity(),getContext(), myArray);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.scrollTo(0,scrollPos);


        return v;

    }
}


