package com.agenda_service_back.agendamento;

import com.agenda_service_back.cliente.ClienteDTO;
import com.agenda_service_back.enums.StatusEnum;

import com.agenda_service_back.servico.ServicoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long agendamento_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime agendamento_hora;
    
    private LocalDate dataAgendamento;

    private String agendamento_observacoes;

    private StatusEnum agendamento_status;

    private ServicoDTO servico;

    private ClienteDTO cliente;

 


    @Override
    public String toString() {
        return "{" +
            " agendamento_id='" + getAgendamento_id() + "'" +
            ", agendamento_hora='" + getAgendamento_hora() + "'" +
            ", agendamento_observacoes='" + getAgendamento_observacoes() + "'" +
            ", agendamento_status='" + getAgendamento_status() + "'" +
            ", dataAgendamento='" + getDataAgendamento() + "'" +
                    "}";
    }

}
