package vn.edu.tlu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.R;
import vn.edu.tlu.model.Vehicle;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private List<Vehicle> vehicleList;

    public VehicleAdapter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.tvVehicleName.setText(vehicle.getNameVehicle());
        holder.imgVehicle.setImageResource(vehicle.getImgVehicle());

    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVehicleName;
        private ImageView imgVehicle;

        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVehicleName = itemView.findViewById(R.id.tvVehicleName);
            imgVehicle = itemView.findViewById(R.id.imgVehicle);
        }
    }
}
