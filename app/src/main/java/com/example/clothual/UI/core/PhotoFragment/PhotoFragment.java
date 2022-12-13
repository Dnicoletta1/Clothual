package com.example.clothual.UI.core.PhotoFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.clothual.R;
import com.example.clothual.databinding.FragmentPhotoBinding;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("deprecation")
public class PhotoFragment extends Fragment {

    private FragmentPhotoBinding binding;


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
            File foto  = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "");
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
            startActivityForResult(intent, 0);
        });

        binding.button2.setOnClickListener(view12 -> Navigation.findNavController(requireView()).navigate(R.id.action_photoFragment_to_addDressActivity));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent databack) {
        assert databack != null;
        Bitmap immagine = (Bitmap) databack.getExtras().get("data");
        // img è un componente imageView presente sul layout
        binding.camera.setImageBitmap(immagine);

    }


}

