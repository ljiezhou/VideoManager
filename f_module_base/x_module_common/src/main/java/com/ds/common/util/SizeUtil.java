package com.ds.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class SizeUtil {
    public static final long sz1B = 1L;
    public static final long sz1GB = 0x40000000L;
    public static final long sz1KB = 0x400L;
    public static final long sz1MB = 0x100000L;
    public static final long sz1TB = 0x10000000000L;

    public static String _formatSizeDecimalPartOnly(long arg2) {
        String v0 = arg2 > 0x40000000L ? SizeUtil.formatSize_3(arg2) : SizeUtil.formatSizeDecimalPartOnly(arg2);
        return v0;
    }

    public static String formatSize(long arg10, int arg12) {
        String v0;
        if(arg10 < 0L) {
            v0 = "" + arg10;
        }
        else if(arg10 >= 0x40000000L) {
            long v0_1 = arg10 % 0x40000000L;
            long v2 = 10L * v0_1 % 0x40000000L;
            v0 = String.format("%sGB", new BigDecimal(arg10 / 0x40000000L + "." + v0_1 * 10L / 0x40000000L + v2 * 10L / 0x40000000L + 10L * (10L * v2 % 0x40000000L) / 0x40000000L).setScale(arg12, RoundingMode.HALF_UP).toString());
        }
        else if(arg10 >= 0x100000L) {
            long v0_2 = arg10 % 0x100000L;
            long v2_1 = 10L * v0_2 % 0x100000L;
            v0 = String.format("%sMB", new BigDecimal(arg10 / 0x100000L + "." + v0_2 * 10L / 0x100000L + v2_1 * 10L / 0x100000L + 10L * (10L * v2_1 % 0x100000L) / 0x100000L).setScale(arg12, RoundingMode.HALF_UP).toString());
        }
        else if(arg10 >= 0x400L) {
            long v0_3 = arg10 % 0x400L;
            long v2_2 = 10L * v0_3 % 0x400L;
            v0 = String.format("%sKB", new BigDecimal(arg10 / 0x400L + "." + v0_3 * 10L / 0x400L + v2_2 * 10L / 0x400L + 10L * (10L * v2_2 % 0x400L) / 0x400L).setScale(arg12, RoundingMode.HALF_UP).toString());
        }
        else if(arg10 == 0L) {
            v0 = "0 KB";
        }
        else {
            v0 = "< 1 KB";
        }

        return v0;
    }

    public static String formatSize(long arg8, String arg10) {
        float v0;
        String v1 = null;
        if(arg8 >= 0x400L) {
            v1 = "KB";
            v0 = (float)((((double)arg8)) / 1024);
            if(v0 >= 1024f) {
                v1 = "MB";
                v0 /= 1024f;
            }

            if(v0 >= 1024f) {
                v1 = "GB";
                v0 /= 1024f;
            }
        }
        else {
            v0 = (float)arg8;
        }

        StringBuilder v3 = new StringBuilder(new DecimalFormat(arg10).format(v0));
        if(v1 == null) {
            v3.append("B");
        }
        else {
            v3.append(v1);
        }

        return v3.toString();
    }

    public static String formatSize2(long arg2) {
        return SizeUtil.formatSize(arg2, "#0.0");
    }

    public static String formatSize2MB(long arg6) {
        String v0 = "0MB";
        if(arg6 > 0L) {
            DecimalFormat v1 = new DecimalFormat("#0.##");
            v0 = v1.format((((float)arg6)) / 1048576f) + "MB";
        }

        return v0;
    }

    public static String formatSize3(long arg6) {
        String v0;
        String v1;
        float v2;
        if(arg6 >= 0x3E800000L) {
            v2 = (float)((((double)arg6)) / 1073741824);
            v1 = "GB";
        }
        else if(arg6 >= 0xFA000L) {
            v2 = (float)((((double)arg6)) / 1048576);
            v1 = "MB";
        }
        else {
            v2 = (float)((((double)arg6)) / 1024);
            v1 = "KB";
        }

        if(v2 > 100f) {
            v0 = "#0";
        }
        else if(v2 > 10f) {
            v0 = "#0.0";
        }
        else {
            v0 = "#0.00";
        }

        DecimalFormat v3 = new DecimalFormat(v0);
        DecimalFormatSymbols v0_1 = v3.getDecimalFormatSymbols();
        v0_1.setDecimalSeparator('.');
        v3.setDecimalFormatSymbols(v0_1);
        String v0_2 = v3.format(v2);
        return v0_2.replaceAll("-", ".") + v1;
    }

    public static String formatSizeDecimalPartOnly(long arg2) {
        return SizeUtil.formatSize(arg2, "#0");
    }

    public static String formatSizeFix2(long arg6) {
        float v0;
        String v1 = null;
        if(arg6 >= 0x400L) {
            v1 = "KB";
            v0 = (float)(arg6 / 0x400L);
            if(v0 >= 1024f) {
                v1 = "MB";
                v0 /= 1024f;
            }

            if(v0 >= 1024f) {
                v1 = "GB";
                v0 /= 1024f;
            }
        }
        else {
            v0 = (float)arg6;
        }

        StringBuilder v3 = new StringBuilder(new DecimalFormat("#0.00").format(v0));
        if(v1 != null) {
            v3.append(v1);
        }

        return v3.toString();
    }

    public static String formatSizeFloatSingle(long arg2) {
        return SizeUtil.formatSize(arg2, 1);
    }

    public static String formatSizeForJunkHeader(long arg8) {
        String v0_1;
        float v1_1;
        String v2;
        if(arg8 >= 1000L) {
            String v1 = "KB";
            float v0 = (float)((((double)arg8)) / 1024);
            if(v0 >= 1000f) {
                v1 = "MB";
                v0 /= 1024f;
            }

            if(v0 >= 1000f) {
                v2 = "GB";
                v1_1 = v0 / 1024f;
            }
            else {
                v2 = v1;
                v1_1 = v0;
            }
        }
        else {
            v2 = "KB";
            v1_1 = (float)((((double)arg8)) / 1024);
        }

        if(v1_1 > 100f) {
            v0_1 = "#0";
        }
        else if(v1_1 > 10f) {
            v0_1 = "#0.0";
        }
        else {
            v0_1 = "#0.00";
        }

        DecimalFormat v3 = new DecimalFormat(v0_1);
        DecimalFormatSymbols v0_2 = v3.getDecimalFormatSymbols();
        v0_2.setDecimalSeparator('.');
        v3.setDecimalFormatSymbols(v0_2);
        StringBuilder v0_3 = new StringBuilder(v3.format(v1_1));
        v0_3.append(v2);
        return v0_3.toString().replaceAll("-", ".");
    }

    public static String formatSizeForMiui(long arg2) {
        String v0_1;
        try {
            Class.forName("miui.text.util.MiuiFormatter");
            v0_1 = SizeUtil.miuiFormatSize(arg2, "#0.0");
        }
        catch(ClassNotFoundException v0) {
            v0_1 = SizeUtil.formatSize(arg2, "#0.0");
        }

        return v0_1;
    }

    public static String formatSizeGB(long arg6) {
        String v0;
        if(arg6 > 0x400L) {
            DecimalFormat v1 = new DecimalFormat("#0.0");
            v0 = v1.format((((float)arg6)) / 1024f) + "GB";
        }
        else {
            v0 = arg6 + "MB";
        }

        return v0;
    }

    public static String formatSizeGetUnit(long arg8) {
        float v1;
        String v0_1;
        if(arg8 >= 0x400L) {
            float v0 = (float)((((double)arg8)) / 1024);
            if(v0 >= 1024f) {
                v0_1 = "MB";
                v1 = v0 / 1024f;
            }
            else {
                v0_1 = "KB";
                v1 = v0;
            }

            if(v1 >= 1024f) {
                v0_1 = "GB";
                float v1_1 = v1 / 1024f;
            }
        }
        else {
            v0_1 = "B";
        }

        return v0_1;
    }

    public static String formatSizeInt(long arg14) {
        String v0;
        if(arg14 < 0L) {
            v0 = "" + arg14;
        }
        else if(arg14 >= 0x10000000000L) {
            v0 = String.format(Locale.US, "%d.%dTB", arg14 / 0x10000000000L, arg14 % 0x10000000000L * 10L / 0x10000000000L);
        }
        else if(arg14 >= 0x40000000L) {
            v0 = String.format(Locale.US, "%d.%dGB", arg14 / 0x40000000L, arg14 % 0x40000000L * 10L / 0x40000000L);
        }
        else if(arg14 >= 0x100000L) {
            v0 = String.format(Locale.US, "%d.%dMB", arg14 / 0x100000L, arg14 % 0x100000L * 10L / 0x100000L);
        }
        else if(arg14 >= 0x400L) {
            v0 = String.format(Locale.US, "%d.%dKB", arg14 / 0x400L, arg14 % 0x400L * 10L / 0x400L);
        }
        else if(arg14 == 0L) {
            v0 = "0KB";
        }
        else {
            v0 = "< 1KB";
        }

        return v0;
    }



    public static float formatSizeMB(long arg4) {
        float v0 = 0f;
        if(arg4 > 0L) {
            v0 = BigDecimal.valueOf((((float) arg4)) / 1048576f).setScale(2, RoundingMode.HALF_UP).floatValue();
        }

        return v0;
    }

    public static String formatSizeSmallestMBUnit(long arg6) {
        String v0;
        String v1;
        float v2;
        if(arg6 >= 0x3E800000L) {
            v2 = (float)((((double)arg6)) / 1073741824);
            v1 = "GB";
        }
        else {
            v2 = (float)((((double)arg6)) / 1048576);
            v1 = "MB";
        }

        if(v2 >= 100f) {
            v0 = "#0";
        }
        else if(v2 >= 10f) {
            v0 = "#0.0";
        }
        else {
            v0 = "#0.00";
        }

        DecimalFormat v3 = new DecimalFormat(v0);
        DecimalFormatSymbols v0_1 = v3.getDecimalFormatSymbols();
        v0_1.setDecimalSeparator('.');
        v3.setDecimalFormatSymbols(v0_1);
        String v0_2 = v3.format(v2).replaceAll("-", ".");
        return v0_2 + v1;
    }

    public static String formatSizeSmallestMBUnit2(long arg8) {
        float v1;
        String v0;
        if(arg8 >= 0x3E800000L) {
            v0 = "GB";
            v1 = (float)((((double)arg8)) / 1073741824);
        }
        else {
            v0 = "MB";
            v1 = (float)((((double)arg8)) / 1048576);
        }

        DecimalFormat v3 = new DecimalFormat("#0.00");
        DecimalFormatSymbols v2 = v3.getDecimalFormatSymbols();
        v2.setDecimalSeparator('.');
        v3.setDecimalFormatSymbols(v2);
        String v1_1 = v3.format(v1).replaceAll("-", "");
        return v1_1 + v0;
    }



    public static String formatSizeWithoutSuffix(long arg6) {
        float v0;
        if(arg6 >= 0x400L) {
            v0 = (float)((((double)arg6)) / 1024);
            if(v0 >= 1024f) {
                v0 /= 1024f;
            }
        }
        else {
            v0 = (float)arg6;
        }

        return new StringBuilder(new DecimalFormat("#0.0").format(v0)).toString();
    }

    public static String formatSizeWithoutSuffix2(long arg6) {
        float v0;
        if(arg6 >= 0x400L) {
            v0 = (float)((((double)arg6)) / 1024);
            if(v0 >= 1024f) {
                v0 /= 1024f;
            }

            if(v0 >= 1024f) {
                v0 /= 1024f;
            }
        }
        else {
            v0 = (float)arg6;
        }

        return new StringBuilder(new DecimalFormat("#0.0").format(v0)).toString();
    }

    public static String formatSize_1(long arg2) {
        return SizeUtil.formatSize(arg2, 2);
    }

    public static String formatSize_2(long arg6) {
        String v0;
        String v1;
        float v2;
        if(arg6 >= 0x3E800000L) {
            v2 = (float)((((double)arg6)) / 1073741824);
            v1 = "GB";
        }
        else if(arg6 >= 0xFA000L) {
            v2 = (float)((((double)arg6)) / 1048576);
            v1 = "MB";
        }
        else {
            v2 = (float)((((double)arg6)) / 1024);
            v1 = "KB";
        }

        if(v2 >= 100f) {
            v0 = "#0";
        }
        else if(v2 >= 10f) {
            v0 = "#0.0";
        }
        else {
            v0 = "#0.00";
        }

        DecimalFormat v3 = new DecimalFormat(v0);
        DecimalFormatSymbols v0_1 = v3.getDecimalFormatSymbols();
        v0_1.setDecimalSeparator('.');
        v3.setDecimalFormatSymbols(v0_1);
        String v0_2 = v3.format(v2).replaceAll("-", ".");
        return v0_2 + v1;
    }

    public static String formatSize_3(long arg2) {
        return SizeUtil.formatSize(arg2, "#0.00");
    }

    private static String miuiFormatSize(long arg8, String arg10) {
        float v0;
        String v1 = null;
        if(arg8 >= 1000L) {
            v1 = "KB";
            v0 = (float)((((double)arg8)) / 1000);
            if(v0 >= 1000f) {
                v1 = "MB";
                v0 /= 1000f;
            }

            if(v0 >= 1000f) {
                v1 = "GB";
                v0 /= 1000f;
            }
        }
        else {
            v0 = (float)arg8;
        }

        StringBuilder v3 = new StringBuilder(new DecimalFormat(arg10).format(v0));
        if(v1 == null) {
            v3.append("B");
        }
        else {
            v3.append(v1);
        }

        return v3.toString();
    }
}

