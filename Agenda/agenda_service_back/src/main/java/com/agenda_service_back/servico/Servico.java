package com.agenda_service_back.servico;

import com.agenda_service_back.agendamento.Agendamento;
import com.agenda_service_back.categoria.Categoria;

import com.agenda_service_back.prestador.Prestador;
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
@Table(name = "servico")
public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long servico_id;

    private String servicoNome;

    private Double servico_preco;

    private String servico_descricao;

    private String servico_informacoesExtras;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Agendamento> agendamento;

    private String servico_classificacao;

    @Override
    public String toString() {
        return "{" +
            " servico_id='" + getServico_id() + "'" +
            ", servicoNome='" + getServicoNome() + "'" +
            ", servico_preco='" + getServico_preco() + "'" +
            ", servico_descricao='" + getServico_descricao() + "'" +
            ", servico_informacoesExtras='" + getServico_informacoesExtras() + " }";
           
    }

}
