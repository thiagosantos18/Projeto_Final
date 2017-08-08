package lp2.projetofinal.controllers;

import java.util.Collections;
import java.util.List;

import lp2.projetofinal.entidades.Item;
import lp2.projetofinal.orders.OrdenaItemAlfabetico;
import lp2.projetofinal.orders.OrdenaItemPreco;

/**
 * Classe responsavel por ser o ControllerPrincipal, delega responsabilidades para ControllerUsuario e chama a classe Checks para validar os parametros passados em cada metodo.
 * 
 * Laboratorio de Programacao 2 - Projeto Final Parte 01
 * 
 * @author Thiago Santos de Moura - 116210967
 * @author Gabriel Almeida Azevedo - 116210009
 * @author Marcelo Gabriel dos Santos Queiroz Vitorino - 116211290
 */

import lp2.projetofinal.util.Checks;

public class Sistema {

	private ControllerUsuario controllerUsuario;

	public Sistema() {
		controllerUsuario = new ControllerUsuario();
	}

	public void cadastrarUsuario(String nome, String telefone, String email) {

		verificacaoPadraoUsuario(nome, telefone);
		Checks.verificaEmailVazioNulo(email);

		controllerUsuario.cadastrarUsuario(nome, telefone, email);

	}

	public String getInfoUsuario(String nome, String telefone, String atributo) {

		verificacaoPadraoUsuario(nome, telefone);
		Checks.verificaAtributolVazioNulo(atributo);

		return controllerUsuario.getInfoUsuario(nome, telefone, atributo);
	}

	public void removerUsuario(String nome, String telefone) {

		verificacaoPadraoUsuario(nome, telefone);

		controllerUsuario.removerUsuario(nome, telefone);
	}

	public void atualizarUsuario(String nome, String telefone, String atributo, String valor) {

		verificacaoPadraoUsuario(nome, telefone);
		Checks.verificaAtributolVazioNulo(atributo);
		Checks.verificaValorVazioNulo(valor);

		controllerUsuario.atualizarUsuario(nome, telefone, atributo, valor);

	}

	private void verificacaoPadraoUsuario(String nome, String telefone) {
		Checks.verificaNomeVazioNulo(nome);
		Checks.verificaTelefoneVazioNulo(telefone);
	}

	private void verificacaoPadraoCadastroItem(String nome, String telefone, String nomeItem, double preco) {
		verificacaoPadraoUsuario(nome, telefone);
		Checks.verificaNomeItemVazioNulo(nomeItem);
		Checks.verificaPrecoZeroNegativo(preco);
	}

	public void cadastrarEletronico(String nome, String telefone, String nomeItem, double preco, String plataforma) {

		verificacaoPadraoCadastroItem(nome, telefone, nomeItem, preco);
		Checks.verificaPlataformaVaziaNula(plataforma);

		controllerUsuario.identificaUsuario(nome, telefone).adicionaItem(nomeItem, preco, plataforma);
	}

	public void cadastrarJogoTabuleiro(String nome, String telefone, String nomeItem, double preco) {

		verificacaoPadraoCadastroItem(nome, telefone, nomeItem, preco);

		controllerUsuario.identificaUsuario(nome, telefone).adicionaItem(nomeItem, preco);
	}

	public void cadastrarBluRayFilme(String nome, String telefone, String nomeItem, double preco, int duracao,
			String genero, String classificacao, int anoLancamento) {

		verificacaoPadraoCadastroItem(nome, telefone, nomeItem, preco);
		Checks.verificaDuracaoZeroNegativa(duracao);
		Checks.verificaGeneroVazioNulo(genero);
		Checks.verificaClassificacaoVaziaNula(classificacao);
		Checks.verificaAnoLancamentoZeroNegativo(anoLancamento);

		controllerUsuario.identificaUsuario(nome, telefone).adicionaItem(nomeItem, preco, duracao, genero,
				classificacao, anoLancamento);
	}

