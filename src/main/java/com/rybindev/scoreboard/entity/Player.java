package com.rybindev.scoreboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Player", indexes = {@Index(name = "name_idx", columnList = "name")})
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Player implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;

    public Player(String name) {
        this.name = name;
    }
}
