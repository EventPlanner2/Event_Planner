package com.example.services;

import com.example.data.UserData;
import com.example.entites.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class SearchServiceProvider {

    private boolean isSelected;

    private String selectedCriteria;

    private String location;
    private String type;
    private int price;

    private String errorMsg;
    private SPAcc spAccService;

    private List<ServiceProvider> tmpSps;
    public SearchServiceProvider() {
        this.isSelected = false;
        this.selectedCriteria = "";
        this.location = "";
        this.type ="";
        this.errorMsg = "";
        spAccService = new SPAcc();
        tmpSps = new ArrayList<>();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if(!spAccService.findLocation(location)){setErrorMsg("invalid location !");return;}
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(!spAccService.findType(type)){setErrorMsg("invalid type !");return;}
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(String price) {
        try{
            this.price = Integer.parseInt(price);
        }
        catch (NumberFormatException e){
            setErrorMsg("invalid price !");
        }

    }

    public String getSelectedCriteria() {
        return selectedCriteria;
    }

    public void setSelectedCriteria(String selectedCriteria) {
        if(selectedCriteria.isEmpty()){
            setErrorMsg("the user must choose one search criteria");
            return;
        }
        if(selectedCriteria.equalsIgnoreCase("location") || selectedCriteria.equalsIgnoreCase("type") || selectedCriteria.equalsIgnoreCase("price")){
            this.selectedCriteria = selectedCriteria;
        }
        else{
            setErrorMsg("the user must select an existed criteria");

        }

    }



    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<ServiceProvider> searchSPPerformed(){


       if(this.selectedCriteria.equalsIgnoreCase("location")){
            return searchSPLocation();
        }
        else if(this.selectedCriteria.equalsIgnoreCase("type")){
            return searchSPType();
        }
        else if(this.selectedCriteria.equalsIgnoreCase("price")){
            return searchSPPrice();
        }
        return tmpSps;

    }
    public List<ServiceProvider> searchSPLocation(){
        tmpSps = new ArrayList<>();
        for(ServiceProvider s : UserData.getSps()){
            if(s.getLocation().equals(location)){
                tmpSps.add(s);
            }
        }
        return tmpSps;

    }
    public List<ServiceProvider> searchSPType(){
        tmpSps = new ArrayList<>();
        for(ServiceProvider s : UserData.getSps()){
            if(s.getType().equals(type)){
                tmpSps.add(s);
            }
        }
        return tmpSps;

    }
    public List<ServiceProvider> searchSPPrice(){
        tmpSps = new ArrayList<>();
        for(ServiceProvider s : UserData.getSps()){
            if(s.getPrice() <= this.getPrice() && s.getPrice() > 0){
                tmpSps.add(s);
            }
        }
        return tmpSps;

    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
