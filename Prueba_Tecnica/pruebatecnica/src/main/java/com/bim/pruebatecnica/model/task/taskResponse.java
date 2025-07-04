package com.bim.pruebatecnica.model.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class taskResponse {
    String id;
    String status;
}
