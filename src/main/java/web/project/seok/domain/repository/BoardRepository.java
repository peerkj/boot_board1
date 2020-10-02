package web.project.seok.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.seok.domain.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    //Containing -> Like 검색
    List<Board> findByTitleContaining(String keyword);
}
