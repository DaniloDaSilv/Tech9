package br.com.curso.repository;

import java.time.LocalDate;

import br.com.curso.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	@Query(value = "select count(c.idCurso) from Curso c where c.dataInicio between :di and :df "
			+ "or (c.dataFim between :di and :df)" + "or (c.dataInicio <= :di and c.dataFim >= :df)"
			+ "or (c.dataInicio >= :di and c.dataFim <= :df)")
	public Long consultaDatas(@Param("di") LocalDate dataInicio, @Param("df") LocalDate dataFim);

	@Query(value = "select count(c.idCurso) from Curso c where (c.dataInicio between :di and :df "
			+ "or (c.dataFim between :di and :df)" + "or (c.dataInicio <= :di and c.dataFim >= :df)"
			+ "or (c.dataInicio >= :di and c.dataFim <= :df))" + "and (c.idCurso != :idc)")
	public Long consultaDatasEditar(@Param("di") LocalDate dataInicio, @Param("df") LocalDate dataFim,
			@Param("idc") Long idCurso);

	@Query("select count(*) from Curso c where ((:descricao = c.descricao)) AND (:idCurso != idCurso)")
	Long editar(String descricao, Long idCurso);
}
