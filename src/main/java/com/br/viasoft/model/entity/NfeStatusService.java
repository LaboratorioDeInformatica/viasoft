package com.br.viasoft.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;

import com.br.viasoft.model.enumerations.AvaliableStatusEnum;
import lombok.*;
import org.hibernate.annotations.Type;

import com.br.viasoft.model.enumerations.StateEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Getter
@Setter
@ToString
public class NfeStatusService {

	@Id
    @Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private LocalDateTime momentColected;
	
	@Column
	private AvaliableStatusEnum status;
	
	@JoinColumn(name ="id_state")
	@ManyToOne()
	private State state;

}
