package com.example.clothual.UI.core.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothual.Model.Clothual;
import com.example.clothual.Model.Image;
import com.example.clothual.databinding.FragmentShoesBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoesFragment extends Fragment {

    private FragmentShoesBinding binding;
    private ShoesFragmentModel model;
    public ShoesFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShoesFragment.
     */

    public static ShoesFragment newInstance() {
        return new ShoesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ShoesFragmentModel(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShoesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext());

            List<Image> image = model.getImageList();
            List<Clothual> clothual = model.getClothualList();
            RecyclerViewShoesAdapter adapter = new RecyclerViewShoesAdapter(clothual, image,
                    getActivity().getContentResolver());
            binding.recyclerViewShoes.setLayoutManager(manager);
            binding.recyclerViewShoes.setAdapter(adapter);

    }

}