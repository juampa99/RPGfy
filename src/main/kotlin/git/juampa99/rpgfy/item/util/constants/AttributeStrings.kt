package git.juampa99.rpgfy.item.util.constants

/**
 * Use for "name" parameter of AttributeModifier
 * */
object AttributeStrings {

    object Item {

        const val ATTACK_DAMAGE = "generic.attack_damage"
        const val ATTACK_SPEED = "generic_attack_speed"
        const val ARMOR = "generic.armor"
        const val ARMOR_THOUGHNESS = "generic.armor_toughness"
        const val MOVEMENT_SPEED = "generic.movement_speed"
        const val LUCK = "generic.luck"

    }

    object LivingEntity {

        const val MAX_HEALTH = "generic.max_health"
        const val ATTACK_DAMAGE = "generic.attack_damage"
        const val FOLLOW_RANGE = "generic.follow_range"
        const val KNOCKBACK_RESISTANCE = "generic.knockback_resistance"
        const val ARMOR = "generic.armor"
        const val ARMOR_THOUGHNESS = "generic.armor_toughness"
        const val ATTACK_KNOCKBACK = "generic.attack_knockback"

        object Player {
            const val ATTACK_SPEED = "generic.attack_speed"
            const val LUCK = "generic.luck"
        }

        object Flying {
            const val FLYING_SPEED = "generic.flying_speed"
        }

        object Zombie {
            const val SPAWN_REINFORCEMENTS = "zombie.spawn_reinforcements"
        }

    }

}