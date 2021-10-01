package service;

import model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{
    private String jdbcURL = "jdbc:mysql://localhost:3306/product_manager";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";


    public ProductService() {
    }

    protected Connection connection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

//      `id` int NOT NULL,
//            `product_name` varchar(45) NOT NULL,
//  `price` double NOT NULL,
//            `quanlity` int NOT NULL,
//            `color` varchar(45) NOT NULL,
//  `category` int NOT NULL,

    String INSERT_PRODUCT_SQL = "INSERT INTO products" + "(product_name, price, quantity, color, category)VALUES" + "(?, ?, ?, ?, ?);";
    String SELECT_PRODUCT_BY_ID = "select id, product_name, price, quantity, color, category from products where id = ?";
    String DELETE_PRODUCT_SQL = "delete from products where id = ?;";
    String FIND_PRODUCT_BY_NAME = "select id, product_name, price, quantity, color, category from products where product_name like = '%?%';";
    String SELECT_ALL_PRODUCTS = "select * from products";
    String UPDATE_PRODUCT_SQL = "update products set name = ?, email = ?, phone = ?, address = ? where id = ?;";

    public boolean insertProduct(Products product) throws SQLException {
        boolean insert = false;
        System.out.println(INSERT_PRODUCT_SQL);
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setInt(5, product.getCategory());
            preparedStatement.executeUpdate();
            insert = true;
            return insert;
        } catch (SQLException e) {
            printSQLException(e);
            return insert;
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    public Products selectProduct(int id) throws SQLException {
        Products product = null;
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String productName = resultSet.getString("product_name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                int category = resultSet.getInt("category");

                product = new Products(id, productName, price, quantity, color, category);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    public List<Products> selectAllProducts() throws SQLException {
        List<Products> products = new ArrayList<>();
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("product_name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                int category = resultSet.getInt("category");

                products.add(new Products(id, productName, price, quantity, color, category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }


    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDelete;
        try (Connection connection = connection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            rowDelete = statement.executeUpdate() > 0;
        }
        return rowDelete;
    }


    public boolean updateProduct(Products product) throws SQLException {
        //                `product_name` varchar(45) NOT NULL,
//  `price` double NOT NULL,
//  `quantity` int NOT NULL,
//  `color` varchar(45) NOT NULL,
//  `category` int NOT NULL,
        boolean rowUpdate;
        try (Connection connection = connection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getColor());
            statement.setInt(4, product.getCategory());
//            statement.setDouble(5, customer.getBalance());
            statement.setInt(5, product.getId());

            rowUpdate = statement.executeUpdate() > 0;
        }
        return rowUpdate;
    }

    public List<Products> findProduct(String name) throws SQLException {
        List<Products> products = new ArrayList<>();
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_NAME);) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("product_name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                int category = resultSet.getInt("category");

                products.add(new Products(id, productName, price, quantity, color, category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }
}
