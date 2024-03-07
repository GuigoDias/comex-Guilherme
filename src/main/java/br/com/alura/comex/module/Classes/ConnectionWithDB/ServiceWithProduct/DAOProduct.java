package br.com.alura.comex.module.Classes.ConnectionWithDB.ServiceWithProduct;

import br.com.alura.comex.module.Classes.Category;
import br.com.alura.comex.module.Classes.Client;
import br.com.alura.comex.module.Classes.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DAOProduct {
    private Connection conn;
    DAOProduct(Connection connection){
        this.conn = connection;
    }

    public void save(Product product){
        String sql = "INSERT INTO produtos (nome, descricao, preco_unitario, quantidade, categoria_id) VALUES (?, ?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3,product.getUnitPrice());
            preparedStatement.setInt(4,product.getAmount());
            preparedStatement.setObject(5,product.getCategory());

            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Product> show(){
        PreparedStatement ps;
        ResultSet rs;
        Set<Product> products = new HashSet<>();
        String sql = "SELECT * from produtos";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                double unitPrice = rs.getDouble(4);
                int amount = rs.getInt(5);
                Category category = (Category) rs.getObject(6);

                products.add(new Product(id,name,description,unitPrice,amount,category));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void alter(String sql, int number, String update) {
        PreparedStatement ps;

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setString(1, update);
            ps.setInt(2, number);

            conn.commit();
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            try{
                conn.rollback();
            } catch (SQLException ex){
                throw new RuntimeException(e);
            }
        }
    }

    public Product productListener(int idProduct) {
        String sql = "SELECT * FROM produtos WHERE nome = " + idProduct;

        Statement ps;
        ResultSet rs;
        Product product = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                double unitPrice = rs.getDouble(4);
                int amount = rs.getInt(5);
                Category category = (Category) rs.getObject(6);

                product = new Product(id,name,description,unitPrice,amount,category);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public void delete(int idProduct){
        String sql = "DELETE FROM produtos WHERE nome = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idProduct);

            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}