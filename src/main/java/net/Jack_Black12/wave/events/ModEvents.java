package net.Jack_Black12.wave.events;

import net.Jack_Black12.wave.GodWaveMod;
import net.Jack_Black12.wave.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GodWaveMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onHoldingGodWave(TickEvent.PlayerTickEvent event) {
        // We only want to handle the event on the server side to avoid
        // potential synchronization issues and applying the effect multiple times.
        if (event.player.level.isClientSide) {
            return;
        }

        boolean hasGodWave = false;
        // Loop through all inventory slots, including hotbar, main inventory, and armor.
        for (ItemStack itemS : event.player.getInventory().items) {
            // Check if the item in the current slot is the GodWave.
            if (itemS.getItem() == ModItems.Godwave.get()) {
                hasGodWave = true;
                // No need to check any further, we found the item.
                break;
            }
        }

        // Apply effects if the GodWave was found in the inventory.
        if (hasGodWave) {
            // If they don't have the effect, add it.
            // The arguments for MobEffectInstance are:
            // 1. MobEffects.EFFECT_YOU_WANT: The effect to apply.
            // 2. 20: The duration in ticks. 20 ticks = 1 second.
            // 3. 100000: The amplifier (strength). 0 is Level 1, 1 is Level 2, and so on.
            // 4. false: 'ambient' - whether the effect is from an ambient source.
            // 5. false: 'showParticles' - whether to show the particle effect.
            if (!event.player.hasEffect(MobEffects.REGENERATION)) {
                event.player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, Integer.MAX_VALUE, 4, false, false));
            }
            // Check if the player already has the damage resistance effect.
            if (!event.player.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {
                event.player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 4, false, false));
            }
            // Check if the player already has the fire resistance effect.
            if (!event.player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                event.player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, Integer.MAX_VALUE, 4, false, false));
            }
            // Check if the player already has the saturation.
            if (!event.player.hasEffect(MobEffects.SATURATION)) {
                event.player.addEffect(new MobEffectInstance(MobEffects.SATURATION, Integer.MAX_VALUE, 1, false, false));
            }
            // Check if the player already has conduit power effect.
            if (!event.player.hasEffect(MobEffects.CONDUIT_POWER)) {
                event.player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, Integer.MAX_VALUE, 1, false, false));
            }
            // Check if the player already has the dolphin effect.
            if (!event.player.hasEffect(MobEffects.DOLPHINS_GRACE)) {
                event.player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 7200, 2, false, false));
            }
            // Check if the player already has health boost.
            if (!event.player.hasEffect(MobEffects.HEALTH_BOOST)) {
                event.player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, Integer.MAX_VALUE, 4, false, false));
            }
            // Check if the player already has absorption.
            if (!event.player.hasEffect(MobEffects.ABSORPTION)) {
                event.player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, Integer.MAX_VALUE, 2, false, false));
            }
            // Check if the player already has strength.
            if (!event.player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                event.player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 32, false, false));
            }
            // Cast the player as a ServerPlayer
            if (event.player instanceof ServerPlayer serverPlayer) {
                if (serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL) {
                    // Give the player the ability to fly
                    serverPlayer.getAbilities().mayfly = true;
                    serverPlayer.onUpdateAbilities();
                }
            }
        } else {
            // Cast the player as a ServerPlayer
            if (event.player instanceof ServerPlayer serverPlayer) {
                if (serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL) {
                    // Take away the player the ability to fly
                    serverPlayer.getAbilities().mayfly = false;
                    serverPlayer.onUpdateAbilities();
                }
            }
        }
    }
}






