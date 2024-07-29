package com.agenda_service_back.horarioatendimento;

import com.agenda_service_back.prestador.Prestador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horarios_atendimento")
public class HorarioAtendimento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long horario_id;

    private DayOfWeek diaSemana;

    private LocalTime horarioAbertura;

    private LocalTime horarioFechamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;
}
