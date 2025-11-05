package client.forms;


import common.exceptions.InFileModeException;
import common.exceptions.InvalidFormException;

public interface Form <T> {
    T build() throws InvalidFormException, InFileModeException;
}
