package com.mcmoddev.nethermetals.init;

import java.util.Arrays;
import java.util.List;

import com.mcmoddev.lib.block.*;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.data.SharedStrings;
import com.mcmoddev.lib.init.Materials;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.util.Oredicts;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Loader;

/**
 * This class initializes all blocks in Nether Metals.
 *
 * @author Jasmine Iwanek
 *
 */
public class NetherBlocks extends com.mcmoddev.lib.init.Blocks {

	private static boolean initDone = false;

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Materials.init();
		ItemGroups.init();

		Arrays.asList("coal", "diamond", "emerald", "gold", "iron", "lapis", "redstone", "antimony", 
				"bismuth", "copper", "lead", "mercury", "nickel", "platinum", "silver", "tin", "zinc", 
				"aluminum", "cadmium", "chromium", "iridium", "magnesium", "manganese", "osmium", 
				"plutonium", "rutile", "tantalum", "titanium", "tungsten", "uranium", "zirconium").stream()
		.filter(Materials::hasMaterial)
		.forEach(NetherBlocks::createNetherOreWrapper);
			
		initDone = true;
	}

	private static void createVanillaNetherOreWrapper(String materialName) {
		final MMDMaterial material = Materials.getMaterialByName(materialName);
			material.addNewBlock(Names.NETHERORE, addBlock(new BlockMMDNetherOre(material), Names.NETHERORE.toString(), material, ItemGroups.getTab(SharedStrings.TAB_BLOCKS)));
			final Block b = material.getBlock(Names.NETHERORE);
			final String oredict = getOredictFromName(Names.NETHERORE);
			if ((oredict != null) && (b != null)) {
				Oredicts.registerOre(oredict + material.getCapitalizedName(), b);
			}
	}

	private static void createBasicNetherOreWrapper(String materialName ) {
		if (Materials.hasMaterial(materialName)) {
			create(Names.NETHERORE, materialName);
		}
	}
	
	private static void createNetherOreWrapper(String materialName) {
		List<String> vanillaMats = Arrays.asList("coal", "diamond", "emerald", "gold", "iron", "lapis", "redstone");
		if (vanillaMats.contains(materialName))
			createVanillaNetherOreWrapper(materialName);
		else
			createBasicNetherOreWrapper(materialName);
	}
}
