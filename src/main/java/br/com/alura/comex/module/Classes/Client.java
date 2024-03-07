package br.com.alura.comex.module.Classes;

import br.com.alura.comex.module.Classes.Address;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "conta")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "cliente_nome")
    private String name;
    @Column(name = "cliente_cpf")
    private String cpf;
    @Column(name = "cliente_email")
    private String email;
    @Column(name = "profissao")
    private String profession;
    @Column(name = "telefone")
    private String telephone;
    @Column(name = "endereco")
    @ManyToMany
    private Address address;

    @Column(name = "saldo")
    private BigDecimal money;

    public Client(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Client(String name,String cpf, String email){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public Client(int id,BigDecimal money, String name, String cpf, String email){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.id = id;
        this.profession = "Unknown";
        this.telephone = "Unknown";
        this.address = null;
        this.money = money;
    }

    public Client(String name, String cpf){
        this.name = name;
        this.cpf = cpf;
        this.email = "Unknown";
        this.profession = "Unknown";
        this.telephone = "Unknown";
        this.address = null;
    }

    public Client(String name, String cpf, String email, String profession, String telephone, Address address){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.profession = profession;
        this.telephone = telephone;
        this.address = address;
    }

    public boolean moneyInAccount(){
        return this.getMoney().compareTo(BigDecimal.ZERO) != 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String replacedCpf(String cpf){
        String newCpf = cpf.replace("-","").replace(".","");
        return newCpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return "name: " + name +
                ", cpf: " + cpf +
                ", email: " + email +
                ", profession: " + profession +
                ", telephone: " + telephone +
                ", address: " + address;

    }
}
