package fileconverter;

public class Main {
    public static void main(String[] args) {
        // Пример создания объекта данных
        Data data = new Data("2 + 2", "4");

        // Запись объекта в JSON файл
        JSONFileWriter jsonWriter = new JSONFileWriter();
        jsonWriter.writeFile("data.json", data);

        // Чтение объекта из JSON файла
        JSONFileReader jsonReader = new JSONFileReader();
        Data readData = jsonReader.readFile("data.json", Data.class);

        // Вывод данных на консоль
        System.out.println("Expression: " + readData.getExpression() + ", Result: " + readData.getResult());
    }
}
