package bsuir.pechuro.entity;

/**
 * class Order created to work with orders
 */
public class Order {
    private Integer id;
    private String date;
    private String type;
    private Double cost;
    private Integer clientId;

    public Order(Integer id, String date, String type, Double cost, Integer clientId) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.cost = cost;
        this.clientId = clientId;
    }

    public Order(String type, Double cost, Integer clientId) {
        this.type = type;
        this.cost = cost;
        this.clientId = clientId;
    }

    public Order(String type, String date, Double cost, Integer clientId) {
        this.date = date;
        this.type = type;
        this.cost = cost;
        this.clientId = clientId;
    }

    public Order() {
    }

    public String[] getYear(){
        return date.split("[\\s\\-:]+");
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (type != null ? !type.equals(order.type) : order.type != null) return false;
        if (cost != null ? !cost.equals(order.cost) : order.cost != null) return false;
        return clientId != null ? clientId.equals(order.clientId) : order.clientId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                ", clientId=" + clientId +
                '}';
    }
}
