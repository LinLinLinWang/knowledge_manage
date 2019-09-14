package knowledgebase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("knowledgebase.mapper") //扫描mapper
@ServletComponentScan  //扫描filter
public class KnowledgebaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgebaseApplication.class, args);

    }

}

