package unitTesting;

import java.math.BigDecimal;
/**
 * Treasure chest that is defined by the amount of gold coins that are stored in it.
 * In this class we assume that each coin is a Twenty-dollar Liberty Gold coin. This
 * assumption combined with the knowledge of the gold price and the amount of gold
 * in each coin allows us to calculate the dollar value of the treasure chest. 
 */
public class TreasureChest {
	/**
	 * Number of gold coins.
	 */
	private int gold;
	/**
	 * Amount of gold per coin measure in ounces.
	 */
	private static final BigDecimal GOLD_PER_COIN = new BigDecimal(0.9675);
	/**
	 * Price of an ounce gold.
	 */
	private static BigDecimal goldPrice = new BigDecimal(1426.90);
		
	/**
	 * Initializes a newly created TreasureChest with the specified number of gold coins.
	 * <p>
	 * @param goldCoins the number of gold coins in the newly created treasure chest.
	 * @throws IllegalArgumentException if <code>goldCoins</code> is negative.
	 */
	public TreasureChest(int goldCoins) {		
		if(goldCoins < 0) {
			throw new IllegalArgumentException("The number of gold coins in the treasure chest can't be negative.");
		}
		this.gold = goldCoins;
	}
	
	/**
	 * @return the amount of gold in this treasure chest
	 */
	public int getGold() {
		return gold;
	}
	
	/**
	 * Adds the specified number of coins to this treasure chest.
	 * <p>
	 * @param numberOfCoins the number of gold coins to be added to the treasure chest.
	 * @throws IllegalArgumentException if <code>numberOfCoins</code> is negative.
	 */
	public void addGold(int numberOfCoins) {
		if(numberOfCoins < 0) {
			throw new IllegalArgumentException("The number of gold coins to add can't be negative.");
		}
		this.gold += numberOfCoins;
	}
	
	/**
	 * Removes the specified number of coins to this treasure chest.
	 * <p>
	 * @param numberOfCoins the number of gold coins to be removed from the treasure chest.
	 * @return
	 * @throws IllegalArgumentException if <code>numberOfCoins</code> is negative.
	 */
	public int removeGold(int numberOfCoins) {
		if(numberOfCoins < 0 || numberOfCoins > this.gold) {
			throw new IllegalArgumentException("The number of gold coins to be removed can't exceed "
					+ "the total stored. It can't be negative either.");
		}
		this.gold -= numberOfCoins;
		return this.gold;		
	}
	
	/**
	 * @return the price of the ounce of gold
	 */
	public static BigDecimal getGoldPrice() {
		return goldPrice;
	}
	
	/**
	 * @param updates the price of gold with the <code>goldPrice</code> provided.
  	 * <p>
	 * @param goldPrice the price of the gold to be set
	 * @return
	 * @throws IllegalArgumentException if <code>goldPrice</code> is negative.
	 */
	public static void setGoldPrice(BigDecimal goldPrice) {
		if(goldPrice.compareTo(BigDecimal.ZERO) == -1) {
			throw new IllegalArgumentException("The price of gold can't be a negative value.");
		}
		TreasureChest.goldPrice = goldPrice;
	}
	
	/**
	 * Calculates the dollar value of all the gold coins in the treasure chest.
	 * <p>
	 * @return the dollar value of the treasure chest 
	 */	
	public BigDecimal valueInDollars() {
		return new BigDecimal(this.gold).multiply(TreasureChest.goldPrice).multiply(TreasureChest.GOLD_PER_COIN);
	}
	/**
	 * {@inheritDoc}
	 * <p>
	 * Returns a string of the following format:<br/>
	 * [  {gold} coins  ] <br/>
	 * where {gold} is replaced with the number of gold coins in this treasure chest.
	 */
	@Override
	public String toString() {
		return "[  " + this.gold + " coins ]";
	}
	
	
}
