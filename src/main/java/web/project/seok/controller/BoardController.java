package web.project.seok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.project.seok.dto.BoardDto;
import web.project.seok.service.BoardService;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(Model model){
        List<BoardDto> dtoList=boardService.getBoardlist();
        model.addAttribute("boardList",dtoList);
        return "board/list";
    }

    @GetMapping("/post")
    public String write(){
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto dto){
        boardService.savePost(dto);
        return "redirect:/";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long id,Model model){
        BoardDto dto=boardService.getPost(id);
        model.addAttribute("boardDto",dto);
        return "board/detail";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long id,Model model){
        BoardDto dto=boardService.getPost(id);
        model.addAttribute("boardDto",dto);
        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto dto,Model model){
        boardService.savePost(dto);
        model.addAttribute("boardDto",dto);
        return "board/detail";
    }
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long id){
        boardService.deletePost(id);

        return "redirect:/";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword,Model model){

        List<BoardDto> boardDtos = boardService.searchPosts(keyword);
        model.addAttribute("boardList",boardDtos);

        return "board/list";
    }

}
