package edu.tudai.arq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class ProductoMayorRecaudacionDTO {

    private int idProducto;

    @Setter
    private String nombre;

    @Setter
    private float valor;

    @Setter
    private float recaudacion;

}
