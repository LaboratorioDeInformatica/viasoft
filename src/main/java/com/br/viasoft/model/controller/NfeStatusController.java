package com.br.viasoft.model.controller;

import com.br.viasoft.model.dto.NfeStatusDto;
import com.br.viasoft.model.entity.NfeStatusService;
import com.br.viasoft.model.service.NfeStatusServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class NfeStatusController {

    private final NfeStatusServiceService nfeStatusServiceService;

    public NfeStatusController(NfeStatusServiceService nfeStatusServiceService) {
        this.nfeStatusServiceService = nfeStatusServiceService;
    }

    @GetMapping
    public List<NfeStatusService> find(){
        return nfeStatusServiceService.getAllStatesActualStatus();
    };
}
