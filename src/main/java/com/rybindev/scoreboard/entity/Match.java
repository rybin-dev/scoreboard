package com.rybindev.scoreboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data

public class Match implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Player first;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Player second;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Player winner;

}
