package br.com.alura.comex.module.Classes;

import br.com.alura.comex.module.Classes.CEP.RecordCEP;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Endereco")
public class Address {
    @Id
    private String cep;
    @Column(name = "bairro")
    private String district;
    @Column(name = "cidade")
    private String city;
    @Column(name = "complemento")
    private String complement;
    @Column(name = "estado")
    private String state;
    @Column(name = "rua")
    private String street;
    @Column(name = "numero")
    private int number;

    public Address(){
    }

    public Address(RecordCEP recordCEPConstuctor){
        this.cep = recordCEPConstuctor.cep();
        this.street = recordCEPConstuctor.street();
        this.complement = recordCEPConstuctor.complement();
        this.district = recordCEPConstuctor.district();
        this.city = recordCEPConstuctor.city();
        this.state = recordCEPConstuctor.uf();
    }

    public Address(String cep, String street, String complement,
                   String district, String city, String uf){
        this.cep = cep;
        this.street = street;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String transformNumber(int number){
        if (number == 0){
            return "Number Not Found";
        } else {
            return String.valueOf(number);
        }
    }

    @Override
    public String toString() {
        return cep + "," +
                street + "," +
                transformNumber(number) + "," +
                complement + "," +
                district + "," +
                city + "," +
                state;
    }
}
