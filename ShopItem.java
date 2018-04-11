package kevin.cox.thesmartshopper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kevin on 18/02/2018.
 */

public class ShopItem {

    @JsonProperty("itemId")
    private int itemId;
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("itemPrice")
    private Double itemPrice;
    @JsonProperty("itemImage")
    private String itemImage;
    @JsonProperty("itemQuantity")
    private int itemQuantity;

    public ShopItem(int itemId, String itemName, Double itemPrice, String itemImage, int itemQuantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.itemQuantity = itemQuantity;
    }


    public int getItemId() {
        return itemId;
    }


    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }


    public String getItemImage() {

        return itemImage;
    }

    public void setItemImage(String itemImage) {this.itemImage = itemImage;}
    
    public int getItemQuantity() {return itemQuantity;}

    public void setItemQuantity(int itemQuantity) {this.itemQuantity = itemQuantity;}

    public String toString(){

        String text = "Name: "+ getItemName() + "\nURL = " + getItemImage();
        return text;
    }


}
