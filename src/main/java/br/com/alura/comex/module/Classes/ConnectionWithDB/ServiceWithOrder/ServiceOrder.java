package br.com.alura.comex.module.Classes.ConnectionWithDB.ServiceWithOrder;

import br.com.alura.comex.module.Classes.ConnectionWithDB.ConnectionFactory;
import br.com.alura.comex.module.Classes.Order;

import java.sql.Connection;
import java.util.Set;

public class ServiceOrder {
    private ConnectionFactory connection;

    public ServiceOrder() {
        this.connection = new ConnectionFactory();
    }
    public void openCategory(Order order) {
        Connection conn = connection.reconnection();
        new DAOOrder(conn).save(order);
    }

    public Set<Order> showOrder(){
        Connection conn = connection.reconnection();
        return new DAOOrder(conn).show();
    }

    public void alterPrice(int number, double update){
        Connection conn = connection.reconnection();
        String sql = "UPDATE pedido SET preco = ? WHERE id = ?";
        new DAOOrder(conn).alter(sql, number,update);
    }

    public void alterDiscount(int number, double update){
        Connection conn = connection.reconnection();
        String sql = "UPDATE pedido SET desconto = ? WHERE id = ?";
        new DAOOrder(conn).alter(sql,number,update);
    }

    private Order findWithId(int idOrder) {
        Connection conn = connection.reconnection();
        Order order = new DAOOrder(conn).orderListener(idOrder);
        if(order != null) {
            return order;
        } else {
            throw new RuntimeException("Não existe pedido cadastrado com esse número!");
        }
    }

    public void deleteOrder(int idOrder){
        Connection conn = connection.reconnection();
        new DAOOrder(conn).delete(idOrder);
    }
}
