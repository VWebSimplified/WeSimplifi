package com.Wesimplifi.WeSimplifiFirstProject.entity;

import jakarta.persistence.*;
import org.apache.tomcat.util.digester.SystemPropertySource;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "USER")
public class User {

    @Column(name = "ID", unique = true, updatable = false)
    private Long id;

    @Id
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;


    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "City")
    private String city;

    @Column(name = "Promocode")
    private String promoCode;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "CREATE_DATE", updatable = false)
    private Timestamp createDate;

    @Column(name = "UPDATE_DATE", updatable = true)
    private Timestamp updateDate;// LocalDate- TimeStamp, always Time Stamp

    @Column(name = "REFERENCE_COUNTER")
    private int referenceCounter=0;

    @Column(name = "REFER_BY")
    private Long referBy;

    public String getName() {
        return name;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }




//    @ManyToOne
//    @JoinColumn(name = "referredByUserWithId")  // This creates the foreign key to another User
//    private User referenceByUser;  // This is a reference to another User, not
//
//    private int refernceCounter =0;




    public User(String email, String promoCode, int counter) {
        this.email = email;
        this.promoCode = promoCode;
        this.counter = counter;
    }



    private int counter = 0;


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int increaseCounterValueByOne(){
        this.counter++;
        return counter;

    }

    public int getReferenceCounter() {
        return referenceCounter;
    }

    public void setReferenceCounter(int referenceCounter) {
        this.referenceCounter = referenceCounter;
    }

    public User(){// if post me object use kr rhe hai to there should be an emppty construcot

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public User(String email, String promoCode) {
        this.email = email;
        this.promoCode = promoCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Timestamp getCreateDate(){
        return createDate;
    }

    public void setCreateDate(Timestamp createDate){
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @PrePersist// @PrePersist: Automatically sets createDate when a new record is inserted into the database.
    public void onCreate(){
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        this.createDate = currentTimeStamp;
        this.updateDate = currentTimeStamp;
        if (id == null) {
            id = System.currentTimeMillis(); // Or another method to generate the id
        }

    }

    @PreUpdate
    public void onUpdate(){
        this.updateDate = new Timestamp(System.currentTimeMillis());
    }




    public void setName(String name) {
    this.name = name;
    }

    public int increaseReferenceCounterByOne() {
        this.referenceCounter++;
       return referenceCounter;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public Long getReferBy() {
        return referBy;
    }

    public void setReferBy(Long referBy) {
        this.referBy = referBy;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", promoCode='" + promoCode + '\'' +
                '}';
    }



}


