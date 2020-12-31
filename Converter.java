package project4;

/**
 * This class contains various methods for converting numbers written using different number systems: binary, decimal, hexadecimal
 * The decimal numbers are represented using int type
 * The binary and hexadecimal numbers are represented using binary and hexadecimal strings
 */
public class Converter
{
    /**
     * Converts binary string to corresponding decimal number
     * @param binary the binary string to be converted
     * @return the decimal number equal in value to the binary string passed as the parameter
     * @throws IllegalArgumentException if the parameter is null
     * @throws NumberFormatException if the binary string passed to the function is invalid
     */
    public static int binaryToDecimal(String binary)
    {
        // Check for null binary string
        if (binary == null)
            throw new IllegalArgumentException("Binary number passed was null");

        // Store length in size variable
        int size = binary.length();

        // Check for valid input
        if (size <= 2 || size > 33)
            throw new NumberFormatException("Invalid binary string passed");
        if (binary.charAt(0) != '0' || binary.charAt(1) != 'b')
            throw new NumberFormatException("Invalid binary string passed");
        if (binary.charAt(size - 1) != '0' && binary.charAt(size - 1) != '1')
            throw new NumberFormatException("Invalid binary string passed");

        // Base case: size is 3
        if (size == 3)
        {
            if (binary.charAt(2) == '0' || binary.charAt(2) == '1')
                return binary.charAt(2) - '0';
            else
                throw new NumberFormatException("Invalid binary string passed");
        }

        // Recursive call
        return binaryToDecimal(binary.substring(0, size - 1)) * 2 + binary.charAt(size - 1) - '0';
    }

    /**
     * Converts binary string to corresponding hexadecimal string
     * Acts as a wrapper method for the binaryToHex(String binary, int size) method
     * @param binary the binary string to be converted
     * @return the hexadecimal string equal in value to the binary string passed as the parameter
     * @throws IllegalArgumentException if the parameter is null
     * @throws NumberFormatException if the binary string passed to the function is invalid
     */
    public static String binaryToHex(String binary)
    {
        // Check for null binary string
        if (binary == null)
            throw new IllegalArgumentException("Binary number passed was null");

        // Store length in size variable
        int size = binary.length();

        // Check for valid input
        if (size <= 2 || size > 33)
            throw new NumberFormatException("Invalid binary string passed");
        if (binary.charAt(0) != '0' || binary.charAt(1) != 'b')
            throw new NumberFormatException("Invalid binary string passed");

        // Create sequence to store padded binary sequence if needed
        String sequence = "";

        // Check if size is not a multiple of 4
        if ((size - 2) % 4 != 0)
            sequence = pad(binary.substring(2), size - 2); // Pad string to have a size that is a multiple of 4

        // Call binaryToHex with original string if it was size = multiple of 4 or call with new padded string
        return sequence.equals("") ? binaryToHex(binary, size) : binaryToHex(sequence, sequence.length());
    }

    /**
     * Converts binary string to corresponding hexadecimal string
     * @param binary the binary string to be converted
     * @param size the length of the binary string
     * @return the hexadecimal string equal in value to the binary string passed as the parameter
     */
    private static String binaryToHex(String binary, int size)
    {
        // Base case: size is 2
        if (size == 2)
            return "0x"; // Add prefix

        // Recursive call: convert last four digits to hex and call method with smaller string
        return binaryToHex(binary.substring(0, size - 4), size - 4) + binTableConvert(binary.substring(size - 4, size));
    }

    /**
     * Pads binary sequence with 0's until its length is a multiple of 4
     * @param binary the binary sequence to be padded
     * @param size the length of the binary string
     * @return a padded binary sequence with length that is a multiple of 4
     */
    private static String pad(String binary, int size)
    {
        // Base case: Check if size is a multiple of 4
        if (size % 4 == 0)
            return "0b" + binary; // Add prefix

        // Recursive call: pad "0" to the left of the binary string
        return pad("0" + binary, size + 1);
    }

