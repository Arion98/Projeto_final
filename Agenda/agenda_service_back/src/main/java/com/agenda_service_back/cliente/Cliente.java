package com.agenda_service_back.cliente;

import com.agenda_service_back.agendamento.Agendamento;
import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.telefone.Telefone;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliente_id;

    private String cliente_nome;

    @Column(unique = true, nullable = false)
    private String cliente_cpf;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String cliente_senha;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate cliente_dataNascimento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_endereco_id")
    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Telefone> telefone;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Agendamento> agendamento;

    @Override
    public String toString() {
        return "{" +
                " cliente_id='" + getCliente_id() + "'" +
                ", cliente_nome='" + getCliente_nome() + "'" +
                ", cliente_cpf='" + getCliente_cpf() + "'" +
                ", email='" + getEmail() + "'" +
                ", cliente_senha='" + getCliente_senha() + "'" +
                ", cliente_dataNascimento='" + getCliente_dataNascimento() + "'" +
                "}";
    }

}
