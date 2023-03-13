package example.Simple.Calculator.model;

public class Expression {
    private String content;

    public Expression(String content) {
        this.content = content;
    }

    public Expression() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
