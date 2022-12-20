package com.example.clothual.UI.core.PhotoFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothual.UI.core.AddDress.AddDressActivity;
import com.example.clothual.databinding.FragmentPhotoBinding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("deprecation")
public class PhotoFragment extends Fragment {

    private FragmentPhotoBinding binding;

    public PhotoModel photoModel;


    public PhotoFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoModel = new PhotoModel(requireActivity().getApplication());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotoBinding.inflate(getLayoutInflater());
        return binding.getRoot();


    }
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        });

        RecyclerView.LayoutManager manager = new GridLayoutManager(requireContext(), 3);



        try {
            List<Bitmap> image = photoModel.getImageList(getActivity().getContentResolver());
            RecyclerViewPhotoAdapter adapter = new RecyclerViewPhotoAdapter(image);
            binding.recyclerView.setLayoutManager(manager);
            binding.recyclerView.setAdapter(adapter);
            binding.recyclerView.addItemDecoration(new RecyclerViewPhotoAdapter.GridSpacingItemDecoration
                    (3, 20, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent databack) {
        assert databack != null;
        Bitmap immagine = (Bitmap) databack.getExtras().get("data");
        Uri uri;
        try {
            uri = photoModel.saveImage(getActivity().getContentResolver(), immagine, photoModel.getNameImage(), "");
            Intent intent = new Intent(getActivity(), AddDressActivity.class);
            intent.putExtra("uri", uri.toString());
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();

        }


    }


}

