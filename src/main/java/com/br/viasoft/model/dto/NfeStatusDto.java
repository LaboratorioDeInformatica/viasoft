package com.br.viasoft.model.dto;

import com.br.viasoft.model.enumerations.AvaliableStatusEnum;
import com.br.viasoft.model.enumerations.StateEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NfeStatusDto {

    private String state;

    private String status;

    private String momentColected;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate filterDate ;

}
