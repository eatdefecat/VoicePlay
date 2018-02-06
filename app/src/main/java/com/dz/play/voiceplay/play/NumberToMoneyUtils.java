package com.dz.play.voiceplay.play;

import java.math.BigDecimal;

public class NumberToMoneyUtils {

    private static final String[] CN_UPPER_NUMBER = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
    private static final String[] CN_UPPER_MONETRAY_UNIT = { "", "", "", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千", "兆", "十", "百", "千" };

    public static String toMoneyUnit(String str){
        try {
            if(str.contains(".") && str.split("\\.").length > 1){
                String[] strs = str.split("\\.");
                return "B" + toMoney(new BigDecimal(strs[0])) + "点" + toNumber(strs[1]) + "元";
            }else{
                return "B" + toMoney(new BigDecimal(str)) + "元";
            }
        }catch (Throwable e){
            return "零";
        }
    }

    private static String toMoney(BigDecimal numberOfMoney) {
        StringBuilder sb = new StringBuilder();
        int signum = numberOfMoney.signum();
        if (signum == 0) {
            return "零";
        }
        long number = numberOfMoney.movePointRight(2).setScale(0, 4).abs().longValue();
        long scale = number % 100;
        int numUnit;
        int numIndex = 0;
        boolean getZero = false;
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            number = number / 10;
            ++numIndex;
        }
        return sb.toString();
    }

    private static String toNumber(String string) {
        String result = "";
        int n = string.length();
        for (int i = 0; i < n; i++) {
            int num = string.charAt(i) - '0';
            result += CN_UPPER_NUMBER[num];
        }
        return result;
    }
}
