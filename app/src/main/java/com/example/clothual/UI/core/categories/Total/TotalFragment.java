package com.example.clothual.UI.core.categories.Total;

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
import com.example.clothual.UI.core.categories.CategoryModel;
import com.example.clothual.UI.core.categories.Shoes.RecyclerViewShoesAdapter;
import com.example.clothual.databinding.FragmentTotalBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TotalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TotalFragment extends Fragment {

   public FragmentTotalBinding binding;
   public CategoryModel model;

    public TotalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TotalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TotalFragment newInstance() {
        return new TotalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new CategoryModel(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTotalBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext());

        List<Image> image = model.getImageList();
        List<Clothual> clothual = model.getClothualList();
        RecyclerViewShoesAdapter adapter = new RecyclerViewShoesAdapter(clothual, image,
                getActivity().getContentResolver(), new RecyclerViewShoesAdapter.OnItemClickListener() {
            @Override
            public void buttonDelete(String notify) {
                Snackbar.make(view, notify, Snackbar.LENGTH_LONG).show();
            }
        }, getActivity().getApplication());
        binding.recyclerViewShoes.setLayoutManager(manager);
        binding.recyclerViewShoes.setAdapter(adapter);

    }

}