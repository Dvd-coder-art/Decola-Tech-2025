package br.com.dio.persistence.entity;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;


public enum OperationEnum {
    INSERT,
    UPDATE,
    DELETE;

    private String dbOperation;

    public static OperationEnum getByDbOperation(final String dbOperation){
        return Stream.of(OperationEnum.values())
                .filter(o->o.name().startsWith(dbOperation.toUpperCase()))
                .findFirst()
                .orElseThrow();
    }


}
