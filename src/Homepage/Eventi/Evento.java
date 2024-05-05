package Homepage.Eventi;

import java.util.EventListener;

public interface Evento extends EventListener {
    void evento(EventoEvent e);
}
