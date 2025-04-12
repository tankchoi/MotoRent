package com.example.motorentmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.databinding.ItemVehicleBinding;
import com.example.motorentmobile.data.model.Vehicle;
import com.example.motorentmobile.view.VehicleDetailActivity;
import com.example.motorentmobile.viewmodel.RentalManager;

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
            List<Vehicle> selectedVehicles = RentalManager.getInstance().getVehiclesLiveData().getValue();

            boolean isAlreadyAdded = false;
            if (selectedVehicles != null) {
                for (Vehicle vItem : selectedVehicles) {
                    if (vItem.getId().equals(vehicle.getId())) {
                        isAlreadyAdded = true;
                        break;
                    }
                }
            }

            if (isAlreadyAdded) {
                Toast.makeText(context, "Xe đã có trong đơn", Toast.LENGTH_SHORT).show();
                holder.binding.btnAdd.setEnabled(false);
                holder.binding.btnAdd.setText("Đã thêm");
                return;
            }

            // Thêm phương tiện vào đơn thuê
            RentalManager.getInstance().addVehicle(vehicle);
            holder.binding.btnAdd.setEnabled(false);
            holder.binding.btnAdd.setText("Đã thêm");
            Toast.makeText(context, "Đã thêm xe vào đơn thuê", Toast.LENGTH_SHORT).show();
        });



        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, VehicleDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("vehicle", vehicle); // Truyền đối tượng vehicle
            context.startActivity(intent);
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

