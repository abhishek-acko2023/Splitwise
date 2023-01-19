package com.project.splitwise;

import com.project.splitwise.local.BalanceList;
import com.project.splitwise.local.ExpenseList;
import com.project.splitwise.local.UserList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.project.splitwise"} )
@RestController
public class SplitwiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
		UserList.getInstance();
		ExpenseList.getInstance();
		BalanceList.getInstance();
	}

}
