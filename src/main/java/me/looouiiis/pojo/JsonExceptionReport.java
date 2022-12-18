package me.looouiiis.pojo;

public class JsonExceptionReport {
    private String exceptionString;
    private String exceptionMessage;
    private String exceptionLocalizedMessage;

    public JsonExceptionReport() {
    }

    public JsonExceptionReport(Exception e) {
        exceptionString = e.toString();
        exceptionMessage = e.getMessage();
        exceptionLocalizedMessage = e.getLocalizedMessage();
    }

    public String getExceptionString() {
        return exceptionString;
    }

    public void setExceptionString(String exceptionString) {
        this.exceptionString = exceptionString;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionLocalizedMessage() {
        return exceptionLocalizedMessage;
    }

    public void setExceptionLocalizedMessage(String exceptionLocalizedMessage) {
        this.exceptionLocalizedMessage = exceptionLocalizedMessage;
    }
}
