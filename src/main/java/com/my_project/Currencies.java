package com.my_project;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Currencies {
    public static final int SCALE_FOR_RATE = 5;
    public static final int SCALE_FOR_DIVADE = 10;
    private Map<String, BigDecimal> curs = new TreeMap<>();

    public void addCurrency(String currency, BigDecimal weight) {
        curs.put(currency, weight);
    }

    //http://localhost:.../currencies.jsp
    public Collection<String> getCurrencies() {
        List<String> result = new ArrayList<>(curs.keySet());
        Collections.sort(result);
        return result;
    }

    //http://localhost:.../exchangeRates.jsp?from=USD
    public Map<String, BigDecimal> getExchangeRates(String referenceCurrency) {
        Map<String, BigDecimal> result = new TreeMap<>();
        BigDecimal rateReferenceCurrency  = curs.get(referenceCurrency);
        for (Map.Entry<String, BigDecimal> entryCurs : curs.entrySet()) {
            result.put(entryCurs.getKey(),
                    rateReferenceCurrency.divide(entryCurs.getValue(), SCALE_FOR_RATE, RoundingMode.HALF_UP));
        }
        return result;
    }

    //http://localhost:.../convert.jsp?amount=366&from=GBP&to=USD
    public BigDecimal convert(BigDecimal amount, String sourceCurrency, String targetCurrency) {
        BigDecimal rateSourceCurrency  = curs.get(sourceCurrency);
        BigDecimal rateTargetCurrency  = curs.get(targetCurrency);
        BigDecimal exchangeRateCurs = rateSourceCurrency.divide(rateTargetCurrency,
                SCALE_FOR_DIVADE, RoundingMode.HALF_UP);

        return amount.multiply(exchangeRateCurs).setScale(SCALE_FOR_RATE, RoundingMode.HALF_UP);
    }
}
