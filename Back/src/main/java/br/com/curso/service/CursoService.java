package br.com.curso.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import Request.CursoPost;
import br.com.curso.entities.Categoria;
import br.com.curso.entities.Curso;
import br.com.curso.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	CursoRepository repository;

	@PersistenceContext
	EntityManager entityManager;

	public List<Curso> GetAll(String descricao, LocalDate dataInicio, LocalDate dataFim) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Curso> criteriaQuery = criteriaBuilder.createQuery(Curso.class);

		Root<Curso> curso = criteriaQuery.from(Curso.class);
		List<Predicate> predList = new ArrayList<>();

		if (descricao != "") {
			Predicate descricaoPredicate = criteriaBuilder.equal(curso.get("descricao"), descricao);
			predList.add(descricaoPredicate);
		}

		if (dataInicio != null) {
			Predicate dataIniPredicate = criteriaBuilder.greaterThanOrEqualTo(curso.get("dataInicio"), dataInicio);
			predList.add(dataIniPredicate);
		}

		if (dataFim != null) {
			Predicate dataTerPredicate = criteriaBuilder.lessThanOrEqualTo(curso.get("dataFim"), dataFim);
			predList.add(dataTerPredicate);
		}

		Predicate[] predicateArray = new Predicate[predList.size()];

		predList.toArray(predicateArray);

		criteriaQuery.where(predicateArray);

		TypedQuery<Curso> query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();
	}

	public ResponseEntity<Curso> GetById(@PathVariable long idCurso) {
		return repository.findById(idCurso).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	public void cadastro(CursoPost cursoPost) {
		Curso curso = new Curso();
		Categoria categoria = new Categoria(cursoPost.getCategoria());

		curso.setDescricao(cursoPost.getDescricao());
		curso.setDataInicio(LocalDate.parse(cursoPost.getDataInicio()));
		curso.setDataFim(LocalDate.parse(cursoPost.getDataFim()));
		curso.setQtdAlunos(Integer.parseInt(cursoPost.getQtdAlunos()));
		curso.setCategoria(categoria);

		if (curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data Invalida");
		}

		Long countcurso = repository.consultaDatas(curso.getDataInicio(), curso.getDataFim());
		if (countcurso > 0) {
			throw new RuntimeException("Você já esta realizando um curso no momento.");
		}
		cursoExiste(curso);

		repository.save(curso);
	}

	public void cursoExiste(Curso curso) {
		Long validacaoId = repository.editar(curso.getDescricao(), curso.getIdCurso());
		if (validacaoId > 0) {
			throw new RuntimeException("Curso ja existente");
		}
	}

	public Curso atualizar(Curso curso) {
		for (Curso descricao : repository.findAll()) {
			if (descricao.getDescricao().equals(curso.getDescricao())) {
			}
		}

		Long editar = repository.consultaDatasEditar(curso.getDataInicio(), curso.getDataFim(), curso.getIdCurso());
		if (editar > 0) {
			throw new RuntimeException("Existe(m) curso(s) planejado(s) dentro do periodo informado");
		}
		cursoExiste(curso);
		if (curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data Invalida");
		}
		return repository.save(curso);
	}

	public ResponseEntity<String> deletar(long idCurso) {
		Optional<Curso> item = repository.findById(idCurso);
		Curso curso = item.get();
		if (LocalDate.now().isBefore(curso.getDataFim())) {
			repository.deleteById(idCurso);
			return ResponseEntity.status(HttpStatus.OK).body("Curso excluido");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Voce não pode excluir este curso pois ele já foi finalizado");
		}

	}
}
