package com.nogueira.rabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nogueira.rabbit.model.Employee;
import com.nogueira.rabbit.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/nogueira-rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	//example: http://localhost:8087/nogueira-rabbitmq/producer?empName=emp1&empId=emp001
	@GetMapping(value = "/producer")
	public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {

		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);

		rabbitMQSender.send(emp);

		return "Message sent to the RabbitMQ Nogueira Successfully";
	}

}