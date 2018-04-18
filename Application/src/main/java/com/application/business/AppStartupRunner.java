package com.application.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.application.model.Product;
import com.application.util.PdfGenaratorUtil;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);
    
    @Autowired
    PdfGenaratorUtil pdfGenaratorUtil;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Your application started with option names : {}", args.getOptionNames());
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("company_name","WelcomeShop.Ltd");
        data.put("company_address","Near Old Market Baramati");
        data.put("company_city_zip_state","Baramati-413102,Maharashtra");
        data.put("company_phone_fax","Fax:01255455");
        data.put("company_email_web","welcomeshop@gmail.com");
        
        data.put("issue_date_label","Invoice");
        data.put("invoice_number","001");
        data.put("issue_date","4/12/2018");
        data.put("invoice_title","Invoice");
        
        data.put("bill_to_label","Bill To");
        data.put("client_name","Swapnil Tambe");
        data.put("client_address","Near Market yard");
        data.put("client_city_zip_state","Baramati-413102,Maharashtra");
        data.put("client_phone_fax","");
        data.put("client_email","swapniltambe@gmail.com");
        data.put("client_other","");
        
        //net days label
        data.put("net_term_label","NET-");
        data.put("due_date_label","Due On");
        //data.put("po_number_label","001");
        
        
        
        data.put("net_term","21");
        data.put("due_date","15/08/2018");
        //data.put("po_number","001");
        
        data.put("currency_label","*All prices are in");
        data.put("currency","INR");
        
        
        data.put("item_row_number_label","SR.No");
        data.put("item_description_label","ITEM");
        data.put("item_quantity_label","Quantity");
        data.put("item_price_label","PRICE");
        data.put("item_discount_label","DISCOUNT");
        data.put("item_tax_label","GST");
        data.put("item_line_total_label","SUB-TOTAL");
        
       
        List<Product> products=new ArrayList<>();
        products=getProductList();
        products.forEach(product->{
        	System.out.println(product.getProductName());
        	System.out.println(product.getQuantity());
        });
        data.put("productList",products);
        
        /*data.put("item_row_number","1");
        data.put("item_description","Laptop");
        data.put("item_quantity","1");
        data.put("item_price","50000");
        data.put("item_discount","5");
        data.put("item_tax","18%");
        data.put("item_line_total","61000");*/
        
        data.put("amount_subtotal_label","Total");
        data.put("amount_subtotal","1681.5");
        
        data.put("tax_name_SGST","SGST");
        data.put("SGST","9%");
        
        data.put("tax_name_CGST","CGST");
        data.put("CGST","9%");
        
        data.put("amount_total","1681.5");
        
        
        data.put("amount_paid_label","Paid ");
        data.put("amount_paid","1500");
        
        data.put("amount_due_label","Due Amount");
        data.put("amount_due","181.5");
        
        data.put("terms_label","Terms & Conditions*");
        data.put("terms","Thank you very much! \n In case of return a product, Product should return brfore 5 days");
        data.put("payment_type","Payment Type");
        data.put("payment_method","cash");
        
        pdfGenaratorUtil.createPdf("InvoiceTemplate",data); 
        
        
    }

	private List<Product> getProductList() {
		List<Product> prodList=new ArrayList<>();
		for(int i=0;i<2;i++) {
			Product product=new Product();
			product.setProductName("Laptop");
			product.setDiscount(5);
			product.setPrice(500);
			product.setQuantity("3");
			product.setTax("18%");
			product.setTotal(560.5);
			prodList.add(product);
		}
		return prodList;
	}
}