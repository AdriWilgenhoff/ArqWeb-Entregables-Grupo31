package edu.tudai.arq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class FacturaProducto {

    private int idFactura;
    private int idProducto;
    private int cantidad;

}