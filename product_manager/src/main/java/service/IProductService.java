package service;

import model.Products;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {

    public boolean insertProduct(Products product) throws SQLException;

    public Products selectProduct(int id) throws SQLException;

    public List<Products> selectAllProducts() throws SQLException;

    public boolean deleteProduct(int id) throws SQLException;

    public boolean updateProduct(Products product) throws SQLException;

    public List<Products> findProduct(String name) throws SQLException;
}

