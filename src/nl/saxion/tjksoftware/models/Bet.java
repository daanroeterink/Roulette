package nl.saxion.tjksoftware.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bet {
	
	public Bet() {
		
	}
	
	private Player player;
	private BetLocation betLocation;
	private int betAmmount;

	public enum BetLocation {zero,one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,
		thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,twenty_one,twenty_two,
		twenty_three,twenty_four,twenty_five,twenty_six,twenty_seven,twenty_eigth,twenty_nine,thirty,
		thirty_one,thirty_two,thirty_three,thirty_four,thirty_five,thirty_six,first_two_to_one,second_two_to_one,
		third_two_to_one,first_twelve,second_twelve,third_twelve,one_to_eighteen,even,red,black,odd,nineteen_to_thirty_six};
		
		public static final int[] EVEN = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36};
		
		public static final int[] ODD = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35};
		
		public static final int[] RED = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
		
		public static final int[] BlACK = {2,4,6,8,11,10,13,15,17,22,24,26,28,29,31,33,35};
		
		public static final int[] FIRST_TWELVE = {1,2,3,4,5,6,7,8,9,10,11,12};
		
		public static final int[] SECOND_TWELVE = {13,14,15,16,17,18,19,20,21,22,23,24};
		
		public static final int[] THIRD_TWELVE = {25,26,27,28,29,30,31,32,33,34,35,36};
		
		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

		public BetLocation getBetLocation() {
			return betLocation;
		}

		public void setBetLocation(BetLocation betLocation) {
			this.betLocation = betLocation;
		}

		public int getBetAmmount() {
			return betAmmount;
		}

		public void setBetAmmount(int betAmmount) {
			this.betAmmount = betAmmount;
		}

		public Bet(Player playerBet, int ammount, BetLocation betlocation) {
			player = playerBet;
			betAmmount = ammount;
			betLocation = betlocation;
		}
}