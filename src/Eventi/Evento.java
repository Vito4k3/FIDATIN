package Eventi;

import java.util.EventListener;

public interface Evento extends EventListener {
    public void evento(EventoEvent e);
}
