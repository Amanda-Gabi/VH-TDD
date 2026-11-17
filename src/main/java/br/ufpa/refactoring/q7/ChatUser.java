package br.ufpa.refactoring.q7;

public interface ChatUser {
    String name();
    boolean isBanned();
    default boolean isGuest() { return false; }
}
