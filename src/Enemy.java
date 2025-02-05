// Description: 
// This file contains the abstract class that represents an enemy in the game.
// All enemies have stats such as health, attack, experience, and name.

// Abstract class that represents an enemy in the game
public abstract class Enemy
{
    // Enemy stats
    int currentHealth;
    int maxHealth;
    int attack;
    int experience;
    String name;

    // Method to deal damage to the player
    public void DealDamage(Adventurer player)
    {
        player.TakeDamage(attack);
    }

    // Method to take damage from the player
    public void TakeDamage(int damage)
    {
        currentHealth -= damage;
    }

    // Method to add experience to the player
    public void GetExperience(Adventurer player)
    {
        player.AddExperience(experience);
    }

    // Method that returns the enemy's attack value
    public int Attack()
    {
        return attack;
    }

    // Method that returns the enemy's voice line
    public String VoiceLine()
    {
        return "";
    }
}
