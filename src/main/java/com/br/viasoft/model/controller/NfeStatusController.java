package com.br.viasoft.model.controller;


import com.br.viasoft.model.dto.NfeStatusDto;
import com.br.viasoft.model.mapper.NfeStatusServiceToNfeStatusServiceDtoMapper;
import com.br.viasoft.model.service.NfeStatusServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class NfeStatusController {

    private final NfeStatusServiceService nfeStatusServiceService;


    private final NfeStatusServiceToNfeStatusServiceDtoMapper mapperToNfeStatusDto;

    public NfeStatusController(NfeStatusServiceService nfeStatusServiceService, NfeStatusServiceToNfeStatusServiceDtoMapper mapperToNfeStatusDto) {
        this.nfeStatusServiceService = nfeStatusServiceService;
        this.mapperToNfeStatusDto = mapperToNfeStatusDto;
    }

    @GetMapping
    public List<NfeStatusDto> findAll(){
        return mapperToNfeStatusDto.mapToNfeStatusDtos(nfeStatusServiceService.getAllStatesActualStatus());
    };

    @GetMapping("state")
    public NfeStatusDto findByFilterByState( NfeStatusDto dto){
        return mapperToNfeStatusDto.map(nfeStatusServiceService.findByState(dto));
    }

    @GetMapping("date")
    public List<NfeStatusDto> findByFilterByDate(NfeStatusDto dto){
        return mapperToNfeStatusDto.mapToNfeStatusDtos(nfeStatusServiceService.getAllStatesStatusByDate(dto));
    }
    @GetMapping("unavaliable")
    public String findUnavaliableService(){
        return nfeStatusServiceService.getUnavaliableService();
    }
}
