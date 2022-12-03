package com.example.clothual.UI.core.PersonalFragment;

import static com.example.clothual.Util.Constant.CREDENTIALS_LOGIN_FILE;
import static com.example.clothual.Util.Constant.ID_ACCOUNT;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clothual.UI.core.CoreActivity;
import com.example.clothual.databinding.ModifyLayoutBinding;

public class ModifyActivity extends AppCompatActivity {

    private ModifyLayoutBinding binding;
    private ModifyModel modifyModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ModifyLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        modifyModel = new ModifyModel(getApplication());

        SharedPreferences sharedPref = getSharedPreferences(CREDENTIALS_LOGIN_FILE, Context.MODE_PRIVATE);


        binding.editTextEmail.setText(modifyModel.getEmail(sharedPref.getInt(ID_ACCOUNT, 0)));
        binding.editTextUsername.setText(modifyModel.getUsername(sharedPref.getInt(ID_ACCOUNT, 0)));

        binding.editTextEmail.setEnabled(false);
        binding.editTextPassword.setEnabled(false);
        binding.editTextUsername.setEnabled(false);
        binding.editTextPasswordNuovo.setEnabled(false);

        binding.modificaEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editTextEmail.setEnabled(true);
            }
        });

        binding.modificaUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editTextUsername.setEnabled(true);
            }
        });

        binding.modificaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editTextPassword.setEnabled(true);
                binding.editTextPasswordNuovo.setEnabled(true);
            }
        });

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
