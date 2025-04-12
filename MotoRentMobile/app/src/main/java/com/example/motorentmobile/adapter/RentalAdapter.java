package com.example.motorentmobile.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.model.Vehicle;
import com.example.motorentmobile.databinding.ItemRentalBinding;
import com.example.motorentmobile.viewmodel.RentalManager;

import java.util.ArrayList;
import java.util.List;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.RentalViewHolder> {

    private List<Vehicle> vehicleList = new ArrayList<>();

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicleList = vehicles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRentalBinding binding = ItemRentalBinding.inflate(inflater, parent, false);
        return new RentalViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.bind(vehicle);
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    class RentalViewHolder extends RecyclerView.ViewHolder {

        private final ItemRentalBinding binding;

        public RentalViewHolder(ItemRentalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Vehicle vehicle) {
            binding.setVehicle(vehicle);
            binding.executePendingBindings();
            String url = ApiClient.BASE_URL + vehicle.getVehicleImages().get(0).getUrl();
            Glide.with(binding.imageVehicle.getContext())
                    .load(url)
                    .into(binding.imageVehicle);

            binding.btnDelete.setOnClickListener(v -> {
                RentalManager.getInstance().removeVehicle(vehicle);
            });
        }
    }
}
