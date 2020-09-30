package web.project.seok.service;

import org.springframework.stereotype.Service;
import web.project.seok.domain.entity.Board;
import web.project.seok.domain.repository.BoardRepository;
import web.project.seok.dto.BoardDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService implements BoardServiceInter{

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    @Transactional
    public void savePost(BoardDto dto) {
        boardRepository.save(dto.toEntity()).getId();
    }


    @Override
    @Transactional
    public List<BoardDto> getBoardlist() {
        List<Board> boards=boardRepository.findAll();
        List<BoardDto> boardDtoList=new ArrayList<>();

        for(Board board:boards){
            BoardDto dto=new BoardDto().builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(dto);
        }
        return boardDtoList;
    }
}
