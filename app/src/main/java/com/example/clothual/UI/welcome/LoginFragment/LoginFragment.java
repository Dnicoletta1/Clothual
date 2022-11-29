package com.example.clothual.UI.welcome.LoginFragment;

import static com.example.clothual.Util.Constant.*;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.clothual.R;
import com.example.clothual.UI.core.CoreActivity;
import com.example.clothual.databinding.FragmentLoginBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    Handler handler = new Handler();

    public LoginFragment() { }

    public static LoginFragment newInstance() {
      return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(binding.changeText.getText().toString().equals("come")){
                    binding.changeText.setText("ciao");
                }else{
                    binding.changeText.setText("come");
                }
            }
        }, 5000);

        */
        Runnable runnable = new Runnable() {
            int i = 0;
            String [] strings = {GIANNI_VERSACE, RALPH_LAUREN, PIER_CARDIN, DONATELLA_VERSACE, GIORGIO_ARMANI, COCO_CHANEL};
            public void run() {
                do {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.changeText.setText(strings[i]);
                            if(i == 5){
                               i = 0;
                            }else{
                               i ++;
                            }
                        }
                    }, 0);
                    //Add some downtime
                    SystemClock.sleep(5000);
                }while (true);
            }
        };
        new Thread(runnable).start();
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CoreActivity.class);
                startActivity(intent);

            }
        });

       binding.textViewRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_fragment_registration);
           }
       });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }



}