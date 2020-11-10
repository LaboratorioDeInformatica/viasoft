package com.br.viasoft.model.service;


import com.br.viasoft.model.dto.NfeStatusDto;
import com.br.viasoft.model.entity.NfeStatusService;
import com.br.viasoft.model.entity.State;
import com.br.viasoft.model.enumerations.AvaliableStatusEnum;
import com.br.viasoft.model.enumerations.StateEnum;
import com.br.viasoft.model.repository.NfeStatusServiceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;
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

    public NfeStatusService findByState(NfeStatusDto dto) {
        return this.filter(dto).stream().findFirst().orElse(null);
    }

    public List<NfeStatusService> getAllStatesStatusByDate(NfeStatusDto dto) {
        return this.filter(dto);
    }

    private List<NfeStatusService> filter(NfeStatusDto dto){
        return nfeStatusServiceRepository.findByFilter(
                dto.getState() == null ? null : StateEnum.valueOf(dto.getState()),
                dto.getFilterDate() == null ? null : dto.getFilterDate().atTime(0,0,0),
                dto.getFilterDate() == null ? null : dto.getFilterDate().atTime(23,59,59)
        );
    }

    public String getUnavaliableService() {
        List<NfeStatusService> list = nfeStatusServiceRepository.findByStatus(AvaliableStatusEnum.UNAVALIABLE);
        Map<State, Long> counted = list.stream()
                .collect(Collectors.groupingBy(NfeStatusService::getState,Collectors.counting()
                        ));
        return maxUnavaliabe(counted).getState().toString();
    }

    private <K, V extends Comparable<V>> K maxUnavaliabe(Map<K, V> map) {
        Optional<Map.Entry<K, V>> maxEntry = map.entrySet()
                .stream()
                .max((Map.Entry<K, V> e1, Map.Entry<K, V> e2) -> e1.getValue()
                        .compareTo(e2.getValue())
                );
        return maxEntry.get().getKey();
    }
}
