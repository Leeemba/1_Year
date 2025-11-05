package Models.Forms;


import Exceptions.InFileModeException;
import Exceptions.InvalidFormException;

public interface Form <T> {
    T build() throws InvalidFormException, InFileModeException;
}