    /**
     * Converts a binary sequence of length 4 to its corresponding hexadecimal string
     * @param binarySeq the binary sequence to be converted
     * @return the corresponding hexadecimal string
     * @throws NumberFormatException if the binary string passed to the function is invalid
     */
    private static String binTableConvert(String binarySeq)
    {
        // Convert four bit binary sequence to hex value
        switch (binarySeq)
        {
            case "0000":
                return "0";
            case "0001":
                return "1";
            case "0010":
                return "2";
            case "0011":
                return "3";
            case "0100":
                return "4";
            case "0101":
                return "5";
            case "0110":
                return "6";
            case "0111":
                return "7";
            case "1000":
                return "8";
            case "1001":
                return "9";
            case "1010":
                return "A";
            case "1011":
                return "B";
            case "1100":
                return "C";
            case "1101":
                return "D";
            case "1110":
                return "E";
            case "1111":
                return "F";
            default:
                throw new NumberFormatException("Invalid binary string passed");
        }
    }

    /**
     * Converts decimal number to corresponding binary string
     * Acts as wrapper method for decimalToBinaryRec(int decimal) method
     * @param decimal the decimal to be converted
     * @return the binary string equal in value to the decimal number passed as the parameter
     *         or null if the decimal is negative
     */
    public static String decimalToBinary(int decimal)
    {
        // Check for valid decimal number
        if (decimal < 0)
            return null;

        // Create empty string to hold binary number
        String number = "";

        // Check if decimal is 0
        if (decimal == 0)
            return "0b0"; // Add prefix
        else
            return decimalToBinaryRec(decimal);
    }

    /**
     * Converts decimal number to corresponding binary string
     * @param decimal the decimal to be converted
     * @return the binary string equal in value to the decimal number passed as the parameter
     *         or null if the decimal is negative
     */
    private static String decimalToBinaryRec(int decimal)
    {
        // Base case: decimal is 0
        if (decimal == 0)
            return "0b"; // Add prefix
        else
            return decimalToBinaryRec(decimal / 2) + (decimal % 2); // Recursive call
    }

    /**
     * Converts decimal number to corresponding hexadecimal string
     * Acts as wrapper method for decimalToHexRec(int decimal) method
     * @param decimal the decimal number to be converted
     * @return the hexadecimal string equal in value to the decimal number passed as the parameter
     *         or null if the decimal is negative
     */
    public static String decimalToHex(int decimal)
    {
        // Check for valid decimal number
        if (decimal < 0)
            return null;

        // Create empty string to hold hex number
        String number = "";

        // Check if decimal is 0
        if (decimal == 0)
            return "0x0";
        else
            return decimalToHexRec(decimal);
    }

    /**
     * Converts decimal number to corresponding hexadecimal string
     * @param decimal the decimal number to be converted
     * @return the hexadecimal string equal in value to the decimal number passed as the parameter
     *         or null if the decimal is negative
     */
    private static String decimalToHexRec(int decimal)
    {
        // Base case: decimal is zero
        if (decimal == 0) {
            return "0x"; // Add prefix
        }
        else
        {
            // Convert digit to hex value and call function recursively
            switch (decimal % 16)
            {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    return decimalToHexRec(decimal / 16) + (decimal % 16);
                case 10:
                    return decimalToHexRec(decimal / 16) + "A";
                case 11:
                    return decimalToHexRec(decimal / 16) + "B";
                case 12:
                    return decimalToHexRec(decimal / 16) + "C";
                case 13:
                    return decimalToHexRec(decimal / 16) + "D";
                case 14:
                    return decimalToHexRec(decimal / 16) + "E";
                case 15:
                    return decimalToHexRec(decimal / 16) + "F";
                default:
                    return "";
            }
        }
    }

    /**
     * Converts hexadecimal string to corresponding binary string
     * Acts as a wrapper method for the hexToBinary(String hex, int size) method
     * @param hex the hexadecimal string to be converted
     * @return the binary string equal in value to the hexadecimal string passed as the parameter
     * @throws IllegalArgumentException if the parameter is null
     * @throws NumberFormatException if the hexadecimal string passed to the function is invalid
     */
    public static String hexToBinary(String hex)
    {
        // Check for null hex string
        if (hex == null)
            throw new IllegalArgumentException("Hex number passed was null");

        // Store length in size variable
        int size = hex.length();

        // Check for valid input
        if (size <= 2 || size > 10)
            throw new NumberFormatException("Invalid hex string passed");
        if (hex.charAt(0) != '0' || hex.charAt(1) != 'x')
            throw new NumberFormatException("Invalid hex string passed");

        return hexToBinary(hex, size);
    }

