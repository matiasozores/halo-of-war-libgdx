package com.haloofwar.common.enums;

import com.haloofwar.engine.entity.EntityDescriptor;

public enum BulletType implements EntityDescriptor {

    ASSAULT("Bala de rifle de asalto", "images/weapons/bullets/bullet_assault.png", SoundType.SHOOT_ASSAULT),
    PISTOL("Bala de pistola", "images/weapons/bullets/bullet_pistol.png", SoundType.SHOOT_PISTOL),
    SHOTGUN("Cartucho de escopeta", "images/weapons/bullets/shell_shotgun.png", SoundType.SHOOT_SHOTGUN),
    SMG("Bala de subfusil", "images/weapons/bullets/bullet_smg.png", SoundType.SHOOT_SMG),
    MACHINE_GUN("Bala de ametralladora", "images/weapons/bullets/bullet_mg.png", SoundType.SHOOT_MG),
    SNIPER("Bala de francotirador", "images/weapons/bullets/bullet_sniper.png", SoundType.SHOOT_SNIPER),
    REVOLVER("Bala de revolver", "images/weapons/bullets/bullet_revolver.png", SoundType.SHOOT_REVOLVER);

    private final String name;
    private final String path;
    private final SoundType sound;

    BulletType(String name, String path, SoundType sound) {
        this.name = name;
        this.path = path;
        this.sound = sound;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    public SoundType getSound() {
        return this.sound;
    }
}
