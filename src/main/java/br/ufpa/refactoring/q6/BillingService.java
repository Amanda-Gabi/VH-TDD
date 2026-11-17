package br.ufpa.refactoring.q6;

public class BillingService {

    public enum Plan {
        FREE(new FreePlanPolicy()),
        STUDENT(new StudentPlanPolicy()),
        PRO(new ProPlanPolicy()),
        ENTERPRISE(new EnterprisePlanPolicy());

        private final PlanPolicy policy;

        Plan(PlanPolicy policy) {
            this.policy = policy;
        }

        public PlanPolicy policy() {
            return policy;
        }
    }

    public double monthlyPrice(Plan plan, int activeUsers, boolean prioritySupport) {
        double price = plan.policy().monthlyPrice(activeUsers, prioritySupport);
        return Math.round(price * 100.0) / 100.0;
    }
}
