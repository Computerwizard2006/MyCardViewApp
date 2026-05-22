package com.example.mycardview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<CardModel> list;
    private CardAdapter adapter;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        // Adding professional content
        list.add(new CardModel(
                "Cyber Security",
                "Master the art of ethical hacking and network protection with our advanced security modules.",
                R.drawable.img));

        list.add(new CardModel(
                "AI & ML",
                "Explore the future of intelligence. Learn neural networks, deep learning, and predictive models.",
                R.drawable.img_1));

        list.add(new CardModel(
                "Cloud Computing",
                "Scale your applications globally using AWS, Azure, and modern DevOps practices.",
                R.drawable.img_2));

        list.add(new CardModel(
                "Android Development",
                "Build modern mobile applications using Kotlin and Jetpack Compose with industry standards.",
                R.drawable.img));

        list.add(new CardModel(
                "Data Science",
                "Uncover insights from complex data using advanced statistical methods and machine learning.",
                R.drawable.img_1));

        list.add(new CardModel(
                "Blockchain Tech",
                "Develop secure decentralized applications and understand the power of smart contracts.",
                R.drawable.img_2));

        adapter = new CardAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
