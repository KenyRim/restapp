package com.kenyrim.appdevtest.listener;

import com.kenyrim.appdevtest.model.Model;

import java.util.ArrayList;

public interface MyAsyncListener {
    void onCompleted(ArrayList<Model> catsDogs);

}