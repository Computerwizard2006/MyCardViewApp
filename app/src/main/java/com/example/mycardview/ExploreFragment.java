package com.example.mycardview;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<CardModel> fullList;
    private List<CardModel> filteredList;
    private TextInputEditText searchEditText;

    public ExploreFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.explore_recycler_view);
        searchEditText = view.findViewById(R.id.searchEditText);
        
        fullList = new ArrayList<>();
        loadExploreData();
        
        filteredList = new ArrayList<>(fullList);

        adapter = new CardAdapter(filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filter(String text) {
        filteredList.clear();
        for (CardModel item : fullList) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase()) || 
                item.getDesc().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void loadExploreData() {
        fullList.add(new CardModel("Machine Learning", "Neural Networks and Data Science.", R.drawable.img));
        fullList.add(new CardModel("Blockchain", "Decentralized Ledgers and Smart Contracts.", R.drawable.img_1));
        fullList.add(new CardModel("UI/UX Design", "User-centric design principles.", R.drawable.img_2));
        fullList.add(new CardModel("Data Science", "Analytics with Python.", R.drawable.img));
        fullList.add(new CardModel("Cyber Security", "Ethical hacking and network defense.", R.drawable.img_1));
    }
}
