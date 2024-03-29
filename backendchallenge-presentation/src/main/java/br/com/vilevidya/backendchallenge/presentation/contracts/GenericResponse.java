package br.com.vilevidya.backendchallenge.presentation.contracts;

public class GenericResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public boolean isSuccess() {
        return success;
    }


    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }


    public GenericResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public GenericResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public static <T> GenericResponse<T> empty() {
        return success(null);
    }

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<T>(true,"SUCCESS", data );
    }

    public static <T> GenericResponse<T> error(String message) {
        return new GenericResponse<T>(false,message);
    }

}
