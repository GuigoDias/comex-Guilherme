package br.com.alura.comex.module.Classes.ConnectionWithDB.ServiceWithOrder;

import br.com.alura.comex.module.Classes.Client;
import br.com.alura.comex.module.Classes.Order;
import br.com.alura.comex.module.Classes.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DAOOrder {
    private Connection conn;
    DAOOrder(Connection connection){
        this.conn = connection;
    }

    public void save(Order order){
        String sql = "INSERT INTO pedido (cliente_id, preco, quantidade, produto_id, data, desconto, fidelidade) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setObject(1,order.getClient());
            preparedStatement.setDouble(2, order.getPrice());
            preparedStatement.setInt(3, order.getAmount());
            preparedStatement.setObject(4, order.getProduct());
            preparedStatement.setObject(5, order.getDay());
            preparedStatement.setDouble(6, order.getDiscount());
            preparedStatement.setString(7, order.getDiscountType());

            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Order> show(){
        PreparedStatement ps;
        ResultSet rs;
        Set<Order> products = new HashSet<>();
        String sql = "SELECT * from produtos";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Client client = (Client) rs.getObject(1);
                double price = rs.getDouble(2);
                int amount = rs.getInt(3);
                Product product = (Product) rs.getObject(4);
                LocalDate day = (LocalDate) rs.getObject(5);
                double discount = rs.getDouble(6);
                String discountType = rs.getString(7);

                products.add(new Order(client,price,amount,product,day,discount,discountType));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void alter(String sql, int number, double update) {
        PreparedStatement ps;

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setDouble(1, update);
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

    public Order orderListener(int idOrder) {
        String sql = "SELECT * FROM produtos WHERE id = " + idOrder;

        Statement ps;
        ResultSet rs;
        Order order = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                Client client = (Client) rs.getObject(1);
                double price = rs.getDouble(2);
                int amount = rs.getInt(3);
                Product product = (Product) rs.getObject(4);
                LocalDate day = (LocalDate) rs.getObject(5);
                double discount = rs.getDouble(6);
                String discountType = rs.getString(7);

                order = new Order(client,price,amount,product,day,discount,discountType);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    public void delete(int idOrder){
        String sql = "DELETE FROM pedido WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idOrder);

            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
