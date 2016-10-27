package dca0120.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Pedido {
	
	public enum Status {

	    ABERTO(1, "Aberto"),
	    EM_PREPARO(2, "Em preparo"),
	    AGUARDANDO_ENTREGADOR(3, "Aguardando um entregador"),
	    EM_TRANSITO(4, "Saiu para entrega"),
	    ENTREGUE(5, "Entregue"),
	    CANCELADO(6, "Cancelado");

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
	
	private class ProdutoQuantidade {
		Produto produto;
		int quantidade;
		
		ProdutoQuantidade(Produto produto, int quantidade) {
			this.produto = produto;
			this.quantidade = quantidade;
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
	
	
	// Métodos mais complexos.
	
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
		for(ProdutoQuantidade pq: produtosQuantidade) {
			if(pq.produto.getId() == p.getId()) {
				produtosQuantidade.remove(pq);
				valorTotal -= p.getPreco() * pq.quantidade;
				pesoTotal -= p.getPeso() * pq.quantidade;
				volumeTotal -= p.getVolume() * pq.quantidade;
				break;
			}
		}		
	}
	
}
