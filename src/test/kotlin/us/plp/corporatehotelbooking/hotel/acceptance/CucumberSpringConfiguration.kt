package us.plp.corporatehotelbooking.hotel.acceptance;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;


@CucumberContextConfiguration
@SpringBootTest
class CucumberSpringConfiguration