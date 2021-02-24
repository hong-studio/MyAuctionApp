package com.hong_studio.myauctionapp.Tab4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.hong_studio.myauctionapp.Activities.MainActivity;
import com.hong_studio.myauctionapp.G;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.Tab1.Tab1Fragment;
import com.kakao.sdk.user.UserApiClient;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Tab4Fragment extends Fragment {

    RelativeLayout layoutProfile;
    CircleImageView ivProfile;
    TextView tvNickname;
    TextView tvBankAccount, tvSetting, tvCustomerCenter, tvLogOut, tvMemberOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivProfile= view.findViewById(R.id.iv_profile);
        tvNickname= view.findViewById(R.id.tv_nickname);
        layoutProfile= view.findViewById(R.id.layout_profile);
        setLayoutProfile(view);

        tvBankAccount = view.findViewById(R.id.tv01_member);
        tvSetting = view.findViewById(R.id.tv02_member);
        tvCustomerCenter = view.findViewById(R.id.tv03_member);
        tvLogOut = view.findViewById(R.id.tv04_member);
        tvMemberOut = view.findViewById(R.id.tv05_member);

        tvBankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), BankAccountActivity.class);
                startActivity(intent);
            }
        });

        tvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        tvCustomerCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), CustomerCenterActivity.class);
                startActivity(intent);
            }
        });

        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setMessage("로그아웃 하시겠습니까?").setNegativeButton("취소", null).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //로그아웃 요청
                        UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                            @Override
                            public Unit invoke(Throwable throwable) {
                                if(throwable!=null){
                                    Toast.makeText(getActivity(), "로그아웃 실패", Toast.LENGTH_SHORT).show();
                                }else {
                                    //회원정보 모두 초기화
                                    G.profileImgUrl= null;
                                    G.memberName= null;
                                    Toast.makeText(getActivity(), "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
                                }
                                return null;
                            }
                        });
                    }
                }).create().show();
            }
        });

        tvMemberOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), MemberOutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setLayoutProfile(@NonNull View view) {
        if(G.memberName!=null){
            Glide.with(getActivity()).load(G.profileImgUrl).into(ivProfile);
            tvNickname.setText(G.memberName);
        }
        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), MyProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
