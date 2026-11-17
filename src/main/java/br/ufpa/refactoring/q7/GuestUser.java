package br.ufpa.refactoring.q7;

public class GuestUser implements ChatUser {
    @Override public String name() { return ""; }
    @Override public boolean isBanned() { return false; }
}
