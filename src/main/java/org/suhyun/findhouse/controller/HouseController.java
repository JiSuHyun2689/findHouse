package org.suhyun.findhouse.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.suhyun.findhouse.dto.*;
import org.suhyun.findhouse.service.*;

import java.security.Principal;

@Controller
@Log4j2
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;
    /*
    private final OptionService optionService;
    private final StructureService structureService;
    private final PriceService priceService;
    private final CostService costService;*/
    private final ReviewService reviewService;


    @GetMapping("/")
    public String index() {
        return "redirect:/house/list";
    }


    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model, Principal principal) {

        log.info("house list ....................... " + pageRequestDTO);

        //model.addAttribute("result", houseService.getList(pageRequestDTO));
        model.addAttribute("result", houseService.getSearchList(pageRequestDTO));
        model.addAttribute("user", principal);
    }


    @GetMapping({"/read", "/modify"})
    public void read(Long houseNum, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model) {

        log.info("house read " + houseNum + " ...........................");

        houseService.updateView(houseNum);

        HouseDTO dto = houseService.read(houseNum);

        model.addAttribute("dto", dto);
    }


    @GetMapping("/register")
    public void register() {
        log.info("house register get ...........................");
    }


    @PostMapping("/register")
    public String registerPost(@ModelAttribute("dto") HouseDTO dto, RedirectAttributes redirectAttributes) {

        log.info("house register post ...........................");

        log.info("HouseDTO : " + dto);

        Long houseNum = houseService.register(dto);

        if(houseNum != null)
            redirectAttributes.addFlashAttribute("msg", "매물이 성공적으로 등록되었습니다.");

        return "redirect:/house/list";
    }


    @PostMapping("/modify")
    public String modifyPost(HouseDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

        log.info("house modify post " + dto.getHouseNum() + " ...........................");

        houseService.modify(dto);

        redirectAttributes.addFlashAttribute("msg", "매물 수정이 완료되었습니다.");

        return "redirect:/house/read?houseNum="+dto.getHouseNum();
    }


    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/remove")
    public String remove(Long houseNum, RedirectAttributes redirectAttributes) {

        log.info("house remove " + houseNum + " ...........................");

        reviewService.removeByHouse(houseNum);

        houseService.remove(houseNum);

        redirectAttributes.addFlashAttribute("msg", "삭제가 완료되었습니다.");

        return "redirect:/house/list";
    }
}
