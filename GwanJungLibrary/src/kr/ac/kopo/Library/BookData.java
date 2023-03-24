package kr.ac.kopo.Library;

public class BookData {
    private String id;
    private String name;
    private String author;
    private boolean rental;
    private int stock;
    public BookData() {
        
    }
    public BookData(String id, String name, String author, boolean rental, int stock) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.rental = rental;
        this.stock = stock;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean isRental() {
        return rental;
    }
    public void setRental(boolean rental) {
        this.rental = rental;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

}
