package br.com.digivalle.colaboradorappbackend.repository;
import br.com.digivalle.colaboradorappbackend.domain.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Colaborador (nome, cargo, senha, senha_score, complexidade) VALUES (:nome, :cargo, :senha, :senhaScore, :complexidade)", nativeQuery = true)
    void criarColaborador(String nome, String cargo, String senha, int senhaScore, String complexidade);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Colaborador SET nome = :nome, cargo = :cargo, senha = :senha, senha_score = :senhaScore, complexidade = :complexidade WHERE id = :id", nativeQuery = true)
    void atualizarColaborador(Long id, String nome, String cargo, String senha, int senhaScore, String complexidade);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Colaborador WHERE id = :id", nativeQuery = true)
    void deletarColaboradorPorId(Long id);

    @Query(value = "SELECT * FROM Colaborador", nativeQuery = true)
    List<Colaborador> findAllColaboradores();
}
