package com.agenda_service_back.endereco;

import com.agenda_service_back.cliente.Cliente;
import com.agenda_service_back.prestador.Prestador;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@CrossOrigin(origins = "http://localhost:5173")
@Table(name = "endereco")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long endereco_id;

    private String endereco_rua;
    private String endereco_cep;

    @Column(unique = false)
    private Number endereco_numero;

    private String endereco_complemento;
    private String endereco_cidade;
    private String endereco_estado;
    private String endereco_bairro;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private List<Cliente> cliente;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private List<Prestador> prestador;

    @Override
    public String toString() {
        return "{" +
            " endereco_id='" + getEndereco_id() + "'" +
            ", endereco_rua='" + getEndereco_rua() + "'" +
            ", endereco_cep='" + getEndereco_cep() + "'" +
            ", endereco_numero='" + getEndereco_numero() + "'" +
            ", endereco_complemento='" + getEndereco_complemento() + "'" +
            ", endereco_cidade='" + getEndereco_cidade() + "'" +
            ", endereco_estado='" + getEndereco_estado() + "'" +
            ", endereco_bairro='" + getEndereco_bairro() + 
            "}";
    }

}
