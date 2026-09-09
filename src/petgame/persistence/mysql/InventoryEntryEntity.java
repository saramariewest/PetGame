package petgame.persistence.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import petgame.domain.item.Item;

@Entity
@Table(name = "inventory_entries")
public class InventoryEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "save_game_id", nullable = false)
    private SaveGameEntity saveGame;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_name", nullable = false, length = 50)
    private Item item;

    @Column(nullable = false)
    private int quantity;

    public InventoryEntryEntity() {
    }

    public InventoryEntryEntity(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSaveGame(SaveGameEntity saveGame) { this.saveGame = saveGame; }
}
