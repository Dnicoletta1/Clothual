package com.example.clothual.UI.core.AddDress;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clothual.R;
import com.example.clothual.UI.core.CoreActivity;

import java.io.FileNotFoundException;

public class AddDressActivity extends AppCompatActivity {


    public AddDressModel model;
    public ImageView imageViewDress;
    public Spinner spinner;
    public EditText brand;
    public EditText template;
    public EditText color;
    public EditText description;
    public Button buttonSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dress_layout);

        model = new AddDressModel(getApplication());

        imageViewDress = findViewById(R.id.imageViewDress);
        spinner = findViewById(R.id.spinner);
        brand = findViewById(R.id.editTextBrand);
        template = findViewById(R.id.editTextTemplate);
        color = findViewById(R.id.editTextColor);
        description = findViewById(R.id.editTextDescriprion);
        buttonSave = findViewById(R.id.save);

        String uri = getIntent().getExtras().getString("uri");

        try {
            Bitmap image = model.importImageFromMemory(getContentResolver(), Uri.parse(uri));
            imageViewDress.setImageBitmap(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        buttonSave.setOnClickListener(view -> {
            int spinnerValue = 0;
            if(spinner.getSelectedItem().toString().equals(getString(R.string.shoes))){
                spinnerValue = 1;
            }
            if(spinner.getSelectedItem().toString().equals(getString(R.string.trousers))){
                spinnerValue = 2;
            }
            if(spinner.getSelectedItem().toString().equals(getString(R.string.tshirt))){
                spinnerValue = 3;
            }
            if(spinner.getSelectedItem().toString().equals(getString(R.string.jackets))){
                spinnerValue = 4;
            }
            if(spinner.getSelectedItem().toString().equals(getString(R.string.jeans))){
                spinnerValue = 5;
            }

            model.createClothual(spinnerValue, brand.getText().toString(), description
                    .getText().toString(), color.getText().toString(), template.getText().toString(), model.getIdByUri(uri));
            Intent intent = new Intent(AddDressActivity.this, CoreActivity.class);
            startActivity(intent);
        });



    }


}
