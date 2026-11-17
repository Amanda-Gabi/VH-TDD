package br.ufpa.refactoring.q7;

import java.util.Objects;

public class SupportChat {

    public String greeting(ChatUser user) {
        user = Objects.requireNonNullElse(user, new GuestUser());
        if (user.isGuest()) return "Olá! Quer continuar como visitante?";
        if (user.isBanned()) return "Conta bloqueada. Contate o suporte.";
        if (user.name() == null || user.name().isBlank()) return "Olá! Como posso ajudar?";
        return "Olá, " + user.name() + "! Como posso ajudar?";
    }

    public record User(String name, boolean banned) implements ChatUser {
        @Override public boolean isBanned() { return banned; }
    }
}
