package com.poccofinance.loan;

import com.poccofinance.loan.repository.LoanRepository;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;


public class PropertyInjectedUtil {

    @Autowired
    protected LoanRepository loanRepository;

    @Value("${com.poccofinance.loan.principal}")
    protected Double loanPrincipal;

    @Value("${com.poccofinance.loan.extend-term-days}")
    protected Integer loanExtensionTermDays;

    @Value("${com.poccofinance.loan.validators.correct-amount.min-amount}")
    protected Long minAmount;

    @Value("${com.poccofinance.loan.validators.correct-amount.max-amount}")
    protected Long maxAmount;

    @Value("${com.poccofinance.loan.validators.correct-amount.min-hour}")
    protected Integer minHour;

    @Value("${com.poccofinance.loan.validators.correct-amount.max-hour}")
    protected Integer maxHour;

    @Value("${com.poccofinance.loan.validators.correct-term-period.min-term}")
    protected Integer minTerm;

    @Value("${com.poccofinance.loan.validators.correct-term-period.max-term}")
    protected Integer maxTerm;

    static public LocalDateTime setFixedTime(LocalDateTime timeToSet) {
        return setFixedTime(timeToSet.toDateTime().getMillis());
    }

    /**
     * Sets fixed Time for all tests. It's necessary for tests to be repeatable,
     * so whenever tests will run between restricted hours will always pass.
     *
     * @return now
     */
    protected LocalDateTime setFixedTime() {
        return setFixedTime(LocalDateTime.now().withHourOfDay(maxHour).plusMinutes(1));
    }

    static private LocalDateTime setFixedTime(long timeToSet) {
        DateTimeUtils.setCurrentMillisFixed(timeToSet);
        return LocalDateTime.now();
    }

    protected Loan prepareSampleLoan() {
        final Loan loan = Loan.builder()
            .loanId(UUID.randomUUID())
            .requestedDate(LocalDateTime.now())
            .term(minTerm)
            .principal(loanPrincipal)
            .amount(minAmount)
            .dueDate(LocalDateTime.now().plusDays(minTerm))
            .build();
        return loanRepository.save(loan);
    }
}
