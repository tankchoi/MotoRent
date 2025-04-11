package vn.edu.tlu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.R;
import vn.edu.tlu.model.History;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<History> historyList;

    public HistoryAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        History history = historyList.get(position);
        holder.tvVehicleName.setText(history.getVehicleName());
        holder.tvStartTime.setText(history.getStartTime().toString());
        holder.tvEndTime.setText(history.getEndTime().toString());
        holder.tvTotalPrice.setText(String.valueOf(history.getTotalPrice()));


    }

    @Override
    public int getItemCount() {
        // Return the total number of items
        return historyList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        // Define your ViewHolder views here
        private TextView tvVehicleName;
        private TextView tvStartTime;
        private TextView tvEndTime;
        private TextView tvTotalPrice;
        public HistoryViewHolder(View itemView) {
            super(itemView);
            // Initialize your views here
            tvVehicleName = itemView.findViewById(R.id.tvVehicleName);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
            tvEndTime = itemView.findViewById(R.id.tvEndTime);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);

        }
    }
}
