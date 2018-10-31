package ipiCompareTool;

public class NotValidIPIFormat extends Exception {
    NotValidIPIFormat() {
        super();
    }

    NotValidIPIFormat(String err) {
        super(err);
    }
}
