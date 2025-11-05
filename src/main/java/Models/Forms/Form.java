package Models.Forms;


import Exceptions.InFileModeException;
import Exceptions.InvalidFormException;

import java.util.NoSuchElementException;

public interface Form <T> {
    T build() throws InvalidFormException, InFileModeException;
}
