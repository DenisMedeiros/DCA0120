package dca0120.model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Pedido {

	public enum Status {

		ABERTO(1, "Aberto"), EM_PREPARO(2, "Em preparo"), AGUARDANDO_ENTREGADOR(3,
				"Aguardando um entregador"), EM_TRANSITO(4,
						"Saiu para entrega"), ENTREGUE(5, "Entregue"), CANCELADO(6, "Cancelado");

		private final int codigo;
		private final String descricao;

		private Status(int codigo, String descricao) {
			this.codigo = codigo;
			this.descricao = descricao;
		}

		public int getCodigo() {
			return this.codigo;
		}

		public String getDescricao() {
			return this.descricao;
		}

	}

	public class ProdutoQuantidade {
		Produto produto;
		int quantidade;

		ProdutoQuantidade(Produto produto, int quantidade) {
			this.produto = produto;
			this.quantidade = quantidade;
		}
		
		public Produto getProduto() {
			return produto;
		}
		
		public int getQuantidade() {
			return quantidade;
		}
	}

	private int id;
	private float volumeTotal;
	private float pesoTotal;
	private float valorTotal;
	private Status status;
	private String descricao;
	private Entregador entregador;
	private Calendar dataHoraAbertura;
	private Calendar dataHoraEntrega;
	private Endereco enderecoEntrega;
	private List<ProdutoQuantidade> produtosQuantidade;

	public Pedido(int id, Status status, String descricao, Entregador entregador, Calendar dataHoraAbertura,
			Calendar dataHoraEntrega, Endereco enderecoEntrega) {
		super();
		this.id = id;
		this.status = status;
		this.descricao = descricao;
		this.entregador = entregador;
		this.dataHoraEntrega = dataHoraEntrega;
		this.dataHoraAbertura = dataHoraAbertura;
		this.enderecoEntrega = enderecoEntrega;
		this.produtosQuantidade = new ArrayList<ProdutoQuantidade>();
		this.volumeTotal = 0;
		this.pesoTotal = 0;
		this.valorTotal = 0;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getVolumeTotal() {
		return volumeTotal;
	}

	public float getPesoTotal() {
		return pesoTotal;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	public Calendar getDataHoraEntrega() {
		return dataHoraEntrega;
	}

	public void setDataHoraEntrega(Calendar dataHoraEntrega) {
		this.dataHoraEntrega = dataHoraEntrega;
	}

	public Calendar getDataHoraAbertura() {
		return dataHoraAbertura;
	}

	public void setDataHoraAbertura(Calendar dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	// MÃ©todos mais complexos.

	public void addProduto(Produto p, int quantidade) {
		if (quantidade <= p.getQuantidadeEstoque()) {
			ProdutoQuantidade pq = new ProdutoQuantidade(p, quantidade);
			produtosQuantidade.add(pq);
			valorTotal += p.getPreco() * quantidade;
			pesoTotal += p.getPeso() * quantidade;
			volumeTotal += p.getVolume() * quantidade;

		}
	}

	public void removerProduto(Produto p, int quantidade) {
		for (ProdutoQuantidade pq : produtosQuantidade) {
			if (pq.produto.getId() == p.getId()) {
				if (pq.quantidade <= quantidade) {
					produtosQuantidade.remove(pq);
				} else {
					produtosQuantidade.get(produtosQuantidade.indexOf(pq)).quantidade -= quantidade;
				}
				valorTotal -= p.getPreco() * pq.quantidade;
				pesoTotal -= p.getPeso() * pq.quantidade;
				volumeTotal -= p.getVolume() * pq.quantidade;
				break;
			}
		}
	}

	/**
	 * @param p
	 *            objeto de Produto a ser pesquisado
	 * @return retorna um topo int com quantidade do produto (ou -1 se não tiver
	 *         o produto)
	 */
	public int getQuantidadeProduto(Produto p) {
		for (ProdutoQuantidade pq : produtosQuantidade) {
			if (pq.produto.getId() == p.getId()) {
				return pq.quantidade;
			}
		}
		return -1;
	}

	public List<Produto> getProdutos() {
		List<Produto> lista = new ArrayList<Produto>();
		for (ProdutoQuantidade pq : produtosQuantidade) {
			lista.add(pq.produto);
		}
		return lista;
	}
	
	public List<ProdutoQuantidade> getProdutosQuantidades() {
		return produtosQuantidade;
	}
	
	public int getPrioridade() {
		return id;
	}
	
	public String getDataHoraEntregaFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String saida = sdf.format(dataHoraEntrega.getTime());
		return saida;
	}
	
	public String getDataHoraAberturaFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String saida = sdf.format(dataHoraAbertura.getTime());
		return saida;
	}
	
	public void avancarStatus() {	
		switch (status) {
		case ABERTO:
			status = Status.EM_PREPARO;
			break;
		case EM_PREPARO:
			status = Status.AGUARDANDO_ENTREGADOR;
			break;
		case AGUARDANDO_ENTREGADOR:
			status = Status.EM_TRANSITO;
			break;
		case EM_TRANSITO:
			status = Status.ENTREGUE;
			break;
		default:
			break;
		}
	}
	
	public String getEntregadorFormatado() {
		if(entregador != null) {
			return entregador.getNome();
		}
		
		return "Sem entregador";
	}
	

	public String getValorTotalFormatado() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String valorFormatado = formatter.format(valorTotal);
		return valorFormatado;
	}
}
