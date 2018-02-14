package bsuir.pechuro.buisness.review.dao;

import bsuir.pechuro.entity.Review;
import bsuir.pechuro.exception.dao.DaoException;

import java.util.List;

public interface IReviewDao {
    boolean addReview(Review review) throws DaoException;
    boolean deleteReview(Integer id) throws DaoException;
    List<Review> getAllReviews() throws DaoException;
}
