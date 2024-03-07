package br.com.alura.comex.module.Classes;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name ="nome")
    private String name;
    @Column(name = "descricao")
    private String description;
    @Column(name = "preco_unitario")
    private double unitPrice;
    @Column(name = "quantidade")
    private int amount;
    @Column(name = "categoria_id")
    @ManyToOne
    private Category category;

    public Product(int id, String name, String description, double unitPrice, int amount, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product(){
        this.name = "Unnamed";
        this.description = "Without description";
        this.unitPrice = 0;
        this.amount = 0;
    }

    public Product(String name){
        this.name = name;
    }

    public Product(String name, String description, double unitPrice, int amount){
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getGrossValue(){
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "name: " + name +
                "\ndescription: " + description +
                "\nunitPrice: " + unitPrice +
                "\namount: " + amount;
    }
}