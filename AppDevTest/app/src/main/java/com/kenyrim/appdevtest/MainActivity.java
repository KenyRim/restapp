package com.kenyrim.appdevtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.kenyrim.appdevtest.fragments.ListFragmentA;
import com.kenyrim.appdevtest.fragments.ListFragmentB;
import com.kenyrim.appdevtest.listener.MyAsyncListener;
import com.kenyrim.appdevtest.model.Model;
import com.kenyrim.appdevtest.rest.JsonAsync;

import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;

    private ListFragmentA fragmentA = new ListFragmentA();
    private ListFragmentB fragmentB = new ListFragmentB();

    private FragmentManager manager;

    private ArrayList<Model> arrayListCat = new ArrayList<>();
    private ArrayList<Model> arrayListDog = new ArrayList<>();
    private int tabNum;

    private String[] animal = {"http://kot3.com/xim/api.php?query=cat",
            "http://kot3.com/xim/api.php?query=dog"};


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("LIST_CATS", arrayListCat);
        outState.putParcelableArrayList("LIST_DOGS", arrayListDog);
        outState.putInt("TAB_NUM", tabNum);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        arrayListCat = savedInstanceState.getParcelableArrayList("LIST_CATS");
        arrayListDog = savedInstanceState.getParcelableArrayList("LIST_DOGS");
        tabNum = savedInstanceState.getInt("TAB_NUM");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        manager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            getArrays(animal[0],arrayListCat);
            addListener();
        }else{
            new Handler().postDelayed(
                    new Runnable(){
                        @Override
                        public void run() {
                            tabLayout.getTabAt(tabNum).select();
                            addListener();
                        }
                    }, 100);

        }

    }

    private void getArrays(String api,final ArrayList<Model> myArray){
        new JsonAsync(api,new MyAsyncListener() {
            @Override
            public void onCompleted(ArrayList<Model> catsDogsArr) {

                myArray.addAll(catsDogsArr);

                if (arrayListDog.isEmpty()){
                    getArrays(animal[1],arrayListDog);
                }else{
                    Log.e("IMAGE_URL",arrayListCat.get(1).getTitle());

                    createFrag(arrayListCat,fragmentA, "FRAG_A");
                }

            }


        }).execute();
    }

    private void addListener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0){
                    tabNum = 0;
                    if (manager.findFragmentByTag ("FRAG_A") == null) {
                        createFrag(arrayListCat,fragmentA,"FRAG_A");
                    }else {
                        showFrag("FRAG_A","FRAG_B");
                    }
                }else{
                    tabNum = 1;
                    if (manager.findFragmentByTag ("FRAG_B") == null) {
                        createFrag(arrayListDog,fragmentB,"FRAG_B");
                    }else{
                        showFrag("FRAG_B","FRAG_A");
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void createFrag(final ArrayList<Model> arrayList,Fragment fragment,String tag){
        Bundle b = new Bundle();
        b.putParcelableArrayList("MY_ARRAY", arrayList);
        fragment.setArguments(b);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.listContainer, fragment, tag);
        transaction.commit();
    }

    private void showFrag(String tagShow,String tagHide){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(Objects.requireNonNull(manager.findFragmentByTag(tagHide)));
        transaction.show(Objects.requireNonNull(manager.findFragmentByTag(tagShow))).commit ();


    }


}
