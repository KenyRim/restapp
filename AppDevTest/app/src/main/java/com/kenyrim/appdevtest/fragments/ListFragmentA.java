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

public class ListFragmentA extends Fragment {

    private int scrollPos;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scrollPos", scrollPos);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            scrollPos = savedInstanceState.getInt("scrollPos");
        }

    }


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


        if (savedInstanceState != null){
            recyclerView.scrollTo(0,scrollPos);
        }

        return v;

    }
}


