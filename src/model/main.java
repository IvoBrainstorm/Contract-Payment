
package model;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContracService;
import model.services.PaypalPaymentService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter contract data: ");
        System.out.print("Number: ");
        int number = sc.nextInt();
        sc.nextLine();
        System.out.print("Date (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.nextLine());
        System.out.print("Contract Value: ");
        double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.print("Enter number of installments: ");
        int installments = sc.nextInt();

        ContracService contracService = new ContracService(new PaypalPaymentService());
        contracService.processContracr(contract, installments);

        System.out.println("installments");
        for (Installment x : contract.getList()){
            System.out.println(x);
        }

        sc.close();
    }
}
