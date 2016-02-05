/**
 * 
 */
package edu.albany.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.albany.bean.Products;
import edu.albany.utils.DBConn;

/**
 * @author Yu Zhang
 *
 */
public class ProductsDao {

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

	public List<Products> searchAllSellerProducts(Integer sellerId) {
		List<Products> lists = new ArrayList<Products>();
		String sql = "select product_id, product_name, price, description, dep_name, image "
				+ "from products p " + "where p.seller_id=" + sellerId;
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Products products = new Products();
				products.setProductName(rs.getString("product_name"));
				products.setPrice(rs.getFloat("price"));
				products.setDescription(rs.getString("description"));
				products.setProductId(rs.getInt("product_id"));
				products.setDepName(rs.getString("dep_name"));
				products.setImage(rs.getString("image"));
				lists.add(products);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return lists;
	}

	public List<Products> searchSellerProducts(Integer sellerId,
			String productName, String depName) {
		List<Products> lists = new ArrayList<Products>();
		String sql = "select product_id, product_name, price, description, dep_name, image "
				+ "from products p "
				+ "where p.seller_id="
				+ sellerId
				+ " and p.product_name like '%"
				+ productName
				+ "%' "
				+ " and p.dep_name like '%" + depName + "%'";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Products products = new Products();
				products.setProductName(rs.getString("product_name"));
				products.setPrice(rs.getFloat("price"));
				products.setDescription(rs.getString("description"));
				products.setProductId(rs.getInt("product_id"));
				products.setDepName(rs.getString("dep_name"));
				products.setImage(rs.getString("image"));
				lists.add(products);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return lists;
	}

	public void deleteProduct(int productId) {
		String deleteOrdersql = "delete from orders where product_id = "
				+ productId;
		db.doUpdate(deleteOrdersql);
		String deleteProductSql = "delete from products where product_id = "
				+ productId;
		db.doUpdate(deleteProductSql);
		release(rs, pstmt);
	}

	public List<Products> searchManagerProducts(String productName,
			String depName) {
		List<Products> lists = new ArrayList<Products>();
		String sql = "select product_id, product_name, price, description, dep_name, image "
				+ "from products p "
				+ "where p.product_name like '%"
				+ productName
				+ "%' "
				+ " and p.dep_name like '%"
				+ depName
				+ "%'";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Products products = new Products();
				products.setProductName(rs.getString("product_name"));
				products.setPrice(rs.getFloat("price"));
				products.setDescription(rs.getString("description"));
				products.setProductId(rs.getInt("product_id"));
				products.setDepName(rs.getString("dep_name"));
				products.setImage(rs.getString("image"));
				lists.add(products);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return lists;
	}

	public List<Products> searchAllManagerProducts() {
		List<Products> lists = new ArrayList<Products>();
		String sql = "select product_id, product_name, price, description, dep_name, image "
				+ "from products p";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Products products = new Products();
				products.setProductName(rs.getString("product_name"));
				products.setPrice(rs.getFloat("price"));
				products.setDescription(rs.getString("description"));
				products.setProductId(rs.getInt("product_id"));
				products.setDepName(rs.getString("dep_name"));
				products.setImage(rs.getString("image"));
				lists.add(products);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return lists;
	}

	public Products getProduct(Integer productId) {
		String sql = "select product_id, product_name, price, description, dep_name, image, seller_id "
				+ "from products p " + "where p.product_id=" + productId;
		Products products = new Products();
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			products.setProductName(rs.getString("product_name"));
			products.setPrice(rs.getFloat("price"));
			products.setDescription(rs.getString("description"));
			products.setProductId(rs.getInt("product_id"));
			products.setDepName(rs.getString("dep_name"));
			products.setImage(rs.getString("image"));
			products.setSellerId(rs.getInt("seller_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return products;
	}

	public int editProduct(int productId, String productName, float price,
			String description, String depName, String image) {
		String sql = "update products set product_name='" + productName
				+ "', price=" + price + ", " + "description='" + description
				+ "', dep_name='" + depName + "', image='" + image + "' "
				+ "where product_id=" + productId;
		int id = db.doUpdate(sql);
		release(rs, pstmt);
		return id;
	}

	public int editProductWithoutImage(int productId, String productName,
			float price, String description, String depName) {
		String sql = "update products set product_name='" + productName
				+ "', price=" + price + ", " + "description='" + description
				+ "', dep_name='" + depName + "' " + "where product_id="
				+ productId;
		int id = db.doUpdate(sql);
		release(rs, pstmt);
		return id;
	}

	public int addProduct(String productName, int sellerId, float price,
			String description, String depName, String image) {
		String sql = "insert into products(product_name, seller_id, price, description, dep_name, image) values("
				+ "'"
				+ productName
				+ "', "
				+ sellerId
				+ ", "
				+ price
				+ ", '"
				+ description + "', '" + depName + "', '" + image + "')";
		int id = db.doInsert(sql);
		release(rs, pstmt);
		return id;
	}
}
