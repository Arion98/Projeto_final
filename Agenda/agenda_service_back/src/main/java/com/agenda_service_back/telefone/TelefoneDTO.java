package com.agenda_service_back.telefone;

import com.agenda_service_back.cliente.Cliente;
import com.agenda_service_back.prestador.Prestador;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long telefone_id;

    @NotNull(message = "O campo NUMERO é requerido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "(XX) XXXXX-XXXX")
    private String telefone_numero;

    private Cliente cliente;

    private Prestador prestador;

    @Override
    public String toString() {
        return "{" +
            " telefone_id='" + getTelefone_id() + "'" +
            ", telefone_numero='" + getTelefone_numero() + "'" +
            
            "}";
    }


}
