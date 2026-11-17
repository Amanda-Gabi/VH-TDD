package br.ufpa.refactoring.q6;

public class StudentPlanPolicy implements PlanPolicy {
    @Override
    public double monthlyPrice(int activeUsers, boolean prioritySupport) {
        double price = 19.90;
        if (prioritySupport) price += 59.90;
        return price;
    }
}
