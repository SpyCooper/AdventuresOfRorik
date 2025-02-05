// Description: 
// This class represents the adventurer, the player's character in the game.
// The adventurer has stats such as health, gold, level, experience, and current level.

// Adventurer class
public class Adventurer
{
    // Adventurer stats
    int currentHealth = 10;
    int maxHealth = 10;
    int gold = 0;
    int level = 1;
    int experience = 0;
    int experienceToNextLevel = 10;
    int healCooldownMax = 4;
    int currentHealCooldown = 0;
    int healAmount = 10;
    // Adventurer's current weapon
    Weapon weapon = new Weapon(Weapon.WeaponType.DAGGER);

    // Adventurer constructor
    public Adventurer()
    {
        currentHealth = maxHealth;
    }

    // Method to attack an enemy
    public void Attack(Enemy enemy)
    {
        enemy.currentHealth -= weapon.Attack();
    }

    // Method to switch the adventurer's weapon
    public void SwitchWeapon(Weapon.WeaponType type)
    {
        weapon.SwitchWeapon(type);
    }

    // Method to heal the adventurer
    public void Heal()
    {
        // If the heal + current health is greater than max health, set current health to max health
        if (currentHealth + healAmount > maxHealth)
        {
            currentHealth = maxHealth;
        }
        // Otherwise, add the heal amount to the current health
        else
        {
            currentHealth += healAmount;
        }

        // Set the heal cooldown to the max cooldown
        currentHealCooldown = healCooldownMax;
    }

    // Method to take damage
    public void TakeDamage(int damage)
    {
        currentHealth -= damage;
    }

    // Method to add gold
    public void AddGold(int gold)
    {
        this.gold += gold;
    }

    // Method to remove gold
    public void RemoveGold(int gold)
    {
        this.gold -= gold;
    }

    // Method to level up the adventurer
    public void LevelUp()
    {
        // Increase level by 1
        level++;
        // Increase max health by 10
        maxHealth += 10;
        // Set current health to max health
        currentHealth = maxHealth;
        // Increase experience to next level by 10
        experienceToNextLevel += 10;
    }

    // Method to add experience to the adventurer
    public boolean AddExperience(int experience)
    {
        // Add the experience to the current experience
        this.experience += experience;

        // If the current experience is greater than or equal to the experience to next level, level up
        if (this.experience >= experienceToNextLevel)
        {
            // Subtract the experience to next level from the current experience
            this.experience -= experienceToNextLevel;
            // Level up the adventurer
            LevelUp();
            // return that the adventurer leveled up
            return true;
        }
        // Otherwise, add the experience to the current experience
        else
        {
            // return that the adventurer did not level up
            return false;
        }
    }

    // Method to return the adventurer's current level
    public int GetLevel()
    {
        return level;
    }

    // Method to set the heal cooldown to 0 (used for the shop)
    public void ResetHealCooldown()
    {
        currentHealCooldown = 0;
    }
    
    // Method to add a heal amount (used for the shop)
    public void AddHealAmount(int amount)
    {
        healAmount += amount;
    }

    // Method to return the adventurer's stats as a string
    public String GetStats()
    {
        return "Level: " + level + "\nExperience: " + experience + "/" + experienceToNextLevel + "\nHealth: " + currentHealth + "/" + maxHealth + "\nGold: " + gold + "\nWeapon: " + weapon.name + " - " + weapon.damage + " dmg";
    }
}
