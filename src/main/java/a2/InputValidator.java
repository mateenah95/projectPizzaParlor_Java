package a2;

public class InputValidator {

    public static boolean isNumericAndWithinRange(String input, int max) {
        if (isNumeric(input)) {
            Integer inputInteger = Integer.parseInt(input);
            if (inputInteger >= 1 && inputInteger <= max) {
                return true;
            }
            else {
                return false;
            }
        }

        return false;
    }

    public static boolean isMenuNumericAndWithinRange(String input, int max) {
        if (isNumeric(input)) {
            Integer inputInteger = Integer.parseInt(input);
            if (inputInteger >= 0 && inputInteger <= max) {
                return true;
            }
            else {
                return false;
            }
        }

        return false;
    }


    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean isPhoneNumeric(String input) {
        try {
            Long.parseLong(input);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean isNameValid(String nameToValidate){
        if((nameToValidate.length() >= 2) && (nameToValidate.length() <= 30))
            return true;
        else
            return false;
    }

    public static boolean isAddressValid(String addressToValidate){
        if((addressToValidate.length() >= 10) && (addressToValidate.length() <= 30))
            return true;
        else
            return false;
    }

    public static boolean isPhoneValid(String phoneToValidate){
        if((phoneToValidate.length() == 10) && (InputValidator.isPhoneNumeric(phoneToValidate)))
            return true;
        else
            return false;

    }



}

