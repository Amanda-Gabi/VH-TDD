package br.ufpa.refactoring.q6;

public class EnterprisePlanPolicy implements PlanPolicy {
    @Override
    public double monthlyPrice(int activeUsers, boolean prioritySupport) {
        double price = 199.90 + (activeUsers * 0.90);
        if (prioritySupport) price += 59.90;
        return price;
    }
}
