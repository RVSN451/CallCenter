package org.example;

public class Operator {
    private String nameOperator;
    AutomaticTelephoneExchange ate;

    public Operator(String nameOperator) {
        this.nameOperator = nameOperator;
        this.ate = AutomaticTelephoneExchange.get();
    }

    public void phoneCallHandling() {
        while (!Thread.interrupted()) {
            if (ate.checkCallQueue()) {
                System.out.println(Thread.currentThread().getName() + " обработал звонок " + ate.callQueue.poll());

                try {
                    Thread.sleep(App.OPERATOR_HANDLING_SLEEP);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + ": Рабочий день закончился. Иду домой.");
                    Thread.interrupted();
                }
            } else {
                try {
                    Thread.sleep(App.OPERATOR_RESET_SLEEP);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + ": Рабочий день закончился. Иду домой.");
                    Thread.interrupted();
                }
                phoneCallHandling();
            }
        }
    }
}

    