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
            /*  Salvo le informazioni in un sharePreference, passo i dati al model, crea l'oggetto con i dati nuovi nelle preference
                o li recupera dal database.
                In alternativa creare una classe DBfittizio che salva questi dati momentaneamente e si resetta ad ogni attivazione:
                dubbio sull'allocazione di memoria che potrebbe non essere mai utilizzata
            */

            @Override
            public void onClick(View view) {
                int i = 0;
                int j = 0;
                if(binding.editTextEmail.isEnabled()){
                    if(modifyModel.checkEmail(binding.editTextEmail.getText().toString())){
                        binding.inputViewEmail.setError(null);
                    }else{
                        binding.inputViewEmail.setError("Mail non valida");
                    }
                }

                if(binding.editTextUsername.isEnabled()){
                    if(binding.editTextUsername.getText().toString().isEmpty()){
                        binding.inputUsername.setError("Username non valido");
                    }else{
                        binding.inputUsername.setError(null);
                    }
                }

                if(binding.editTextPassword.isEnabled()){
                    if(modifyModel.checkPassword(binding.editTextUsername.getText().toString(), sharedPref.getInt(ID_ACCOUNT, 0))){
                        if(binding.editTextPasswordNuovo.getText().toString().isEmpty()){
                            binding.inputPassword.setError("Password non valida");
                        }else{
                            binding.inputPassword.setError(null);
                        }
                    }else{
                        binding.inputPassword.setError("Password Errata");
                    }
                }

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
