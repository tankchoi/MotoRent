package com.example.motorentmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.motorentmobile.R;
import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.model.VehicleImage;

import java.util.List;

public class VehicleImageAdapter extends RecyclerView.Adapter<VehicleImageAdapter.VehicleImageViewHolder> {

    private final Context context;
    private final List<VehicleImage> vehicleImages;

    public VehicleImageAdapter(Context context, List<VehicleImage> vehicleImages) {
        this.context = context;
        this.vehicleImages = vehicleImages;
    }

    @Override
    public VehicleImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicle_image, parent, false);
        return new VehicleImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleImageViewHolder holder, int position) {
        VehicleImage vehicleImage = vehicleImages.get(position);
        String url = ApiClient.BASE_URL + vehicleImage.getUrl();
        Glide.with(context)
                .load(url)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return vehicleImages.size();
    }

    static class VehicleImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public VehicleImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgVehicle);
        }
    }
}
