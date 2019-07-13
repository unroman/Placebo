package shadows.placebo.recipe;

import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;

public class VanillaPacketDispatcher {

	/**
	 * Sends the {@link SUpdateTileEntityPacket} to all players watching this tile entity.
	 * @param tile
	 */
	public static void dispatchTEToNearbyPlayers(TileEntity tile) {
		ServerWorld world = (ServerWorld) tile.getWorld();
		world.getChunkProvider().chunkManager.getTrackingPlayers(new ChunkPos(tile.getPos()), false).forEach(player -> {
			player.connection.sendPacket(tile.getUpdatePacket());
		});

	}

	public static void dispatchTEToNearbyPlayers(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null) dispatchTEToNearbyPlayers(tile);
	}
}