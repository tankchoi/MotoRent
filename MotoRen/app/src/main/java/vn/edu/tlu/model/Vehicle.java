package vn.edu.tlu.model;

public class Vehicle {

    private String nameVehicle;
    private int imgVehicle;
    public Vehicle(String nameVehicle, int imgVehicle) {
        this.nameVehicle = nameVehicle;
        this.imgVehicle = imgVehicle;
    }
    public String getNameVehicle() {
        return nameVehicle;
    }

    public void setNameVehicle(String nameVehicle) {
        this.nameVehicle = nameVehicle;
    }

    public int getImgVehicle() {
        return imgVehicle;
    }

    public void setImgVehicle(int imgVehicle) {
        this.imgVehicle = imgVehicle;
    }


}
