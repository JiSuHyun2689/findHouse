package org.suhyun.findhouse.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.suhyun.findhouse.dto.*;
import org.suhyun.findhouse.entity.Option;
import org.suhyun.findhouse.service.*;

@Controller
@Log4j2
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;
    private final OptionService optionService;
    private final StructureService structureService;
    private final PriceService priceService;
    private final CostService costService;



    @GetMapping("/")
    public String index(){
        return "redirect:/house/list";
    }



    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("house list ....................... " + pageRequestDTO);

        model.addAttribute("result", houseService.getList(pageRequestDTO));
    }



    @GetMapping({"/read", "/modify"})
    public void read(Long houseNum, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model){

        log.info("house read "+ houseNum + " ...........................");

        HouseDTO dto = houseService.read(houseNum);
        OptionDTO optionDto = optionService.read(houseNum);
        StructureDTO structureDto = structureService.read(houseNum);
        PriceDTO priceDto = priceService.read(houseNum);
        CostDTO costDto = costService.read(houseNum);

        model.addAttribute("dto", dto);
        model.addAttribute("optionDto", optionDto);
        model.addAttribute("structureDto", structureDto);
        model.addAttribute("priceDto", priceDto);
        model.addAttribute("costDto", costDto);
    }



    @GetMapping("/register")
    public void register(){
        log.info("house register get ...........................");
    }



    @PostMapping("/register")
    public String registerPost(HouseDTO dto, OptionDTO optionDto, StructureDTO structureDto, PriceDTO priceDto, CostDTO costDto, RedirectAttributes redirectAttributes){

        log.info("house register post ...........................");

        log.info("HouseDTO : " + dto);
        log.info("OptionDTO : " + optionDto);
        log.info("StructureDTO : " + structureDto);
        log.info("PriceDTO : " + priceDto);
        log.info("CostDTO : " + costDto);

        Long houseNum = houseService.register(dto);

        optionDto.setHouseNum(houseNum);
        structureDto.setHouseNum(houseNum);
        priceDto.setHouseNum(houseNum);
        costDto.setHouseNum(houseNum);

        optionService.register(optionDto);
        structureService.register(structureDto);
        priceService.register(priceDto);
        costService.register(costDto);

        redirectAttributes.addFlashAttribute("msg", "매물이 성공적으로 등록되었습니다.");

        return "redirect:/house/list";
    }



    @PostMapping("/modify")
    public String modifyPost(HouseDTO dto, OptionDTO optionDto, StructureDTO structureDto, PriceDTO priceDto, CostDTO costDto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){

        log.info("house modify post "+ dto.getHouseNum() + " ...........................");

        costService.modify(costDto);
        priceService.modify(priceDto);
        structureService.modify(structureDto);
        optionService.modify(optionDto);
        houseService.modify(dto);


        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("houseNum", dto.getHouseNum());
        redirectAttributes.addFlashAttribute("msg", "매물 수정이 완료되었습니다.");

        return "redirect:/house/read";
    }




    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, path = "/remove")
    public String remove(Long houseNum, RedirectAttributes redirectAttributes){

        log.info("house remove "+ houseNum + " ...........................");

        costService.remove(houseNum);
        priceService.remove(houseNum);
        structureService.remove(houseNum);
        optionService.remove(houseNum);
        houseService.remove(houseNum);

        redirectAttributes.addFlashAttribute("msg", "삭제가 완료되었습니다.");

        return "redirect:/house/list";
    }
}
