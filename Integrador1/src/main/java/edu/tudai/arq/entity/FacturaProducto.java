package edu.tudai.arq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class FacturaProducto {


    private int idFactura;

    private int idProducto;

    @Setter
    private int cantidad;

}