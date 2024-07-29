package com.agenda_service_back.enums;
public enum StatusEnum {
    ESPERA(0, "Em Espera"),
    ANDAMENTO(1, "Em Andamento"),
    FINALIZADO(2, "Finalizado");
    private Integer codigo;
    private String descricao;

    StatusEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    public static StatusEnum toEnum(Integer cod){
        if(cod == null){
            return null;
        } for (StatusEnum status: StatusEnum.values()) {
            if (cod.equals(status.getCodigo())){
                return status;
            }
        } throw new IllegalArgumentException("Status inválido"); }
}