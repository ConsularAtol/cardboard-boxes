package consular.cardboard.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BoxBlockItem extends BlockItem{
    public BoxBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public boolean canBeNested(){
        return false;
    }
}
