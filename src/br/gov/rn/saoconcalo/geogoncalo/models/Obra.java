package br.gov.rn.saoconcalo.geogoncalo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="obras.obra")
public class Obra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="detalhes")
	private String detalhes;
	
	@Column(name="rua")
	private String rua;
	
	@Column(name="custo_estimado")
	private Long custoEstimado;
	
	@Column(name="meta_fisica")
	private Long metaFisica;
	
	@ManyToOne
	@JoinColumn(name="bairro_id")
	private Bairro bairro;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="tipo_id")
	private Tipo tipo;
	
	@OneToMany()
	@JoinTable(name="obras.imagem", joinColumns = @JoinColumn(name="obra_id"), inverseJoinColumns = @JoinColumn(name="id"))
	private List<Imagem> imagens;

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getCustoEstimado() {
		return custoEstimado;
	}

	public void setCustoEstimado(Long custoEstimado) {
		this.custoEstimado = custoEstimado;
	}

	public Long getMetaFisica() {
		return metaFisica;
	}

	public void setMetaFisica(Long metaFisica) {
		this.metaFisica = metaFisica;
	}

	@Override
	public String toString() {
		return "Obra [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", detalhes=" + detalhes
				+ ", rua=" + rua + ", custoEstimado=" + custoEstimado + ", metaFisica=" + metaFisica + ", bairro="
				+ bairro + ", categoria=" + categoria + ", tipo=" + tipo + ", imagens=" + imagens + ", status=" + status
				+ "]";
	}	
}
