package com.machado.apresentacao;

public class MyFormatter {

    private MyFormatter() {}

    public static String formatCpf(long l) {
        return String.format("%011d", l);
    }

    public static String formatMoney(float f) {
        return String.format("%.2f", f);
    }
}
