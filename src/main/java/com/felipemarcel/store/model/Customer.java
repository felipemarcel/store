package com.felipemarcel.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(value = "firstName")
    @Column(name = "first_name")
    @NotNull
    @NotEmpty
    private String firstName;

    @JsonProperty("lastName")
    @Column(name = "last_name")
    @NotNull
    @NotEmpty
    private String lastName;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    Customer(Long id) {
        this.id = id;
    }

    Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
