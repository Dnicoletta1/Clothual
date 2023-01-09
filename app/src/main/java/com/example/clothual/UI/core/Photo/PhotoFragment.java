package com.example.clothual.UI.core.Photo;

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

import com.example.clothual.Model.Image;
import com.example.clothual.UI.core.AddDress.AddDressActivity;
import com.example.clothual.UI.core.adapter.RecyclerViewPhotoAdapter;
import com.example.clothual.databinding.FragmentPhotoBinding;
import com.google.android.material.snackbar.Snackbar;

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
    private boolean isFABOpen;


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


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu1();
                }else{
                    closeFABMenu();
                }
            }
        });





        RecyclerView.LayoutManager manager = new GridLayoutManager(requireContext(), 3);




            List<Image> image = photoModel.getImageList();//(getActivity(), getContext(), getActivity().getContentResolver());
            RecyclerViewPhotoAdapter adapter = new RecyclerViewPhotoAdapter(image, new RecyclerViewPhotoAdapter.OnItemClickListener() {
                @Override
                public void delete() {
                    Snackbar.make(view, "Photo Delite", Snackbar.LENGTH_LONG).show();
                }

            }, getActivity().getApplication(), getContext().getContentResolver());
            binding.recyclerView.setLayoutManager(manager);
            binding.recyclerView.setAdapter(adapter);
           /* binding.recyclerView.addItemDecoration(new RecyclerViewPhotoAdapter.GridSpacingItemDecoration
                   (3, 20, true));

            */


        binding.fab1Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

        binding.fab2Make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

    }



    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 1);
    }

    private void showFABMenu1() {
        isFABOpen = true;
        binding.fab2Make.animate().translationY(-305);
        binding.makePhoto.setVisibility(View.VISIBLE);
        showFABMenu2();
    }

    private void showFABMenu2() {
        isFABOpen = true;
        binding.fab1Upload.animate().translationY(-555);
        binding.upload.setVisibility(View.VISIBLE);
    }

        private void closeFABMenu () {
            isFABOpen = false;
            binding.fab1Upload.animate().translationY(0);
            binding.fab2Make.animate().translationY(0);
            binding.upload.setVisibility(View.INVISIBLE);
            binding.makePhoto.setVisibility(View.INVISIBLE);
        }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent databack) {
        Uri uri;
        if(requestCode == 0) {
            assert databack != null;
            Bitmap immagine = (Bitmap) databack.getExtras().get("data");

            try {
                uri = photoModel.saveImage(getActivity().getContentResolver(), immagine, photoModel.getNameImage(), "");
                Intent intent = new Intent(getActivity(), AddDressActivity.class);
                intent.putExtra("uri", uri.toString());
                intent.putExtra("action", 0);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }else{
            uri = databack.getData();
            if(uri != null){
                try {
                    Bitmap bitmap = photoModel.importImageFromMemory(getActivity(), getContext(), getActivity().getContentResolver(), uri);
                    Uri newUri = photoModel.saveImage(getActivity().getContentResolver(), bitmap,
                            photoModel.getNameImage(), "");
                    Intent intent = new Intent(getActivity(), AddDressActivity.class);
                    intent.putExtra("uri", newUri.toString());
                    intent.putExtra("action", 0);
                    startActivity(intent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


    }



}

