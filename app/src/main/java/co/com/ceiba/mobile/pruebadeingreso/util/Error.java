package co.com.ceiba.mobile.pruebadeingreso.util;

public class Error {


    public Error(int title, int text) {
        this.title = title;
        this.text = text;
    }

    private int title;

    private int text;

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }
}
