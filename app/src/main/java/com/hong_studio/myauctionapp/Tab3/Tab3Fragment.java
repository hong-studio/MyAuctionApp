package com.hong_studio.myauctionapp.Tab3;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.MaterialToolbar;
import com.hong_studio.myauctionapp.MainActivity;
import com.hong_studio.myauctionapp.R;

import static androidx.core.content.ContextCompat.getSystemService;

public class Tab3Fragment extends Fragment {

    MaterialToolbar toolbar;
    NestedScrollView scrollView;
    EditText etMsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //여기서 xml의 뷰들에 대한 find 참조.
        toolbar= view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        etMsg= view.findViewById(R.id.et_msg);
        scrollView= view.findViewById(R.id.scrollView);
        etMsgTouch();
    }

    private void etMsgTouch() {
        etMsg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    scrollView.smoothScrollTo(0, scrollView.getScrollY() + etMsg.getHeight());
                }
                return false;
            }
        });
    }
}
