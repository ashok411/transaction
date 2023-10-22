package com.ashok.transaction.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "person")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE shipment SET deleted = 1 WHERE id=?")
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonNaming(SnakeCaseStrategy.class)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    // getters, setters, and constructors
}
