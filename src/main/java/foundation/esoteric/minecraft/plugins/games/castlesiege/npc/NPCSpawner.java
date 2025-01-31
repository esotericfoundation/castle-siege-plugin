package foundation.esoteric.minecraft.plugins.games.castlesiege.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.npc.NPCData;
import foundation.esoteric.minecraft.plugins.games.castlesiege.npc.connection.EmptyConnection;
import foundation.esoteric.minecraft.plugins.games.castlesiege.util.NMSUtil;
import org.bukkit.World;

import java.util.UUID;

@Getter
public final class NPCSpawner {
    private final ServerLevel world;
    private final NPCData data;

    public NPCSpawner(NPCData data, World world) {
        this.world = NMSUtil.toNMSWorld(world);
        this.data = data;
    }

    public NPC spawn() {
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", data.getSkinValue(), data.getSkinSignature()));

        ServerPlayer npc = new ServerPlayer(world.getServer(), world, profile, ClientInformation.createDefault());
        SynchedEntityData entityData = npc.getEntityData();

        byte bitmask = (byte) (0x01 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40);
        entityData.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), bitmask);

        npc.connection = new ServerGamePacketListenerImpl(
            npc.server,
            new EmptyConnection(null),
            npc,
            CommonListenerCookie.createInitial(npc.getGameProfile(), false)
        );

        return new NPC(npc, data);
    }
}
