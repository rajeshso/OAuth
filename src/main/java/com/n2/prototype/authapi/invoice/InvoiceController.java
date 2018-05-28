package com.n2.prototype.authapi.invoice;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	private InvoiceRepository invoiceRepository;

	public InvoiceController(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@PostMapping
	public void addInvoice(@RequestBody Invoice invoice) {
		invoiceRepository.save(invoice);
	}

	@GetMapping
	public List<Invoice> getInvoices() {
		return invoiceRepository.findAll();
	}

	@PutMapping("/{id}")
	public void editInvoice(@PathVariable long id, @RequestBody Invoice invoice) {
		Invoice existingInvoice = invoiceRepository.findOne(id);
		Assert.notNull(existingInvoice, "Invoice not found");
		existingInvoice.setDescription(invoice.getDescription());
		invoiceRepository.save(existingInvoice);
	}

	@DeleteMapping("/{id}")
	public void deleteInvoice(@PathVariable long id) {
		invoiceRepository.delete(id);
	}
}
