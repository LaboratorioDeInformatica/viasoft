package com.br.viasoft.model.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.br.viasoft.model.enumerations.AvaliableStatusEnum;
import com.br.viasoft.model.enumerations.StateEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.viasoft.model.entity.NfeStatusService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NfeStatusServiceRepository extends JpaRepository<NfeStatusService, UUID>{

    @Query(value=" select n from NfeStatusService as n  inner join n.state as s " +
            " where s.state in :states " +
            " and n.momentColected >= :acutualDate  order by n.momentColected DESC ")
    List<NfeStatusService> findAllStatesActualStatus (@Param("states") List<StateEnum> states,@Param("acutualDate") LocalDateTime acutualDate);

    @Query(value = "Select n from NfeStatusService as n order by n.momentColected DESC ")
    List<NfeStatusService> findLastNfeStatus(Pageable page);

    @Query(value = " Select n from NfeStatusService as n inner join n.state as s " +
            " where 1=1 " +
            " AND ( :state is null or s.state = : state)" +
            " AND ( :startedFrom is null or n.momentColected BETWEEN :startedFrom AND :startedTo )" +
            " order by n.momentColected DESC ")
    List<NfeStatusService> findByFilter( @Param("state") StateEnum state,
                                         @Param("startedFrom") LocalDateTime startedFrom,
                                         @Param("startedTo") LocalDateTime startedTo);

    List<NfeStatusService> findByStatus(AvaliableStatusEnum unavaliable);
}
