package com.mkowalski.nb.etl.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GlobalErrorsDto implements Serializable{

    private List<RestErrorDto> errors;

    public GlobalErrorsDto(RestErrorDto error){
        errors = new ArrayList<>();
        errors.add(error);
    }
}
