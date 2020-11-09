package com.br.viasoft.model.service;


import com.br.viasoft.model.entity.NfeStatusService;
import com.br.viasoft.model.enumerations.StateEnum;
import com.br.viasoft.model.repository.NfeStatusServiceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class NfeStatusServiceService {

    private final NfeStatusServiceRepository nfeStatusServiceRepository;

    public NfeStatusServiceService(NfeStatusServiceRepository nfeStatusServiceRepository) {
        this.nfeStatusServiceRepository = nfeStatusServiceRepository;
    }

    public List<NfeStatusService> getAllStatesActualStatus() {
        PageRequest topOne = PageRequest.of(0,1);
        List<NfeStatusService> lastNfeStatus = nfeStatusServiceRepository.findLastNfeStatus(topOne);
        List<StateEnum> states = Arrays.asList(StateEnum.values());
        LocalDateTime acutualDate =  lastNfeStatus.get(0).getMomentColected().minusMinutes(5);
        return filterNfeStatusService (nfeStatusServiceRepository.findAllStatesActualStatus(states, acutualDate));
    }

    private List<NfeStatusService> filterNfeStatusService(List<NfeStatusService> nfeStatusServiceList){
        List<NfeStatusService> list = new ArrayList<>(nfeStatusServiceList.stream().collect(Collectors.toCollection(
                () -> new TreeSet<NfeStatusService>((x1,x2)->x1.getState().getState().compareTo(x2.getState().getState())))));
        return list ;
    }
}
