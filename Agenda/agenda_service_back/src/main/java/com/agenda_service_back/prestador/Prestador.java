package com.agenda_service_back.prestador;

import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.telefone.Telefone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prestador")
public class Prestador implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long prestador_id;
    private String prestador_nome;
    
    @Column(unique = true)
    private String prestador_cnpj;
    
    @Column(unique = true)
    private String prestador_cpf;
    private String prestador_razaoSocial;
    
    @Column(unique = true)
    private String prestadorEmail;
    private String prestador_senha;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Servico> servico;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prestador_endereco_id")
    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Telefone> telefone;

    @Override
    public String toString() {
        return "{" +
            " prestador_id='" + getPrestador_id() + "'" +
            ", prestador_nome='" + getPrestador_nome() + "'" +
            ", prestador_cnpj='" + getPrestador_cnpj() + "'" +
            ", prestador_cpf='" + getPrestador_cpf() + "'" +
            ", prestador_razaoSocial='" + getPrestador_razaoSocial() + "'" +
            ", prestador_email='" + getPrestadorEmail() + "'" +
            ", prestador_senha='" + getPrestador_senha() + "'" +
            "}";
    }
}
