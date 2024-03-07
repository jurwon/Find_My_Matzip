package com.matzip.controller;

import com.matzip.dto.*;
import com.matzip.entity.Restaurant;
import com.matzip.service.BoardService;
import com.matzip.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final BoardService boardService;

    @GetMapping("/map")
    public String findAll(Model model){
        List<RestaurantDto> restaurantDtoList = restaurantService.findAll();
        model.addAttribute("restaurantList",restaurantDtoList);
        return "map/mapForm";
    }

    @GetMapping(value = {"/restaurant/new"})
    public String restaurantForm(Model model){
        model.addAttribute("restaurantFormDto", new RestaurantFormDto());
        return "/restaurant/restaurantForm";
    }

    @PostMapping(value = "/admin/restaurant/new")
    public String restaurantNew(@Valid RestaurantFormDto restaurantFormDto, BindingResult bindingResult,
                                Model model, @RequestParam("restaurantImgFile") List<MultipartFile> restaurantImgFileList){

        if(bindingResult.hasErrors()){
            return "restaurant/restaurantForm";
        }

        if(restaurantImgFileList.get(0).isEmpty() && restaurantFormDto.getResId() == null){
            model.addAttribute("errorMessage", "첫번째 레스토랑 이미지는 필수 입력 값 입니다.");
            return "restaurant/restaurantForm";
        }

        try {
//            System.out.println("restaurantFormDto 내용 확인: =======================================");
//            System.out.println("restaurantFormDto 내용 확인: "+ restaurantFormDto.getRestaurant_title());
//            System.out.println("restaurantFormDto 내용 확인: "+ restaurantFormDto.getContent());
//            System.out.println("restaurantFormDto 내용 확인: "+ restaurantFormDto.getScore());
////            System.out.println("restaurantFormDto 내용 확인: "+ restaurantFormDto.getRestaurantImgDtoList().get(0).getImgName());
////            System.out.println("restaurantFormDto 내용 확인: "+ restaurantFormDto.getRestaurantImgDtoList().get(0).getImgUrl());
//            System.out.println("restaurantFormDto 내용 확인: =======================================");

        } catch (Exception e){
            model.addAttribute("errorMessage", "리뷰 등록 중 에러가 발생하였습니다.");
            return "restaurant/restaurantForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/restaurant/main")
    public String restaurantMain(RestaurantSearchDto restaurantSearchDto, Optional<Integer> page, Model model){
        List<RestaurantDto> restaurantDtoList = restaurantService.findAll();
        System.out.println("restaurantDtoList 의 소스 확인 동네 1: "+restaurantDtoList.get(0).getRes_name());
        model.addAttribute("restaurantList",restaurantDtoList);
        return "/restaurant/restaurant";
    }

    //원래 이걸로 하려고했는데 불러오는걸 못해서 바꿈 ,,, 확인해볼것
//    @GetMapping(value = "/restaurant/main")
//    public String restaurantMain(RestaurantSearchDto restaurantSearchDto, Optional<Integer> page, Model model){
//
//        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
//        Page<MainRestaurantDto> restaurants = restaurantService.getMainRestaurantPage(restaurantSearchDto, pageable);
//
//        model.addAttribute("restaurants", restaurants);
//        model.addAttribute("restaurantSearchDto", restaurantSearchDto);
//        model.addAttribute("maxPage", 5);
//
//        return "/restaurant/restaurant";
//    }

//    //식당상세페이지 매핑
//    @GetMapping(value = "/restaurant/{resId}")
//    public String restaurantDtl(Model model, @PathVariable("resId") String resId){
//        RestaurantFormDto restaurantFormDto = restaurantService.getRestaurantDtl(resId);
//        model.addAttribute("restaurant", restaurantFormDto);
//        return "restaurant/restaurantDtl";
//    }

    //식당상세페이지 리뷰추가 매핑...
    // 근데 이거 뒤에 review 안붙어도 나오게 하고싶다. 그런데 그렇게하면
    // 리뷰 폼 등록할 때도 나오지 않을까...
    @GetMapping(value = "/restaurant/{resId}")
    public String sumResRivew(Model model, @PathVariable("resId") String resId,Optional<Integer> page,BoardSearchDto boardSearchDto){
        RestaurantFormDto restaurantFormDto = restaurantService.getRestaurantDtl(resId);
        model.addAttribute("restaurant", restaurantFormDto);
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        /*Page<MainBoardDto> boards = boardService.getMainBoardPage(boardSearchDto, pageable);*/
        Page<MainBoardDto> boards = boardService.getBoardPageByResId(boardSearchDto, pageable,resId);
        model.addAttribute("boards", boards);
        model.addAttribute("boardSearchDto", boardSearchDto);
        model.addAttribute("maxPage", 5);
        return "restaurant/restaurant_review";
    }

    //게시글 관리 화면 및 조회한 게시글 데이터를 화면에 전달하는 로직을 구현
    //value에 상품 관리 화면 진입 시 URL에 페이지 번호가 없는 경우와 페이지 번호가 있는 경우 2가지를 매핑한다.
    @GetMapping(value = {"/restaurants", "/restaurants/{page}"})
    public String restaurantManage(RestaurantSearchDto restaurantSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        //페이징을 위해서 PageRequest.of 메서드를 통해 Pageable 객체생성
        //첫번째 파라미터로는 조회할페이지번호 / 두번째 파라미터로는 한번에 가져올 데이터 수를 넣어준다
        //URL경로에 페이지 번호가 있으면 해당 페이지를 조회하도록 세팅하고 페이지 번호가 없으면 0페이지를 조회하도록한다.
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        //조회조건과 페이징 정보를 파라미터로 넘겨서 Page<Restaurant> 객체를 반환 받는다.
        Page<Restaurant> restaurants = restaurantService.getAdminRestaurantPage(restaurantSearchDto, pageable);

        //조회한 게시글 데이터 및 페이징 정보를 뷰에 전달
        model.addAttribute("restaurants", restaurants);
        //페이지 전환시 기존 검색조건을 유지한 채 이동할 수 있도록 뷰에 다시 전달
        model.addAttribute("restaurantSearchDto", restaurantSearchDto);
        //게시글 관리 메뉴 하단에 보여줄 페이지 번호의 최대 개수. 5로 설정했으므로 최대 5개의 이동할 페이지 번호만 보여준다.
        model.addAttribute("maxPage", 5);

        //template/restaurant 폴더 아래에 restaurantMng.html 파일을 생성한다.
        return "restaurant/restaurantMng";
    }

    @GetMapping("/ranking")
    public String getTop3RestaurantsByAvgScore(Model model) {
        List<RestaurantDto> ranking = restaurantService.getTop3RestaurantsByAvgScore();
        model.addAttribute("ranking", ranking);
        return "restaurant/restaurantRanking";
    }

}