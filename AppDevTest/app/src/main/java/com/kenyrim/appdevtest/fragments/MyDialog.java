package com.kenyrim.appdevtest.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.kenyrim.appdevtest.R;
import com.squareup.picasso.Picasso;


public class MyDialog extends DialogFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.AppTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_description,null);


        Bundle b = getArguments();
        assert b != null;
        String title = b.getString("FULL_DESC");
        String imageUrl = b.getString("FULL_IMAGE");

        TextView tv_full = view.findViewById(R.id.tv_full);
        ImageView iv_full = view.findViewById(R.id.iv_full);

        tv_full.setText(title);
        Picasso.with(getContext())
                .load(imageUrl)
                .into(iv_full);

        ImageView btn_back = view.findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

            }
        });

        return view;
    }


}