package com.example.clothual.UI.core.PersonalFragment;

import static com.example.clothual.Util.Constant.ACCESS_PREFERENCE;
import static com.example.clothual.Util.Constant.CREDENTIALS_LOGIN_FILE;
import static com.example.clothual.Util.Constant.URI;
import static com.example.clothual.Util.Constant.USERNAME_PREFERENCE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.clothual.R;
import com.example.clothual.UI.welcome.WelcomeActivity;
import com.example.clothual.databinding.FragmentPersonalBinding;

import java.io.FileNotFoundException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {

    private FragmentPersonalBinding binding;
    private PersonalModel personalModel;

    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personalModel = new PersonalModel(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonalBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(CREDENTIALS_LOGIN_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String username = sharedPref.getString(USERNAME_PREFERENCE, "");
        String nameSurname = personalModel.getName(username);
        binding.textName.setText(nameSurname);

        if(sharedPref.getString(URI, " ").equals(" ")){
            binding.imagePersonal.setImageResource(R.drawable.avatar);
        }else{
            try {
                binding.imagePersonal.setImageBitmap(personalModel.importImageFromMemory(getActivity(), getContext(), getActivity().getContentResolver(),
                        Uri.parse(sharedPref.getString(URI, " "))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        binding.goOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt(ACCESS_PREFERENCE, 0);
                editor.apply();
                Intent intent = new Intent(requireContext(), WelcomeActivity.class);
                startActivity(intent);
            }
        });

        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(requireContext(), ModifyActivity.class);
              startActivity(intent);
            }
        });

        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.action_personalFragment_to_settingsFragment);
            }
        });


    }



}