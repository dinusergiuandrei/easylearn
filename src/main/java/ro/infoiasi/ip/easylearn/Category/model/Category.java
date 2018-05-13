package ro.infoiasi.ip.easylearn.Category.model;

public class Category {

    private long categoryId;
    private String nume;

    public Category(long categoryId, String nume) {
        super();
        this.categoryId = categoryId;
        this.nume = nume;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

}

