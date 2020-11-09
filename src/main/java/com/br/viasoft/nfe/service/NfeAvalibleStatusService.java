package com.br.viasoft.nfe.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.br.viasoft.model.entity.NfeStatusService;
import com.br.viasoft.model.enumerations.AvaliableStatusEnum;
import com.br.viasoft.model.enumerations.StateEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.br.viasoft.model.repository.NfeStatusServiceRepository;
import com.br.viasoft.model.repository.StateRepository;

@Component
public class NfeAvalibleStatusService {
	
	private final StateRepository stateRepository;
	private final NfeStatusServiceRepository nfeStatusServiceRepository;

	public NfeAvalibleStatusService(StateRepository stateRepository,
			NfeStatusServiceRepository nfeStatusServiceRepository) {
		this.stateRepository = stateRepository;
		this.nfeStatusServiceRepository = nfeStatusServiceRepository;
	}

	public void statusAvaliabelService() throws IOException {
		
		Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx")
				.maxBodySize(0)
                .timeout(70000)
                .get();
       
        
        for (Element table : doc.select("table[class=tabelaListagemDados]")) {
        	  for (Element row : table.select("tr")) {
        		  Elements tds = row.select("td");
        		  if(tds.size()>0) {
        		  	String state = tds.get(0).text();
        			String avaliableStatus = cleanPngname( tds.get(5).select("img").first().attr("src") );

        			NfeStatusService nfeStatusService =  new NfeStatusService();
        			nfeStatusService.setMomentColected(LocalDateTime.now());

        			if(tds.get(0).text().length() <=2) {
        				nfeStatusService.setState(stateRepository.findByState(StateEnum.valueOf(tds.get(0).text())));
						nfeStatusService.setStatus(
								cleanPngname(avaliableStatus).equals("bola_verde") ?
										AvaliableStatusEnum.AVALIABLE : AvaliableStatusEnum.UNAVALIABLE);
						nfeStatusServiceRepository.save(nfeStatusService);
					}
        		  }
        	  }
        }
	}

	private String cleanPngname (String pngName){
		pngName = pngName.replace("imagens/", "");
		pngName = pngName.replace("_P.png", "");
		return pngName;
	}
}