    /**
     * Converts hexadecimal string to corresponding binary string
     * @param hex the hexadecimal string to be converted
     * @param size the length of the hexadecimal string
     * @return the binary string equal in value to the hexadecimal string passed as the parameter
     */
    private static String hexToBinary(String hex, int size)
    {
        if (size == 2)
            return "0b"; // Add prefix

        return hexToBinary(hex.substring(0, size - 1), size - 1) + hexTableConvert(hex.charAt(size - 1));
    }

    /**
     * Converts hexadecimal single character to corresponding binary string
     * @param hex the hexadecimal char to be converted
     * @return the four digit binary string equal in value to hex
     */
    private static String hexTableConvert(char hex)
    {
        // Convert hex value to binary sequence
        switch (hex)
        {
            case '0':
                return "0000";
            case '1':
                return "0001";
            case '2':
                return "0010";
            case '3':
                return "0011";
            case '4':
                return "0100";
            case '5':
                return "0101";
            case '6':
                return "0110";
            case '7':
                return "0111";
            case '8':
                return "1000";
            case '9':
                return "1001";
            case 'A':
                return "1010";
            case 'B':
                return "1011";
            case 'C':
                return "1100";
            case 'D':
                return "1101";
            case 'E':
                return "1110";
            case 'F':
                return "1111";
            default:
                throw new NumberFormatException("Invalid hex value passed");
        }
    }

    /**
     * Converts hexadecimal string to corresponding decimal number
     * Acts as a wrapper method for the hexToDecimal(String hex, int size) method
     * @param hex the hexadecimal string to be converted
     * @return the decimal number equal in value to the hexadecimal string passed as the parameter
     * @throws IllegalArgumentException if the parameter is null
     * @throws NumberFormatException if the hexadecimal string passed to the function is invalid
     * @throws ArithmeticException when the hexadecimal string parameter is greater than 0x7FFFFFFF
     *                            (the largest value that can be represented as an int)
     */
    public static int hexToDecimal(String hex)
    {
        // Check for null hex string
        if (hex == null)
            throw new IllegalArgumentException("Hex number passed was null");

        // Store length in size variable
        int size = hex.length();

        // Check for valid input
        if (size <= 2 || size > 10)
            throw new NumberFormatException("Invalid hex string passed");
        if (hex.charAt(0) != '0' || hex.charAt(1) != 'x')
            throw new NumberFormatException("Invalid hex string passed");
        if (hex.charAt(2) > 55 && size == 10)
            throw new ArithmeticException("Hexadecimal string is too big to be converted to int");

        return hexToDecimal(hex, size);
    }

    /**
     * Converts hexadecimal string to corresponding decimal number
     * @param hex the hexadecimal string to be converted
     * @param size the length of the hexadecimal string
     * @return the decimal number equal in value to the hexadecimal string passed as the parameter
     * @throws NumberFormatException if the hexadecimal string passed to the function is invalid
     */
    private static int hexToDecimal(String hex, int size)
    {
        // Base case: size is 3
        if (size == 3)
        {
            switch (hex.charAt(2))
            {
                case 'A':
                    return 10;
                case 'B':
                    return 11;
                case 'C':
                    return 12;
                case 'D':
                    return 13;
                case 'E':
                    return 14;
                case 'F':
                    return 15;
                default:
                    if (hex.charAt(2) <= '9' && hex.charAt(2) >= '0')
                        return hex.charAt(2) - '0';
                    else
                        throw new NumberFormatException("Invalid hex string passed");
            }
        }

        // Recursive call
        if (hex.charAt(size - 1) <= 57 && hex.charAt(size - 1) >= 48)
            return hexToDecimal(hex.substring(0, size - 1), size - 1) * 16 + (hex.charAt(size - 1) - '0');
        else if (hex.charAt(size - 1) >= 65 && hex.charAt(size - 1) <= 70)
            return hexToDecimal(hex.substring(0, size - 1), size - 1) * 16 + (hex.charAt(size - 1) - 55);
        else
            throw new NumberFormatException("Invalid hex string passed");
    }
}


