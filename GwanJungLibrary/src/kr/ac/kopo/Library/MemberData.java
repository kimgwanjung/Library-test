package kr.ac.kopo.Library;

public class MemberData  {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String publishedDate;
    public MemberData() {}
    public MemberData(String id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;

    }
    public MemberData(String id, String name, String address, String phone, String publishedDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.publishedDate = publishedDate;
        this.address = address;

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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
