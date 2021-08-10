package org.example;

public class Operator {
    private final String nameOperator;
    private AutomaticTelephoneExchange ate;

    public Operator(String nameOperator, AutomaticTelephoneExchange ate) {
        this.nameOperator = nameOperator;
        this.ate = ate;
    }

    public void phoneCallHandling() {
        while (!Thread.interrupted()) {
            Call call = ate.getCallQueuePoll();
            if (call != null) {
                System.out.println(nameOperator + " обработал звонок " + call);
                try {
                    Thread.sleep(App.OPERATOR_HANDLING_SLEEP);
                } catch (InterruptedException e) {
                    System.out.println(nameOperator + ": Рабочий день закончился. Иду домой.");
                }
            } else {
                try {
                    Thread.sleep(App.OPERATOR_RESET_SLEEP);
                } catch (InterruptedException e) {
                    System.out.println(nameOperator + ": Рабочий день закончился!!! Иду домой.");
                }
            }
        }
    }
}

    