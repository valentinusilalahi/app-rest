package com.silalahi.valentinus.app.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.ResourceSupport;

import com.silalahi.valentinus.app.controller.CustomerController;
import com.silalahi.valentinus.app.entity.Customer;

import lombok.Getter;

@Getter
public class CustomerResource extends ResourceSupport {
	private final Customer customer;

	public CustomerResource(Customer c) {
		this.customer = c;
		add(linkTo(CustomerController.class).withRel("customers"));
	}

	public Customer getCustomer() {
		return customer;
	}
}
