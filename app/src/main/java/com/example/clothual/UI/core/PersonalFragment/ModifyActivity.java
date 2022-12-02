package com.example.clothual.UI.core.PersonalFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clothual.UI.core.CoreActivity;
import com.example.clothual.databinding.ModifyLayoutBinding;

public class ModifyActivity extends AppCompatActivity {

    private ModifyLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ModifyLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.fine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifyActivity.this, CoreActivity.class);
                startActivity(intent);
            }
        });

        binding.annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifyActivity.this, CoreActivity.class);
                startActivity(intent);
            }
        });

    }
}
