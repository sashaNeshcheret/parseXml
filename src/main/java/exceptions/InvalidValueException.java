package exceptions;

public class InvalidValueException extends ParseException{
    public InvalidValueException() {
    }

    public InvalidValueException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return " value must be positive number!";
    }
}
