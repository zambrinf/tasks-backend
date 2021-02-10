package br.ce.wcaquino.taskbackend.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void deveRetornarTrueParaDatasFuturas() {
        LocalDate localDate = LocalDate.now().plusYears(1);
        boolean resultado = DateUtils.isEqualOrFutureDate(localDate);
        Assert.assertFalse(resultado);
    }

    @Test
    public void deveRetornarFalseParaDatasPassadas() {
        LocalDate localDate = LocalDate.now().minusYears(1);
        boolean resultado = DateUtils.isEqualOrFutureDate(localDate);
        Assert.assertFalse(resultado);
    }

    @Test
    public void deveRetornarTrueParaDataAtual() {
        LocalDate localDate = LocalDate.now();
        boolean resultado = DateUtils.isEqualOrFutureDate(localDate);
        Assert.assertTrue(resultado);
    }
}
