package edu.tudai.arq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Producto{

    private int id;
    private String nombre;
    private Float valor;

}