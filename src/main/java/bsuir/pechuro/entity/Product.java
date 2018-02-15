package bsuir.pechuro.entity;


public class Product {

    private Integer id;
    private String type;
    private String nameRu;
    private String nameEn;
    private Integer weight;
    private Double cost;
    private String descriptionRu;
    private String descriptionEn;
    private String imagePath;
    private Integer number;
    private Integer ordered;
    private Integer orderId;
    private Double commonCost;

    public Product() {
    }

    public Product(Integer id, String type, String nameRu, String nameEn, Integer weight, Double cost, String descriptionRu, String descriptionEn, String imagePath, Integer number, Integer ordered, Integer orderId) {

        this.id = id;
        this.type = type;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.weight = weight;
        this.cost = cost;
        this.descriptionRu = descriptionRu;
        this.descriptionEn = descriptionEn;
        this.imagePath = imagePath;
        this.number = number;
        this.ordered = ordered;
        this.orderId = orderId;
    }

    public void setCommonCost() {
        commonCost = Math.rint(100.0 * (cost * number)) / 100.0;
    }

    public Double getCommonCost() {
        return commonCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", nameRu='" + nameRu + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                ", descriptionRu='" + descriptionRu + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", number=" + number +
                ", ordered=" + ordered +
                ", orderId=" + orderId +
                ", commonCost=" + commonCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (type != null ? !type.equals(product.type) : product.type != null) return false;
        if (nameRu != null ? !nameRu.equals(product.nameRu) : product.nameRu != null) return false;
        if (nameEn != null ? !nameEn.equals(product.nameEn) : product.nameEn != null) return false;
        if (weight != null ? !weight.equals(product.weight) : product.weight != null) return false;
        if (cost != null ? !cost.equals(product.cost) : product.cost != null) return false;
        if (descriptionRu != null ? !descriptionRu.equals(product.descriptionRu) : product.descriptionRu != null)
            return false;
        if (descriptionEn != null ? !descriptionEn.equals(product.descriptionEn) : product.descriptionEn != null)
            return false;
        if (imagePath != null ? !imagePath.equals(product.imagePath) : product.imagePath != null) return false;
        if (number != null ? !number.equals(product.number) : product.number != null) return false;
        if (ordered != null ? !ordered.equals(product.ordered) : product.ordered != null) return false;
        if (orderId != null ? !orderId.equals(product.orderId) : product.orderId != null) return false;
        return commonCost != null ? commonCost.equals(product.commonCost) : product.commonCost == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (nameRu != null ? nameRu.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (descriptionRu != null ? descriptionRu.hashCode() : 0);
        result = 31 * result + (descriptionEn != null ? descriptionEn.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (ordered != null ? ordered.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (commonCost != null ? commonCost.hashCode() : 0);
        return result;
    }
}
