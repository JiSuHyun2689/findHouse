package org.suhyun.findhouse.repository.search;

import com.fasterxml.jackson.databind.ObjectReader;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import com.querydsl.core.types.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.entity.*;

import java.util.List;
import java.util.stream.Collectors;


@Log4j2
public class SearchHouseRepositoryImpl extends QuerydslRepositorySupport implements SearchHouseRepository {

    public SearchHouseRepositoryImpl() {
        super(House.class);
    }

    @Override
    public House search() {
        log.info("search house ............................................");

        QHouse house = QHouse.house;
        QReview review = QReview.review;
        QMember member = QMember.member;

        JPQLQuery<House> jpqlQuery = from(house);
        jpqlQuery.leftJoin(member).on(house.id.eq(member.id));
        jpqlQuery.leftJoin(review).on(review.house.eq(house));


        //jpqlQuery.select(house, member.id, review.count()).groupBy(house).where(house.houseNum.eq(3L));


        JPQLQuery<Tuple> tuple = jpqlQuery.select(house, member.id, review.count());
        tuple.groupBy(house);

        log.info("----------------------");
        log.info(tuple);
        log.info("-----------------------");


        List<Tuple> result = tuple.fetch();

        log.info(result);

        return null;
    }


    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage ............................................................");

        QHouse house = QHouse.house;
        QReview review = QReview.review;
        QMember member = QMember.member;

        JPQLQuery<House> jpqlQuery = from(house);
        jpqlQuery.leftJoin(member).on(house.id.eq(member.id));
        jpqlQuery.leftJoin(review).on(review.house.eq(house));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(house, member, review.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = house.houseNum.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String str : typeArr) {
                switch (str) {
                    case "t":
                        conditionBuilder.or(house.title.contains(keyword));
                    case "w":
                        conditionBuilder.or(member.id.contains(keyword));
                    case "c":
                        conditionBuilder.or(house.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

        tuple.where(booleanBuilder);

        //order by
        Sort sort = pageable.getSort();
        // tuple.orderBy(house.houseNum.desc());

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(House.class, "house");

            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });


        tuple.groupBy(house);

        // page
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        // count
        Long count = tuple.fetchCount();
        log.info("count : " + count);

        return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }


    @Override
    public Page<Object[]> searchPageWithAll(PageRequestDTO requestDTO) {

        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        Pageable pageable = requestDTO.getPageable(Sort.by("houseNum").descending());

        QHouse house = QHouse.house;
        QReview review = QReview.review;
        QMember member = QMember.member;
        QOption option = QOption.option;
        QPrice price = QPrice.price1;
        QStructure structure = QStructure.structure;
        QCost cost = QCost.cost;
        QHouseImage houseImage = QHouseImage.houseImage;

        JPQLQuery<House> jpqlQuery = from(house);
        jpqlQuery.leftJoin(option).on(option.house.eq(house));
        jpqlQuery.leftJoin(price).on(price.house.eq(house));
        jpqlQuery.leftJoin(structure).on(structure.house.eq(house));
        jpqlQuery.leftJoin(cost).on(cost.house.eq(house));
        //jpqlQuery.leftJoin(review).on(review.targetId.eq(house.id));
        jpqlQuery.leftJoin(review).on(review.house.eq(house));
        jpqlQuery.leftJoin(member).on(house.id.eq(member.id));
        jpqlQuery.leftJoin(houseImage).on(houseImage.house.houseNum.eq(house.houseNum));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(house, houseImage, option, price, structure, cost, review.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = house.houseNum.gt(0L);
        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String str : typeArr) {
                switch (str) {
                    case "t":
                        conditionBuilder.or(house.title.contains(keyword));
                    case "w":
                        conditionBuilder.or(house.id.contains(keyword));
                    case "c":
                        conditionBuilder.or(house.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(House.class, "house");

            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.where(booleanBuilder).groupBy(house.houseNum).offset(pageable.getOffset()).limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        Long count = tuple.fetchCount();

        log.info("Search List Page Result : " + result);

        return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }
}
