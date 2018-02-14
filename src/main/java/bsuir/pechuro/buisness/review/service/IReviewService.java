package bsuir.pechuro.buisness.review.service;

import bsuir.pechuro.entity.Review;
import bsuir.pechuro.exception.service.ServiceException;

import java.util.List;

public interface IReviewService {
    boolean addReview(String text, Double mark, Integer clientId) throws ServiceException;

    boolean deleteReview(Integer reviewId) throws ServiceException;

    List<Review> getAllReviews()throws ServiceException;
}
