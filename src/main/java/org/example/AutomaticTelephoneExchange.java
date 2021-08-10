package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


public class AutomaticTelephoneExchange {


    private final String name;
    private ConcurrentLinkedQueue<Call> callQueue = new ConcurrentLinkedQueue<>();
    private List<Operator> listOperator = new ArrayList<>();

    public AutomaticTelephoneExchange(String name) {
        this.name = name;
    }

    public Call getCallQueuePoll() {
        return callQueue.poll();
    }

    public int getListOperatorSize() {
        return listOperator.size();
    }

    public Operator getOperator(int i) {
        return listOperator.get(i);
    }

    public Operator addTphOperator(String nameOperator) {
        Operator operator = new Operator(nameOperator, this);
        listOperator.add(operator);
        return operator;
    }

    public void generateCalls() {
        int calls = App.CALLS_FOR_OPERATOR * listOperator.size();
        for (int i = 0; i < calls; i++) {
            callQueue.add(new Call(i));
            System.out.println(name + ": В очередь добавлен Call-" + (i + 1) + ".");
            try {
                Thread.sleep(App.ATE_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.interrupted();
    }
}
