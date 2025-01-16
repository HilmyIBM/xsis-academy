package com.xa.spring272.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;
import com.xa.spring272.exporters.OrderDetailExporter;
import com.xa.spring272.models.OrderDetail;
import com.xa.spring272.services.OrderServices;

@Controller
@RequestMapping(value="/order/")
public class OrderController {
	
	@GetMapping(value="index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/order/index");
		return view;
	}

	@GetMapping(value="neworder")
	public ModelAndView neworder() {
		ModelAndView view = new ModelAndView("/order/neworder");
		return view;
	}
	
	@Autowired OrderServices service;
	
	@GetMapping(value="exportpdf")
	public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateformat = new SimpleDateFormat("yyyymmddHHmmss");
		String currentdate = dateformat.format(new Date());
		
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=od_"+currentdate+".pdf";
		response.setHeader(headerkey, headervalue);
		
		List<OrderDetail> listorderdetails = service.listAll();
		
		OrderDetailExporter exporter = new OrderDetailExporter(listorderdetails);
		exporter.export(response);
	}
}
