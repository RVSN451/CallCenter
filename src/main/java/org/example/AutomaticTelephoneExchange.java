package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


public class AutomaticTelephoneExchange {
    private static AutomaticTelephoneExchange instance = null;

    private String name;
    private ConcurrentLinkedQueue<Call> callQueue = new ConcurrentLinkedQueue<>();
    private List<Operator> listOperator = new ArrayList<>();

    private AutomaticTelephoneExchange() {
        name = "ATE";
    }

    public static AutomaticTelephoneExchange get() {
        if (instance == null) instance = new AutomaticTelephoneExchange();
        return instance;
    }

    public Call getCallQueuePoll() {
        return callQueue.poll();
    }

    public int getCallQueueSize() {
        return callQueue.size();
    }

    public Operator addTphOperator(String nameOperator) {
        Operator operator = new Operator(nameOperator);
        listOperator.add(operator);
        return operator;
    }

    public void generateCalls() {
        int calls = App.CALLS_FOR_OPERATOR * listOperator.size();
        for (int i = 0; i < calls; i++) {
            callQueue.add(new Call(i));
            System.out.println(Thread.currentThread().getName() + ": В очередь добавлен Call-" + (i + 1) + ".");
            try {
                Thread.sleep(App.ATE_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.interrupted();
    }
}
