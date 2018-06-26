package com.cheng;


import java.text.DecimalFormat;

/**
 * @author cheng_mboy
 */
public class Money {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.00");
        double sum = 0;
        int money = 4000;
        double r = 0.00010628;
        int year = 3;
        int day = 30 * 12 * year;
        int cricle = 30;
        double interest ;
        double sumInterRest = 0;

        for (int i = 1; i <= day; i++) {
            if ((i - 1) % cricle == 0 || sum == 0) {
                sum += money;
                System.out.printf("当前第%d月: ", (i - 1) / cricle + 1);
                System.out.println(df.format(sum));
            }
            interest = sum * r;
            sumInterRest += interest;
            System.out.println("第" + i + "天利息： " + df.format(interest));
            sum = sum * (1 + r);
        }
        int sumMoney = day / 30 * money;
        System.out.println("月存：" + money + " 年数： " + year );
        System.out.println("总金额： " + df.format(sum) + " 本金 ：" + sumMoney + "  复利利息 ：" + df.format(sumInterRest));

    }
}
