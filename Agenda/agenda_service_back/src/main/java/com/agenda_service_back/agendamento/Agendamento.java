    package com.agenda_service_back.agendamento;

    import com.agenda_service_back.cliente.Cliente;
    import com.agenda_service_back.enums.StatusEnum;
    import com.agenda_service_back.servico.Servico;
    import com.fasterxml.jackson.annotation.JsonFormat;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.io.Serializable;
    import java.time.LocalDate;
    import java.time.LocalTime;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "agendamento")
    public class Agendamento implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long agendamento_id;

        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern ="HH:mm")
        private LocalTime agendamento_hora;
        private String agendamento_observacoes;
        
        @Enumerated(EnumType.STRING)
        private StatusEnum agendamento_status;
        
        private LocalDate dataAgendamento;
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "servico_id")
        private Servico servico;

        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "cliente_id")
        private Cliente cliente;

        
        @Override
        public String toString() {
            return "{" +
                " agendamento_id='" + getAgendamento_id() + "'" +
            
                ", agendamento_hora='" + getAgendamento_hora() + "'" +
                ", agendamento_observacoes='" + getAgendamento_observacoes() + "'" +
                ", agendamento_status='" + getAgendamento_status() + "'" +
                ", dataAgendamento='" + getDataAgendamento() + 
                "}";
        }

    }
