package edu.tudai.arq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cliente {

    private Long id;
    private String nombre;
    private String email;

}