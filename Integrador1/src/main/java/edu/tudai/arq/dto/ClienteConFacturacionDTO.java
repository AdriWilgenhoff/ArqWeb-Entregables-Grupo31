package edu.tudai.arq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ClienteConFacturacionDTO {

    private int idCliente;

    @Setter
    private String nombre;

    @Setter
    private String email;

    @Setter
    private float totalFacturado;

}
