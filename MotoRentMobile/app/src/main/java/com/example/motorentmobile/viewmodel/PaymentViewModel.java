package com.example.motorentmobile.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.model.RentalRequest;
import com.example.motorentmobile.data.model.Vehicle;
import com.example.motorentmobile.data.repository.PaymentRepository;
import com.example.motorentmobile.util.DateTimeUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentViewModel extends AndroidViewModel {
    private final PaymentRepository repository;
    public final MutableLiveData<String> paymentUrl = new MutableLiveData<>();
    public final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<String> getPaymentUrl() {
        return paymentUrl;
    }

    public PaymentViewModel(@NonNull Application application) {
        super(application);
        repository = new PaymentRepository(application);
    }

    public void processPayment(double amountPaid) {
        RentalManager rentalManager = RentalManager.getInstance();
        List<Long> vehicleIds = new ArrayList<>();
        for (Vehicle v : rentalManager.getVehiclesLiveData().getValue()) {
            vehicleIds.add(v.getId());
        }

        Date start = rentalManager.getStartTime();
        Date end = rentalManager.getEndTime();

        RentalRequest dto = new RentalRequest(
                rentalManager.getTotalPrice(),
                amountPaid,
                DateTimeUtil.format(start),
                DateTimeUtil.format(end),
                "VNPAY",
                vehicleIds
        );

        repository.createPayment(dto).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String url = response.body().get("paymentUrl");
                    if (url != null) {
                        paymentUrl.setValue(url);
                    } else {
                        errorMessage.setValue("Không nhận được đường dẫn thanh toán");
                    }
                } else {
                    String error = "Đã có lỗi xảy ra";
                    if (response.errorBody() != null) {
                        try {
                            String errorBodyString = response.errorBody().string();
                            JSONObject jsonError = new JSONObject(errorBodyString);
                            if (jsonError.has("error")) {
                                error = jsonError.getString("error");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            error = "Không thể đọc lỗi từ server";
                        }
                    }

                    errorMessage.setValue(error);
                }
            }



            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                errorMessage.setValue("Không thể kết nối tới server");
            }
        });
    }
}
