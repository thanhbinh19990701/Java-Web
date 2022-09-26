package com.web.services;

import com.web.models.Customer;
import com.web.models.ResponseObject;

public interface ICustomerService {
	public ResponseObject<Customer> getCustomerByMail(Customer customer);
	public ResponseObject<Customer> createCustomer(Customer customer);
}