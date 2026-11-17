package br.ufpa.refactoring.q6;

public class FreePlanPolicy implements PlanPolicy {
    @Override
    public double monthlyPrice(int activeUsers, boolean prioritySupport) {
        return 0.0;
    }
}
