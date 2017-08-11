package lp2.projetofinal.controllers;

/**
 * Classe responsavel por controlar todos os objetos Emprestimos registrados no sistema.
 * 
 * Laboratorio de Programacao 2 - Projeto Final Parte 01
 * 
 * @author Thiago Santos de Moura - 116210967
 * @author Gabriel Almeida Azevedo - 116210009
 * @author Marcelo Gabriel dos Santos Queiroz Vitorino - 116211290
 */

import java.util.ArrayList;
import java.util.List;

import lp2.projetofinal.entidades.Emprestimo;
import lp2.projetofinal.entidades.Item;
import lp2.projetofinal.entidades.Usuario;
import lp2.projetofinal.enums.EstadoItem;
import lp2.projetofinal.util.Exceptions;

public class ControllerEmprestimos {

	private List<Emprestimo> emprestimos;

	public ControllerEmprestimos() {
		this.emprestimos = new ArrayList<Emprestimo>();
	}

	/**
	 * Metodo responsavel por cadastrar um novo Emprestimo no sistema. Veficando
	 * antes a disponibilidade do item escolhido e solicitando a alteração do
	 * seu estado após registro realizado.
	 * 
	 * @param donoItem
	 *            Objeto do tipo Usuario resgatado pelo controlador de usuarios
	 *            e dono do item que será emprestado.
	 * @param requerenteItem
	 *            Objeto do tipo Usuario resgatado pelo controlador de usuarios
	 *            e requerente do item.
	 * @param item
	 *            Objeto do tipo Item, resgatado pelo contralador de itens.
	 * @param dataEmprestimo
	 *            Dado da solicitação do emprestimo, passada como string.
	 * @param periodo
	 *            Periodo de duração do emprestimo.
	 */
	public void registrarEmprestimo(Usuario donoItem, Usuario requerenteItem, Item item, String dataEmprestimo,
			int periodo) {

		if (item.getEstado().equals(EstadoItem.EMPRESTADO.getEstado()))
			Exceptions.itemEmprestadoNoMomento();

		Emprestimo emprestimo = new Emprestimo(donoItem, requerenteItem, item, dataEmprestimo, periodo);

		this.emprestimos.add(emprestimo);
		item.itemEmprestado();
	}

	/**
	 * Metodo responsavel realizar a devolução do item que foi emprestado,
	 * identifando antes o objeto do tipo Emprestimo na lista de emprestimos.
	 * 
	 * @param donoItem
	 *            Objeto do tipo Usuario resgatado pelo controlador de usuarios
	 *            e dono do item que foi emprestado.
	 * @param requerenteItem
	 *            Objeto do tipo Usuario resgatado pelo controlador de usuarios
	 *            e requerente do item que foi emprestado.
	 * @param item
	 *            Objeto do tipo Item, resgatado pelo contralador de itens.
	 * @param dataEmprestimo
	 *            Dado da solicitação do emprestimo, passada como string.
	 * @param dataDevolucao
	 *            Data da entrega do item.
	 */
	public void devolverItem(Usuario donoItem, Usuario requerenteItem, Item item, String dataEmprestimo,
			String dataDevolucao) {

		Emprestimo emprestimo = identificaEmprestimo(donoItem, requerenteItem, dataEmprestimo);

		emprestimo.setDataDevolucao(dataDevolucao);
		item.itemDevolvido();

	}

	/**
	 * Metodo responsavel por identificar um Emprestimo na lista de emprestimo.
	 * Para isso ele instancia uma referencia de desse objeto emprestimo para
	 * que seja possivel encontrar igual na lista.
	 * 
	 * @param donoItem
	 *            Objeto do tipo Usuario resgatado pelo controlador de usuarios
	 *            e dono do item que foi emprestado.
	 * @param requerenteItem
	 *            Objeto do tipo Usuario resgatado pelo controlador de usuarios
	 *            e requerente do item que foi emprestado.
	 * @param dataEmprestimo
	 *            Dado da solicitação do emprestimo, passada como string.
	 * 
	 * @return Retorna um objeto do tipo Emprestimo, que encontra-se na lista de
	 *         emprestimos.
	 */
	public Emprestimo identificaEmprestimo(Usuario donoItem, Usuario requerenteItem, String dataEmprestimo) {

		Emprestimo emprestimo = new Emprestimo(donoItem, requerenteItem, dataEmprestimo);

		if (!this.emprestimos.contains(emprestimo))
			Exceptions.emprestimoNaoEncontrado();

		return this.emprestimos.get(this.emprestimos.indexOf(emprestimo));
	}

}