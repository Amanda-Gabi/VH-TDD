package br.ufpa.refactoring.q6;

public class ProPlanPolicy implements PlanPolicy {
    @Override
    public double monthlyPrice(int activeUsers, boolean prioritySupport) {
        double price = 49.90 + (activeUsers * 1.25);
        if (prioritySupport) price += 59.90;
        if (activeUsers > 200) price *= 0.95;
        return price;
    }
}
