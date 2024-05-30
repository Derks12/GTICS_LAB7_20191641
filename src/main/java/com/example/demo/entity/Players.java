package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "players")
@Getter
@Setter

public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerId", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "mmr")
    private BigInteger mmr;

    @Column(name = "position")
    private Integer position;

    @Column(name = "region")
    private String region;



}
