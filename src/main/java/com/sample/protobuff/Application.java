package com.sample.protobuff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sample.protobuff.domain.SampleData;
import com.sample.protobuff.mapper.SampleMapper;


@SpringBootApplication
@ComponentScan(basePackages = "com.sample.protobuff.mapper")
@EnableScheduling
public class Application implements CommandLineRunner {

	@Autowired
	private SampleMapper sampleMapper;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	public Application() {
		
	}

	@Override
	public void run(String... args) throws Exception {
		List<SampleData> dataList = sampleMapper.selectList();
		
		System.out.println(dataList.size());
	}

}
