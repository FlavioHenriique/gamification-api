package io.github.gamification.gamificationapi.service;

import io.github.gamification.gamificationapi.config.InsigniaProperties;
import io.github.gamification.gamificationapi.model.Insignia;
import io.github.gamification.gamificationapi.service.strategy.CheckInsigniaFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InsigniaService {

    @Autowired
    private InsigniaProperties insigniaProperties;

    @Autowired
    private CheckInsigniaFactory factory;

    public List<Insignia> getListaInsignias() {
        return insigniaProperties.getLista();
    }

    public List<Insignia> verificaInsigniasLiberadas(List<Long> obtidas, long idUsuario) {
        var map = factory.retornaTodos();
        var insigniasConquistadas = new ArrayList<Insignia>();
        map.entrySet().forEach(i->{
            if (!obtidas.contains(i.getKey())){
               if (i.getValue().checkCondition(idUsuario)){
                   insigniasConquistadas.add(getInsigniaById(i.getKey()).orElse(null));
               }
            }
        });
        return insigniasConquistadas;
    }

    public Optional<Insignia> getInsigniaById(long id){
        return insigniaProperties.getLista()
                .stream()
                .filter(ins-> ins.getId() == id)
                .findFirst();
    }
}
