package unitTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreasureChestTest {
	private TreasureChest treasure;
	private TreasureChest emptyChest;
	private final BigDecimal delta = new BigDecimal(0.000001);

	@BeforeEach
	void setUp() throws Exception {
		treasure = new TreasureChest(10);		
		emptyChest = new TreasureChest(0);
		TreasureChest.setGoldPrice(new BigDecimal(1426.90));
	}

	@Test
	void TreasureChest_PositiveNumberOfCoins_AllCoinsStoredInTreasureChest() {
		TreasureChest Treasure7 = new TreasureChest(7);
		int expected = 7;
		int actual = Treasure7.getGold();
		assertEquals(expected, actual);
	}
	
	@Test
	void TreasureChest_ZeroCoins_NoCoinsStoredInTreasureChest() {
		TreasureChest Treasure0 = new TreasureChest(0);
		int expected = 0;
		int actual = Treasure0.getGold();
		assertEquals(expected, actual);
	}
	
	@Test
	void TreasureChest_NegativeNumberOfCoins_ThrowsIllegalArgumentException() {
		 assertThrows(IllegalArgumentException.class, () -> {new TreasureChest(-2);});
	}

	@Test
	void AddGold_PositiveNumbeOfCoins_IncreasesNumberOfCoinsInTreasureChest() {
		emptyChest.addGold(10);
		int expected = 10;
		int actual = emptyChest.getGold();
		assertEquals(expected, actual);
	}
	
	@Test
	void AddGold_NegativeNumbeOfCoins_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {emptyChest.addGold(-1);});		
	}
	
	@Test
	void AddGold_ZeroCoins_LeavesNumberOfCoinsInTreasureChestUnchanged() {
		emptyChest.addGold(0);
		int expected = 0;
		int actual = emptyChest.getGold();
		assertEquals(expected, actual);
	}

	@Test
	void RemoveGold_PositiveNumberOfCoins_DecreaseTotalNumberOfCoinsStored() {
		treasure.removeGold(5);
		int expected = 5;
		int actual = treasure.getGold();
		assertEquals(expected, actual);
	}
	
	@Test
	void RemoveGold_RemovingAllAvaibleCoins_EmptiesTreasureChest() {
		treasure.removeGold(10);
		int expected = 0;
		int actual = treasure.getGold();
		assertEquals(expected, actual);
	}
	
	@Test
	void RemoveGold_PositiveNumberOfCoinsThatExceedTotalStoredCoins_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {emptyChest.removeGold(1);});
	}
	
	@Test
	void RemoveGold_ZeroCoins_LeavesNumberOfCoinsInTreasureChestUnchanged() {
		treasure.removeGold(0);
		int expected = 10;
		int actual = treasure.getGold();
		assertEquals(expected, actual);
	}
	
	@Test
	void RemoveGold_NegativeNumberOfCoins_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {emptyChest.removeGold(-1);});
	}
	
	@Test
	void RemoveGold_PositiveNumberOfCoinsRemoved_CorrectTotalIsReturned() {		
		int expected = 5;
		int actual = treasure.removeGold(5);
		assertEquals(expected, actual);
	}
	
	@Test
	void RemoveGold_ZeroCoinsRemoved_TotalRemainsUnchanged() {		
		int expected = 10;
		int actual = treasure.removeGold(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void RemoveGold_AllAvaibleCoinsRemoved_Returns0() {		
		int expected = 0;
		int actual = treasure.removeGold(10);
		assertEquals(expected, actual);
	}

	@Test
	void SetGoldPrice_ZeroValueArgument_GoldPriceSetToZero() {
		TreasureChest.setGoldPrice(BigDecimal.ZERO);
		BigDecimal expected = BigDecimal.ZERO;
		BigDecimal actual = TreasureChest.getGoldPrice();
		assertEquals(expected, actual);
	}
	
	@Test
	void SetGoldPrice_PositiveValueArgument_GoldPriceSetToValue() {
		TreasureChest.setGoldPrice(new BigDecimal(1e19));
		BigDecimal expected = new BigDecimal(1e19);
		BigDecimal actual = TreasureChest.getGoldPrice();
		assertEquals(expected, actual);
	}
	
	@Test
	void SetGoldPrice_NegativeValueArgument_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {TreasureChest.setGoldPrice(new BigDecimal(-1));});
	}

	@Test
	void ValueInDollars_ZeroGoldCoins_Return0() {		
		BigDecimal expected = BigDecimal.ZERO;
		BigDecimal actual = emptyChest.valueInDollars();
		assertEquals(expected.compareTo(actual),0);
	}
	
	@Test
	void ValueInDollars_PositiveNumberOfGoldCoins_ReturnPositiveValue() {	
		BigDecimal expected = new BigDecimal(13805.2575);
		BigDecimal actual = treasure.valueInDollars();
		BigDecimal diff = expected.subtract(actual).abs();
		System.out.println(diff);
		assertTrue(diff.compareTo(delta) == -1);
	}

	@Test
	void toString_PositiveNumberOfCoins_ReturnNumberCoinsInBrackets() {
		String expected = "[  10 coins ]";
		String actual = treasure.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	void toString_NoCoins_Return0CoinsInBrackets() {
		String expected = "[  0 coins ]";
		String actual = emptyChest.toString();
		assertEquals(expected, actual);
	}
}
