package nz.govt.stats.mdt.dl.exceptions;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String input) {
        super(String.format("Supplied value for the input %s is not valid", input));
    }
}
