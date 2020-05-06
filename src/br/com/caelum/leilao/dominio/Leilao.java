package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if (lances.isEmpty() || podeLance(lance.getUsuario()))
			lances.add(lance);
	}

	private Lance ultimoLance() {
		return lances.get(lances.size() - 1);
	}
	private boolean podeLance(Usuario usuario) {
		return !ultimoLance().getUsuario().equals(usuario) && qtdeLance(usuario) < 5;
	}
	private int qtdeLance(Usuario usuario) {
		int total = 0;
		for (Lance l : lances) {
			if (l.getUsuario().equals(usuario)) {
				total++;
			}
		}
		return total;
	}


	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
}
