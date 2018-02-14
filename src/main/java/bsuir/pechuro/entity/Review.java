package bsuir.pechuro.entity;

/**
 * class Review created to work with reviews
 */
public class Review {
    private String text;
    private Double mark;
    private Integer clientId;
    private String clientName;
    private String clientSurname;
    private Integer reviewId;

    public Review() {}

    public Review(String text, Double mark, Integer clientId) {
        this.text = text;
        this.mark = mark;
        this.clientId = clientId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (text != null ? !text.equals(review.text) : review.text != null) return false;
        if (mark != null ? !mark.equals(review.mark) : review.mark != null) return false;
        if (clientId != null ? !clientId.equals(review.clientId) : review.clientId != null) return false;
        if (clientName != null ? !clientName.equals(review.clientName) : review.clientName != null) return false;
        if (clientSurname != null ? !clientSurname.equals(review.clientSurname) : review.clientSurname != null)
            return false;
        return reviewId != null ? reviewId.equals(review.reviewId) : review.reviewId == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (clientSurname != null ? clientSurname.hashCode() : 0);
        result = 31 * result + (reviewId != null ? reviewId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "text='" + text + '\'' +
                ", mark=" + mark +
                ", clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", reviewId=" + reviewId +
                '}';
    }
}
