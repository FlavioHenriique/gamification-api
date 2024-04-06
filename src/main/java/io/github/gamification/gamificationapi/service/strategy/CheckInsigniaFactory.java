package io.github.gamification.gamificationapi.service.strategy;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CheckInsigniaFactory {

    private Map<Long, CheckInsigniaStrategy> checkInsigniaMap;
    @Autowired
    public CheckInsigniaFactory(List<CheckInsigniaStrategy> strategies) {
        checkInsigniaMap = new HashMap<>();
        for (CheckInsigniaStrategy strategy : strategies) {
            checkInsigniaMap.put(strategy.getId(), strategy);
        }
    }
    public CheckInsigniaStrategy selecionaStrategy(long id) throws Exception {
        if (!checkInsigniaMap.containsKey(id))
            throw new Exception("Strategy nao encontrado");

        return checkInsigniaMap.get(id);
    }

    public Map<Long, CheckInsigniaStrategy> retornaTodos(){
        return checkInsigniaMap;
    }
}
