package com.mikaelsonbraz.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mikaelsonbraz.cursomc.domain.Categoria;
import com.mikaelsonbraz.cursomc.domain.Cidade;
import com.mikaelsonbraz.cursomc.domain.Cliente;
import com.mikaelsonbraz.cursomc.domain.Endereco;
import com.mikaelsonbraz.cursomc.domain.Estado;
import com.mikaelsonbraz.cursomc.domain.ItemPedido;
import com.mikaelsonbraz.cursomc.domain.Pagamento;
import com.mikaelsonbraz.cursomc.domain.PagamentoComBoleto;
import com.mikaelsonbraz.cursomc.domain.PagamentoComCartao;
import com.mikaelsonbraz.cursomc.domain.Pedido;
import com.mikaelsonbraz.cursomc.domain.Produto;
import com.mikaelsonbraz.cursomc.domain.enums.EstadoPagamento;
import com.mikaelsonbraz.cursomc.domain.enums.TipoCliente;
import com.mikaelsonbraz.cursomc.repositories.CategoriaRepository;
import com.mikaelsonbraz.cursomc.repositories.CidadeRepository;
import com.mikaelsonbraz.cursomc.repositories.ClienteRepository;
import com.mikaelsonbraz.cursomc.repositories.EnderecoRepository;
import com.mikaelsonbraz.cursomc.repositories.EstadoRepository;
import com.mikaelsonbraz.cursomc.repositories.ItemPedidoRepository;
import com.mikaelsonbraz.cursomc.repositories.PagamentoRepository;
import com.mikaelsonbraz.cursomc.repositories.PedidoRepository;
import com.mikaelsonbraz.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired 
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired 
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Produtos e Categorias
		
		Categoria cat1 = new Categoria(null, "Inform??tica");
		Categoria cat2 = new Categoria(null, "Eletr??nico");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		// Estados e Cidades
		
		Estado est1 = new Estado(null, "Rio Grande do Norte");
		Estado est2 = new Estado(null, "Para??ba");
		
		Cidade cidade1 = new Cidade(null, "Natal", est1);
		Cidade cidade2 = new Cidade(null, "Campina Grande", est2);
		Cidade cidade3 = new Cidade(null, "Patos", est2);
		Cidade cidade4 = new Cidade(null, "Santana do Serid??", est1);
		
		est1.getCidades().addAll(Arrays.asList(cidade1, cidade4));
		est2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
		
		// Clientes e Enderecos
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "111.222.333-44", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("1111-1111", "3232-4554"));
		Cliente cliente2 = new Cliente(null, "Jo??o Borrachinha", "joaob@gmail.com", "222.222.333-44", TipoCliente.PESSOAFISICA);
		cliente2.getTelefones().addAll(Arrays.asList("3333-2222"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 302", "Jardim", "53210-000", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Zez?? Apr??gio", "225", "Apto 1032", "Centro", "59350-000", cliente2, cidade4);
		Endereco endereco3 = new Endereco(null, "Rua das Margaridas", "24", "Casa", "Rua do Bigode", "59350-000", cliente2, cidade4);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1));
		cliente2.getEnderecos().addAll(Arrays.asList(endereco2, endereco3));
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3));
		
		// Pedidos e Pagamentos
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("19/08/2019 20:30"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("12/03/2020 13:12"), cliente2, endereco3);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, pedido1, 10);
		pedido1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.CANCELADO, pedido2, sdf.parse("16/03/2020 13:11"), null);
		pedido2.setPagamento(pagto2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1));
		cliente2.getPedidos().addAll(Arrays.asList(pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		// Items de Pedidos
		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, prod1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, prod3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, prod2, 100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		
		prod1.getItens().addAll(Arrays.asList(itemPedido1));
		prod2.getItens().addAll(Arrays.asList(itemPedido3));
		prod3.getItens().addAll(Arrays.asList(itemPedido2));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
		
		
	}

}
