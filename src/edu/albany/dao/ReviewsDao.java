/**
 * 
 */
package edu.albany.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.albany.bean.Orders;
import edu.albany.utils.DBConn;

/**
 * @author Yu Zhang
 *
 */
public class ReviewsDao {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	DBConn db = new DBConn();

	private void release(ResultSet rs, PreparedStatement pstmt) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public List<Orders> searchProductReviews(Integer sellerId) {

		List<Orders> lists = new ArrayList<Orders>();
		String sql = "select order_id, username, review "
				+ "from orders o, users u " + "where o.buyer_id=u.user_id "
				+ "and o.seller_id=" + sellerId;

		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Orders orders = new Orders();
				orders.setOrderId(rs.getInt("order_id"));
				orders.setBuyerName(rs.getString("username"));
				orders.setReview(rs.getString("review"));
				lists.add(orders);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		release(rs, pstmt);

		return lists;

	}

	public List<Orders> searchReviews(Integer productId) {

		List<Orders> lists = new ArrayList<Orders>();
		String sql = "select order_id, username, review "
				+ "from orders o, users u " + "where o.buyer_id=u.user_id "
				+ "and o.product_id=" + productId;

		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Orders orders = new Orders();
				orders.setOrderId(rs.getInt("order_id"));
				orders.setBuyerName(rs.getString("username"));
				orders.setReview(rs.getString("review"));
				lists.add(orders);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		release(rs, pstmt);

		return lists;

	}
}
