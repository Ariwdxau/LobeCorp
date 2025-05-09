package net.unitego.lobecorp.common.manager;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.unitego.lobecorp.common.access.EGORankAccess;
import net.unitego.lobecorp.common.access.EquipRequireAccess;
import net.unitego.lobecorp.common.util.DelayedTaskUtils;
import net.unitego.lobecorp.common.util.MiscUtils;
import net.unitego.lobecorp.registry.AttributesRegistry;
import net.unitego.lobecorp.registry.DamageTypesRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//职员数据
public class StaffManager {
    private final Player player;

    public StaffManager(Player player) {
        this.player = player;
    }

    //计算品质等级
    public static StaffRank calculateVirtueRank(int value) {
        if (value >= 100) return StaffRank.EX;
        if (value >= 85) return StaffRank.V;
        if (value >= 65) return StaffRank.IV;
        if (value >= 45) return StaffRank.III;
        if (value >= 30) return StaffRank.II;
        if (value > 0) return StaffRank.I;
        return StaffRank.ER;
    }

    //计算职员等级
    public static StaffRank calculateStaffRank(StaffRank fortitudeRank, StaffRank prudenceRank, StaffRank temperanceRank, StaffRank justiceRank) {
        int total = fortitudeRank.value + prudenceRank.value + temperanceRank.value + justiceRank.value;
        if (total >= 24) return StaffRank.EX;
        if (total >= 16) return StaffRank.V;
        int value = Mth.clamp((total) / 3, -1, 4);
        return switch (value) {
            case 4 -> StaffRank.IV;
            case 3 -> StaffRank.III;
            case 2 -> StaffRank.II;
            case 1 -> StaffRank.I;
            default -> StaffRank.ER;
        };
    }

    public double getMaxHealth() {
        return player.getAttributeValue(Attributes.MAX_HEALTH);
    }

    public void setMaxHealth(double maxHealth) {
        Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(maxHealth);
    }

    public double getMaxSanity() {
        return player.getAttributeValue(AttributesRegistry.MAX_SANITY);
    }

    public void setMaxSanity(double maxSanity) {
        Objects.requireNonNull(player.getAttribute(AttributesRegistry.MAX_SANITY)).setBaseValue(maxSanity);
    }

    public double getWorkSuccess() {
        return player.getAttributeValue(AttributesRegistry.WORK_SUCCESS);
    }

    public void setWorkSuccess(double workSuccess) {
        Objects.requireNonNull(player.getAttribute(AttributesRegistry.WORK_SUCCESS)).setBaseValue(workSuccess);
    }

    public double getWorkVelocity() {
        return player.getAttributeValue(AttributesRegistry.WORK_VELOCITY);
    }

    public void setWorkVelocity(double workVelocity) {
        Objects.requireNonNull(player.getAttribute(AttributesRegistry.WORK_VELOCITY)).setBaseValue(workVelocity);
    }

    public double getAttackVelocity() {
        return player.getAttributeValue(AttributesRegistry.ATTACK_VELOCITY);
    }

    public void setAttackVelocity(double attackVelocity) {
        Objects.requireNonNull(player.getAttribute(AttributesRegistry.ATTACK_VELOCITY)).setBaseValue(attackVelocity);
    }

    public double getMoveVelocity() {
        return player.getAttributeValue(AttributesRegistry.MOVE_VELOCITY);
    }

    public void setMoveVelocity(double moveVelocity) {
        Objects.requireNonNull(player.getAttribute(AttributesRegistry.MOVE_VELOCITY)).setBaseValue(moveVelocity);
    }

    public StaffRank getJusticeRank() {
        return calculateVirtueRank((int) ((getAttackVelocity() + getMoveVelocity()) / 2));
    }

    public StaffRank getTemperanceRank() {
        return calculateVirtueRank((int) ((getWorkSuccess() + getWorkVelocity()) / 2));
    }

    public StaffRank getPrudenceRank() {
        return calculateVirtueRank((int) getMaxSanity());
    }

    public StaffRank getFortitudeRank() {
        return calculateVirtueRank((int) getMaxHealth());
    }

    public StaffRank getStaffRank() {
        return calculateStaffRank(getFortitudeRank(), getPrudenceRank(), getTemperanceRank(), getJusticeRank());
    }

