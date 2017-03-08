package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.web.client.RestTemplate;

import com.guru.controller.EmpRestURLConsonants;
import com.guru.model.Car;
import com.guru.model.Employee;

public class TestRestEmployee {
	
	private static final String SERVER_URI="http://localhost:8080/";
	
	public static void main(String[] args) {
		System.out.println("start");
		testDummyEmp();
		System.out.println("***************");
		testEmps();
	}
	
	public static void testDummyEmp(){
		RestTemplate restTemplate= new RestTemplate();
		Employee empResp= restTemplate.getForObject(SERVER_URI+EmpRestURLConsonants.EMP_DUMMY, Employee.class);
		printEmp(empResp);
		
	}
	public static void testEmps(){
		RestTemplate restTemplate= new RestTemplate();
		List<LinkedHashMap> emps=  restTemplate.getForObject(SERVER_URI+EmpRestURLConsonants.EMPS, List.class);
		List<LinkedHashMap> cars;
		List<Car> cars1= new ArrayList<>();
		for (LinkedHashMap map: emps) {
			cars1=(List<Car>) map.get("cars");
//			cars=(List<LinkedHashMap>) map.get("cars");
			for (Car car : cars1) {
				System.out.println("id "+map.get("id")+" name "+map.get("name")+" cars "+car.getCarName());
			}
		}
		
	}
	public static void printEmp(Employee emp){
		System.out.println("id "+emp.getId()+" name: "+emp.getName());
	}
}