package com.hong_studio.myauctionapp.Tab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hong_studio.myauctionapp.R;

public class Tab5Fragment extends Fragment {

    LinearLayout layoutProfile;
    TextView tv01Member, tv02Member, tv03Member, tv04Member, tv05Member;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //여기서 xml의 뷰들에 대한 find 참조.
        layoutProfile= view.findViewById(R.id.layout_profile);
        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), MyProfileActivity.class);
                startActivity(intent);
            }
        });

        tv01Member= view.findViewById(R.id.tv01_member);
        tv01Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), BankAccountActivity.class);
                startActivity(intent);
            }
        });

        tv02Member= view.findViewById(R.id.tv02_member);
        tv02Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        tv03Member= view.findViewById(R.id.tv03_member);
        tv03Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), CustomerCenterActivity.class);
                startActivity(intent);
            }
        });

        tv04Member= view.findViewById(R.id.tv04_member);
        tv04Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃하시겠습니까? 취소, 확인 다이얼로그
            }
        });

        tv05Member= view.findViewById(R.id.tv05_member);
        tv05Member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), MemberOutActivity.class);
                startActivity(intent);
            }
        });
    }
}
