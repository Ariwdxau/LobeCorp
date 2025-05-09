package net.unitego.lobecorp.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.unitego.lobecorp.LobeCorp;
import net.unitego.lobecorp.registry.ItemsRegistry;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LobeCorp.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //脑叶公司
        basicItem(ItemsRegistry.LOBECORP_LOGO.get());
        basicItem(ItemsRegistry.BLUE_LEAF.get());
        basicItem(ItemsRegistry.CODE_SUIT.get());
        basicItem(ItemsRegistry.CODE_RIOT_STICK.get());
        basicItem(ItemsRegistry.STANDARD_SUIT.get());
        basicItem(ItemsRegistry.RED_RIOT_STICK.get());
        basicItem(ItemsRegistry.WHITE_RIOT_STICK.get());
        basicItem(ItemsRegistry.BLACK_RIOT_STICK.get());
        basicItem(ItemsRegistry.PALE_RIOT_STICK.get());
        //部门袖标
        basicItem(ItemsRegistry.CONTROL_TEAM_BADGE.get());
        basicItem(ItemsRegistry.INFORMATION_TEAM_BADGE.get());
        basicItem(ItemsRegistry.SECURITY_TEAM_BADGE.get());
        basicItem(ItemsRegistry.TRAINING_TEAM_BADGE.get());
        basicItem(ItemsRegistry.CENTRAL_COMMAND_TEAM_BADGE.get());
        basicItem(ItemsRegistry.WELFARE_TEAM_BADGE.get());
        basicItem(ItemsRegistry.DISCIPLINARY_TEAM_BADGE.get());
        basicItem(ItemsRegistry.RECORD_TEAM_BADGE.get());
        basicItem(ItemsRegistry.EXTRACTION_TEAM_BADGE.get());
        basicItem(ItemsRegistry.ARCHITECTURE_TEAM_BADGE.get());
        //EGO武器
        basicItem(ItemsRegistry.STANDARD_TRAINING_EGO_WEAPON.get());
        basicItem(ItemsRegistry.PENITENCE_WEAPON.get());
        //EGO护甲
        basicItem(ItemsRegistry.STANDARD_TRAINING_EGO_SUIT.get());
        basicItem(ItemsRegistry.PENITENCE_SUIT.get());
        //EGO饰品
        basicItem(ItemsRegistry.STANDARD_TRAINING_EGO_GIFT.get());
        basicItem(ItemsRegistry.PENITENCE_GIFT.get());
        basicItem(ItemsRegistry.BLESS_GIFT.get());
    }
}
