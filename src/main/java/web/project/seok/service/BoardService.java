package web.project.seok.service;

import org.springframework.stereotype.Service;
import web.project.seok.domain.repository.BoardRepository;
import web.project.seok.dto.BoardDto;

import javax.transaction.Transactional;

@Service
public class BoardService implements BoardServiceInter{

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @Transactional
    @Override
    public void savePost(BoardDto dto) {
        boardRepository.save(dto.toEntity()).getId();
    }
}
