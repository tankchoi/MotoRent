package com.example.motorentmobile.data.model;

import java.io.Serializable;

public class VehicleImage implements Serializable {
    private Long id;
    private String url;

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
