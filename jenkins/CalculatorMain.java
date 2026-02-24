public class CalculatorMain extends Calculator {
    public static void main(String[] args) {
        if (args[0] == "add") {
            System.out.println(add(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
        } else if (args[0] == "sub") {
            System.out.println(sub(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
        }
    }
}
