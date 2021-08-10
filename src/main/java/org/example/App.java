package org.example;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static final long ATE_SLEEP = 158L;
    public static final long OPERATOR_HANDLING_SLEEP = 900L;
    public static final long OPERATOR_RESET_SLEEP = 70L;
    public static final int CALLS_FOR_OPERATOR = 3;
    public static final int WORKING_HOURS = 5;


    public static void main(String[] args) {
        AutomaticTelephoneExchange ate = new AutomaticTelephoneExchange("ATE");

        ate.addTphOperator("ОПЕРАТОР 1");
        ate.addTphOperator("ОПЕРАТОР 2");
        ate.addTphOperator("ОПЕРАТОР 3");

        Thread thread_1 = new Thread(null, ate::generateCalls);
        thread_1.start();

        ExecutorService service = Executors.newFixedThreadPool(ate.getListOperatorSize());
        for (int i = 0; i < ate.getListOperatorSize(); i++){
            service.submit(ate.getOperator(i)::phoneCallHandling);
        }

        try {
            if (!service.awaitTermination(WORKING_HOURS, TimeUnit.SECONDS)){
                service.shutdownNow();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
