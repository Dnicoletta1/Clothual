package com.example.clothual.UI.core.AddDress;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clothual.R;

import java.io.FileNotFoundException;

public class AddDressActivity extends AppCompatActivity {


    public AddDressModel model;
    public ImageView imageViewDress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dress_layout);

        model = new AddDressModel(getApplication());

        imageViewDress = findViewById(R.id.imageViewDress);
        String uri = getIntent().getExtras().getString("uri");

        try {
            Bitmap immagine = model.importImageFromMemory(getContentResolver(), Uri.parse(uri));
            imageViewDress.setImageBitmap(immagine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }


}
