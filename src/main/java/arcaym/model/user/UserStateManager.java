package arcaym.model.user;

import java.util.Set;

import arcaym.model.game.core.events.EventsObserver;
import arcaym.model.game.events.GameEvent;
import arcaym.model.game.objects.GameObjectType;

/**
 * Interface modelling the user state and its relative operations.
 * This class serves primarily as collector of useful information.
 */
public interface UserStateManager extends EventsObserver<GameEvent> {

    /**
     * @return the credit of the user.
     */
    int getCredit();

    /**
     * Adds a new game object to the collection of purchased assets.
     * 
     * @param gameObject
     * @throws IllegalArgumentException if the gameObject is already owned.
     */
    void unlockNewItem(GameObjectType gameObject);

    /**
     * Increments the user score.
     * 
     * @param amount the amount to add to the score.
     * @throws IllegalArgumentException if the amount is negative.
     */
    void incrementCredit(int amount);

    /**
     * Decrements the user score.
     * 
     * @param amount the amount to subtract to the score. If the subtraction
     * makes the score drop below 0, then it goes to 0.
     * @throws IllegalArgumentException if the amount is negative.
     */
    void decrementCredit(int amount);

    /**
     * @return the items the user purchased from the shop.
     */
    Set<GameObjectType> getPurchasedItems();

    /**
     * @return the items the user has (default + purchased).
     */
    Set<GameObjectType> getItemsOwned();

    /**
     * @param item
     * @return {@code true} if the user owns the item, {@code false} otherwise
     */
    boolean hasItem(GameObjectType item);
}
