package com.example.clothual.UI.welcome.LogoFragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.clothual.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogoFragment extends Fragment {
    Handler handler = new Handler();

    public LogoFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment logo_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogoFragment newInstance() {
        LogoFragment fragment = new LogoFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logo, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Navigation.findNavController(requireView()).navigate(R.id.action_logoFragment_to_loginFragment);
            }
        };

        handler.*/
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Navigation.findNavController(requireView()).navigate(R.id.action_logoFragment_to_loginFragment);
            }
        }, 500);
    }

}