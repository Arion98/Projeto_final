package com.agenda_service_back.telefone;

import com.agenda_service_back.cliente.Cliente;
import com.agenda_service_back.prestador.Prestador;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "telefones")
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telefone_id;
    @Column(nullable = false, length = 45)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "(XX) XXXXX-XXXX")
    private String telefone_numero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telefone_cliente_id")
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telefone_prestador_id")
    private Prestador prestador;

    @Override
    public String toString() {
        return "{" +
            " telefone_id='" + getTelefone_id() + "'" +
            ", telefone_numero='" + getTelefone_numero() + "'" +
           
            "}";
    }




}
