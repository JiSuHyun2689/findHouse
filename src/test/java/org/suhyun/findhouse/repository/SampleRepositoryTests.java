package org.suhyun.findhouse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.suhyun.findhouse.entity.Sample;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class SampleRepositoryTests {

    @Autowired
    SampleRepository sampleRepository;

    @Test
    public void testClass(){
        System.out.println(sampleRepository.getClass().getName());
    }

    @Test
    public void testInsert(){

        IntStream.rangeClosed(1, 100).forEach(i ->{
            Sample sample = Sample.builder().text("Sample ... " + i).build();
            sampleRepository.save(sample);
        });
    }

    @Test
    public void testSelect(){

        Long sno = 200L;

        Optional<Sample> result = sampleRepository.findById(sno);

        System.out.println("------------------------------------------------------------------------------");

        if(result.isPresent())
            System.out.println(result.get());
    }

    @Test
    public void testUpdate(){

        Sample sample = Sample.builder().sno(200L).text("Text Update!!!").build();

        sampleRepository.save(sample);
    }

    @Test
    public void testDelete(){

        sampleRepository.deleteById(199L);
    }

    @Test
    public void testPageDefault(){

        Sort sort = Sort.by("sno").descending();

        Pageable pageable = PageRequest.of(0, 10, sort);

        Page<Sample> result = sampleRepository.findAll(pageable);

        System.out.println(result);

        System.out.println("=========================================");

        System.out.println("Total pages : " + result.getTotalPages());

        System.out.println("Total count : " + result.getTotalElements());

        System.out.println("Page Number : " + result.getNumber());

        System.out.println("has next page? " + result.hasNext());

        System.out.println("first page? " + result.isFirst());

        System.out.println("=========================================");

        result.get().forEach(sample -> {
            System.out.println(sample);
        });
    }

}
