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

import com.bumptech.glide.Glide;
import com.hong_studio.myauctionapp.R;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class Tab5Fragment extends Fragment {

    LinearLayout layoutProfile;
    CircleImageView ivProfile;
    TextView tvNickname;
    TextView tv01Member, tv02Member, tv03Member, tv04Member, tv05Member;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setProfileAndNickname(view);

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

    private void setProfileAndNickname(@NonNull View view) {
        ivProfile= view.findViewById(R.id.iv_profile);
        tvNickname= view.findViewById(R.id.tv_nickname);
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if(user!=null){
                    String nickname= user.getKakaoAccount().getProfile().getNickname();
                    String profileImgUrl= user.getKakaoAccount().getProfile().getThumbnailImageUrl();
                    tvNickname.setText(nickname);
                    Glide.with(getActivity()).load(profileImgUrl).into(ivProfile);
                }
                return null;
            }
        });
        layoutProfile= view.findViewById(R.id.layout_profile);
        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), MyProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
