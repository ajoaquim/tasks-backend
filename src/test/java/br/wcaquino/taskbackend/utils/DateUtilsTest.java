package br.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.taskbackend.utils.DateUtils;


public class DateUtilsTest {
	
	@Test
	public void deveRetornarTrueParaDataFutura() {
		
		LocalDate date = LocalDate.of(2030,01,10);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	
	}
	@Test
	public void deveRetornarFalseParaDataPassada() {
		
		LocalDate date = LocalDate.of(2020,01,10);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
	
	}
	@Test
	public void deveRetornarTrueParaDataIgual() {
		
		LocalDate date = LocalDate.now();
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	
	}
}
