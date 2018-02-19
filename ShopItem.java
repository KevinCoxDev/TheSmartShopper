package kevin.cox.thesmartshopper;

/**
 * Created by kevin on 18/02/2018.
 */

public class ShopItem {

    private Long itemId;
    private String itemName;
    private Double itemPrice;
    private String imageURL;

    public ShopItem(Long itemId, String itemName, Double itemPrice, String imageURL) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.imageURL = imageURL;
    }

    public String toString(){
        return itemName + " \n " + itemPrice;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
