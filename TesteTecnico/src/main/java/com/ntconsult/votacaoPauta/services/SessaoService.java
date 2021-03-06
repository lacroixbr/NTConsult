package com.ntconsult.votacaoPauta.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntconsult.votacaoPauta.dto.PautaDTO;
import com.ntconsult.votacaoPauta.dto.SessaoDTO;
import com.ntconsult.votacaoPauta.entities.Pauta;
import com.ntconsult.votacaoPauta.entities.Sessao;
import com.ntconsult.votacaoPauta.repositories.SessaoRepository;

@Service
public class SessaoService {
	
	@Autowired
	private SessaoRepository sessaoRepo;
	
	@Autowired
	private PautaService pautaService;
	
	@Transactional
	public SessaoDTO insert(SessaoDTO dto) {
		
		Sessao sessao = new Sessao(null, dto.getPautaId(), dto.getInicioVotacao(), dto.getFimVotacao());
		Optional<Pauta> pauta = pautaService.GetById(dto.getPautaId());
		
		sessao = sessaoRepo.save(sessao);
		
		return new SessaoDTO(sessao);
	}

	public List<SessaoDTO> GetAll() {
		List<Sessao> list = sessaoRepo.findAll();
		return list.stream().map(x -> new SessaoDTO(x)).collect(Collectors.toList());
	}

}
