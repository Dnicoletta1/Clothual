package com.example.clothual.UI.core.AddDress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.clothual.R;
import com.example.clothual.databinding.FragmentInformationBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class InformationFragment extends Fragment {

    public FragmentInformationBinding binding;

    public InformationFragment(){

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment typedress.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance() {
        return new InformationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInformationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.action_informationFragment_to_coreActivity);
            }
        });

    }
}
