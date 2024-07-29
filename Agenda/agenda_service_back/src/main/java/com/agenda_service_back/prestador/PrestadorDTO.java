package com.agenda_service_back.prestador;

import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.telefone.Telefone;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestadorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long prestador_id;

    @NotNull(message = "O campo NOME é requerido")
    private  String prestador_nome;

    private  String prestador_cnpj;

    @NotNull(message = "O campo CPF é requerido")
    private  String prestador_cpf;

    @NotNull(message = "O campo RAZAO SOCIAL é requerido")
    private  String prestador_razaoSocial;

    @NotNull(message = "O campo EMAIL é requerido")
    private  String prestadorEmail;

    private String prestador_senha;

    private Endereco endereco;

    private List<Servico> servico;

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
