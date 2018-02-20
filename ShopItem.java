package kevin.cox.thesmartshopper;

/**
 * Created by kevin on 18/02/2018.
 */

public class ShopItem {

    private int itemId;
    private String itemName;
    private Double itemPrice;
    private String itemImageURL;

    public ShopItem(int itemId, String itemName, Double itemPrice, String itemImageURL) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImageURL = itemImageURL;
    }

    public String toString(){
        return "ItemId: " + itemId+ "\n" + "ItemName: " + itemName+ "\n" + "ItemPrice: " + itemPrice+ "\n" + "ItemImageURL: " + itemImageURL;
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

    public String getImageURL() {
        return itemImageURL;
    }

    public void setImageURL(String imageURL) {
        this.itemImageURL = imageURL;
    }
}
