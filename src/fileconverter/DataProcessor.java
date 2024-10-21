package fileconverter;

public class DataProcessor {
    public String process(String content) {
        // Пример: заменить арифметические выражения на их результаты.
        content = content.replaceAll("\\b2\\s*\\+\\s*2\\b", "4");
        content = content.replaceAll("\\b3\\s*\\*\\s*3\\b", "9");
        content = content.replaceAll("\\b5\\s*-\\s*1\\b", "4");
        return content;
    }
}
