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
		Product productById = null;

		String sqlStatement = "SELECT a.id AS id, a.name AS name, a.unitPrice AS unitPrice, " +
				"a.description AS description, a.unitsInStock AS unitsInStock," +
				"b.name AS category, c.name AS manufacture FROM PRODUCT a " +
				"JOIN PRODUCT_CATEGORIES b ON a.category_id = b.id " +
				"JOIN PRODUCT_MANUFACTURES c on a.manufacturer_id = c.id";

		Connection connection = null;

		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareCall(sqlStatement);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				productById = new Product(Integer.toString(rs.getInt("id")), rs.getString("name"), rs.getBigDecimal("unitPrice"));
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
		String sqlStatement = "SELECT a.id AS id, a.name AS name, a.unitPrice AS unitPrice, " +
				"a.description AS description, a.unitsInStock AS unitsInStock," +
				"b.name AS category, c.name AS manufacture FROM PRODUCT a " +
				"JOIN PRODUCT_CATEGORIES b ON a.category_id = b.id " +
				"JOIN PRODUCT_MANUFACTURES c on a.manufacturer_id = c.id WHERE a.ID = ?";

		Connection connection = null;

		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareCall(sqlStatement);
			ps.setInt(1, Integer.parseInt(productId));
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				productById = new Product(Integer.toString(rs.getInt("id")), rs.getString("name"), rs.getBigDecimal("unitPrice"));
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
		System.out.println("ProductID: " + product.getProductId() + ". Nazwa:" + product.getName() + ". Cena: " +
			product.getUnitPrice() + ". Opis: " + product.getDescription() + ". Producent: " + product.getManufacturer() +
				". Kategoria: " + product.getCategory() + ". UnitsInStock: " + product.getUnitsInStock() + ". Cindition: " +
					product.getCondition() + ". ");
	}
}
