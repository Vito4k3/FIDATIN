package Login.Eventi;

import java.util.EventListener;

public interface Evento extends EventListener {
    void evento(EventoEvent ev);
}
