package net.unitego.lobecorp.common.component;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.unitego.lobecorp.registry.DataComponentTypesRegistry;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;

//脑叶公司属性修饰符
public record LobeCorpAttributeModifiers(List<Entry> modifiers, boolean showInTooltip) {
    public static final LobeCorpAttributeModifiers EMPTY = new LobeCorpAttributeModifiers(List.of(), true);
    public static final Codec<LobeCorpAttributeModifiers> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, LobeCorpAttributeModifiers> STREAM_CODEC;
    public static final DecimalFormat ATTRIBUTE_MODIFIER_FORMAT;
    private static final Codec<LobeCorpAttributeModifiers> FULL_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Entry.CODEC.listOf().fieldOf("modifiers").forGetter(LobeCorpAttributeModifiers::modifiers),
                            Codec.BOOL.optionalFieldOf("show_in_tooltip", true).forGetter(LobeCorpAttributeModifiers::showInTooltip))
                    .apply(instance, LobeCorpAttributeModifiers::new));

    static {
        CODEC = Codec.withAlternative(FULL_CODEC, Entry.CODEC.listOf(), entries -> new LobeCorpAttributeModifiers(entries, true));
        STREAM_CODEC = StreamCodec.composite(Entry.STREAM_CODEC.apply(ByteBufCodecs.list()), LobeCorpAttributeModifiers::modifiers,
                ByteBufCodecs.BOOL, LobeCorpAttributeModifiers::showInTooltip, LobeCorpAttributeModifiers::new);
        ATTRIBUTE_MODIFIER_FORMAT = Util.make(new DecimalFormat("#.##"), format ->
                format.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static void forEachModifier(LobeCorpEquipmentSlot slot, ItemStack stack, BiConsumer<Holder<Attribute>, AttributeModifier> biConsumer) {
        LobeCorpAttributeModifiers component = stack.getOrDefault(DataComponentTypesRegistry.LOBECORP_ATTRIBUTE_MODIFIERS, LobeCorpAttributeModifiers.EMPTY);
        if (!component.modifiers().isEmpty()) {
            component.forEach(slot, biConsumer);
        }
    }

    public static void addModifierTooltip(List<Component> tooltipAdder, @Nullable Player player, Holder<Attribute> attribute, AttributeModifier modifier, ItemStack stack) {
        double d0 = modifier.amount();
        boolean flag = false;
        if (player != null) {
            if (modifier.id() == Item.BASE_ATTACK_DAMAGE_UUID) {
                d0 += player.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);
                d0 += EnchantmentHelper.getDamageBonus(stack, null);
                flag = true;
            } else if (modifier.id() == Item.BASE_ATTACK_SPEED_UUID) {
                d0 += player.getAttributeBaseValue(Attributes.ATTACK_SPEED);
                flag = true;
            }
        }
        double d1;
        if (modifier.operation() == AttributeModifier.Operation.ADD_MULTIPLIED_BASE || modifier.operation() == AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL) {
            d1 = d0 * 100.0;
        } else if (attribute.equals(Attributes.KNOCKBACK_RESISTANCE)) {
            d1 = d0 * 10.0;
        } else {
            d1 = d0;
        }
        if (flag) {
            tooltipAdder.add(CommonComponents.space().append(Component.translatable("attribute.modifier.equals." + modifier.operation().id(), ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(attribute.value().getDescriptionId()))).withStyle(ChatFormatting.DARK_GREEN));
        } else if (d0 > 0.0) {
            tooltipAdder.add(Component.translatable("attribute.modifier.plus." + modifier.operation().id(), ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(attribute.value().getDescriptionId())).withStyle(ChatFormatting.BLUE));
        } else if (d0 < 0.0) {
            tooltipAdder.add(Component.translatable("attribute.modifier.take." + modifier.operation().id(), ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(-d1), Component.translatable(attribute.value().getDescriptionId())).withStyle(ChatFormatting.RED));
        }
    }

    public void forEach(LobeCorpEquipmentSlot slot, BiConsumer<Holder<Attribute>, AttributeModifier> action) {
        for (Entry entry : modifiers) {
            if (entry.slot == slot) {
                action.accept(entry.attribute, entry.modifier);
            }
        }
    }

    //构建组件实例
    public static class Builder {
        private final ImmutableList.Builder<Entry> entries = ImmutableList.builder();

        Builder() {
        }

        public Builder add(Holder<Attribute> attribute, AttributeModifier modifier, LobeCorpEquipmentSlot slot) {
            entries.add(new Entry(attribute, modifier, slot));
            return this;
        }

        public LobeCorpAttributeModifiers build() {
            return new LobeCorpAttributeModifiers(entries.build(), true);
        }
    }

    //序列化和反序列化
    public record Entry(Holder<Attribute> attribute, AttributeModifier modifier, LobeCorpEquipmentSlot slot) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(BuiltInRegistries.ATTRIBUTE.holderByNameCodec().fieldOf("type").forGetter(Entry::attribute),
                                AttributeModifier.MAP_CODEC.forGetter(Entry::modifier),
                                LobeCorpEquipmentSlot.CODEC.optionalFieldOf("slot", LobeCorpEquipmentSlot.LOBECORP_ANY).forGetter(Entry::slot))
                        .apply(instance, Entry::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, Entry> STREAM_CODEC;

        static {
            STREAM_CODEC = StreamCodec.composite(
                    ByteBufCodecs.holderRegistry(Registries.ATTRIBUTE), Entry::attribute,
                    AttributeModifier.STREAM_CODEC, Entry::modifier,
                    LobeCorpEquipmentSlot.STREAM_CODEC, Entry::slot,
                    Entry::new);
        }

        public Holder<Attribute> attribute() {
            return attribute;
        }

        public AttributeModifier modifier() {
            return modifier;
        }

        public LobeCorpEquipmentSlot slot() {
            return slot;
        }
    }
}
