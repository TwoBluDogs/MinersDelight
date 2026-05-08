package com.sammy.minersdelight.content.block;

import com.sammy.minersdelight.setup.MDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import static vectorwing.farmersdelight.common.BlockShapes.TRAY_SHAPE;

public class StuffedSquidFeastBlock extends FeastBlock {
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 5);
    public static final VoxelShape[] FEAST_SHAPE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D),
            Block.box(2, 1, 2, 14, 6, 14),
            Block.box(2, 1, 2, 14, 7, 14),
            Block.box(2, 1.2, 2, 14, 8.2, 14),
            Block.box(2, 1.2, 2, 14, 11.2, 14),
            Block.box(2, 1.2, 2, 14, 12.2, 14)
    };

    public StuffedSquidFeastBlock(Properties properties) {
        super(properties, MDItems.BOWL_OF_STUFFED_SQUID::get, true);
    }

    @Override
    public int getMaxServings() {
        return 5;
    }

    @Override
    public IntegerProperty getServingsProperty() {
        return SERVINGS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int servings  = state.getValue(SERVINGS);
        return Shapes.or(TRAY_SHAPE, FEAST_SHAPE[servings]);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }
}