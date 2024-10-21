package fileconverter;

public class Data {
    private String expression;  // Хранит математическое выражение
    private String result;      // Хранит результат выражения

    // Пустой конструктор (необходим для Jackson)
    public Data() {}

    // Конструктор с параметрами для удобства
    public Data(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    // Геттеры и сеттеры для полей
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
