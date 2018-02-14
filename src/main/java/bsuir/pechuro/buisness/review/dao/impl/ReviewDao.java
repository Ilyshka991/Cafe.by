package bsuir.pechuro.buisness.review.dao.impl;

import bsuir.pechuro.buisness.review.dao.IReviewDao;
import bsuir.pechuro.connectionpool.ConnectionPool;
import bsuir.pechuro.entity.Review;
import bsuir.pechuro.exception.dao.ConnectionException;
import bsuir.pechuro.exception.dao.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * class ReviewDAO created for working with clients' reviews
 */
public class ReviewDao implements IReviewDao {
    private static final Logger LOGGER = Logger.getLogger(ReviewDao.class);
    private static final String ADD_REVIEW = "INSERT INTO cafeby.review (reviewText, reviewMark, clientId) VALUES (?, ?, ?)";
    private static final String DELETE_REVIEW = "DELETE FROM cafeby.review WHERE reviewId=?";
    private static final String EDIT_REVIEW = "UPDATE cafeby.review SET reviewText = ?, reviewMark = ? WHERE reviewId = ?";
    private static final String GET_ALL_REVIEWS = "SELECT * FROM cafeby.review";
    private ConnectionPool connectionPool;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * @param review
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean addReview(Review review) throws DaoException {
        LOGGER.log(Level.DEBUG, "ReviewDao: start addReview");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_REVIEW);
            statement.setString(1, review.getText());
            statement.setDouble(2, review.getMark());
            statement.setInt(3, review.getClientId());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "ReviewDao: success addReview");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "ReviewDao: unsuccess addReview");
                return false;
            }
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } catch (SQLException e) {
            return false;
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
    }

    /**
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean deleteReview(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "ReviewDao: start deleteReview");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_REVIEW);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "ReviewDao: success deleteReview");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "ReviewDao: unsuccess deleteReview");
                return false;
            }
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } catch (SQLException e) {
            return false;
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
    }

    /**
     * @return List<Review>
     * @throws DaoException
     */
    @Override
    public List<Review> getAllReviews() throws DaoException {
        LOGGER.log(Level.DEBUG, "ReviewDao: start getAllReviews");
        List<Review> reviews = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_REVIEWS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reviews.add(createReviewByResultSet(resultSet));
            }
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } catch (SQLException e) {
            return null;
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "ReviewDao: success getAllReviews");
        return reviews;
    }

    /**
     * @param resultSet
     * @return Review
     * @throws DaoException
     */
    private Review createReviewByResultSet(ResultSet resultSet) throws DaoException {
        Review review = new Review();
        try {
            review.setReviewId(resultSet.getInt("reviewId"));
            review.setClientId(resultSet.getInt("clientId"));
            review.setMark(resultSet.getDouble("reviewMark"));
            review.setText(resultSet.getString("reviewText"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return review;
    }
}
