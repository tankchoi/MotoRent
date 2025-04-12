package com.example.motorentmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.databinding.ItemVehicleBinding;
import com.example.motorentmobile.data.model.Vehicle;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private List<Vehicle> vehicleList;
    private final Context context;

    public VehicleAdapter(Context context, List<Vehicle> vehicleList) {
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemVehicleBinding binding = ItemVehicleBinding.inflate(inflater, parent, false);
        return new VehicleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.binding.setVehicle(vehicle);

        if (vehicle.getVehicleImages() != null && !vehicle.getVehicleImages().isEmpty()) {
            String imageUrl = ApiClient.BASE_URL + vehicle.getVehicleImages().get(0).getUrl();
            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.binding.imageVehicle);
        }

        holder.binding.btnAdd.setOnClickListener(v -> {
            // TODO: Xử lý khi nhấn "Thêm"
        });
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public void updateVehicleList(List<Vehicle> newList) {
        this.vehicleList = newList;
        notifyDataSetChanged();
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {
        final ItemVehicleBinding binding;

        public VehicleViewHolder(ItemVehicleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

