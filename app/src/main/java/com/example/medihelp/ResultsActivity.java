package com.example.medihelp;

public class ResultsActivity {

    public static String calculateRBCStatus(double rbc, String gender) {
        double calculatedRBC = rbc;

        if("Male".equals(gender))
        {
            if (calculatedRBC < 4.7) {
                return "Low";
            } else if (calculatedRBC > 6.1) {
                return "High";
            } else {
                return "Normal";
            }
        }
        else{
            if (calculatedRBC < 4.1) {
                return "Low";
            } else if (calculatedRBC > 5.5) {
                return "High";
            } else {
                return "Normal";
            }
        }

    }

    public static String calculateWBCStatus(double wbc){
        if(wbc<4500)
        {
            return "Low";
        } else if (wbc>11000) {
            return "High";
        }
        else return "Normal";
    }

    public static String calculatePlateletStatus(double platelet){
        if(platelet<150000)
        {
            return "Low";
        } else if (platelet>450000) {
            return "High";
        }
        else return "Normal";
    }

    public static String calculateBilirubinStatus(double bilirubin){
        if(bilirubin>1.0 || bilirubin<0.3) {
            return "Abnormal";
        } else return "Normal";
    }

    public static String calculateDiabetesStatus(double diabetes){
        if(diabetes<5) return "Normal";
        else if (diabetes>6) {
            return "Diabetes";
        }
        else return "Pre-Diabetes";
    }


}
