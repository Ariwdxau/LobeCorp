package net.unitego.lobecorp.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.unitego.lobecorp.LobeCorp;
import net.unitego.lobecorp.client.init.KeyInit;
import net.unitego.lobecorp.common.component.LobeCorpEquipmentSlot;
import net.unitego.lobecorp.common.item.badge.TeamBadge;
import net.unitego.lobecorp.common.item.ego.gift.head.PenitenceGift;
import net.unitego.lobecorp.common.item.ego.suit.PenitenceSuit;
import net.unitego.lobecorp.common.item.ego.weapon.EGOWeaponTemplate;
import net.unitego.lobecorp.common.item.ego.weapon.mace.PenitenceWeapon;
import net.unitego.lobecorp.common.util.MiscUtils;
import net.unitego.lobecorp.registry.*;

public class EnUsLangGenerator extends LanguageProvider {
    public EnUsLangGenerator(PackOutput output) {
        super(output, LobeCorp.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //属性名
        add(AttributesRegistry.MAX_SANITY.get().getDescriptionId(), "Max Sanity");
        add(AttributesRegistry.MAX_ASSIMILATION.get().getDescriptionId(), "Max Assimilation");
        add(AttributesRegistry.WORK_SUCCESS.get().getDescriptionId(), "Work Success");
        add(AttributesRegistry.WORK_VELOCITY.get().getDescriptionId(), "Work Velocity");
        add(AttributesRegistry.ATTACK_VELOCITY.get().getDescriptionId(), "Attack Velocity");
        add(AttributesRegistry.MOVE_VELOCITY.get().getDescriptionId(), "Move Velocity");
        //创造栏名
        add(CreativeModeTabsRegistry.LOBECORP_TAB_NAME, "Lobe Corp");
        //死亡信息
        add(DamageTypesRegistry.DEATH_ATTACK_DRY, "%1$s dried to death");
        add(DamageTypesRegistry.DEATH_ATTACK_DRY_PLAYER, "%1$s dried to death whilst fighting %2$s");
        add(DamageTypesRegistry.DEATH_ATTACK_INSANE, "%1$s died due to insanity");
        add(DamageTypesRegistry.DEATH_ATTACK_INSANE_PLAYER, "%1$s experienced insanity to death whilst fighting %2$s");
        add(DamageTypesRegistry.DEATH_ATTACK_INDIRECT_MYSTIC, "%1$s was killed by %2$s using mystic");
        add(DamageTypesRegistry.DEATH_ATTACK_INDIRECT_MYSTIC_ITEM, "%1$s was killed by %2$s using %3$s");
        add(DamageTypesRegistry.DEATH_ATTACK_MYSTIC, "%1$s was killed by mystic");
        add(DamageTypesRegistry.DEATH_ATTACK_MYSTIC_PLAYER, "%1$s was killed by mystic whilst trying to escape %2$s");
        add(DamageTypesRegistry.DEATH_ATTACK_RED, "%2$s caused %1$s died from Physical Shattering");
        add(DamageTypesRegistry.DEATH_ATTACK_RED_ITEM, "%2$s using %3$s caused %1$s died from Physical Shattering");
        add(DamageTypesRegistry.DEATH_ATTACK_WHITE, "%2$s caused %1$s died from Mental Collapse");
        add(DamageTypesRegistry.DEATH_ATTACK_WHITE_ITEM, "%2$s using %3$s caused %1$s died from Mental Collapse");
        add(DamageTypesRegistry.DEATH_ATTACK_BLACK, "%2$s caused %1$s died from Erosion Penetration");
        add(DamageTypesRegistry.DEATH_ATTACK_BLACK_ITEM, "%2$s using %3$s caused %1$s died from Erosion Penetration");
        add(DamageTypesRegistry.DEATH_ATTACK_PALE, "%2$s caused %1$s died from Soul Erasure");
        add(DamageTypesRegistry.DEATH_ATTACK_PALE_ITEM, "%2$s using %3$s caused %1$s died from Soul Erasure");
        //物品名
        //脑叶公司
        add(ItemsRegistry.LOBECORP_LOGO.get(), "LobeCorp Logo");
        add(ItemsRegistry.BLUE_LEAF.get(), "Blue Leaf");
        add(ItemsRegistry.CODE_SUIT.get(), "Code Suit");
        add(ItemsRegistry.CODE_RIOT_STICK.get(), "Code Riot Stick");
        add(ItemsRegistry.STANDARD_SUIT.get(), "Standard Suit");
        add(ItemsRegistry.RED_RIOT_STICK.get(), "Red Riot Stick");
        add(ItemsRegistry.WHITE_RIOT_STICK.get(), "White Riot Stick");
        add(ItemsRegistry.BLACK_RIOT_STICK.get(), "Black Riot Stick");
        add(ItemsRegistry.PALE_RIOT_STICK.get(), "Pale Riot Stick");
        //部门袖标
        add(ItemsRegistry.CONTROL_TEAM_BADGE.get(), "Control Team Badge");
        add(ItemsRegistry.INFORMATION_TEAM_BADGE.get(), "Information Team Badge");
        add(ItemsRegistry.SECURITY_TEAM_BADGE.get(), "Security Team Badge");
        add(ItemsRegistry.TRAINING_TEAM_BADGE.get(), "Training Team Badge");
        add(ItemsRegistry.CENTRAL_COMMAND_TEAM_BADGE.get(), "Central Command Team Badge");
        add(ItemsRegistry.WELFARE_TEAM_BADGE.get(), "Welfare Team Badge");
        add(ItemsRegistry.DISCIPLINARY_TEAM_BADGE.get(), "Disciplinary Team Badge");
        add(ItemsRegistry.RECORD_TEAM_BADGE.get(), "Record Team Badge");
        add(ItemsRegistry.EXTRACTION_TEAM_BADGE.get(), "Extraction Team Badge");
        add(ItemsRegistry.ARCHITECTURE_TEAM_BADGE.get(), "Architecture Team Badge");
        //EGO武器
        add(ItemsRegistry.STANDARD_TRAINING_EGO_WEAPON.get(), "Standard Training E.G.O");
        add(ItemsRegistry.PENITENCE_WEAPON.get(), "Penitence");
        //EGO护甲
        add(ItemsRegistry.STANDARD_TRAINING_EGO_SUIT.get(), "Standard Training E.G.O");
        add(ItemsRegistry.PENITENCE_SUIT.get(), "Penitence");
        //EGO饰品
        add(ItemsRegistry.STANDARD_TRAINING_EGO_GIFT.get(), "Standard Training E.G.O");
        add(ItemsRegistry.PENITENCE_GIFT.get(), "Penitence");
        add(ItemsRegistry.BLESS_GIFT.get(), "Bless");
        //菜单名
        add(MenusRegistry.CONTAINER_LOBECORP_EQUIPMENT, "Equipment");
        //效果名
        add(MobEffectsRegistry.ABSENT.get(), "Absent");
        add(MobEffectsRegistry.ASSIMILATION.get(), "Assimilation");
        add(MobEffectsRegistry.HYDRATION.get(), "Hydration");
        add(MobEffectsRegistry.INSANE.get(), "Insane");
        add(MobEffectsRegistry.RESTORATION.get(), "Restoration");
        add(MobEffectsRegistry.SANITY_BOOST.get(), "Sanity Boost");
        add(MobEffectsRegistry.THIRST.get(), "Thirst");
        add(MobEffectsRegistry.INSTANT_SANITY.get(), "Instant Sanity");
        add(MobEffectsRegistry.INSTANT_INJURY.get(), "Instant Injury");
        //声音名
        add(SoundEventsRegistry.SWALLOW_WATER.get().getLocation().toLanguageKey(), "Swallow Water");
        add(SoundEventsRegistry.CHANGE_EQUIPMENT.get().getLocation().toLanguageKey(), "Change Equipment");

        //按键名
        add(KeyInit.KEY_CATEGORIES_LOBECORP, "Lobe Corp");
        add(KeyInit.KEY_TOGGLE_EQUIPMENT.get().getName(), "Open/Close Equipment");

        //武器模板
        add(EGOWeaponTemplate.MACE.getTranslationKey(), "Mace");
        add(EGOWeaponTemplate.AXE.getTranslationKey(), "Axe");
        add(EGOWeaponTemplate.KNIFE.getTranslationKey(), "Knife");
        //插槽名
        add(LobeCorpEquipmentSlot.LOBECORP_WEAPON.getSlotModifiersName(), "When in Weapon Slot: ");
        add(LobeCorpEquipmentSlot.LOBECORP_SUIT.getSlotModifiersName(), "When in Suit Slot: ");
        add(LobeCorpEquipmentSlot.LOBECORP_BADGE.getSlotModifiersName(), "When in Badge Slot: ");
        add(LobeCorpEquipmentSlot.LOBECORP_TOOL.getSlotModifiersName(), "When in Tool Slot: ");
        add(LobeCorpEquipmentSlot.LOBECORP_HAT.getSlotModifiersName(), "When in Hat Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_HEAD.getSlotModifiersName(), "When in Head Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_OCCIPUT.getSlotModifiersName(), "When in Occiput Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_EYE.getSlotModifiersName(), "When in Eye Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_FACE.getSlotModifiersName(), "When in Face Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_CHEEK.getSlotModifiersName(), "When in Cheek Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_MASK.getSlotModifiersName(), "When in Mask Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_MOUTH.getSlotModifiersName(), "When in Mouth Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_NECK.getSlotModifiersName(), "When in Neck Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_CHEST.getSlotModifiersName(), "When in Chest Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_HAND.getSlotModifiersName(), "When in Hand Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_GLOVE.getSlotModifiersName(), "When in Glove Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_RIGHTBACK.getSlotModifiersName(), "When in RightBack Slot:");
        add(LobeCorpEquipmentSlot.LOBECORP_LEFTBACK.getSlotModifiersName(), "When in LeftBack Slot:");

        //脑叶公司
        add(MiscUtils.MOTTO, "Face the Fear, Build the Future");
        add(MiscUtils.CONFIRM, "Confirm");
        add(MiscUtils.INVALID_VALUE, "Invalid Input，please check the values.");
        add(MiscUtils.STAFF_RANK, "Staff Rank");
        add(MiscUtils.STAFF_FORTITUDE, "Fortitude");
        add(MiscUtils.STAFF_PRUDENCE, "Prudence");
        add(MiscUtils.STAFF_TEMPERANCE, "Temperance");
        add(MiscUtils.STAFF_JUSTICE, "Justice");
        add(MiscUtils.HOLD_LEFT_SHIFT_SHOW_SKILL, "Hold Left Shift Show Skill");
        add(MiscUtils.EGO_RANK, "EGO Rank: ");
        add(MiscUtils.EQUIP_REQUIRE, "Equip Require: ");
        add(MiscUtils.WEAPON_TEMPLATE, "Weapon Template: ");
        add(MiscUtils.DAMAGE_TYPE, "Damage Type: ");
        add(MiscUtils.DAMAGE_RESIST, "Damage Resist: ");
        add(MiscUtils.RED, "Red");
        add(MiscUtils.WHITE, "White");
        add(MiscUtils.BLACK, "Black");
        add(MiscUtils.PALE, "Pale");
        //部门名
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.CONTROL_TEAM.getTeamName()), "[Control Team]");
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.INFORMATION_TEAM.getTeamName()), "[Information Team]");
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.SECURITY_TEAM.getTeamName()), "[Security Team]");
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.TRAINING_TEAM.getTeamName()), "[Training Team]");
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.CENTRAL_COMMAND_TEAM.getTeamName()), "[Central Command Team]");
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.WELFARE_TEAM.getTeamName()), "[Welfare Team]");
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.DISCIPLINARY_TEAM.getTeamName()), "[Disciplinary Team]");
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.RECORD_TEAM.getTeamName()), "[Record Team]");
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.EXTRACTION_TEAM.getTeamName()), "[Extraction Team]");
        add(TeamBadge.teamBadgeString(TeamBadge.LobeCorpTeam.ARCHITECTURE_TEAM.getTeamName()), "[Architecture Team]");

        //技能
        //忏悔
        add(PenitenceWeapon.WEAPON_PENITENCE1, "5% of healing 10 SP upon attacking with Justice Lvl 2+");
        add(PenitenceSuit.SUIT_PENITENCE1, "5% of heal 10 SP when damaged by Red or Black");
        add(PenitenceGift.GIFT_PENITENCE1, "Success Rate increases %10 with the corresponding Abnormality");
    }
}
