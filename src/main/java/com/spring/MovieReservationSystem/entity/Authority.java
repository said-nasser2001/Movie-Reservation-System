package com.spring.MovieReservationSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name="authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Customer customer;

    public Authority() {
    }

    public Authority(String name, Customer customer) {
        this.name = name;
        this.customer = customer;
    }
}
