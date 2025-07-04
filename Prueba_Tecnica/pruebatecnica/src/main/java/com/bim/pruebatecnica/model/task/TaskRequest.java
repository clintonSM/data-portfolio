package com.bim.pruebatecnica.model.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TaskRequest {
    String id;
    String title;
    String detail;
}
