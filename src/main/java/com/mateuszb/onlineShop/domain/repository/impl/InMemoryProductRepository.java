package com.mateuszb.onlineShop.domain.repository.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mateuszb.onlineShop.domain.Product;
import com.mateuszb.onlineShop.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
public class InMemoryProductRepository implements ProductRepository {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	private List<Product> listOfProducts = new ArrayList<Product>();

	public List<Product> getAllProducts() {
		Product productById;

		String sqlStatement = "SELECT A.PRODUCTID AS productId, A.NAME AS name, A.UNITPRICE AS unitPrice, " +
				"A.DESCRIPTION AS description, A.UNITSINSTOCK AS unitsInStock," +
				"B.NAME AS category, C.NAME AS manufacture FROM PRODUCT A " +
				"JOIN PRODUCT_CATEGORIES B ON A.CATEGORY_ID = B.ID " +
				"JOIN PRODUCT_MANUFACTURES C on A.MANUFACTURER_ID = C.ID";

		Connection connection = null;

		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareCall(sqlStatement);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				productById = new Product(rs.getString("productId"), rs.getString("name"), rs.getBigDecimal("unitPrice"));
				productById.setDescription(rs.getString("description"));
				productById.setCategory(rs.getString("category"));
				productById.setManufacturer(rs.getString("manufacture"));
				productById.setUnitsInStock(rs.getLong("unitsInStock"));
				if (!listOfProducts.contains(productById)){
					listOfProducts.add(productById);
				}
			}

