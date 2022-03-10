package org.suhyun.findhouse.repository;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.web.WebAppConfiguration;
import org.suhyun.findhouse.dto.HouseDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.entity.*;
import org.suhyun.findhouse.repository.search.SearchHouseRepository;
import org.suhyun.findhouse.service.HouseService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

@SpringBootTest
@WebAppConfiguration
public class HouseRepositoryTests {


    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseImageRepository houseImageRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private StructureRepository structureRepository;

    @Autowired
    private CostRepository costRepository;

    @Autowired
    private HouseService houseService;


    @Test
    public void insertHouseTest() {

        LocalDate date = LocalDate.of(2022, 12, 31);


        House house = House.builder()
                .completionDate(date)
                .title("사회 초년생이 살기 너무 좋은 집")
                .address("서울 ... ")
                .buildingType("오피스텔")
                .contractType("월세")
                .content("content...")
                .id("user")
                .moveInDate(date)
                .area(21)
                .brokerage(20)
                .minTerm(3)
                .loan(false)
                .parking(true)
                .pet(true)
                .elevator(true)
                .theFloor(3)
                .wholeFloor(10)
                .status("거래가능")
                .build();

        houseRepository.save(house);

    }


    @Test
    public void insertTest() {

        LocalDate date = LocalDate.of(2022, 12, 31);

        IntStream.rangeClosed(1, 200).forEach(i -> {

            House house = House.builder()
                    .title("사회 초년생이 살기 너무 좋은 집")
                    .address("서울 ... " + i)
                    .buildingType("오피스텔")
                    .contractType("월세")
                    .content("content..." + i)
                    .id("user" + i)
                    .moveInDate(date)
                    .completionDate(date)
                    .area(21)
                    .brokerage(20)
                    .minTerm(3)
                    .loan(false)
                    .parking(true)
                    .pet(true)
                    .elevator(true)
                    .theFloor(3)
                    .wholeFloor(10)
                    .status("거래가능")
                    .build();

            houseRepository.save(house);

            Option option = Option.builder()
                    .washer(true)
                    .dishwasher(false)
                    .tv(true)
                    .sink(true)
                    .shoeRack(true)
                    .refrigerator(true)
                    .induction(true)
                    .gasStove(false)
                    .bookshelf(false)
                    .dryer(false)
                    .desk(true)
                    .closet(true)
                    .bed(true)
                    .airConditioner(true)
                    .airConditioner(true)
                    .house(house)
                    .build();

            optionRepository.save(option);

            Price price = Price.builder()
                    .deposit(5000000)
                    .monthly(500000)
                    .house(house)
                    .build();

            priceRepository.save(price);

            Structure structure = Structure.builder()
                    .room(1)
                    .toilet(1)
                    .house(house)
                    .build();

            structureRepository.save(structure);

            Cost cost = Cost.builder()
                    .totalCost(100000)
                    .electricity(true)
                    .gas(false)
                    .internet(true)
                    .tv(true)
                    .parking(false)
                    .water(true)
                    .etc(true)
                    .content("cost Content!!!")
                    .house(house)
                    .build();

            costRepository.save(cost);

            int random = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < random; j++) {

                HouseImage houseImage = HouseImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .house(house)
                        .imageName("imgName..." + i)
                        .path("path..." + i)
                        .build();

                houseImageRepository.save(houseImage);
            }
        });
    }

    @Test
    @Transactional
    @Commit
    public void houseUpdateTest() {

        House house = houseRepository.getById(20L);

        house.changeStatus("변경");
        house.changeContent("내용 변경");
        houseRepository.save(house);

    }

    @Test
    public void houseGetTest() {

        Optional<House> result = houseRepository.findById(200L);

        House house = result.get();

        System.out.println(house);
    }

    @Test
    @Transactional
    @Commit
    public void houseDeleteTest() {

        Long houseNum = 1L;
        House house = House.builder().houseNum(houseNum).build();
        System.out.println(house);

        costRepository.deleteByHouse(houseNum);

        structureRepository.deleteByHouse(houseNum);

        priceRepository.deleteByHouse(houseNum);

        optionRepository.deleteByHouse(houseNum);

        houseImageRepository.deleteByHouse(houseNum);

        houseRepository.deleteById(houseNum);

    }


    @Commit
    @Transactional
    @Test
    public void insertHouses() {

        IntStream.rangeClosed(1, 20).forEach(i -> {

            House house = House.builder()
                    .title("이미지 테스트")
                    .address("주소")
                    .area(12.9)
                    .brokerage(200000)
                    .buildingType("오피스텔")
                    .completionDate(LocalDate.of(2022, 05, 05))
                    .moveInDate(LocalDate.of(2022, 05, 10))
                    .status("거래불가")
                    .wholeFloor(10)
                    .theFloor(5)
                    .elevator(true)
                    .pet(false)
                    .parking(true)
                    .loan(true)
                    .minTerm(6)
                    .contractType("전세")
                    .id("작성자")
                    .content("내용내용내용")
                    .build();

            System.out.println("-----------------------------------------------------------------");

            houseRepository.save(house);

            int count = ((int) (Math.random()) * 5) + 1;

            for (int j = 0; j < count; j++) {
                HouseImage houseImage = HouseImage.builder().uuid(UUID.randomUUID().toString()).house(house).imageName("test" + j + ".jpg").build();
                houseImageRepository.save(houseImage);
            }
            System.out.println("==================================================================");
        });

    }


    @Test
    public void testListPage() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "houseNum"));

        Page<Object[]> result = houseRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    /*
    @Test
    public void testGetListPageTest(){

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "houseNum"));

        Page<Object[]> result = houseRepository.getListPageTest(pageRequest);

        for(Object[] objects : result.getContent()){
            System.out.println(Arrays.toString(objects));
        }
    }*/


    @Test
    public void testGetHouseWithAll() {

        List<Object[]> result = houseRepository.getHouseWithAll(3L);

        //System.out.println(result);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }

    }

    @Test
    public void testSearch() {
        houseRepository.search();
    }

    @Test
    public void testSearchPage() {

        PageRequestDTO requestDTO = new PageRequestDTO();
        requestDTO.setPage(0);
        requestDTO.setKeyword("수정");
        requestDTO.setSize(10);
        requestDTO.setType("c");
        Pageable pageable = PageRequest.of(0, 10, Sort.by("houseNum").descending().and(Sort.by("title").ascending()));
        Page<Object[]> result = houseRepository.searchPageWithAll(requestDTO);

    }


    @Test
    public void testSearch1() {

        PageRequestDTO requestDTO = new PageRequestDTO();
        requestDTO.setPage(1);
        requestDTO.setKeyword("수정");
        requestDTO.setSize(10);
        requestDTO.setType("c");

        Pageable pageable = requestDTO.getPageable(Sort.by("houseNum").descending().and(Sort.by("title").ascending()));

        Page<Object[]> result = houseRepository.searchPageWithAll(requestDTO);

        Function<Object[], HouseDTO> fn = (arr -> houseService.entityToDto(
                (House) arr[0],
                Arrays.asList((HouseImage) arr[1]),
                (Option) arr[2],
                (Price) arr[3],
                (Structure) arr[4],
                (Cost) arr[5],
                (Long) arr[6])
        );

        System.out.println(fn.toString());
        System.out.println(result);

    }


    @Test
    public void testFindByHouse(){

        List<HouseImage> result = houseImageRepository.findByHouse(4L);

        System.out.println(result);
    }
}
