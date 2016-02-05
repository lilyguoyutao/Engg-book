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
public class OrdersDao {

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

	public List<Orders> searchSellerOrders(int sellerId) {
		List<Orders> lists = new ArrayList<Orders>();
		String sql = "select distinct order_id, p.product_name, u.username, order_status, o.quantity, o.price "
				+ "from orders o, users u, products p "
				+ "where p.product_id=o.product_id and o.buyer_id=u.user_id and order_id in ("
				+ "select o.order_id from users u where o.seller_id=u.user_id and u.user_id="
				+ sellerId + ")";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Orders orders = new Orders();
				orders.setOrderId(rs.getInt("order_id"));
				orders.setProductName(rs.getString("product_name"));
				orders.setBuyerName(rs.getString("username"));
				orders.setOrderStatus(rs.getInt("order_status"));
				orders.setQuantity(rs.getInt("quantity"));
				orders.setPrice(rs.getFloat("price"));
				lists.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return lists;
	}

	public void shippedOrder(int orderId) {
		String sql = "update orders set order_status=2 where order_id="
				+ orderId;
		db.doUpdate(sql);
		release(rs, pstmt);
	}

	public List<Orders> searchManagerOrders() {
		List<Orders> lists = new ArrayList<Orders>();
		String sql = "select order_id, p.product_name, u.username, order_status, o.quantity, o.price "
				+ "from orders o, users u, products p "
				+ "where p.product_id=o.product_id and o.buyer_id=u.user_id";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Orders orders = new Orders();
				orders.setOrderId(rs.getInt("order_id"));
				orders.setProductName(rs.getString("product_name"));
				orders.setBuyerName(rs.getString("username"));
				orders.setOrderStatus(rs.getInt("order_status"));
				orders.setQuantity(rs.getInt("quantity"));
				orders.setPrice(rs.getFloat("price"));
				lists.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return lists;
	}

	public void closeOrder(int orderId) {
		String sql = "update orders set order_status=3 where order_id="
				+ orderId;
		db.doUpdate(sql);
		release(rs, pstmt);
	}

}
