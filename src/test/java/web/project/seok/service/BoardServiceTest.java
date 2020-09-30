package web.project.seok.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import web.project.seok.dto.BoardDto;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired BoardServiceInter boardService;

    @Test
    void savePost() {
        //given
        BoardDto dto=new BoardDto();
        dto.setWriter("seok");
        dto.setTitle("seok");
        dto.setContent("seok");
        //when
        boardService.savePost(dto);
        //then

    }
}