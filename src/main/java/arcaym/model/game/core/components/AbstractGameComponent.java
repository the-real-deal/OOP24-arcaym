package arcaym.model.game.core.components;

import java.util.Objects;

import arcaym.common.utils.representation.FieldRepresentation;
import arcaym.common.utils.representation.StringRepresentation;
import arcaym.common.utils.representation.TypeRepresentation;
import arcaym.model.game.core.engine.GameStateInfo;
import arcaym.model.game.core.events.EventsSubscriber;
import arcaym.model.game.core.scene.GameSceneInfo;
import arcaym.model.game.events.GameEvent;
import arcaym.model.game.events.InputEvent;

/**
 * Abstract implementation of {@link GameComponent}.
 * It provides game object access while leaving the logic.
 */
@TypeRepresentation
public abstract class AbstractGameComponent implements GameComponent {

    private final ComponentsBasedGameObject gameObject;

    /**
     * Initialize with the given game object.
     * 
     * @param gameObject game object
     */
    protected AbstractGameComponent(final ComponentsBasedGameObject gameObject) {
        this.gameObject = Objects.requireNonNull(gameObject);
    }

    /**
     * Get component game object.
     * 
     * @return game object
     */
    @FieldRepresentation
    protected final ComponentsBasedGameObject gameObject() {
        return this.gameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup(
        final EventsSubscriber<GameEvent> gameEventsSubscriber,
        final EventsSubscriber<InputEvent> inputEventsSubscriber,
        final GameSceneInfo gameScene,
        final GameStateInfo gameState
    ) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return StringRepresentation.ofObject(this);
    }

}
