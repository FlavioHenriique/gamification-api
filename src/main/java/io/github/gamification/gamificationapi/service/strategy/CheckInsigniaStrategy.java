package io.github.gamification.gamificationapi.service.strategy;

public abstract class CheckInsigniaStrategy {

    abstract long getId();

    public abstract boolean checkCondition(long idUsuario);
}
