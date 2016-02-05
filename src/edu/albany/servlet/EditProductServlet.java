/**
 * 
 */
package edu.albany.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import edu.albany.bean.Products;
import edu.albany.dao.ProductsDao;

/**
 * @author Yu Zhang
 *
 */
public class EditProductServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		SmartUpload sUpload = new SmartUpload();
		PageContext pageContext = JspFactory.getDefaultFactory()
				.getPageContext(this, request, response, "", true, 8192, true);
		Request sRequest = sUpload.getRequest();
		sUpload.initialize(pageContext);
		try {
			sUpload.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		ProductsDao pDao = new ProductsDao();
		Integer productId = Integer
				.parseInt(sRequest.getParameter("productId"));

		String submit = sRequest.getParameter("submit");
		if (submit != null && submit.equals("Submit")) {

			Integer sellerId = Integer.parseInt(sRequest
					.getParameter("sellerId"));
			String productName = sRequest.getParameter("productName");
			float price = 0.00f;
			if (sRequest.getParameter("price") != null) {
				price = Float.parseFloat(sRequest.getParameter("price"));
			}
			String description = sRequest.getParameter("description");
			String depName = sRequest.getParameter("departmentName");

			File sFile = sUpload.getFiles()
					.getFile(0);
			String fileName = sFile.getFileName();

			byte[] fileContent = new byte[(int) sFile.getSize()];

			for (int k = 0; k < (int) sFile.getSize(); k++) {
				fileContent[k] = sFile.getBinaryData(k);
			}
			InputStream is = null;
			if (!sFile.isMissing()) {
				is = new ByteArrayInputStream(fileContent);
			}
			try {
				if (sFile.getSize() > 0) {
					sFile.saveAs("/product_images" + java.io.File.separator
							+ fileName);
					String image = fileName;
					pDao.editProduct(productId, productName, price,
							description, depName, image);
				} else {
					pDao.editProductWithoutImage(productId, productName, price,
							description, depName);
				}
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("msg", "Edit product successfully.");
			request.getRequestDispatcher("editProduct.jsp").forward(request,
					response);
		} else {
			Products product = pDao.getProduct(productId);
			request.setAttribute("product", product);
			request.getRequestDispatcher("editProduct.jsp").forward(request,
					response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

}
