package vn.aptech.java.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import vn.aptech.java.configs.VNPAYConfig;
import vn.aptech.java.utils.VNPayUtil;

import java.util.*;

@Service
public class PaymentService {

    private final VNPAYConfig vnPayConfig;


    public PaymentService(VNPAYConfig vnPayConfig) {
        this.vnPayConfig = vnPayConfig;
    }

    public String createVnPayPayment(HttpServletRequest request, Long rentalId, double amountPaid) {
        long vnPayAmount = (long)(amountPaid * 100);
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(vnPayAmount));
        vnpParamsMap.put("vnp_TxnRef", rentalId.toString());
        vnpParamsMap.put("vnp_OrderInfo", "Thanh toan don thue xe so " + rentalId);
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;

        return vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
    }



}
