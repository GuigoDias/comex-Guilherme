package br.com.alura.comex.module.Classes;

import br.com.alura.comex.module.Classes.Client;
import br.com.alura.comex.module.Classes.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Order implements Comparable<Order>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private Client client;
    @Column(name = "preco")
    private double price;
    @Column(name = "quantidade")
    private int amount = 1;

    @ManyToMany
    private Product product;
    @Column(name = "data")
    private LocalDate day;
    @Column(name = "desconto")
    private double discount;
    @Column(name = "fidelidade")
    private String discountType;

    public Order(Client client, double price, int amount, Product product, LocalDate day, double discount, String discountType) {
        this.client = client;
        this.price = price;
        this.amount = amount;
        this.product = product;
        this.day = day;
        this.discount = discount;
        this.discountType = discountType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
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

    public Order(){

    }

    public Order(Product product, Client client, double price, int year, int month, int day){
        this.product = product;
        this.client = client;
        this.price = price;
        this.day = LocalDate.of(year,month,day);
    }

    public Order(Product product, Client client, double price,int amount, int year, int month, int day){
        this.product = product;
        this.client = client;
        this.price = price;
        this.amount = amount;
        this.day = LocalDate.of(year,month,day);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\n\nProduct:" + product.getName() +
                "\nclient: " + client.getName() +
                "\nprice: R$:" + getTotalPrice() +
                "\nDate: " + day;
    }

    public boolean CheaperThan(Order otherOrder) {
        if (this.price < otherOrder.price) {
            return true;
        } else{
            return false;
        }
    }

    public boolean MoreExpensiveThan(Order otherOrder){
        if (this.price > otherOrder.price){
            return true;
        } else{
            return false;
        }
    }

    public BigDecimal getTotalPrice() {
        BigDecimal priceBigDecimal = BigDecimal.valueOf(this.price);
        BigDecimal amountBigDecimal = BigDecimal.valueOf(this.amount);
        return priceBigDecimal.multiply(amountBigDecimal);
    }

    @Override
    public int compareTo(Order otherOrder) {
        return this.getTotalPrice().compareTo(otherOrder.getTotalPrice());
    }

}
