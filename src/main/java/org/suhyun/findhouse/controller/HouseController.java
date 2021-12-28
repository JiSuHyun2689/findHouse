package org.suhyun.findhouse.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.suhyun.findhouse.dto.HouseDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.service.HouseService;

@Controller
@Log4j2
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/")
    public String index(){
        return "redirect:/house/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("house list ....................... " + pageRequestDTO);

        model.addAttribute("result", houseService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("house register get ...........................");
    }

    @PostMapping("/register")
    public String registerPost(HouseDTO dto, RedirectAttributes redirectAttributes){

        log.info("house register post ...........................");

        Long houseNum = houseService.register(dto);

        redirectAttributes.addFlashAttribute("msg", houseNum);

        return "redirect:/house/list";

    }
}
