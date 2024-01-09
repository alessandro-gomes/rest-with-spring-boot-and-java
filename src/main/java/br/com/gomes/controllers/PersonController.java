package br.com.gomes.controllers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gomes.exceptions.UnsupportedMathOperationException;
import br.com.gomes.services.PersonServices;

@RestController
public class PersonController{

	@Autowired
	private PersonServices service;
	//private PersonServices service = new PersonServices();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne , @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please, set a numeric value!"); 
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}

	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double subtraction(@PathVariable(value = "numberOne") String numberOne , @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please, set a numeric value!"); 
		}
		
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double multiplication(@PathVariable(value = "numberOne") String numberOne , @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please, set a numeric value!"); 
		}
		
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double division(@PathVariable(value = "numberOne") String numberOne , @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please, set a numeric value!"); 
		}
		
		Double result = convertToDouble(numberOne) / convertToDouble(numberTwo);
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	    symbols.setDecimalSeparator('.');
	    
		DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
		String formattedResult = decimalFormat.format(result);
		
		return convertToDouble(formattedResult);
	}
	
	@RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double average(@PathVariable(value = "numberOne") String numberOne , @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please, set a numeric value!"); 
		}
		
		Double result = (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		
		DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
		String formattedResult = decimalFormat.format(result);
		
		return convertToDouble(formattedResult);
	}
	
	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
	private Double convertToDouble(String strNumber) {
		if (strNumber == null) return 0D; 
			// BR 10,25  US 10.25
			String number = strNumber.replaceAll(",", ".");
			if (isNumeric(number)) return Double.parseDouble(number);
			return 0D;
	}

}
