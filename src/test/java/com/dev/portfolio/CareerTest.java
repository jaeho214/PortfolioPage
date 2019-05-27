package com.dev.portfolio;

import com.dev.portfolio.model.dto.CareerDto;
import com.dev.portfolio.repository.CareerRepository;
import com.dev.portfolio.service.CareerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CareerTest {
    @Autowired
    private CareerService careerService;

    @Test
    public void testInsert(){
//        CareerDto dto = CareerDto.builder()
//                .company("Tottenham")
//                .department("Striker")
//                .reason("Harry Kane")
//                .build();

//        careerService.saveCareer(dto);
        List<CareerDto> lists = careerService.getCareers();
        for (CareerDto dto:lists) {
              System.out.println(dto);
        }
    }

}
