package bsuir.pechuro.entity;

/**
 * class OrderProduct created to work with products from orders
 */
public class OrderProduct {
    Integer orderId;
    Integer productId;
    Integer productCount;

    public OrderProduct(Integer orderId, Integer productId, Integer productCount) {
        this.orderId = orderId;
        this.productId = productId;
        this.productCount = productCount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProduct that = (OrderProduct) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        return productCount != null ? productCount.equals(that.productCount) : that.productCount == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (productCount != null ? productCount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", productCount=" + productCount +
                '}';
    }

}
