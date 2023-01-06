package com.swcoaching.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SwCoachingApplication {

  public static void main(String[] args) {
    // 자바의 진입점, 이 안에서 톰캣이 실행된다. 여러가지 코드들이 돌아가는 구조
    SpringApplication.run(SwCoachingApplication.class, args);
  }

}
