package su.csmb.server.data.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.csmb.common.response.ResponseCards;
import su.csmb.server.data.entity.Cards;
import su.csmb.server.data.objects.CardObject;
import su.csmb.server.data.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SrvGetCardsByClientIdServer {
    private CardsCrudRepository cardsCrudRepository;

    public List<ResponseCards> getCardsByClientId(Long clientId) {
        Iterable<Cards> cardsIterable = cardsCrudRepository.findByClientId(clientId);
        List<ResponseCards> cardsList = new ArrayList<>();
        for (Cards cards : cardsIterable) {
            if (clientId == cards.getClientId()) {
                cardsList.add(new ResponseCards(cards.getId(), Long.valueOf(cards.getTariff()), cards.getCardNumber()));
            }
        }
        return cardsList;
    }
}

