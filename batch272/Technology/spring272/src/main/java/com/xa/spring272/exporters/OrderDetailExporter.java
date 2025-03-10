package com.xa.spring272.exporters;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.xa.spring272.models.OrderDetail;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class OrderDetailExporter {

	private List<OrderDetail> listOrderDetail;
	
	public OrderDetailExporter(List<OrderDetail> listOrderDetail) {
		this.listOrderDetail = listOrderDetail;
	}
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.blue);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.white);
		
		cell.setPhrase(new Phrase("RefNumber", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Product", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Price", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Quantity", font));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table) {
		for(OrderDetail order:listOrderDetail) {
			table.addCell(String.valueOf(order.getOrderheader().getReference()));
			table.addCell(String.valueOf(order.getProduct().getName()));
			table.addCell(String.valueOf(order.getPrice()));
			table.addCell(String.valueOf(order.getQuantity()));
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("List of Order", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {3.0f, 3.0f, 3.0f, 3.0f});
		table.setSpacingBefore(10);
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		document.close();
		
	}	
}
