// Description: 
// This file contains the Weapon class, which is used to for the weapon stats needed for combat.

// Weapon class
public class Weapon
{
    // Weapon type enum
    enum WeaponType
    {
        DAGGER,
        SHORTSWORD,
        LONGSWORD,
    }

    // Base weapon stats
    int damage = 5;
    String name = "Dagger";
    WeaponType currentWeaponType = WeaponType.DAGGER;

    // Weapon constructor
    public Weapon(WeaponType type)
    {
        // Set the weapon type
        SwitchWeapon(type);
    }

    // Method to return the weapon's attack value
    public int Attack()
    {
        return damage;
    }

    // Method to switch the weapon type
    public void SwitchWeapon(WeaponType type)
    {
        // Switch the weapon type and stats based on the type
        switch (type)
        {
            // If the weapon type is a dagger, set the weapon stats to the dagger stats
            case DAGGER:
                damage = 5;
                name = "Dagger";
                currentWeaponType = WeaponType.DAGGER;
                break;
            // If the weapon type is a shortsword, set the weapon stats to the shortsword stats
            case SHORTSWORD:
                damage = 10;
                name = "Shortsword";
                currentWeaponType = WeaponType.SHORTSWORD;
                break;
            // If the weapon type is a longsword, set the weapon stats to the longsword stats
            case LONGSWORD:
                damage = 15;
                name = "Longsword";
                currentWeaponType = WeaponType.LONGSWORD;
                break;
            // If the weapon type is invalid, default to the dagger
            default:
                damage = 5;
                name = "Dagger";
                currentWeaponType = WeaponType.DAGGER;
                break;
        }
    }
}
