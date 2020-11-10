package com.br.viasoft.model.mapper;

import com.br.viasoft.model.dto.NfeStatusDto;
import com.br.viasoft.model.entity.NfeStatusService;
import com.br.viasoft.utils.AbstractMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NfeStatusServiceToNfeStatusServiceDtoMapper implements AbstractMapper<NfeStatusService, NfeStatusDto> {

    public List<NfeStatusDto> mapToNfeStatusDtos(List<NfeStatusService> nfeStatusServices){
        return nfeStatusServices.stream().map( nfeStatusService ->{
            return map(nfeStatusService);
        }).collect(Collectors.toList());
    }

    @Override
    public NfeStatusDto map(NfeStatusService nfeStatusService) {
        if(nfeStatusService == null){
            return null;
        }
        return NfeStatusDto.builder()
                .state(nfeStatusService.getState().getState().toString())
                .status(nfeStatusService.getStatus().toString())
                .momentColected(this.formatData(nfeStatusService.getMomentColected()))
                .filterDate(nfeStatusService.getMomentColected().toLocalDate())
                .build();
    }

    private String formatData(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
