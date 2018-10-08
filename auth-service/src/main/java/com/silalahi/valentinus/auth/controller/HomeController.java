package com.silalahi.valentinus.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping("/home")
	public void homePage() {

	}

	@PreAuthorize("hasAuthority('VIEW_TRANSAKSI')")
	@GetMapping("/transaksi/view")
	@ResponseBody
	public Map<String, Object> viewTransaksi() {
		Map<String, Object> hasil = new HashMap<>();
		hasil.put("page", "View Transaksi");
		return hasil;
	}

	@PreAuthorize("hasAuthority('EDIT_TRANSAKSI')")
	@GetMapping("/transaksi/edit")
	@ResponseBody
	public Map<String, Object> editTransaksi() {
		Map<String, Object> hasil = new HashMap<>();
		hasil.put("page", "Edit Transaksi");
		return hasil;
	}

	@PreAuthorize("hasAuthority('APPROVE_TRANSAKSI')")
	@GetMapping("/transaksi/approve")
	@ResponseBody
	public Map<String, Object> approveTransaksi() {
		Map<String, Object> hasil = new HashMap<>();
		hasil.put("page", "Approve Transaksi");
		return hasil;
	}
}
