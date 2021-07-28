package org.example;


public class App {
    public static final long MAIN_SLEEP = 300L;
    public static final long ATE_SLEEP = 300L;
    public static final long OPERATOR_HANDLING_SLEEP = 900L;
    public static final long OPERATOR_RESET_SLEEP = 70L;
    public static final int CALLS_FOR_OPERATOR = 3;


    public static void main(String[] args) {
        AutomaticTelephoneExchange ate = AutomaticTelephoneExchange.get();

        Operator operator1 = ate.addTphOperator("ОПЕРАТОР 1");
        Operator operator2 = ate.addTphOperator("ОПЕРАТОР 2");
        Operator operator3 = ate.addTphOperator("ОПЕРАТОР 3");

        Thread thread_1 = new Thread(null, ate::generateCalls, "ATE");
        thread_1.start();


        ThreadGroup operatorGroup = new ThreadGroup("OperatorGroup");
        new Thread(operatorGroup, operator1::phoneCallHandling, "!ОПЕРАТОР-1").start();
        new Thread(operatorGroup, operator2::phoneCallHandling, "!ОПЕРАТОР-2").start();
        new Thread(operatorGroup, operator3::phoneCallHandling, "!ОПЕРАТОР-3").start();

        while (true) {
            if (!thread_1.isAlive()) {
                operatorGroup.interrupt();
                break;
            } else {
                try {
                    Thread.sleep(App.MAIN_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
