package Client.Forms;


import Common.Exceptions.InFileModeException;
import Common.Exceptions.InvalidFormException;

public interface Form <T> {
    T build() throws InvalidFormException, InFileModeException;
}
