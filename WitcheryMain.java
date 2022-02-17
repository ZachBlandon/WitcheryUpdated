package Sncwy.WitcheryUpdated;

import Sncwy.WitcheryUpdated.itemDropTable.SeedDrop;
import Sncwy.WitcheryUpdated.proxy.CommonProxy;
import Sncwy.WitcheryUpdated.tabs.WitcheryTab;
import Sncwy.WitcheryUpdated.util.Reference;
import Sncwy.WitcheryUpdated.util.handlers.GuiHandler;
import Sncwy.WitcheryUpdated.util.handlers.RecipeHandler;
import Sncwy.WitcheryUpdated.util.handlers.TileEntityHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class WitcheryMain {

	@Instance
	public static WitcheryMain instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		SeedDrop.init();
		RecipeHandler.registerSmelting();
	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {

	}

	public static final CreativeTabs witcherytab = new WitcheryTab();
}
