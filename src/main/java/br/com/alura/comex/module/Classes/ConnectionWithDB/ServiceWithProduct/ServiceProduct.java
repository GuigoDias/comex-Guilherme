package br.com.alura.comex.module.Classes.ConnectionWithDB.ServiceWithProduct;

import br.com.alura.comex.module.Classes.Category;
import br.com.alura.comex.module.Classes.ConnectionWithDB.ConnectionFactory;
import br.com.alura.comex.module.Classes.Product;

import java.sql.Connection;
import java.util.Set;

public class ServiceProduct {
    private ConnectionFactory connection;

    public ServiceProduct() {
        this.connection = new ConnectionFactory();
    }
    public void openCategory(Product product) {
        Connection conn = connection.reconnection();
        new DAOProduct(conn).save(product);
    }

    public Set<Product> showCategory(){
        Connection conn = connection.reconnection();
        return new DAOProduct(conn).show();
    }

    public void alterDescription(int number, String update){
        Connection conn = connection.reconnection();
        String sql = "UPDATE produtos SET descricao = ? WHERE id = ?";
        new DAOProduct(conn).alter(sql, number,update);
    }

    public void alterName(int number, String update){
        Connection conn = connection.reconnection();
        String sql = "UPDATE produtos SET nome = ? WHERE id = ?";
        new DAOProduct(conn).alter(sql,number,update);
    }

    private Product findWithId(int idProduct) {
        Connection conn = connection.reconnection();
        Product product = new DAOProduct(conn).productListener(idProduct);
        if(product != null) {
            return product;
        } else {
            throw new RuntimeException("Não existe produto cadastrado com esse número!");
        }
    }

    public void deleteProduct(int idProduct){
        Connection conn = connection.reconnection();
        new DAOProduct(conn).delete(idProduct);
    }
}