    public void checkEGO(ItemStack stack) {
        if (!player.isInvulnerable()) {
            if (stack.getItem() instanceof EquipRequireAccess equipRequireAccess && stack.getItem() instanceof EGORankAccess egoRankAccess) {
                EquipRequire equipRequire = equipRequireAccess.getEquipRequire();
                if (!equipRequire.isSatisfiedBy(this)) {
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 254, false, false, false));
                    DelayedTaskUtils.runLater(200, () -> {
                        if (!player.isInvulnerable()) {
                            player.hurt(MiscUtils.noKnockBackDamageSource(DamageTypesRegistry.BLACK, player), egoRankAccess.getEGORank().getValue() * 2);
                        }
                    });
                }
            }
        }
    }

    //职员等级
    public enum StaffRank {
        I("I", 1),
        II("II", 2),
        III("III", 3),
        IV("IV", 4),
        V("V", 5),
        EX("EX", 6),
        ER("ER", -1);
        private final String rank;
        private final int value;

        StaffRank(String rank, int value) {
            this.rank = rank;
            this.value = value;
        }

        public String getRank() {
            return rank;
        }

        public int getValue() {
            return value;
        }
    }

    public record EquipRequire(StaffRank fortitudeRank, StaffRank prudenceRank, StaffRank temperanceRank,
                               StaffRank justiceRank, StaffRank staffRank) {
        public static final EquipRequire NONE = new EquipRequire(null, null, null, null, null);

        public static Builder builder() {
            return new Builder();
        }

        public boolean isSatisfiedBy(StaffManager data) {
            return (staffRank == null || data.getStaffRank().getValue() >= staffRank.getValue()) &&
                    (fortitudeRank == null || data.getFortitudeRank().getValue() >= fortitudeRank.getValue()) &&
                    (prudenceRank == null || data.getPrudenceRank().getValue() >= prudenceRank.getValue()) &&
                    (temperanceRank == null || data.getTemperanceRank().getValue() >= temperanceRank.getValue()) &&
                    (justiceRank == null || data.getJusticeRank().getValue() >= justiceRank.getValue());
        }

        public List<Component> getDisplayTooltip() {
            StaffRank displayStaff = staffRank != null ? staffRank : StaffRank.I;
            StaffRank displayFortitude = fortitudeRank != null ? fortitudeRank : StaffRank.I;
            StaffRank displayPrudence = prudenceRank != null ? prudenceRank : StaffRank.I;
            StaffRank displayTemperance = temperanceRank != null ? temperanceRank : StaffRank.I;
            StaffRank displayJustice = justiceRank != null ? justiceRank : StaffRank.I;

            List<Component> lines = new ArrayList<>();

            lines.add(Component.translatable(MiscUtils.EQUIP_REQUIRE).withStyle(ChatFormatting.DARK_GRAY)
                    .append(Component.translatable(MiscUtils.STAFF_RANK)
                            .append(Component.literal(displayStaff.getRank())).withStyle(ChatFormatting.GOLD)));
            lines.add(Component.empty()
                    .append(Component.translatable(MiscUtils.STAFF_FORTITUDE)
                            .append(Component.literal(displayFortitude.getRank())).withStyle(ChatFormatting.DARK_RED))
                    .append(Component.literal("|").withStyle(ChatFormatting.DARK_GRAY))
                    .append(Component.translatable(MiscUtils.STAFF_PRUDENCE)
                            .append(Component.literal(displayPrudence.getRank())).withStyle(ChatFormatting.WHITE))
                    .append(Component.literal("|").withStyle(ChatFormatting.DARK_GRAY))
                    .append(Component.translatable(MiscUtils.STAFF_TEMPERANCE)
                            .append(Component.literal(displayTemperance.getRank())).withStyle(ChatFormatting.DARK_PURPLE))
                    .append(Component.literal("|").withStyle(ChatFormatting.DARK_GRAY))
                    .append(Component.translatable(MiscUtils.STAFF_JUSTICE)
                            .append(Component.literal(displayJustice.getRank())).withStyle(ChatFormatting.AQUA))
            );
            return lines;
        }

        public static class Builder {
            private StaffRank fortitude, prudence, temperance, justice, staff;

            public Builder fortitude(StaffRank rank) {
                this.fortitude = rank;
                return this;
            }

            public Builder prudence(StaffRank rank) {
                this.prudence = rank;
                return this;
            }

            public Builder temperance(StaffRank rank) {
                this.temperance = rank;
                return this;
            }

            public Builder justice(StaffRank rank) {
                this.justice = rank;
                return this;
            }

            public Builder staff(StaffRank rank) {
                this.staff = rank;
                return this;
            }

            public EquipRequire build() {
                return new EquipRequire(fortitude, prudence, temperance, justice, staff);
            }
        }
    }
}
