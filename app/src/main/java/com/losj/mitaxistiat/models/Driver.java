package com.losj.mitaxistiat.models;

public class Driver {
    String id;
    String name;
    String email;
    String modelsCars; //esto es lo mismo que vehicleBarnd
    String placaCar; //esto es lo mismo a plate

    public Driver(String id, String name, String email, String modelsCars, String placaCar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.modelsCars = modelsCars;
        this.placaCar = placaCar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModelsCars() {
        return modelsCars;
    }

    public void setModelsCars(String modelsCars) {
        this.modelsCars = modelsCars;
    }

    public String getPlacaCar() {
        return placaCar;
    }

    public void setPlacaCar(String placaCar) {
        this.placaCar = placaCar;
    }
}