	public void cadastrarBluRayShow(String nome, String telefone, String nomeItem, double preco, int duracao,
			int numeroFaixas, String artista, String classificacao) {

		verificacaoPadraoCadastroItem(nome, telefone, nomeItem, preco);
		Checks.verificaDuracaoZeroNegativa(duracao);
		Checks.verificaNumeroFaixasZeroNegativo(numeroFaixas);
		Checks.verificaArtistaVazioNulo(artista);
		Checks.verificaClassificacaoVaziaNula(classificacao);

		controllerUsuario.identificaUsuario(nome, telefone).adicionaItem(nomeItem, preco, duracao, numeroFaixas,
				artista, classificacao);
	}

	public void cadastrarBluRaySerie(String nome, String telefone, String nomeItem, double preco, String descricao,
			int duracao, String classificacao, String genero, int temporada) {

		verificacaoPadraoCadastroItem(nome, telefone, nomeItem, preco);
		Checks.verificaDuracaoZeroNegativa(duracao);
		Checks.verificaClassificacaoVaziaNula(classificacao);
		Checks.verificaGeneroVazioNulo(genero);
		Checks.verificaDescricaoVaziaNula(descricao);
		Checks.verificaTemporadaZeroNegativa(temporada);

		controllerUsuario.identificaUsuario(nome, telefone).adicionaItem(nomeItem, preco, descricao, duracao,
				classificacao, genero, temporada);
	}

	public void adicionarBluRay(String nome, String telefone, String BlurayTemporada, int duracao) {

		verificacaoPadraoUsuario(nome, telefone);
		Checks.verificaNomeItemVazioNulo(BlurayTemporada);
		Checks.verificaDuracaoZeroNegativa(duracao);

		controllerUsuario.identificaUsuario(nome, telefone).adicionarBluRayEpisodio(BlurayTemporada, duracao);
	}

	public void adicionarPecaPerdida(String nome, String telefone, String nomeItem, String nomePeca) {

		verificacaoPadraoUsuario(nome, telefone);
		Checks.verificaNomeItemVazioNulo(nomeItem);
		Checks.verificaPecaVaziaNula(nomePeca);

		controllerUsuario.identificaUsuario(nome, telefone).cadastrarPecaPerdidaNoTabuleiro(nomeItem, nomePeca);
	}

	public void removerItem(String nome, String telefone, String nomeItem) {

		verificacaoPadraoUsuario(nome, telefone);
		Checks.verificaNomeItemVazioNulo(nomeItem);

		controllerUsuario.identificaUsuario(nome, telefone).removerItem(nomeItem);
	}

	public void atualizarItem(String nome, String telefone, String nomeItem, String atributo, String valor) {

		verificacaoPadraoUsuario(nome, telefone);
		Checks.verificaNomeItemVazioNulo(nomeItem);
		Checks.verificaAtributolVazioNulo(atributo);
		Checks.verificaValorVazioNulo(valor);

		controllerUsuario.identificaUsuario(nome, telefone).atualizarItem(nomeItem, atributo, valor);

	}

	public String getInfoItem(String nome, String telefone, String nomeItem, String atributo) {

		verificacaoPadraoUsuario(nome, telefone);
		Checks.verificaNomeItemVazioNulo(nomeItem);
		Checks.verificaAtributolVazioNulo(atributo);

		return controllerUsuario.identificaUsuario(nome, telefone).getInfoItem(nomeItem, atributo);
	}
	
	public String pesquisarDetalhesItem(String nome, String telefone, String nomeItem) {

		verificacaoPadraoUsuario(nomeItem, telefone);
		Checks.verificaNomeItemVazioNulo(nomeItem);

		return controllerUsuario.identificaUsuario(nome, telefone).getDetalhesItem(nomeItem);
	}

	public void finalizar() {

	}
	
	//Isso não pode ficar aqui.
	public String listarItensOrdenadosPorNome() {

		List<Item> listaItens = controllerUsuario.retornaUsuarioItens();
		Collections.sort(listaItens, new OrdenaItemAlfabetico());

		String itensString = "";
		for (Item item : listaItens) {
			itensString += item.toString() + "|";
		}
		return itensString;
	}

	//Isso não pode ficar aqui.
	public String listarItensOrdenadosPorValor() {

		List<Item> listaItens = controllerUsuario.retornaUsuarioItens();
		Collections.sort(listaItens, new OrdenaItemPreco());

		String itensString = "";
		for (Item item : listaItens) {
			itensString += item.toString() + "|";
		}
		return itensString;
	}

}