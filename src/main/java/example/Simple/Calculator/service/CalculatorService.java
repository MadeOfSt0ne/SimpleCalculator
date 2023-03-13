package example.Simple.Calculator.service;

import example.Simple.Calculator.model.Operation;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    /**
     * Метод поддерживает операции сложения, вычитания, умножения, деления, поиска синуса, косинуса, извлечения
     * квадратного корня и возведения в степень
     */
    public String calculate(String expression) {
        String[] str = expression
                .replaceAll("\\(|\\)|,", " ")
                .trim()
                .split("\\s+");

        double result;

        Operation op = mapToOperation(str);

        switch (op.getOperator()) {
            case "+" -> result = op.getFirstArg() + op.getSecondArg();
            case "-" -> result = op.getFirstArg() - op.getSecondArg();
            case "*" -> result = op.getFirstArg() * op.getSecondArg();
            case "/" -> {
                if (op.getSecondArg() == 0) {
                    throw new UnsupportedOperationException("Нельзя делить на 0");
                }
                result = op.getFirstArg() / op.getSecondArg();
            }
            case "sin" -> result = Math.sin(op.getFirstArg());
            case "cos" -> result = Math.cos(op.getFirstArg());
            case "sqrt" -> {
                if (op.getFirstArg() < 0) {
                    throw new UnsupportedOperationException("Нельзя извлечь квадратный корень из отрицательного числа");
                }
                result = Math.sqrt(op.getFirstArg());
            }
            case "pow" -> result = Math.pow(op.getFirstArg(), op.getSecondArg());
            default -> throw new UnsupportedOperationException("Данная операция не поддерживается");
        }

        return String.format("%.2f", result);
    }

    /**
     * Маппинг массива строк в объект Operation. Сделал метод публичным для тестов
     */
    public static Operation mapToOperation(String[] str) {
        Operation operation = new Operation();
        if (str[0].equals("sin") || str[0].equals("cos") || str[0].equals("sqrt") || str[0].equals("pow")) {
            operation.setOperator(str[0]);
            operation.setFirstArg(Double.parseDouble(str[1]));
            operation.setSecondArg(str.length == 3 ? Double.parseDouble(str[2]) : 0.0);
        } else if (str[1].equals("+") || str[1].equals("-") || str[1].equals("*") || str[1].equals("/")) {
            operation.setFirstArg(Double.parseDouble(str[0]));
            operation.setOperator(str[1]);
            operation.setSecondArg(Double.parseDouble(str[2]));
        } else {
            throw new UnsupportedOperationException("Операция не распознана");
        }
        return operation;
    }
}
