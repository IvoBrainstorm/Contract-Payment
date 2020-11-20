package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContracService {

    private OnlinePaymentService onlinePaymentService;


    public ContracService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContracr(Contract contract, Integer months){
        double basicQuote = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i ++){
            Date date = addMoth(contract.getDate(), i);
            double updateQuota = basicQuote + onlinePaymentService.interest(basicQuote, i);
            double fullQuota = updateQuota + onlinePaymentService.paymentFee(updateQuota);

            contract.addInstallment(new Installment(date, fullQuota));
        }

    }

    public Date addMoth(Date date, int n){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
}
