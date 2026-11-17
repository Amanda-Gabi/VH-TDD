package br.ufpa.refactoring.q1;

import java.time.LocalDate;
import java.util.List;

public class PurchaseEmailService {

    public String buildEmail(Purchase purchase) {
        StringBuilder sb = new StringBuilder();
        appendGreeting(sb, purchase);
        appendItems(sb, purchase);
        appendTotals(sb, purchase);
        appendAddress(sb, purchase);
        sb.append("Obrigado por comprar com a gente!\n");
        return sb.toString();
    }

    private void appendGreeting(StringBuilder sb, Purchase p) {
        sb.append("Olá, ").append(p.customerName()).append("!\n\n");
        sb.append("Seu pedido #").append(p.id()).append(" foi confirmado em ")
          .append(LocalDate.now()).append(".\n\n");
    }

    private void appendItems(StringBuilder sb, Purchase p) {
        sb.append("Itens:\n");
        for (PurchaseItem it : p.items()) {
            double line = lineTotal(it);
            sb.append("- ").append(it.name())
              .append(" (").append(it.quantity()).append("x) : R$ ")
              .append(formatMoney(line)).append("\n");
        }
    }

    private void appendTotals(StringBuilder sb, Purchase p) {
        double subtotal = subtotal(p);
        double shipping = shipping(p);
        double discount = discount(p, subtotal);
        double total = subtotal + shipping - discount;

        sb.append("\nSubtotal: R$ ").append(formatMoney(subtotal)).append("\n");
        sb.append("Frete: R$ ").append(formatMoney(shipping)).append("\n");
        sb.append("Descontos: R$ ").append(formatMoney(discount)).append("\n");
        sb.append("TOTAL: R$ ").append(formatMoney(total)).append("\n\n");
    }

    private void appendAddress(StringBuilder sb, Purchase p) {
        sb.append("Endereço de entrega:\n");
        sb.append(p.address().street()).append(", ")
          .append(p.address().number()).append("\n");
        sb.append(p.address().city()).append(" - ")
          .append(p.address().zip()).append("\n\n");
    }

    private double lineTotal(PurchaseItem it) {
        return it.unitPrice() * it.quantity();
    }

    private double subtotal(Purchase p) {
        double s = 0.0;
        for (PurchaseItem it : p.items()) s += lineTotal(it);
        return s;
    }

    private double shipping(Purchase p) {
        return p.isExpress() ? 29.90 : 14.90;
    }

    private double discount(Purchase p, double subtotal) {
        double d = 0.0;
        if (subtotal >= 300.0) d = subtotal * 0.10;
        if (p.coupon() != null && p.coupon().startsWith("VIP")) d += 20.0;
        return d;
    }

    private String formatMoney(double v) {
        return String.format("%.2f", v);
    }

    public record Purchase(String id, String customerName, List<PurchaseItem> items,
                           boolean isExpress, String coupon, Address address) {}

    public record PurchaseItem(String name, int quantity, double unitPrice) {}

    public record Address(String street, String number, String city, String zip) {}
}
