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
import com.example.clothual.databinding.FragmentTypedressBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypedressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TypedressFragment extends Fragment {

    public FragmentTypedressBinding binding;

    public TypedressFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TypedressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TypedressFragment newInstance() {
        return new TypedressFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTypedressBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.scarpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.action_typedress_to_informationFragment);
            }
        });
    }
}