package com.example.payrollapi;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.payrollapi.controller.EmployeeController;
import com.example.payrollapi.model.Employee;
import com.example.payrollapi.repo.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;


import java.util.*;

// TODO: Add more robust negative tests

@WebMvcTest(EmployeeController.class)
class PayrollApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	private EmployeeRepository employeeRepository;

	@MockBean
	private EmployeeController employeeController;

	// Stage test data
	Employee emp1 = new Employee(1l, "Brent", "Lead Engineer");
	Employee emp2 = new Employee(2l, "Chris", "Director of Development");

	List<Employee> employees = new ArrayList<>(Arrays.asList(emp1, emp2));

	@Test
	public void generalApiTest_success() throws Exception {
		when(employeeController.sayHello()).thenReturn("I'm up and running waiting for some requests!");

		//System.out.println("###########################  generalApiTest_success()  ###########################");

		this.mockMvc.perform(get("/api/test"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("I'm up and running waiting for some requests!")));

		//System.out.println("###########################  END  ###########################");
	}

	@Test
	public void getAllEmployees_success() throws Exception {

		//System.out.println("###########################  getAllEmployees_success()  ###########################");

		Mockito.when(employeeController.getAllEmployees()).thenReturn(employees);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/employees").contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(employees.size())))
				.andExpect(jsonPath("$[0].empname", is("Brent")))
				.andExpect(jsonPath("$[1].empname", is("Chris")));

		//System.out.println("###########################  END  ###########################");
	}

	@Test
	public void getEmployeeById_success() throws Exception {

		Mockito.when(employeeController.getEmployeeByID(emp1.getId())).thenReturn(emp1);

		//System.out.println("###########################  getEmployeeById_success()  ###########################");

		mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/1").contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.empname", is("Brent")));

		//System.out.println("###########################  END  ###########################");
	}

	@Test
	public void createEmployee_success() throws Exception {

		//System.out.println("###########################  createEmployee_success()  ###########################");

		Employee employee = new Employee(3l, "Wes", "Director of IT Infra");

		Mockito.when(employeeController.addEmployee(employee)).thenReturn(employee);

		employees.add(employee);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(employee)))
				.andDo(print())
				.andExpect(status().isOk());

		// run all employees again
		//getAllEmployees_success();

		//System.out.println("###########################  END  ###########################");
	}

	@Test
	public void updateEmployee_success() throws Exception {

		//System.out.println("###########################  updateEmployee_success()  ###########################");

		Employee updatedEmployee = new Employee(1l, "Brent", "Manager");

		Mockito.when(employeeController.updateEmployee(updatedEmployee.getId(), updatedEmployee)).thenReturn(updatedEmployee);

		employees.set(0, updatedEmployee);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/1").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(updatedEmployee)))
				.andDo(print())
				.andExpect(status().isOk());

		// run all employees again
		//getAllEmployees_success();

		//System.out.println("###########################  END  ###########################");
	}
}
