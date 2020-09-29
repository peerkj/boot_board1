package web.project.seok.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.seok.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
