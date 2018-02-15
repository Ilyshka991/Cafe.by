package bsuir.pechuro.buisness.review.service.impl;

import bsuir.pechuro.buisness.review.dao.IReviewDao;
import bsuir.pechuro.buisness.review.service.IReviewService;
import bsuir.pechuro.entity.Review;
import bsuir.pechuro.exception.dao.DaoException;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.dao.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class ReviewService implements IReviewService {
    private static final Logger LOGGER = Logger.getLogger(ReviewService.class);
    private static final IReviewDao reviewDao = DaoFactory.getInstance().getReviewDao();


    @Override
    public boolean addReview(String text, Double mark, Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Review Service: start addReview");
        Review review = new Review(text, mark, clientId);
        try {
            LOGGER.log(Level.DEBUG, "Review Service: finish addReview");
            return reviewDao.addReview(review);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public boolean deleteReview(Integer reviewId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Review Service: start deleteReview");
        try {
            LOGGER.log(Level.DEBUG, "Review Service: finish deleteReview");
            return reviewDao.deleteReview(reviewId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public List<Review> getAllReviews() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Review Service: start getAllReviews");
        try {
            LOGGER.log(Level.DEBUG, "Review Service: finish getAllReviews");
            return reviewDao.getAllReviews();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}
