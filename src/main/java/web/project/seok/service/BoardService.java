package web.project.seok.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private static final int BLOCK_PAGE_NUM_COUNT=5;//블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT=10; //한 페이지에 존재하는 게시글 수

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
    public List<BoardDto> getBoardlist(Integer pageNum) {
        Page<Board> page = boardRepository
                .findAll(PageRequest
                        .of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC,"createdDate")));

        //List<Board> boards = boardRepository.findAll();

        List<Board> boards=page.getContent();
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
    @Transactional
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
    @Transactional
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
            boardDtoList.add(convertEntityToDto(b));
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

    @Override
    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList=new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 수
        Double postsTotalCount=Double.valueOf(getBoardCount());

        //총 게시글 수를 기준으로 계산한 마지막 페이지 번호
        Integer totalLastPageNum=(int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호
        Integer blockLastPageNum = (totalLastPageNum>curPageNum+BLOCK_PAGE_NUM_COUNT)
                ?curPageNum+BLOCK_PAGE_NUM_COUNT
                :totalLastPageNum;

        //페이지 시작 번호 조정
        curPageNum = (curPageNum<=3)?1:curPageNum-2;
        for(int val=curPageNum,i=0; val<=blockLastPageNum;val++,i++){
            pageList[i]=val;
        }

        return pageList;
    }
    @Transactional
    public Long getBoardCount(){
        return boardRepository.count();
    }
}
