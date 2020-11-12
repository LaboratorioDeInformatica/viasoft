package com.br.viasoft.model.controller;


import com.br.viasoft.model.dto.NfeStatusDto;
import com.br.viasoft.model.mapper.NfeStatusServiceToNfeStatusServiceDtoMapper;
import com.br.viasoft.model.service.NfeStatusServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("VIASOFT API")
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
    @ApiOperation("list all states actual status ")
    public List<NfeStatusDto> findAll(){
        return mapperToNfeStatusDto.mapToNfeStatusDtos(nfeStatusServiceService.getAllStatesActualStatus());
    };

    @GetMapping("state")
    @ApiOperation("find a state actual status by state")
    public NfeStatusDto findByFilterByState( NfeStatusDto dto){
        return mapperToNfeStatusDto.map(nfeStatusServiceService.findByState(dto));
    }

    @GetMapping("date")
    @ApiOperation("find list states status by date")
    public List<NfeStatusDto> findByFilterByDate(NfeStatusDto dto){
        return mapperToNfeStatusDto.mapToNfeStatusDtos(nfeStatusServiceService.getAllStatesStatusByDate(dto));
    }
    @GetMapping("unavaliable")
    @ApiOperation("find the state with more unavaliable status")
    public NfeStatusDto findUnavaliableService(){
        return nfeStatusServiceService.getUnavaliableService();
    }
}
