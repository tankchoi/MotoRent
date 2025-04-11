package vn.edu.tlu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.adapter.HistoryAdapter;
import vn.edu.tlu.model.History;
import vn.edu.tlu.ui.BaseFragment;


public class HistoryFragment extends BaseFragment {


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rvHistoryList);
        List<History> historyList = new ArrayList<>();
        historyList.add(new History("1", 120000, 120000, LocalDateTime.parse("2023-10-01T00:00:00"), LocalDateTime.parse("2023-10-02T00:00:00"),"Vision"));

        historyList.add(new History("2", 120000, 120000, LocalDateTime.parse("2023-10-01T00:00:00"), LocalDateTime.parse("2023-10-02T00:00:00"),"Air Blade"));

        HistoryAdapter adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(mContext, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {

    }
}