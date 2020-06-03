package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.BiomeSource;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;
import kaptainwutax.seedutils.util.UnsupportedVersion;
import kaptainwutax.featureutils.Feature;

public class Fortress extends Structure<Feature.Config, Feature.Data<?>> {

	public static final VersionMap<Feature.Config> CONFIGS = new VersionMap<Feature.Config>()
			.add(MCVersion.v1_7, new Feature.Config())
			.add(MCVersion.v1_16, new RegionStructure.Config(30, 4, 30084232));

	protected UniformStructure internal;

	public Fortress(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);

		if(!version.isOlderThan(MCVersion.v1_16)) {
			this.internal = new UniformStructure((RegionStructure.Config)CONFIGS.getAsOf(version), version) {
				@Override
				public boolean isValidBiome(Biome biome) {
					return Fortress.this.isValidBiome(biome);
				}
			};
		}
	}

	public Fortress(RegionStructure.Config config, MCVersion version) {
		super(config, version);

		if(version.isOlderThan(MCVersion.v1_16)) {
			throw new UnsupportedVersion(version, "fortress region");
		} else {
			this.internal = new UniformStructure((RegionStructure.Config)CONFIGS.getAsOf(version), version) {
				@Override
				public boolean isValidBiome(Biome biome) {
					return Fortress.this.isValidBiome(biome);
				}
			};
		}
	}

	@Override
	public boolean canStart(Data<?> data, long structureSeed, ChunkRand rand) {
		if(this.getVersion().isOlderThan(MCVersion.v1_16)) {
			rand.setWeakSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
			rand.nextInt();
			if(rand.next(3) != 0)return false;
			if(data.chunkZ - 4 != (data.chunkZ & -16) + rand.nextInt(8))return false;
			if(data.chunkX - 4 != (data.chunkX & -16) + rand.nextInt(8))return false;
			return true;
		}

		if(this.internal.canStart((RegionStructure.Data<?>)data, structureSeed, rand));
		return rand.nextInt(6) < 2;
	}

	@Override
	public boolean canSpawn(Data<?> data, BiomeSource source) {
		if(this.getVersion().isOlderThan(MCVersion.v1_16)) {
			return this.isValidBiome(Biome.REGISTRY.get(source.voronoi.sample((data.chunkX << 4) + 9, (data.chunkX << 4) + 9)));
		}

		return this.internal.canSpawn((RegionStructure.Data<?>)data, source);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.BASALT_DELTAS || biome == Biome.CRIMSON_FOREST || biome == Biome.NETHER_WASTES
				|| biome == Biome.SOUL_SAND_VALLEY || biome == Biome.WARPED_FOREST;
	}

}