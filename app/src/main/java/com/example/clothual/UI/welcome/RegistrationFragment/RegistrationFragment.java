package com.example.clothual.UI.welcome.RegistrationFragment;

import static com.example.clothual.Util.Constant.CREDENTIALS_LOGIN_FILE;
import static com.example.clothual.Util.Constant.PASSWORD_PREFERENCE;
import static com.example.clothual.Util.Constant.USERNAME_PREFERENCE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clothual.UI.core.CoreActivity;
import com.example.clothual.databinding.FragmentRegistrationBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    private FragmentRegistrationBinding binding;
    public RegistrationModel registrationModel;
    public RegistrationFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment fragment_registration.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationModel = new RegistrationModel(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(CREDENTIALS_LOGIN_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationModel.createUser(binding.editTextUsername.getText().toString(),
                        binding.editTextName.getText().toString(),
                        binding.editTextSurname.getText().toString(),
                        binding.editTextPassword.getText().toString(),
                        binding.editTextEmail.getText().toString());
                editor.putString(USERNAME_PREFERENCE, binding.editTextUsername.getText().toString());
                editor.putString(PASSWORD_PREFERENCE, binding.editTextPassword.getText().toString());
                editor.apply();
                Intent intet = new Intent(requireContext(), CoreActivity.class);
                startActivity(intet);
            }
        });

    }

}