			rs.close();
			ps.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try{
					connection.close();
				} catch (SQLException e) { }
			}
		}

		return listOfProducts;
	}

	public Product getProductById(String productId) {
		Product productById = null;

		String sqlStatement = "SELECT A.PRODUCTID AS productId, A.NAME AS name, A.UNITPRICE AS unitPrice, " +
				"A.DESCRIPTION AS description, A.UNITSINSTOCK AS unitsInStock," +
				"B.NAME AS category, C.NAME AS manufacture FROM PRODUCT A " +
				"JOIN PRODUCT_CATEGORIES B ON A.CATEGORY_ID = B.ID " +
				"JOIN PRODUCT_MANUFACTURES C on A.MANUFACTURER_ID = C.ID WHERE A.PRODUCTID=?";

		Connection connection = null;

		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareCall(sqlStatement);
			ps.setString(1,productId);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				productById = new Product(rs.getString("productId"), rs.getString("name"), rs.getBigDecimal("unitPrice"));
				productById.setDescription(rs.getString("description"));
				productById.setCategory(rs.getString("category"));
				productById.setManufacturer(rs.getString("manufacture"));
				productById.setUnitsInStock(rs.getLong("unitsInStock"));
			}

			rs.close();
			ps.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try{
					connection.close();
				} catch (SQLException e) { }
			}
		}

		if(productById == null){
			throw new IllegalArgumentException("Brak produktu o wskazanym id: "+ productId);
		}

		return productById;
	}

	public List<Product> getProductsByCategory(String category) {
		List<Product> productsByCategory = new ArrayList<Product>();

		listOfProducts = getAllProducts();

		for(Product product: listOfProducts) {
			if(category.equalsIgnoreCase(product.getCategory())){
				productsByCategory.add(product);
			}
		}

		return productsByCategory;
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();

		Set<String> criterias = filterParams.keySet();

		listOfProducts = getAllProducts();

		if(criterias.contains("brand")) {
			for(String brandName: filterParams.get("brand")) {
				for(Product product: listOfProducts) {
					if(brandName.equalsIgnoreCase(product.getManufacturer())){
						productsByBrand.add(product);
					}
				}
			}
		}

		if(criterias.contains("category")) {
			for(String category: filterParams.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}

		productsByCategory.retainAll(productsByBrand);

		return productsByCategory;
	}

	public void addProduct(Product product) {
		Connection connection = null;
		String sqlStatement;
		ResultSet rs;

		// sprawdzamy czy podany typ urządzenia już istnieje w bazie
		// jeżeli tak to nie robimy nic
		// jeżeli nie to dodajemy tą pozycję do bazy
		sqlStatement = "SELECT * FROM PRODUCT_CATEGORIES WHERE NAME = ?";
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareCall(sqlStatement);
			ps.setString(1, product.getCategory());
			rs = ps.executeQuery();

			if(!rs.next()) {
				sqlStatement = "INSERT INTO PRODUCT_CATEGORIES" + "(NAME) VALUES (?)";
				try {
					connection = dataSource.getConnection();
					ps = connection.prepareCall(sqlStatement);
					ps.setString(1, product.getCategory());
					ps.executeUpdate();
					ps.close();
				} catch (SQLException e){
					throw new RuntimeException(e);
				} finally {
					if (connection != null) {
						try{
							connection.close();
						} catch (SQLException e) { }
					}
				}
			}
			rs.close();
			ps.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try{
					connection.close();
				} catch (SQLException e) { }
			}
		}


		// sprawdzamy czy podany producent już istnieje w bazie
		// jeżeli tak to nie robimy nic
		// jeżeli nie to dodajemy tą pozycję do bazy
		sqlStatement = "SELECT * FROM PRODUCT_MANUFACTURES WHERE NAME = ?";
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareCall(sqlStatement);
			ps.setString(1, product.getManufacturer());
			rs = ps.executeQuery();

			if(!rs.next()) {
				sqlStatement = "INSERT INTO PRODUCT_MANUFACTURES" + "(NAME) VALUES (?)";
				try {
					connection = dataSource.getConnection();
					ps = connection.prepareCall(sqlStatement);
					ps.setString(1, product.getManufacturer());
					ps.executeUpdate();
					ps.close();
				} catch (SQLException e){
					throw new RuntimeException(e);
				} finally {
					if (connection != null) {
						try{
							connection.close();
						} catch (SQLException e) { }
					}
				}
			}
			rs.close();
			ps.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try{
					connection.close();
				} catch (SQLException e) { }
			}
		}

		// pobieramy z bazy id kategorii produktu
		// 0 oznacza w bazie brak kategorii
		int category_id = 0;
		sqlStatement = "SELECT * FROM PRODUCT_CATEGORIES WHERE NAME = ?";
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareCall(sqlStatement);
			ps.setString(1, product.getCategory());
			rs = ps.executeQuery();

			if(rs.next()) {
				category_id = rs.getInt("id");
			}
			rs.close();
			ps.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try{
					connection.close();
				} catch (SQLException e) { }
			}
		}

		// pobieramy z bazy id producenta produktu
		// 0 oznacza w bazie brak producenta
		int manufactury_id = 0;
		sqlStatement = "SELECT * FROM PRODUCT_MANUFACTURES WHERE NAME = ?";
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareCall(sqlStatement);
			ps.setString(1, product.getManufacturer());
			rs = ps.executeQuery();

			if(rs.next()) {
				manufactury_id = rs.getInt("id");
			}
			rs.close();
			ps.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try{
					connection.close();
				} catch (SQLException e) { }
			}
		}

		//dodawanie do tabeli PRODUCT
		sqlStatement = "INSERT INTO PRODUCT (PRODUCTID, NAME, DESCRIPTION, UNITPRICE, UNITSINSTOCK, CATEGORY_ID, MANUFACTURER_ID) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareCall(sqlStatement);
			ps.setString(1, product.getProductId());
			ps.setString(2, product.getName());
			ps.setString(3, product.getDescription());
			ps.setBigDecimal(4, product.getUnitPrice());
			ps.setLong(5, product.getUnitsInStock());
			ps.setInt(6, category_id);
			ps.setInt(7, manufactury_id);

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e){
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try{
					connection.close();
				} catch (SQLException e) { }
			}
		}
	}
}
