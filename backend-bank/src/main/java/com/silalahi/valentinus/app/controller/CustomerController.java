package com.silalahi.valentinus.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.silalahi.valentinus.app.dao.CustomerDao;
import com.silalahi.valentinus.app.entity.Customer;
import com.silalahi.valentinus.app.hateoas.CustomerResource;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	private static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Value("${upload.folder}")
	String uploadFolder;

	@Autowired
	private CustomerDao customerDao;

	@CrossOrigin
	@PreAuthorize("hasAuthority('VIEW_TRANSAKSI')")
	@GetMapping("/")
	public Page<Customer> findCustomer(Pageable page) {
		return customerDao.findAll(page);
	}

	@PreAuthorize("hasAuthority('VIEW_TRANSAKSI')")
	@GetMapping("/{customer}")
	public Customer findCustomerById(@PathVariable Customer customer) {
		return customer;
	}

	@PreAuthorize("hasAuthority('VIEW_TRANSAKSI')")
	@GetMapping("/{customer}/resource")
	public CustomerResource findCustomerResource(@PathVariable Customer customer) {
		return new CustomerResource(customer);
	}

	@PreAuthorize("hashAuthority('EDIT_TRANSAKSI')")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Customer customer, HttpServletRequest request,
			HttpServletResponse response) {
		customerDao.save(customer);
		String id = customer.getId();
		String currentUrl = request.getRequestURL().append(id).toString();
		response.setHeader("Location", currentUrl);
	}

	@PreAuthorize("hashAuthority('APPROVE_TRANSAKSI')")
	@DeleteMapping("/{customer}")
	public void delete(@PathVariable Customer customer) {
		File photo = filePhoto(customer.getPhoto());
		photo.delete();
		customerDao.delete(customer);
	}

	private File filePhoto(String fileName) {
		// TODO Auto-generated method stub
		return new File(uploadFolder + File.separator + fileName);
	}

	@PutMapping("/{customer}")
	public void update(@PathVariable("customer") Customer old, @RequestBody @Valid Customer newData) {
		newData.setId(old.getId());
		customerDao.save(newData);
	}

	@PutMapping("/{customer}/photo")
	public void setPhoto(@PathVariable Customer customer, MultipartFile photo) throws IOException {
		String destFileName = customer.getId() + ".jpg";
		new File(uploadFolder).mkdirs();
		File destFile = filePhoto(destFileName);
		photo.transferTo(destFile);
		customer.setPhoto(destFileName);
		customerDao.save(customer);
	}

	public ResponseEntity<Resource> downloadPhoto(@PathVariable Customer customer) {
		try {
			File photo = filePhoto(customer.getPhoto());
			InputStreamResource resource;
			resource = new InputStreamResource(new FileInputStream(photo));
			return ResponseEntity.ok().contentLength(photo.length()).contentType(MediaType.IMAGE_JPEG).body(resource);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}

}
