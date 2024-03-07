package br.com.alura.comex.module.Classes;

import javax.persistence.*;

@Entity
@Table(name = "item_pedido")
public class OrderItems {
    @Column(name = "preco_unitario")
    private double UnitPrice;
    @Column(name = "quantidade")
    private int amount;
    @ManyToMany
    private Product product;
    @ManyToOne
    private Order order;
    @Column(name = "desconto")
    private double discount;
    @Column(name = "fidelidade")
    private String discountType;

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
}
