package lld.bre;

class Product {
    private int pid;
    private int quantity;
    private Category category;

    public Product(int pid, int quantity, Category category) {
        this.pid = pid;
        this.quantity = quantity;
        this.category = category;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
