package com.chatelain.deliverbackend.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArrayResponseDTO extends ResponseDTO {

    private List<Object> list;

    public ArrayResponseDTO(List<Object> list) {
        super();
        this.list = list;
    }

    public ArrayResponseDTO(Integer code, String message, List<Object> list) {
        super(code, message);
        this.list = list;
    }
}
