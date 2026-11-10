package fr.bibliotek.service;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class CollectionEvents {
    private static final Logger log = LoggerFactory.getLogger(CollectionEvents.class);
    private final Set<SseEmitter> emitters = ConcurrentHashMap.newKeySet();

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(300_000L);
        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));

        emitter.onTimeout(() -> {
            this.emitters.remove(emitter);
            emitter.complete();
        });

        emitter.onError(error -> {
            this.emitters.remove(emitter);
            log.debug("Connexion SSE des collections interrompue", error);
        });

        this.send(emitter, "ready");

        return emitter;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void collectionsChanged(CollectionsChangedEvent event) {
        this.emitters.forEach(emitter -> send(emitter, "changed"));
    }

    private void send(SseEmitter emitter, String message) {
        try {
            emitter.send(SseEmitter.event().data(message));
        }

        catch (IOException | IllegalStateException ex) {
            this.emitters.remove(emitter);

            log.debug("Impossible d'envoyer une notification SSE des collections", ex);

            emitter.completeWithError(ex);
        }
    }
}
