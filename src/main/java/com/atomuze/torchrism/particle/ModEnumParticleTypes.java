package com.atomuze.torchrism.particle;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

public enum ModEnumParticleTypes {
	ALTARPARTICLE("altar_particle", 30, false);

	private final String particleName;
	private final int particleID;
	private final boolean shouldIgnoreRange;
	private final int argumentCount;
	private static final Map<Integer, ModEnumParticleTypes> PARTICLES = Maps.<Integer, ModEnumParticleTypes>newHashMap();
	private static final Map<String, ModEnumParticleTypes> BY_NAME = Maps.<String, ModEnumParticleTypes>newHashMap();

	private ModEnumParticleTypes(String particleNameIn, int particleIDIn, boolean shouldIgnoreRangeIn, int argumentCountIn) {
		this.particleName = particleNameIn;
		this.particleID = particleIDIn;
		this.shouldIgnoreRange = shouldIgnoreRangeIn;
		this.argumentCount = argumentCountIn;
	}

	private ModEnumParticleTypes(String particleNameIn, int particleIDIn, boolean shouldIgnoreRangeIn) {
		this(particleNameIn, particleIDIn, shouldIgnoreRangeIn, 0);
	}

	public static Set<String> getParticleNames() {
		return BY_NAME.keySet();
	}

	public String getParticleName() {
		return this.particleName;
	}

	public int getParticleID() {
		return this.particleID;
	}

	public int getArgumentCount() {
		return this.argumentCount;
	}

	public boolean getShouldIgnoreRange() {
		return this.shouldIgnoreRange;
	}

	/**
	 * Gets the relative EnumParticleTypes by id.
	 */
	@Nullable
	public static ModEnumParticleTypes getParticleFromId(int particleId) {
		return PARTICLES.get(Integer.valueOf(particleId));
	}

	@Nullable
	public static ModEnumParticleTypes getByName(String nameIn) {
		return BY_NAME.get(nameIn);
	}

	static {
		for (ModEnumParticleTypes enumparticletypes : values()) {
			PARTICLES.put(Integer.valueOf(enumparticletypes.getParticleID()), enumparticletypes);
			BY_NAME.put(enumparticletypes.getParticleName(), enumparticletypes);
		}
	}
}
