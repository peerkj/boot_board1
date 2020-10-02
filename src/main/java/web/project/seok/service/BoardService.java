package web.project.seok.service;

import org.springframework.stereotype.Service;
import web.project.seok.domain.entity.Board;
import web.project.seok.domain.repository.BoardRepository;
import web.project.seok.dto.BoardDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService implements BoardServiceInter {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    @Transactional
    public void savePost(BoardDto dto) {
        boardRepository.save(dto.toEntity());
    }


    @Override
    @Transactional
    public List<BoardDto> getBoardlist() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boards) {
            BoardDto dto = new BoardDto().builder()
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

    @Override
    public BoardDto getPost(Long id) {
        Optional<Board> boardWrapper = boardRepository.findById(id);
        Board board = boardWrapper.get();

        BoardDto dto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedDate())
                .build();

        return dto;

    }

    @Override
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<Board> boards=boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList=new ArrayList<>();

        if(boards.isEmpty()) return boardDtoList;

        for(Board b : boards){
            boardDtoList.add(this.convertEntityToDto(b));
        }
        return boardDtoList;
    }

    @Override
    public BoardDto convertEntityToDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedDate())
                .build();
    }
}
