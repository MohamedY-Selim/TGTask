package eg.amazon.objects;

public class Product {
    //Attributes
    private String title;
    private int size;
    private int qty;
    private float price;


    //Constructor
    public Product(String title, int size, int qty, float price) {
        this.title = title;
        this.size = size;
        this.qty = qty;
        this.price = price;
    }

    //Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
