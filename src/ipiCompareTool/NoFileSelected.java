package ipiCompareTool;

class NoFileSelected extends Exception {
    NoFileSelected() {
        super();
    }

    NoFileSelected(String err) {
        super(err);
    }
}
