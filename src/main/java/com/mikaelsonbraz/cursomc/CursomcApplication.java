package com.mikaelsonbraz.cursomc;

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
import com.mikaelsonbraz.cursomc.domain.Produto;
import com.mikaelsonbraz.cursomc.domain.enums.TipoCliente;
import com.mikaelsonbraz.cursomc.repositories.CategoriaRepository;
import com.mikaelsonbraz.cursomc.repositories.CidadeRepository;
import com.mikaelsonbraz.cursomc.repositories.ClienteRepository;
import com.mikaelsonbraz.cursomc.repositories.EnderecoRepository;
import com.mikaelsonbraz.cursomc.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Produtos e Categorias
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletrônico");
		
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
		Estado est2 = new Estado(null, "Paraíba");
		
		Cidade cidade1 = new Cidade(null, "Natal", est1);
		Cidade cidade2 = new Cidade(null, "Campina Grande", est2);
		Cidade cidade3 = new Cidade(null, "Patos", est2);
		Cidade cidade4 = new Cidade(null, "Santana do Seridó", est1);
		
		est1.getCidades().addAll(Arrays.asList(cidade1, cidade4));
		est2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
		
		// Clientes e Enderecos
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "111.222.333-44", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("1111-1111", "3232-4554"));
		Cliente cliente2 = new Cliente(null, "João Borrachinha", "joaob@gmail.com", "222.222.333-44", TipoCliente.PESSOAFISICA);
		cliente2.getTelefones().addAll(Arrays.asList("3333-2222"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 302", "Jardim", "53210-000", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Zezé Aprígio", "225", "Apto 1032", "Centro", "59350-000", cliente2, cidade4);
		Endereco endereco3 = new Endereco(null, "Rua das Margaridas", "24", "Casa", "Rua do Bigode", "59350-000", cliente2, cidade4);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1));
		cliente2.getEnderecos().addAll(Arrays.asList(endereco2, endereco3));
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
		
	}

}
