package cn.mccraft.chinacraft.block.machine;

import cn.mccraft.chinacraft.block.BlockBase;
import cn.mccraft.chinacraft.init.CCMaterials;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;

public abstract class BlockMachine extends BlockBase implements ITileEntityProvider {
    public BlockMachine() {
        super(CCMaterials.MACHINE_MATERIAL);
        setHardness(2.5F);
        setSoundType(SoundType.METAL);
    }
}
