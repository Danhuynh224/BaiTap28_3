package com.example.fragmenttablayoutandviewpager2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmenttablayoutandviewpager2.databinding.FragmentNeworderBinding;

/**

 */
public class FragmentNeworder extends Fragment {

    FragmentNeworderBinding binding;

    public FragmentNeworder() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNeworderBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }
}