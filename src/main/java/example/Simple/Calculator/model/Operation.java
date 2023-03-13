package example.Simple.Calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    private String operator;
    private Double firstArg;
    private Double secondArg;
}
