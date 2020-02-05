package com.chatelain.deliverbackend.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SingleResponseDTO extends ResponseDTO {

    private Object data;

    public SingleResponseDTO() {
        super();
    }

    public SingleResponseDTO(Integer code, String message) {
        super(code, message);
    }

    public SingleResponseDTO(Object data) {
        super();
        this.data = data;
    }

    public SingleResponseDTO(Integer code, String message, Object data) {
        super(code, message);
        this.data = data;
    }
}
