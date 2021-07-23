package za.co.whatyourvibe.LogicLayer.Helper;

import java.text.DecimalFormat;

public class NumericHelper {

    public static String GetDecimalPlaces(double amount)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(amount);
    }

    public static String GetDecimalFormatter(double amount)
    {
        DecimalFormat formatter = new DecimalFormat("00");
        return formatter.format(amount);
    }
}
